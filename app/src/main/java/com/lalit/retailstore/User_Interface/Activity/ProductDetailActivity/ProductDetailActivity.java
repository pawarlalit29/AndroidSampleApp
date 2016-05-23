package com.lalit.retailstore.User_Interface.Activity.ProductDetailActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lalit.retailstore.AppConstant.SampleAppConstant;
import com.lalit.retailstore.Database.Product_Data;
import com.lalit.retailstore.Pojo.Product_Item;
import com.lalit.retailstore.R;
import com.lalit.retailstore.User_Interface.Activity.BaseActivity;
import com.lalit.retailstore.User_Interface.Activity.CheckOutActivity.CheckOutActivity;
import com.lalit.retailstore.User_Interface.Activity.ProductImageActivity.ProductImageviewActivity;
import com.lalit.retailstore.User_Interface.Event.ProductCountEvent;
import com.lalit.retailstore.User_Interface.Fragment.Frag_ProductView.Frag_ProductView;
import com.lalit.retailstore.Utils.Common_Utils;
import com.lalit.retailstore.Utils.FontManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.info_image)
    ImageView infoImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab_add)
    FloatingActionButton fabAdd;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.info_text_desc)
    TextView infoTextDesc;
    @Bind(R.id.info_text_title)
    TextView infoTextTitle;

    private Drawable fab_drawable;
    private String fab_text, str_image_url;
    private Product_Item product_item;
    private boolean flag_cart;
    private Context context;
    private Product_Data product_data;
    private TextView cartCount;
    private RelativeLayout cart_layout;
    private ImageButton cart_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        context = this;

        init(getIntent().getExtras());
    }

    private void init(Bundle bundle) {

        product_data = Product_Data.getInstance(ProductDetailActivity.this);
        EventBus.getDefault().register(this);
        product_item = (Product_Item) bundle.getParcelable(Frag_ProductView.PRODCUT_DETAIL);

        if (!product_item.equals(null)) {
            setProductDetail();
        }

    }


    private void setProductDetail() {

        int fab_color = getResources().getColor(R.color.white);


        product_item.setFlag_state(product_data.checkAvailabilty(product_item));

        flag_cart = product_item.isFlag_state();

        if (flag_cart) {
            fab_text = getResources().getString(R.string.fa_cart_item_add);
            fab_drawable = Common_Utils.getFontDrawable(fab_color, 40, fab_text, FontManager.CUSTOM_FONT);
        } else {
            fab_text = getResources().getString(R.string.fa_cart_item);
            fab_drawable = Common_Utils.getFontDrawable(fab_color, 40, fab_text, FontManager.CUSTOM_FONT);
        }

        fabAdd.setImageDrawable(fab_drawable);
        collapsingToolbar.setTitle(product_item.getStr_title());
        infoTextTitle.setText(product_item.getStr_title());
        infoTextDesc.setText(product_item.getStr_decs());


        str_image_url = product_item.getStr_imgurl();
        if (Common_Utils.getValidString(str_image_url)) {
            Common_Utils.getImageFromServer(context, infoImage, str_image_url, R.drawable.placeholder_image);
        }


    }


    @OnClick(R.id.fab_add)
    public void addORremove() {
        if (!product_item.isFlag_state()) {
            product_data.addProduct(product_item);
            product_item.setFlag_state(true);
            Common_Utils.showToast("Item added successfully");
        } else {
            product_data.removeProduct(product_item);
            product_item.setFlag_state(false);
            Common_Utils.showToast("Item removed successfully");
        }

        ProductCountEvent productCountEvent = new ProductCountEvent(product_data.productSize());
        EventBus.getDefault().post(productCountEvent);

        cart_count();

        setProductDetail();
    }

    @OnClick(R.id.info_image)
    public void onInfoImageView() {
        Intent intent = new Intent(ProductDetailActivity.this, ProductImageviewActivity.class);
        intent.putExtra(SampleAppConstant.P_IMAGE_URL, str_image_url);
        startActivity(intent);
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
        setProductDetail();
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

            // cartCount.setText(String.valueOf(mcartCount));
            cartCount.setText(String.valueOf(cart_size));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        product_data.releaseDBHelper();
    }

    private void switch_activity() {
        Intent intent = new Intent(ProductDetailActivity.this, CheckOutActivity.class);
        startActivity(intent);
    }

}
