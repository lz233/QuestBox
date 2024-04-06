package com.oculus.ocui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/* loaded from: classes.dex */
public class PaddedImageSpan extends ImageSpan {
    public final int mPaddingLeft;
    public final int mPaddingRight;

    public PaddedImageSpan(Drawable drawable) {
        this(drawable, 2, 0, 0);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        super.draw(canvas, charSequence, i, i2, f + this.mPaddingLeft, i3, i4, i5, paint);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return super.getSize(paint, charSequence, i, i2, fontMetricsInt) + this.mPaddingLeft + this.mPaddingRight;
    }

    public PaddedImageSpan(Drawable drawable, int i, int i2, int i3) {
        super(drawable, i);
        this.mPaddingLeft = i2;
        this.mPaddingRight = i3;
    }
}