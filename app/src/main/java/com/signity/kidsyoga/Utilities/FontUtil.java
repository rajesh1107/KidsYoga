package com.signity.kidsyoga.Utilities;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

public class FontUtil {

    public static final String FONT_SC_GUM = "fonts/sc_gum_kids.otf";

    private static final Map<String, Typeface> typefaces = new HashMap<String, Typeface>();

    public static Typeface getTypeface(Context ctx, String fontName) {
        Typeface typeface = typefaces.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(ctx.getAssets(), fontName);
            typefaces.put(fontName, typeface);
        }
        return typeface;
    }
}
