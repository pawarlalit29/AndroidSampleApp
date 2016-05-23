package com.lalit.retailstore.User_Interface.Fragment.Frag_ProductView;

import com.lalit.retailstore.Pojo.Product_Item;

import java.util.ArrayList;

/**
 * Created by kaede on 2015/10/13.
 */
public class GetDatasEvent {
	ArrayList<Product_Item> datas;

	public GetDatasEvent(ArrayList<Product_Item> datas) {
		this.datas = datas;
	}

	public ArrayList<Product_Item> getDatas() {
		return datas;
	}
}
