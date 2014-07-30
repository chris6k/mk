package com.jk.makemoney.component;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.jk.makemoney.R;

import java.lang.ref.WeakReference;

/**
 * @author chris.xue
 */
public class IrrListItem {
    private TextView itemDetail;
    private ImageView itemIcon;
    private View container;
    private WeakReference<Context> ctxRef;

    public IrrListItem(Context context) {
        container = extract(context);
    }

    private View extract(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.irrigation_item, null, false);
        itemDetail = (TextView) view.findViewById(R.id.irrItemName);
        itemIcon = (ImageView) view.findViewById(R.id.irrItemIcon);
        ctxRef = new WeakReference<Context>(context);
        return view;
    }

    public View getContainer() {
        return container;
    }

    public void setDetail(String detail) {
        itemDetail.setText(detail);
    }

    public void setItemIcon(String path) {
        itemIcon.setImageDrawable(BitmapDrawable.createFromPath(path));
    }

    public void setItemIcon(int id) {
        Context ctx = ctxRef.get();
        if (ctx != null) {
            itemIcon.setImageDrawable(ctx.getResources().getDrawable(id));
        }
    }

    public TextView getItemDetail() {
        return itemDetail;
    }

    public ImageView getItemIcon() {
        return itemIcon;
    }
}
