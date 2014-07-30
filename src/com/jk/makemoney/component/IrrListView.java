package com.jk.makemoney.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.jk.makemoney.beans.IrrItemDetail;
import com.jk.makemoney.beans.UserBaseDetail;

import java.util.*;

/**
 * @author chris.xue
 *         任务墙
 */
public class IrrListView extends ListView {
    //数据列表
    private Map<Date, List<UserBaseDetail>> dataMap;

    public IrrListView(Context context) {
        super(context);
    }

    public IrrListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IrrListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private static final class IrrListAdaptor extends BaseAdapter {
        private List<IrrItemDetail> items;

        private IrrListAdaptor(Map<Date, List<IrrItemDetail>> dateListMap) {
            if (dateListMap == null || dateListMap.isEmpty()) {
                items = new ArrayList<IrrItemDetail>(0);
            }
            items = new LinkedList<IrrItemDetail>();
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
            if (item instanceof IrrItemDetail) {
                IrrListItem irrListItem = new IrrListItem(viewGroup.getContext());
                ((IrrItemDetail) item).fill(irrListItem);
                v = irrListItem.getContainer();
            } else {
                v = new LinearLayout(viewGroup.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                v.setLayoutParams(params);
                ((LinearLayout) v).setOrientation(LinearLayout.HORIZONTAL);
            }
            return v;
        }

    }
}
