package com.oculus.ocui;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;

/* loaded from: classes.dex */
public class GlowEffect {
    public static final float THRESHOLD = 1.0E-5f;
    public int mGlowColor;
    public float mGlowFactor;
    public Paint mGlowPaint;
    public final Path mPath = new Path();
    public View mView;

    private void initPaint() {
        Paint paint = new Paint();
        this.mGlowPaint = paint;
        paint.setColor(this.mGlowColor);
        this.mGlowPaint.setAntiAlias(true);
        this.mGlowPaint.setStyle(Paint.Style.STROKE);
        this.mGlowPaint.setStrokeWidth(10.0f);
        this.mGlowPaint.setMaskFilter(new BlurMaskFilter(5.0f, BlurMaskFilter.Blur.NORMAL));
    }

    public void draw(Canvas canvas) {
        if (Math.abs(this.mGlowFactor) >= 1.0E-5f) {
            canvas.save();
            this.mPath.reset();
            View view = this.mView;
            float width = this.mView.getWidth();
            float height = this.mView.getHeight();
            Outline outline = new Outline();
            ViewOutlineProvider outlineProvider = this.mView.getOutlineProvider();
            if (outlineProvider != null) {
                outlineProvider.getOutline(this.mView, outline);
            }
            Rect clipBounds = canvas.getClipBounds();
            this.mPath.addRoundRect(new RectF(clipBounds), outline.getRadius(), outline.getRadius(), Path.Direction.CW);
            canvas.clipOutPath(this.mPath);
            canvas.drawRoundRect(new RectF(clipBounds), outline.getRadius(), outline.getRadius(), this.mGlowPaint);
            StringBuilder $outlined$0$e8f334e7690f88d5 = new StringBuilder("glow effect ondraw");
            $outlined$0$e8f334e7690f88d5.append(clipBounds.toString());
            $outlined$0$e8f334e7690f88d5.append(" : ");
            $outlined$0$e8f334e7690f88d5.append(width);
            $outlined$0$e8f334e7690f88d5.append(",");
            $outlined$0$e8f334e7690f88d5.append(height);
            Log.d("GlowEffect", $outlined$0$e8f334e7690f88d5.toString());
            canvas.restore();
        }
    }

    public boolean setGlowFactor(float f) {
        if (Math.abs(f - this.mGlowFactor) > 1.0E-5f) {
            this.mGlowFactor = f;
            return true;
        }
        return false;
    }

    public GlowEffect(View view, int i) {
        this.mView = view;
        this.mGlowColor = i;
        initPaint();
    }
}