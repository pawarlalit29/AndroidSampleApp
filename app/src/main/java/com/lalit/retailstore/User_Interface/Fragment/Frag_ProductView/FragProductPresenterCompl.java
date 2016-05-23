package com.lalit.retailstore.User_Interface.Fragment.Frag_ProductView;

import android.content.Context;

import com.lalit.retailstore.AppConstant.SampleAppConstant;
import com.lalit.retailstore.Pojo.Product_Item;
import com.lalit.retailstore.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by kaede on 2015/5/19.
 */
public class FragProductPresenterCompl implements FragProductPresenter {
    ArrayList<Product_Item> datas;
    FragProductView fragProductView;

    public FragProductPresenterCompl(FragProductView fragProductView) {
        this.fragProductView = fragProductView;
    }

    @Override
    public void loadDatas(Context context, int cat_id) {

        datas = new ArrayList<>();

        datas.addAll(getProductList(context, cat_id));
        GetDatasEvent getDatasEvent = new GetDatasEvent(datas);
        EventBus.getDefault().post(getDatasEvent);

    }

    private ArrayList<Product_Item> getProductList(Context context, int cat_id) {

        ArrayList<Product_Item> data = new ArrayList<>();

        InputStream inputStream = context.getResources().openRawResource(R.raw.product_data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(byteArrayOutputStream.toString());
            JSONArray jArray = jObject.getJSONArray(SampleAppConstant.PRODUCT_LIST);

            for (int i = 0; i < jArray.length(); i++) {

                Product_Item product_item = new Product_Item();

                int catid = Integer.parseInt(jArray.getJSONObject(i).getString(SampleAppConstant.P_CAT));

                if (cat_id == catid) {
                    product_item.setStr_pid(jArray.getJSONObject(i).getString(SampleAppConstant.P_ID));
                    product_item.setStr_title(jArray.getJSONObject(i).getString(SampleAppConstant.P_TITLE));
                    product_item.setStr_decs(jArray.getJSONObject(i).getString(SampleAppConstant.P_DESC));
                    product_item.setStr_category(jArray.getJSONObject(i).getString(SampleAppConstant.P_CAT));
                    product_item.setStr_imgurl(jArray.getJSONObject(i).getString(SampleAppConstant.P_IMAGE_URL));
                    product_item.setStr_price(jArray.getJSONObject(i).getString(SampleAppConstant.P_PRICE));
                    product_item.setFlag_state(false);
                    data.add(product_item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
