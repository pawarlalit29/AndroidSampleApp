package com.lalit.retailstore.User_Interface.Fragment.Frag_ProductView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lalit.retailstore.Adaptor.ProductList_Adapter;
import com.lalit.retailstore.Pojo.Product_Item;
import com.lalit.retailstore.R;
import com.lalit.retailstore.User_Interface.Activity.ProductDetailActivity.ProductDetailActivity;
import com.lalit.retailstore.User_Interface.Fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class Frag_ProductView extends BaseFragment implements FragProductView {

    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    private FragProductPresenter iEventBusPresenter;
    private ProductList_Adapter productList_adapter;
    private Context context;
    private ArrayList<Product_Item> productItemArrayList;

    public static String PRODCUT_DETAIL = "product_detail";
    public static String PRODCUT_CAT_ID = "product_cat_id";

    private int category_id;

    public static Fragment newInstance(int category_id) {
        Frag_ProductView frag_productView = new Frag_ProductView();
        Bundle b = new Bundle();
        b.putInt(PRODCUT_CAT_ID, category_id);
        frag_productView.setArguments(b);
        return frag_productView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.frag_productview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity();

        category_id = getArguments().getInt(PRODCUT_CAT_ID);

        //register
        EventBus.getDefault().register(this);

        recycleview.setLayoutManager(new LinearLayoutManager(context));

        iEventBusPresenter = new FragProductPresenterCompl(this);

        productItemArrayList = new ArrayList<>();
        productList_adapter = new ProductList_Adapter(context, productItemArrayList);
        recycleview.setAdapter(productList_adapter);
        recycleview.setNestedScrollingEnabled(true);
        iEventBusPresenter.loadDatas(context, category_id);


        productList_adapter.SetOnItemClickListener(new ProductList_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch_activity(productItemArrayList.get(position));
            }
        });

    }


    // EventBus Subscribe
    public void onEvent(GetDatasEvent getDatasEvent) {
        ArrayList<Product_Item> dashBoard_list = getDatasEvent.getDatas();
        Product_Item product_item = dashBoard_list.get(0);

        if (product_item.getStr_category().equalsIgnoreCase(String.valueOf(category_id))) {
            if (!productItemArrayList.isEmpty()) {
                productItemArrayList.clear();
            }

            productItemArrayList.addAll(dashBoard_list);
            productList_adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void switch_activity(Product_Item product_item) {
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra(PRODCUT_DETAIL, product_item);
        startActivity(intent);
    }


}
