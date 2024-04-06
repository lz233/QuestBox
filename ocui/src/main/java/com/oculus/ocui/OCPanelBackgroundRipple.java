package com.oculus.ocui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCPanelBackgroundRipple extends View {
    public static final int CURSOR_WIDTH = 720;
    public static final float LARGEST_CURSOR_SCALE = 1.0f;
    public static final int RADIAL_GRADIENT_MASK_BITMAP_COLOR = 1157627903;
    public static final float SMALLEST_CURSOR_SCALE = 0.2f;
    public static final float START_RIPPLE_DISTANCE = 0.2f;
    public float mCursorScale;
    public ObjectAnimator mFadeOutAnimator;
    public Paint mGradientPaint;
    public int mPosX;
    public int mPosY;

    public OCPanelBackgroundRipple(Context context) {
        this(context, null);
    }

    public float interpolateDistanceToCursorAlpha(float f) {
        if (f > 0.2f) {
            return 0.0f;
        }
        return (0.2f - f) * 5.0f;
    }

    public float interpolateDistanceToCursorScale(float f) {
        if (f > 0.2f) {
            return 0.0f;
        }
        float f2 = f / 0.2f;
        return ((1.0f - f2) * 0.2f) + (f2 * 1.0f);
    }

    public boolean handleHoverOrTouchEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        float axisValue = motionEvent.getAxisValue(24);
        if (axisValue > 0.0f) {
            f = interpolateDistanceToCursorAlpha(axisValue);
        } else {
            f = 0.99f;
        }
        setAlpha(f);
        if (axisValue <= 0.2f) {
            if (axisValue > 0.0f) {
                f2 = interpolateDistanceToCursorScale(axisValue);
            } else {
                f2 = 1.0f;
            }
            this.mCursorScale = f2;
            int action = motionEvent.getAction();
            if (action != 2 && action != 7) {
                if (action != 9) {
                    if (action == 10) {
                        this.mFadeOutAnimator.setFloatValues(f, 0.0f);
                        this.mFadeOutAnimator.start();
                    }
                } else {
                    this.mFadeOutAnimator.cancel();
                }
            }
            this.mPosX = (int) motionEvent.getX();
            this.mPosY = (int) motionEvent.getY();
            invalidate();
            return false;
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable background = getBackground();
        if (background != null) {
            Path path = new Path();
            path.addCircle(this.mPosX, this.mPosY, (this.mCursorScale * 720.0f) - 2.0f, Path.Direction.CW);
            canvas.clipPath(path);
            background.setBounds(0, 0, getWidth(), getHeight());
            background.draw(canvas);
            float f = this.mCursorScale;
            canvas.scale(f, f, this.mPosX, this.mPosY);
            canvas.translate(this.mPosX, this.mPosY);
            canvas.drawCircle(0.0f, 0.0f, 720.0f, this.mGradientPaint);
        }
    }

    public OCPanelBackgroundRipple(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public OCPanelBackgroundRipple(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("alpha", 0.99f, 0.0f));
        this.mFadeOutAnimator = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setTarget(this);
        this.mFadeOutAnimator.setDuration(2000);
        Paint paint = new Paint();
        this.mGradientPaint = paint;
        paint.setShader(new RadialGradient(0.0f, 0.0f, 720.0f, (int) RADIAL_GRADIENT_MASK_BITMAP_COLOR, 0, Shader.TileMode.CLAMP));
        this.mGradientPaint.setAntiAlias(true);
        this.mGradientPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        setBackgroundResource(R.drawable.ocpanel_background_bright);
        setAlpha(0.0f);
    }

    public OCPanelBackgroundRipple(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}