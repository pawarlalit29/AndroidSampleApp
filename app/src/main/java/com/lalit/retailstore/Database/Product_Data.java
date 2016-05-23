package com.lalit.retailstore.Database;

import android.app.Activity;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.lalit.retailstore.Pojo.Product_Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by atulsia on 11/1/16.
 */
public class Product_Data {

    private DatabaseHelper databaseHelper = null;
    private List<ProductDetails> productDetailsList = new ArrayList<>();

    private static ArrayList<Product_Item> productInventoryItems = new ArrayList<>();
    private static Product_Data product_data = null;
    private Activity activity;


    private Product_Data(Activity activity) {
      /*Private Constructor will prevent
       * the instantiation of this class directly*/

        // This is how, a reference of DAO object can be done
        try {
            this.activity = activity;
            releaseDBHelper();
            getCheckOutList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Product_Data getInstance(Activity activity) {
    /*This logic will ensure that no more than
     * one object can be created at a time */
        if (product_data == null) {
            product_data = new Product_Data(activity);
        }
        return product_data;
    }

    private void getCheckOutList() {
        try {
            Dao<ProductDetails, Integer> productDao = getHelper().getProductDao();

            if (productDetailsList != null && !productDetailsList.isEmpty()) {
                productDetailsList.clear();
            }

            productDetailsList = productDao.queryForAll();

            if (!productInventoryItems.isEmpty()) {
                productInventoryItems.clear();
            }

            for (ProductDetails productDetails : productDetailsList) {
                Product_Item product_item = new Product_Item();
                product_item.setStr_pid(productDetails.productID);
                product_item.setStr_title(productDetails.productTitle);
                product_item.setStr_decs(productDetails.productDisc);
                product_item.setStr_category(productDetails.productCat);
                product_item.setStr_imgurl(productDetails.productImageUrl);
                product_item.setStr_price(productDetails.productPrice);
                product_item.setFlag_state(true);

                productInventoryItems.add(product_item);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product_Item> getProductInventoryItems() {
        return productInventoryItems;
    }

    public void removeProduct(Product_Item productInventoryItem) {
        try {
            if (!productInventoryItems.isEmpty()) {
                String pid = productInventoryItem.getStr_pid();
                int pos = getProductPos(pid);
                productInventoryItems.remove(pos);
                removeFormCart(pid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getProductPos(String pid) {
        if (!productInventoryItems.isEmpty()) {
            for (int i = 0; i < productInventoryItems.size(); i++) {
                if (productInventoryItems.get(i).getStr_pid().equals(pid)) {
                    return i;
                }
            }
        }

        return 0;
    }

    private int getCheckOutProductPos(String pid) {
        if (!productDetailsList.isEmpty()) {
            for (int i = 0; i < productDetailsList.size(); i++) {
                if (productDetailsList.get(i).productID.equals(pid)) {
                    return i;
                }
            }
        }

        return 0;
    }


    public int addProduct(Product_Item productInventoryItem) {
        boolean flag = false;
        String pid = productInventoryItem.getStr_pid();

        if (!productInventoryItems.isEmpty()) {
            for (int i = 0; i < productInventoryItems.size(); i++) {
                if (productInventoryItems.get(i).getStr_pid().equals(pid)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                productInventoryItems.add(productInventoryItem);
                addToCart(productInventoryItem);
            }

        } else {
            productInventoryItems.add(productInventoryItem);
            addToCart(productInventoryItem);
        }

        return productInventoryItems.size();
    }

    public boolean checkAvailabilty(Product_Item productInventoryItem) {
        boolean flag = false;
        String pid = productInventoryItem.getStr_pid();

        if (!productInventoryItems.isEmpty()) {
            for (int i = 0; i < productInventoryItems.size(); i++) {
                if (productInventoryItems.get(i).getStr_pid().equals(pid)) {
                    flag = true;
                    break;
                }
            }

        } else {
            flag = false;
        }

        return flag;
    }


    public int productSize() {
        return productInventoryItems.size();
    }

    // This is how, DatabaseHelper can be initialized for future use
    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(activity, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    private void addToCart(Product_Item product_item) {
        final ProductDetails productDetails = new ProductDetails();

        productDetails.productID = product_item.getStr_pid();
        productDetails.productTitle = product_item.getStr_title();
        productDetails.productDisc = product_item.getStr_decs();
        productDetails.productCat = product_item.getStr_category();
        productDetails.productImageUrl = product_item.getStr_imgurl();
        productDetails.productPrice = product_item.getStr_price();
        productDetails.addedDate = new Date();

        try {
            // Now, need to interact with StudentDetails table/object, so initialize DAO for that
            final Dao<ProductDetails, Integer> productDao = getHelper().getProductDao();

            //This is the way to insert data into a database table
            productDao.create(productDetails);
            getCheckOutList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeFormCart(String pid) {
        // This is how, data from the database can be deleted
        try {
            final Dao<ProductDetails, Integer> productDao = getHelper().getProductDao();
            int removePos = getCheckOutProductPos(pid);
            ProductDetails productDetails = productDetailsList.get(removePos);
            productDao.delete(productDetails);
            getCheckOutList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void releaseDBHelper() {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

}
