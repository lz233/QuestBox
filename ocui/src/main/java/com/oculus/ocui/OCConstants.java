package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import java.util.Arrays;
import java.util.List;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCConstants {
    public static final Integer[] META_BRAND_DEVICE_HASHES = {Integer.valueOf((int) -1291874439)};

    public static boolean isMetaBrandDevice() {
        int hashCode;
        List asList = Arrays.asList(META_BRAND_DEVICE_HASHES);
        hashCode = Build.DEVICE.hashCode();
        return asList.contains(Integer.valueOf(hashCode));
    }

    public static int getAppTileBorderRadiusInPixels(Resources.Theme theme) {
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        theme.resolveAttribute(R.attr.ocAppTileBorderRadius, $outlined$0$ec6da1aeeb717785, true);
        if ($outlined$0$ec6da1aeeb717785.type == 5) {
            return TypedValue.complexToDimensionPixelSize($outlined$0$ec6da1aeeb717785.data, theme.getResources().getDisplayMetrics());
        }
        throw new IllegalStateException("Invalid value for R.attr.ocAppTileBorderRadius");
    }

    public static int getCardBorderRadiusInPixels(Resources.Theme theme) {
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        theme.resolveAttribute(R.attr.ocCardBorderRadius, $outlined$0$ec6da1aeeb717785, true);
        if ($outlined$0$ec6da1aeeb717785.type == 5) {
            return TypedValue.complexToDimensionPixelSize($outlined$0$ec6da1aeeb717785.data, theme.getResources().getDisplayMetrics());
        }
        throw new IllegalStateException("Invalid value for R.attr.ocCardBorderRadius");
    }

    public static int getDialogBorderRadiusPx(Resources.Theme theme) {
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        theme.resolveAttribute(R.attr.ocDialogBorderRadius, $outlined$0$ec6da1aeeb717785, true);
        if ($outlined$0$ec6da1aeeb717785.type == 5) {
            return TypedValue.complexToDimensionPixelSize($outlined$0$ec6da1aeeb717785.data, theme.getResources().getDisplayMetrics());
        }
        throw new IllegalStateException("Invalid value for R.attr.ocDialogBorderRadius");
    }

    public static float getOpacity(Resources resources, boolean z) {
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        int i = R.dimen.ocopacity_disabled_opacity;
        if (z) {
            i = R.dimen.ocopacity_enabled_opacity;
        }
        resources.getValue(i, $outlined$0$ec6da1aeeb717785, true);
        return $outlined$0$ec6da1aeeb717785.getFloat();
    }

    public static int getPanelBorderRadiusPx(Resources.Theme theme) {
        TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
        theme.resolveAttribute(R.attr.ocPanelBorderRadius, $outlined$0$ec6da1aeeb717785, true);
        if ($outlined$0$ec6da1aeeb717785.type == 5) {
            return TypedValue.complexToDimensionPixelSize($outlined$0$ec6da1aeeb717785.data, theme.getResources().getDisplayMetrics());
        }
        throw new IllegalStateException("Invalid value for R.attr.ocPanelBorderRadius");
    }

    public static int getAppTileBorderRadiusInPixels(Context context) {
        return getAppTileBorderRadiusInPixels(context.getTheme());
    }

    public static int getCardBorderRadiusInPixels(Context context) {
        return getAppTileBorderRadiusInPixels(context.getTheme());
    }

    public static int getDialogBorderRadiusPx(Context context) {
        return getDialogBorderRadiusPx(context.getTheme());
    }

    public static int getPanelBorderRadiusPx(Context context) {
        return getPanelBorderRadiusPx(context.getTheme());
    }
}