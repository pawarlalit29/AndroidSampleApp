package com.lalit.retailstore.User_Interface.Activity.ProductImageActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lalit.retailstore.AppConstant.SampleAppConstant;
import com.lalit.retailstore.R;
import com.lalit.retailstore.User_Interface.Activity.BaseActivity;
import com.lalit.retailstore.User_Interface.CustomWidget.TouchImageView;
import com.lalit.retailstore.Utils.Common_Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lalit on 2/6/15.
 */
public class ProductImageviewActivity extends BaseActivity {


    Context context;
    @Bind(R.id.img_detail)
    TouchImageView imgDetail;
    @Bind(R.id.btnClose)
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productimageview);
        ButterKnife.bind(this);

        context = this;

        init(getIntent().getExtras());

        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                redirectToHome();
            }
        });


    }

    private void init(Bundle bundle) {
        try {

            String detail_url = bundle.getString(SampleAppConstant.P_IMAGE_URL);

            if (Common_Utils.getValidString(detail_url)) {
                Common_Utils.getImageFromServer(context, imgDetail,detail_url, R.drawable.placeholder_image);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        redirectToHome();
    }

    public void redirectToHome() {
        this.finish();
    }


}
