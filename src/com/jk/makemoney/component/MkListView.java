package com.jk.makemoney.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.UserBaseDetail;
import com.jk.makemoney.com.jk.makemoney.utils.DateTimeUtils;

import java.util.*;

/**
 * @author chris.xue
 *         <p/>
 *         列表组件
 */
public class MkListView extends ListView {
    //数据列表
    private Map<Date, List<UserBaseDetail>> dataMap;


    public void append(List<UserBaseDetail> newData) {
        if (dataMap == null) {
            dataMap = UserBaseDetail.mix(newData);
        } else {
            dataMap.putAll(UserBaseDetail.mix(newData));
        }
        setAdapter(new MkListAdaptor(dataMap));
    }


    public MkListView(Context context) {
        super(context);
    }

    public MkListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MkListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private static final class MkListAdaptor extends BaseAdapter {
        private List<Object> items;

        private MkListAdaptor(Map<Date, List<UserBaseDetail>> dateListMap) {
            if (dateListMap == null || dateListMap.isEmpty()) {
                items = new ArrayList<Object>(0);
            }
            items = new LinkedList<Object>();
            for (Map.Entry<Date, List<UserBaseDetail>> entry : dateListMap.entrySet()) {
                items.add(DateTimeUtils.extractDayOfYear(entry.getKey()));
                for (UserBaseDetail ub : entry.getValue()) {
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
            } else if (item instanceof UserBaseDetail) {
                MkListItem mkListItem = new MkListItem(viewGroup.getContext());
                ((UserBaseDetail) item).fillItem(mkListItem);
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
}
