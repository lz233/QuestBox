package com.oculus.ocui;

import android.content.Context;
import android.util.TypedValue;

import com.oculus.vrshell.panels.AndroidPanelLayer;

import fish.with.ocui.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class PanelGradientConfig {
    public static final Companion Companion = new Companion();
    public final float[] backgroundColor;
    public final float cornerRadius;
    public final AndroidPanelLayer.BorderRadiusType cornerRadiusType;
    public final boolean isControlBar;
    public final float yOffset;

    /* loaded from: classes.dex */
    public static final class Companion {
        public final PanelGradientConfig fromContext(Context context, boolean z, boolean z2) {
            AndroidPanelLayer.BorderRadiusType borderRadiusType;
            float[] backgroundColor = getBackgroundColor(context, R.attr.ocPanelBackground);
            float f = 0.0f;
            if (z) {
                try {
                    f = OCConstants.getPanelBorderRadiusPx(context);
                } catch (IllegalStateException unused) {
                }
            }
            if (z2) {
                borderRadiusType = AndroidPanelLayer.BorderRadiusType.Top;
            } else {
                borderRadiusType = AndroidPanelLayer.BorderRadiusType.All;
            }
            return new PanelGradientConfig(backgroundColor, f, 0.0f, borderRadiusType, z2);
        }

        public static float[] getColorArgb(int i) {
            return new float[]{((i >>> 16) & 255) / 255.0f, ((i >>> 8) & 255) / 255.0f, (i & 255) / 255.0f, ((i >>> 24) & 255) / 255.0f};
        }

        public static /* synthetic */ PanelGradientConfig fromContext$default(Companion companion, Context context, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            if ((i & 4) != 0) {
                z2 = false;
            }
            return companion.fromContext(context, z, z2);
        }

        private final float[] getBackgroundColor(Context context, int i) {
            TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
            context.getTheme().resolveAttribute(i, $outlined$0$ec6da1aeeb717785, true);
            return getColorArgb($outlined$0$ec6da1aeeb717785.data);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public Companion() {
        }
    }

    public /* synthetic */ PanelGradientConfig(float[] fArr, float f, float f2, AndroidPanelLayer.BorderRadiusType borderRadiusType, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, f, (i & 4) != 0 ? 0.0f : f2, (i & 8) != 0 ? AndroidPanelLayer.BorderRadiusType.All : borderRadiusType, (i & 16) != 0 ? false : z);
    }

    public final float[] getBackgroundColor() {
        return this.backgroundColor;
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final AndroidPanelLayer.BorderRadiusType getCornerRadiusType() {
        return this.cornerRadiusType;
    }

    public final float getYOffset() {
        return this.yOffset;
    }

    public final boolean isControlBar() {
        return this.isControlBar;
    }

    public PanelGradientConfig(float[] fArr, float f, float f2, AndroidPanelLayer.BorderRadiusType borderRadiusType, boolean z) {
        this.backgroundColor = fArr;
        this.cornerRadius = f;
        this.yOffset = f2;
        this.cornerRadiusType = borderRadiusType;
        this.isControlBar = z;
        if (f2 >= 0.0f) {
            return;
        }
        throw new UnsupportedOperationException("Invalid yOffset value: " + f2);
    }
}