package com.oculus.ocui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import fish.with.ocui.R;

/* loaded from: classes.dex */
@SuppressLint("AppCompatCustomView")
public class OCTextView extends TextView {
    public void setLineHeightCompat(int i, boolean z) {
        float f;
        if (z) {
            f = OCTextUtils.getInstance().getFontScaledSize(getResources(), i);
        } else {
            f = i;
        }
        super.setLineHeight((int) f);
    }

    public void setMarqueeScrollOnHover() {
        setOnHoverListener(new View.OnHoverListener() { // from class: com.oculus.ocui.OCTextView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCTextView.this.m3248lambda$setMarqueeScrollOnHover$0$comoculusocuiOCTextView(view, motionEvent);
            }
        });
    }

    public OCTextView(Context context) {
        super(context);
        init(null, 0, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0041, code lost:
        if (r6.hasValue(3) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void init(AttributeSet attributeSet, int i, int i2) {
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.OCTextView, i, i2);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCTextView_android_textSize, resources.getDimensionPixelSize(R.dimen.abc_text_size_menu_header_material));
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.OCTextView_allowFontScaling, true);
        float fontScaledSize = OCTextUtils.getInstance().getFontScaledSize(resources, dimensionPixelSize);
        if (z) {
            setTextSize(0, fontScaledSize);
        }
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.octypography_body1_line_height);
        int i3 = 1;
        if (!obtainStyledAttributes.hasValue(R.styleable.OCTextView_android_lineHeight)) {
            i3 = 3;
        }
        dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(i3, dimensionPixelSize2);
        setLineHeightCompat(dimensionPixelSize2, z);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: lambda$setMarqueeScrollOnHover$0$com-oculus-ocui-OCTextView  reason: not valid java name */
    public /* synthetic */ boolean m3248lambda$setMarqueeScrollOnHover$0$comoculusocuiOCTextView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            setEllipsize(TextUtils.TruncateAt.MARQUEE);
            view.setSelected(true);
            return true;
        } else if (motionEvent.getAction() != 10) {
            return false;
        } else {
            setEllipsize(TextUtils.TruncateAt.END);
            view.setSelected(false);
            return true;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    public int getDefaultFontSizeRes() {
        return R.dimen.abc_text_size_menu_header_material;
    }

    public int getDefaultLineHeightRes() {
        return R.dimen.octypography_body1_line_height;
    }

    public OCTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    public OCTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        init(attributeSet, i, i2);
    }

    public OCTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }
}