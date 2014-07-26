package com.jk.makemoney.activitys;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.jk.makemoney.R;

public class MainActivity extends Activity implements DrawerLayout.DrawerListener {
    private DrawerLayout mainLayout;
    private FrameLayout mainBody;
    private ListView mainMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainLayout = (DrawerLayout) findViewById(R.id.mainLayout);
        mainLayout.setDrawerListener(this);
        mainBody = (FrameLayout) findViewById(R.id.mainBody);
        mainMenu = (ListView) findViewById(R.id.mainMenu);
        View frontItem = LayoutInflater.from(this).inflate(R.layout.left_menu_top, null, false);
        mainMenu.addHeaderView(frontItem);
        mainMenu.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View menuItem = LayoutInflater.from(getBaseContext()).inflate(R.layout.menu_item, null, false);
                TextView menuText = (TextView) menuItem.findViewById(R.id.leftMenuText);
                switch (i) {
                    case 0:
                        menuText.setText("我的首页");
                        menuText.setClickable(true);
                        menuText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DashboardFragment myFragment = new DashboardFragment();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.mainBody, myFragment).commit();
                                mainLayout.closeDrawer(mainMenu);
                            }
                        });
                        break;
                    case 1:
                        menuText.setText("收支明细");
                        menuText.setClickable(true);
                        menuText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CommissionFragment myFragment = new CommissionFragment();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.mainBody, myFragment).commit();
                                mainLayout.closeDrawer(mainMenu);
                            }
                        });
                        break;
                    case 2:
                        menuText.setText("赚钱功能");
                        break;
                    default:
                        break;
                }
                return menuItem;
            }
        });

        DashboardFragment myFragment = new DashboardFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainBody, myFragment).commit();
    }

    @Override
    public void onDrawerSlide(View view, float v) {

    }

    @Override
    public void onDrawerOpened(View view) {

    }

    @Override
    public void onDrawerClosed(View view) {

    }

    @Override
    public void onDrawerStateChanged(int i) {

    }
}
