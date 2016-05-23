package com.lalit.retailstore.Adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lalit.retailstore.Pojo.Product_Item;
import com.lalit.retailstore.R;
import com.lalit.retailstore.Utils.Common_Utils;
import java.util.ArrayList;

/**
 * Created by lalit on 26/4/15.
 */
public class ProductList_Adapter extends  RecyclerView.Adapter<ProductList_Adapter.FeedListRowHolder>  {

    private ArrayList<Product_Item> ItemList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public ProductList_Adapter(Context context, ArrayList<Product_Item> feedItemList) {
        this.ItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_card_event, viewGroup, false);
        FeedListRowHolder mh = new FeedListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {
        Product_Item Item = ItemList.get(i);

        feedListRowHolder.txt_title.setText(Item.getStr_title());
        //feedListRowHolder.txt_desc.setText(Item.getStr_decs());
        feedListRowHolder.txt_price.setText(Item.getStr_price());

        String img_url = Item.getStr_imgurl();
        if(Common_Utils.getValidString(img_url)){
            Common_Utils.getImageFromServer(mContext,feedListRowHolder.img_banner,img_url,R.drawable.placeholder_image);
        }


    }

    @Override
    public int getItemCount() {
        return (null != ItemList ? ItemList.size() : 0);
    }

    public Product_Item getItem(int pos){
        return ItemList.get(pos);
    }

    class FeedListRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView txt_title,txt_price;
        protected ImageView img_banner;

        public FeedListRowHolder(View view)  {
            super(view);
            this.txt_title = (TextView) view.findViewById(R.id.info_text_title);
            this.img_banner = (ImageView) view.findViewById(R.id.info_image);
            this.txt_price = (TextView) view.findViewById(R.id.info_text_price);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getPosition());
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(
            final OnItemClickListener mitemClickListener) {
        this.mItemClickListener = mitemClickListener;
    }

}
