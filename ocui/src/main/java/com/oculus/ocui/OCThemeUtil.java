package com.oculus.ocui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/* loaded from: classes.dex */
public class OCThemeUtil {
    public static int getColorInt(Context context, int i, int i2) {
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        context.getTheme().resolveAttribute(i, $outlined$0$ec6da1aeeb717785, true);
        int i3 = $outlined$0$ec6da1aeeb717785.type;
        if (i3 >= 16 && i3 <= 31) {
            return $outlined$0$ec6da1aeeb717785.data;
        }
        return i2;
    }

    public static int getDimensionPixelSize(Context context, int i, int i2) {
        DisplayMetrics displayMetrics;
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        context.getTheme().resolveAttribute(i, $outlined$0$ec6da1aeeb717785, true);
        if ($outlined$0$ec6da1aeeb717785.type == 5) {
            int i3 = $outlined$0$ec6da1aeeb717785.data;
            displayMetrics = context.getResources().getDisplayMetrics();
            return TypedValue.complexToDimensionPixelSize(i3, displayMetrics);
        }
        return i2;
    }
}