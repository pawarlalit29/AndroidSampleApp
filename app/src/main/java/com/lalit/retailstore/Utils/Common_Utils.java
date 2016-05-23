package com.lalit.retailstore.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.UnderlineSpan;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lalit.retailstore.R;
import com.lalit.retailstore.RetailStoreApp;
import com.lalit.retailstore.User_Interface.CustomWidget.FontDrawable.FontDrawable;

/**
 * Created by lalit on 31/7/15.
 */
public class Common_Utils {

    public static Toast toast;

    public static void getImageFromServer(Context context, ImageView imageview, String url, int drawble) {
        try {
            if (InternetConnectionDetect.isConnectingToInternet(context)) {
                Glide.with(context)
                        .load(url)
                        .placeholder(drawble)
                        .error(drawble)
                        .into(imageview);
            } else {
                showToast("No Internet Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void showToast(String data) {
        try {
            if (toast == null) {
                toast = Toast.makeText(RetailStoreApp.getAppContext(), data, Toast.LENGTH_LONG);
            }

            if (!toast.getView().isShown()) {
                toast.setText(data);
                toast.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean getValidString(String string) {

        if (string != null && string.length() != 0 && !string.equalsIgnoreCase("")) {
            return true;
        } else {
            return false;
        }
    }

    public static SpannableString getUnderline(Context context, String str_temp) {

        TextPaint tp = new TextPaint();

        tp.linkColor = context.getResources().getColor(R.color.myAccentColor);
        UnderlineSpan us = new UnderlineSpan();
        us.updateDrawState(tp);

        SpannableString content = new SpannableString(str_temp);
        content.setSpan(us, 0, str_temp.length(), 0);

        return content;
    }

    public static Drawable getFontDrawable(int color, int size, String code, String font) {
        FontDrawable fontDrawable = new FontDrawable.Builder(RetailStoreApp.getAppContext(), code, font)
                .setSizeDp(size)
                .setColor(color)
                .setPaddingDp(2)
                .build();

        return fontDrawable;
    }


}
