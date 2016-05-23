package com.lalit.retailstore.User_Interface.Activity.MainActivty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lalit.retailstore.Adaptor.ViewPagerAdapter;
import com.lalit.retailstore.Database.Product_Data;
import com.lalit.retailstore.R;
import com.lalit.retailstore.User_Interface.Activity.BaseActivity;
import com.lalit.retailstore.User_Interface.Activity.CheckOutActivity.CheckOutActivity;
import com.lalit.retailstore.User_Interface.Event.ProductCountEvent;
import com.lalit.retailstore.Utils.Common_Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import io.karim.MaterialTabs;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tablayout)
    MaterialTabs tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private Context context;
    private TextView cartCount;
    private RelativeLayout cart_layout;
    private ImageButton cart_btn;
    private Product_Data product_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        context = this;

        init();
    }

    private void init() {

        //register
        EventBus.getDefault().register(this);


        product_data = Product_Data.getInstance(MainActivity.this);
        tablayout.setTextColorUnselected(
                getResources().getColor(R.color.grey_400));

        if (viewpager != null) {
            //setupViewPager(viewpager);
            getViewPager();
        }

    }

    private void onCartClick() {
        if (product_data.productSize() > 0) {
            switch_activity();
        } else {
            Common_Utils.showToast("Your Cart is empty!");
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        onCartClick();
    }

    private void getViewPager() {

        // Assigning ViewPager View and setting the adapter
        viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


        // Setting the ViewPager For the SlidingTabsLayout
        tablayout.setViewPager(viewpager);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewpager.setPageMargin(pageMargin);
        viewpager.setCurrentItem(0);
    }


    @SuppressLint("NewApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);

        menu.findItem(R.id.action_cart).setVisible(true);
        View count = menu.findItem(R.id.action_cart).getActionView();

        cartCount = (TextView) count.findViewById(R.id.tvcart_count);
        cart_btn = (ImageButton) count.findViewById(R.id.cartimg_btn);
        cart_layout = (RelativeLayout) count.findViewById(R.id.cartlayout);

        cartCount.setBackground(getResources().getDrawable(
                R.drawable.shape_notification));

        cart_btn.setOnClickListener(this);
        cart_layout.setOnClickListener(this);
        cartCount.setOnClickListener(this);


        cart_count();

        return true;
    }

    public void onEvent(ProductCountEvent productCountEvent) {
        int count = productCountEvent.getCartCount();
        cart_count();
    }

    private void cart_count() {
        int cart_size = 0;
        cart_size = product_data.productSize();

        if (cartCount != null) {
            if (cart_size > 0) {
                cartCount.setVisibility(View.VISIBLE);
            } else {
                cartCount.setVisibility(View.GONE);
            }
            cartCount.setText(String.valueOf(cart_size));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        product_data.releaseDBHelper();
    }

    private void switch_activity() {
        Intent intent = new Intent(MainActivity.this, CheckOutActivity.class);
        startActivity(intent);
    }

}
