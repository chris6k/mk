package com.jk.makemoney.component;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.UserBilling;
import com.jk.makemoney.com.jk.makemoney.utils.DateTimeUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chris.xue
 *         <p/>
 *         列表组件
 */
public class MkListView extends ListView {
    private transient Handler handler;
    //数据列表
    private Map<Date, List<UserBilling>> dataMap;
    private View loadMore;
    private List<ListEventNotify> listEventNotifies;
    private AtomicBoolean updateLock;

    public MkListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.handler = new android.os.Handler(Looper.getMainLooper());
        this.listEventNotifies = new ArrayList<ListEventNotify>(5);
        updateLock = new AtomicBoolean(false);
        this.loadMore = LayoutInflater.from(context).inflate(R.layout.comm_footer, null, false);
        loadMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                fireUpdateNotify(getCount(), MkListView.this);
            }
        });
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断滚动到底部
                    if (getLastVisiblePosition() == (getCount() - 1)) {
                        loadMore.setVisibility(GONE);
                        fireUpdateNotify(getCount(), MkListView.this);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public MkListView(Context context) {
        super(context);
    }

    public MkListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addUpdateNotify(ListEventNotify notify) {
        listEventNotifies.add(notify);
    }

    public void append(List<UserBilling> newData) {
        if (newData == null || newData.size() == 0) {
            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
        }
        if (dataMap == null) {
            dataMap = UserBilling.mix(newData);
        } else {
            dataMap.putAll(UserBilling.mix(newData));
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (getFooterViewsCount() == 0) {
                    addFooterView(loadMore);
                }
                setAdapter(new MkListAdaptor(dataMap));
            }
        });
        //更新后重置更新
        updateLock.set(false);
        loadMore.setVisibility(VISIBLE);
    }

    private void fireUpdateNotify(int count, MkListView listView) {
        for (ListEventNotify notify : listEventNotifies) {
            notify.update(count, listView);
        }
    }


    private static final class MkListAdaptor extends BaseAdapter {
        private List<Object> items;

        private MkListAdaptor(Map<Date, List<UserBilling>> dateListMap) {
            if (dateListMap == null || dateListMap.isEmpty()) {
                items = new ArrayList<Object>(0);
            }
            items = new LinkedList<Object>();
            for (Map.Entry<Date, List<UserBilling>> entry : dateListMap.entrySet()) {
                items.add(DateTimeUtils.extractDayOfYear(entry.getKey()));
                for (UserBilling ub : entry.getValue()) {
                    items.add(ub);
                }
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Object item = getItem(i);
            View v;
            if (item instanceof String) {
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comm_list_day, null, false);
                View dateText = v.findViewById(R.id.commDayText);
                if (dateText instanceof TextView) {
                    ((TextView) dateText).setText((String) item);
                }
            } else if (item instanceof UserBilling) {
                MkListItem mkListItem = new MkListItem(viewGroup.getContext());
                ((UserBilling) item).fillItem(mkListItem);
                v = mkListItem.getContainer();
            } else {
                v = new LinearLayout(viewGroup.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                v.setLayoutParams(params);
                ((LinearLayout) v).setOrientation(LinearLayout.HORIZONTAL);
            }
            return v;
        }

        @Override
        public boolean isEnabled(int position) {
            return !(items.get(position) instanceof String);
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        handler = null;
        super.onDetachedFromWindow();
    }
}
