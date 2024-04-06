package com.oculus.ocui;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/* loaded from: classes.dex */
public class ThemedAttribute {
    public final TypedValue mAttrValue;

    public void applyTheme(Resources.Theme theme) {
        TypedValue typedValue = this.mAttrValue;
        if (typedValue.type == 2) {
            theme.resolveAttribute(typedValue.data, typedValue, true);
        }
    }

    public boolean getBoolean(boolean z) {
        TypedValue typedValue = this.mAttrValue;
        int i = typedValue.type;
        if (i >= 16 && i <= 31) {
            return typedValue.data != 0;
        }
        return z;
    }

    public int getColor(int i) {
        TypedValue typedValue = this.mAttrValue;
        int i2 = typedValue.type;
        if (i2 >= 16 && i2 <= 31) {
            return typedValue.data;
        }
        return i;
    }

    public float getDimensionPixelSize(float f, DisplayMetrics displayMetrics) {
        TypedValue typedValue = this.mAttrValue;
        if (typedValue.type == 5) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, displayMetrics);
        }
        return f;
    }

    public ThemedAttribute(TypedValue typedValue) {
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        this.mAttrValue = $outlined$0$ec6da1aeeb717785;
        if (typedValue != null) {
            $outlined$0$ec6da1aeeb717785.setTo(typedValue);
        }
    }
}