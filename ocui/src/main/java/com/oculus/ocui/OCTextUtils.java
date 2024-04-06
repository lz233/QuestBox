package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import java.util.List;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public final class OCTextUtils {
    public static OCTextUtils sInstance;
    public int mFontSizeSetting;

    /* loaded from: classes.dex */
    public interface FontSizes {
        public static final int EXTRA_LARGE = 2;
        public static final int LARGE = 1;
        public static final int LARGEST = 3;
        public static final int MEDIUM = 0;
        public static final int SMALL = -1;
    }

    private float getPixels(Resources resources, int i) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, i, resources.getDisplayMetrics());
    }

    public final SpannableStringBuilder appendIconsWithText(List<Pair<Integer, String>> list, Context context) {
        return appendIconsWithText(list, context, OCThemeUtil.getColorInt(context, R.attr.ocSecondaryIcon, 0), context.getResources().getDimensionPixelSize(R.dimen.active_call_bar_active_call_button_padding), context.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_elevation_material), context.getResources().getDimensionPixelSize(R.dimen.abc_button_padding_horizontal_material));
    }

    public final float getFontScaledSizeSP(Resources resources, int i) {
        return getFontScaledSize(resources, 2, i);
    }

    public static synchronized OCTextUtils getInstance() {
        OCTextUtils oCTextUtils;
        synchronized (OCTextUtils.class) {
            oCTextUtils = sInstance;
            if (oCTextUtils == null) {
                oCTextUtils = new OCTextUtils();
                sInstance = oCTextUtils;
            }
        }
        return oCTextUtils;
    }

    public final SpannableStringBuilder createBoldTextSpannable(String str) {
        int indexOf = str.indexOf("<b>");
        String replace = str.replace("<b>", "");
        int indexOf2 = replace.indexOf("</b>");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(replace.replace("</b>", ""));
        spannableStringBuilder.setSpan(new StyleSpan(1), indexOf, indexOf2, 33);
        return spannableStringBuilder;
    }

    public final float getFontScaledSize(Resources resources, float f) {
        float f2;
        int i = this.mFontSizeSetting;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        i = 6;
                    }
                } else {
                    i = 4;
                }
            } else {
                f2 = getPixels(resources, 2);
                return f + f2;
            }
        } else {
            i = -2;
        }
        f2 = getPixels(resources, i);
        return f + f2;
    }

    public final int getFontSizeSetting() {
        return this.mFontSizeSetting;
    }

    public OCTextUtils() {
        this.mFontSizeSetting = 0;
    }

    public final SpannableStringBuilder appendIconsWithText(List<Pair<Integer, String>> list, Context context, int i, int i2, int i3, int i4) {
        int intValue;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        boolean z = true;
        for (Pair<Integer, String> pair : list) {
            intValue = ((Number) pair.first).intValue();
            Drawable drawable = context.getDrawable(intValue);
            if (drawable != null) {
                int i5 = 0;
                drawable.setBounds(0, 0, i2, i2);
                drawable.setTint(i);
                if (z) {
                    z = false;
                } else {
                    i5 = i4;
                }
                PaddedImageSpan paddedImageSpan = new PaddedImageSpan(drawable, 2, i5, i3);
                spannableStringBuilder.append((CharSequence) " ");
                spannableStringBuilder.setSpan(paddedImageSpan, spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 33);
            }
            spannableStringBuilder.append((CharSequence) pair.second);
        }
        return spannableStringBuilder;
    }

    public final float getFontScaledSize(Resources resources, int i, int i2) {
        return getFontScaledSize(resources, (int) TypedValue.applyDimension(i, i2, resources.getDisplayMetrics()));
    }
}