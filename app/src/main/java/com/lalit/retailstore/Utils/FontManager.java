package com.lalit.retailstore.Utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atulsia on 4/1/16.
 */
public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static final String CUSTOM_FONT = ROOT + "custom_webfont.ttf";
    public static final String Material_FONT = ROOT + " MaterialIcons-Regular.ttf";

    private Map<String, Typeface> fontCache = new HashMap<String, Typeface>();
    private static FontManager instance = null;
    private Context mContext;

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }


    private FontManager(Context mContext2) {
        mContext = mContext2;
    }

    public synchronized static FontManager getInstance(Context mContext) {

        if (instance == null) {
            instance = new FontManager(mContext);
        }
        return instance;
    }

    public Typeface loadFont(String font) {

        if (false == fontCache.containsKey(font)) {
            fontCache.put(font,
                    Typeface.createFromAsset(mContext.getAssets(), font));
        }
        return fontCache.get(font);
    }

}
