package com.oculus.ocui;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import com.oculus.vrshell.panels.AndroidPanelLayer;

import java.io.IOException;
import java.util.Arrays;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCPanelBackgroundDrawable extends Drawable {
    public static final int BRIGHTEN_AMOUNT = 160;
    public static final String TAG = "OCPanelBackgroundDrawable";
    public PanelBackgroundState mDrawableState;
    public boolean mMutated;

    /* loaded from: classes.dex */
    public class PanelBackgroundState extends Drawable.ConstantState {
        public final Paint mBackgroundPaint;
        public float[] mBorderRadii;
        public float mBorderRadius;
        public ThemedAttribute mBorderRadiusAttr;
        public AndroidPanelLayer.BorderRadiusType mBorderRadiusType;
        public int mChangingConfigurations;
        public boolean mDisableRoundedCorners;
        public boolean mEnableDither;
        public final Paint mGradientFillPaintBottom;
        public final Paint mGradientFillPaintBottomBezel;
        public final Paint mGradientFillPaintControlBarLeft;
        public final Paint mGradientFillPaintControlBarRight;
        public final Paint mGradientFillPaintLeft;
        public final Paint mGradientFillPaintRight;
        public boolean mHasControlBarAttached;
        public boolean mIsBright;
        public boolean mIsControlBar;
        public final Path mPath;
        public final RectF mRect;
        public ThemedAttribute mSolidBgColorAttr;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return true;
        }

        private void decreasePosterization() {
            this.mGradientFillPaintBottom.setDither(true);
            this.mGradientFillPaintLeft.setDither(true);
            this.mGradientFillPaintRight.setDither(true);
            this.mGradientFillPaintBottomBezel.setDither(true);
            this.mGradientFillPaintControlBarLeft.setDither(true);
            this.mGradientFillPaintControlBarRight.setDither(true);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new OCPanelBackgroundDrawable(new PanelBackgroundState(this));
        }

        public PanelBackgroundState(PanelBackgroundState panelBackgroundState) {
            this.mEnableDither = true;
            this.mDisableRoundedCorners = false;
            this.mBorderRadiusType = AndroidPanelLayer.BorderRadiusType.All;
            this.mChangingConfigurations = panelBackgroundState.mChangingConfigurations;
            this.mHasControlBarAttached = panelBackgroundState.mHasControlBarAttached;
            this.mIsControlBar = panelBackgroundState.mIsControlBar;
            this.mBorderRadius = panelBackgroundState.mBorderRadius;
            this.mDisableRoundedCorners = panelBackgroundState.mDisableRoundedCorners;
            this.mSolidBgColorAttr = panelBackgroundState.mSolidBgColorAttr;
            this.mBorderRadiusAttr = panelBackgroundState.mBorderRadiusAttr;
            this.mBorderRadii = panelBackgroundState.mBorderRadii;
            this.mBorderRadiusType = panelBackgroundState.mBorderRadiusType;
            this.mRect = new RectF(panelBackgroundState.mRect);
            this.mPath = new Path(panelBackgroundState.mPath);
            this.mBackgroundPaint = new Paint(panelBackgroundState.mBackgroundPaint);
            this.mGradientFillPaintBottom = new Paint(panelBackgroundState.mGradientFillPaintBottom);
            this.mGradientFillPaintLeft = new Paint(panelBackgroundState.mGradientFillPaintLeft);
            this.mGradientFillPaintRight = new Paint(panelBackgroundState.mGradientFillPaintRight);
            this.mGradientFillPaintBottomBezel = new Paint(panelBackgroundState.mGradientFillPaintBottomBezel);
            this.mGradientFillPaintControlBarLeft = new Paint(panelBackgroundState.mGradientFillPaintControlBarLeft);
            this.mGradientFillPaintControlBarRight = new Paint(panelBackgroundState.mGradientFillPaintControlBarRight);
            if (panelBackgroundState.mEnableDither) {
                decreasePosterization();
            }
            this.mIsBright = panelBackgroundState.mIsBright;
        }

        public PanelBackgroundState() {
            this.mEnableDither = true;
            this.mDisableRoundedCorners = true;
            this.mBorderRadiusType = AndroidPanelLayer.BorderRadiusType.All;
            this.mRect = new RectF();
            this.mPath = new Path();
            this.mBackgroundPaint = new Paint();
            this.mGradientFillPaintBottom = new Paint();
            this.mGradientFillPaintLeft = new Paint();
            this.mGradientFillPaintRight = new Paint();
            this.mGradientFillPaintBottomBezel = new Paint();
            this.mGradientFillPaintControlBarLeft = new Paint();
            this.mGradientFillPaintControlBarRight = new Paint();
            if (this.mEnableDither) {
                decreasePosterization();
            }
            this.mIsBright = false;
            this.mHasControlBarAttached = true;
            this.mIsControlBar = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            OCPanelBackgroundDrawable oCPanelBackgroundDrawable = new OCPanelBackgroundDrawable(new PanelBackgroundState(this));
            if (theme != null) {
                oCPanelBackgroundDrawable.applyTheme(theme);
            }
            return oCPanelBackgroundDrawable;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new OCPanelBackgroundDrawable(new PanelBackgroundState(this));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        draw(canvas, false);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    private float[] getBorderRadii() {
        PanelBackgroundState panelBackgroundState = this.mDrawableState;
        if (panelBackgroundState.mBorderRadii == null) {
            panelBackgroundState.mBorderRadii = getUpdatedBorderRadii();
        }
        return this.mDrawableState.mBorderRadii;
    }

    private float[] getUpdatedBorderRadii() {
        PanelBackgroundState panelBackgroundState = this.mDrawableState;
        float[] fArr = new float[8];
        panelBackgroundState.mBorderRadii = fArr;
        AndroidPanelLayer.BorderRadiusType borderRadiusType = panelBackgroundState.mBorderRadiusType;
        if (borderRadiusType == AndroidPanelLayer.BorderRadiusType.Bottom) {
            Arrays.fill(fArr, 4, 8, panelBackgroundState.mBorderRadius);
        } else if (borderRadiusType == AndroidPanelLayer.BorderRadiusType.Top) {
            Arrays.fill(fArr, 0, 4, panelBackgroundState.mBorderRadius);
        } else if (borderRadiusType == AndroidPanelLayer.BorderRadiusType.All) {
            Arrays.fill(fArr, 0, 8, panelBackgroundState.mBorderRadius);
        } else if (borderRadiusType == AndroidPanelLayer.BorderRadiusType.Left) {
            Arrays.fill(fArr, 0, 2, panelBackgroundState.mBorderRadius);
            PanelBackgroundState panelBackgroundState2 = this.mDrawableState;
            Arrays.fill(panelBackgroundState2.mBorderRadii, 6, 8, panelBackgroundState2.mBorderRadius);
        } else if (borderRadiusType == AndroidPanelLayer.BorderRadiusType.Right) {
            Arrays.fill(fArr, 2, 6, panelBackgroundState.mBorderRadius);
        }
        return this.mDrawableState.mBorderRadii;
    }

    private void updateGradients() {
        float width = this.mDrawableState.mRect.width() / 2.0f;
        if (width > 0.0f) {
            int[] iArr = new int[2];
            int i = 180;
            int i2 = 20;
            if (this.mDrawableState.mIsBright) {
                i2 = 180;
            }
            iArr[0] = Color.argb(i2, 255, 108, 92);
            PanelBackgroundState panelBackgroundState = this.mDrawableState;
            Paint paint = panelBackgroundState.mGradientFillPaintBottom;
            float centerX = panelBackgroundState.mRect.centerX();
            float f = this.mDrawableState.mRect.bottom;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            paint.setShader(new RadialGradient(centerX, f, width, iArr, (float[]) null, tileMode));
            PanelBackgroundState panelBackgroundState2 = this.mDrawableState;
            panelBackgroundState2.mGradientFillPaintBottomBezel.setShader(new RadialGradient(panelBackgroundState2.mRect.centerX(), this.mDrawableState.mRect.top, width, iArr, (float[]) null, tileMode));
            if (!this.mDrawableState.mIsBright) {
                i = 20;
            }
            iArr[0] = Color.argb(i, 160, 51, 255);
            PanelBackgroundState panelBackgroundState3 = this.mDrawableState;
            Paint paint2 = panelBackgroundState3.mGradientFillPaintLeft;
            RectF rectF = panelBackgroundState3.mRect;
            paint2.setShader(new RadialGradient(rectF.left, rectF.top, width, iArr, (float[]) null, tileMode));
            PanelBackgroundState panelBackgroundState4 = this.mDrawableState;
            Paint paint3 = panelBackgroundState4.mGradientFillPaintControlBarLeft;
            RectF rectF2 = panelBackgroundState4.mRect;
            paint3.setShader(new RadialGradient(rectF2.left, rectF2.bottom, width, iArr, (float[]) null, tileMode));
            int i3 = 10;
            if (this.mDrawableState.mIsBright) {
                i3 = 170;
            }
            iArr[0] = Color.argb(i3, 37, 211, 102);
            PanelBackgroundState panelBackgroundState5 = this.mDrawableState;
            Paint paint4 = panelBackgroundState5.mGradientFillPaintRight;
            RectF rectF3 = panelBackgroundState5.mRect;
            paint4.setShader(new RadialGradient(rectF3.right, rectF3.top, width, iArr, (float[]) null, tileMode));
            PanelBackgroundState panelBackgroundState6 = this.mDrawableState;
            Paint paint5 = panelBackgroundState6.mGradientFillPaintControlBarRight;
            RectF rectF4 = panelBackgroundState6.mRect;
            paint5.setShader(new RadialGradient(rectF4.right, rectF4.bottom, width, iArr, (float[]) null, tileMode));
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        this.mDrawableState.mChangingConfigurations = getChangingConfigurations();
        return this.mDrawableState;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mDrawableState = new PanelBackgroundState(this.mDrawableState);
            updateGradients();
            this.mMutated = true;
        }
        return this;
    }

    public void setBackgroundPaintColor(int i) {
        this.mDrawableState.mBackgroundPaint.setColor(i);
    }

    public void setBorderRadii(AndroidPanelLayer.BorderRadiusType borderRadiusType, int i) {
        //Log.d(TAG, Outlined$0$0$0.$outlined$0$f4cab3e0547d211(", value: ", Outlined$0$0$0.$outlined$0$86ee3ae1f6d32a87(borderRadiusType, "updateBorderRadius with type: "), i));
        PanelBackgroundState panelBackgroundState = this.mDrawableState;
        panelBackgroundState.mBorderRadiusType = borderRadiusType;
        panelBackgroundState.mBorderRadius = i;
        panelBackgroundState.mBorderRadii = getUpdatedBorderRadii();
        invalidateSelf();
    }

    public OCPanelBackgroundDrawable(PanelBackgroundState panelBackgroundState) {
        this.mDrawableState = panelBackgroundState;
    }
    public OCPanelBackgroundDrawable() {
        super();
        this.mDrawableState = new PanelBackgroundState();
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        ThemedAttribute themedAttribute = this.mDrawableState.mBorderRadiusAttr;
        if (themedAttribute != null) {
            themedAttribute.applyTheme(theme);
            PanelBackgroundState panelBackgroundState = this.mDrawableState;
            panelBackgroundState.mBorderRadius = panelBackgroundState.mBorderRadiusAttr.getDimensionPixelSize(panelBackgroundState.mBorderRadius, theme.getResources().getDisplayMetrics());
        }
        ThemedAttribute themedAttribute2 = this.mDrawableState.mSolidBgColorAttr;
        if (themedAttribute2 != null) {
            themedAttribute2.applyTheme(theme);
            PanelBackgroundState panelBackgroundState2 = this.mDrawableState;
            Paint paint = panelBackgroundState2.mBackgroundPaint;
            paint.setColor(panelBackgroundState2.mSolidBgColorAttr.getColor(paint.getColor()));
        }
        updateGradients();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray obtainStyledAttributes;
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        int[] iArr = R.styleable.OCPanelBackgroundDrawable;
        if (theme == null) {
            obtainStyledAttributes = resources.obtainAttributes(attributeSet, iArr);
        } else {
            obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        }
        try {
            this.mDrawableState.mBorderRadiusAttr = new ThemedAttribute(obtainStyledAttributes.peekValue(0));
            PanelBackgroundState panelBackgroundState = this.mDrawableState;
            panelBackgroundState.mBorderRadius = panelBackgroundState.mBorderRadiusAttr.getDimensionPixelSize(panelBackgroundState.mBorderRadius, resources.getDisplayMetrics());
            PanelBackgroundState panelBackgroundState2 = this.mDrawableState;
            panelBackgroundState2.mHasControlBarAttached = obtainStyledAttributes.getBoolean(R.styleable.OCPanelBackgroundDrawable_hasControlBarAttached, panelBackgroundState2.mHasControlBarAttached);
            PanelBackgroundState panelBackgroundState3 = this.mDrawableState;
            panelBackgroundState3.mIsControlBar = obtainStyledAttributes.getBoolean(R.styleable.OCPanelBackgroundDrawable_isControlBar, panelBackgroundState3.mIsControlBar);
            PanelBackgroundState panelBackgroundState4 = this.mDrawableState;
            panelBackgroundState4.mDisableRoundedCorners = obtainStyledAttributes.getBoolean(R.styleable.OCPanelBackgroundDrawable_disableRoundedCorners, panelBackgroundState4.mDisableRoundedCorners);
            PanelBackgroundState panelBackgroundState5 = this.mDrawableState;
            panelBackgroundState5.mEnableDither = obtainStyledAttributes.getBoolean(R.styleable.OCPanelBackgroundDrawable_enableDither, panelBackgroundState5.mEnableDither);
            PanelBackgroundState panelBackgroundState6 = this.mDrawableState;
            panelBackgroundState6.mIsBright = obtainStyledAttributes.getBoolean(R.styleable.OCPanelBackgroundDrawable_isBright, panelBackgroundState6.mIsBright);
            this.mDrawableState.mSolidBgColorAttr = new ThemedAttribute(obtainStyledAttributes.peekValue(R.styleable.OCPanelBackgroundDrawable_solidBgColor));
            PanelBackgroundState panelBackgroundState7 = this.mDrawableState;
            Paint paint = panelBackgroundState7.mBackgroundPaint;
            paint.setColor(panelBackgroundState7.mSolidBgColorAttr.getColor(paint.getColor()));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDrawableState.mRect.set(rect);
        this.mDrawableState.mPath.reset();
        PanelBackgroundState panelBackgroundState = this.mDrawableState;
        if (panelBackgroundState.mDisableRoundedCorners) {
            panelBackgroundState.mPath.addRect(panelBackgroundState.mRect, Path.Direction.CW);
        } else {
            if (panelBackgroundState.mHasControlBarAttached) {
                setBorderRadii(AndroidPanelLayer.BorderRadiusType.Top, (int) panelBackgroundState.mBorderRadius);
            }
            PanelBackgroundState panelBackgroundState2 = this.mDrawableState;
            panelBackgroundState2.mPath.addRoundRect(panelBackgroundState2.mRect, getBorderRadii(), Path.Direction.CW);
        }
        updateGradients();
    }

    public void draw(Canvas canvas, boolean z) {
        Path path;
        Paint paint;
        this.mDrawableState.mPath.reset();
        PanelBackgroundState panelBackgroundState = this.mDrawableState;
        boolean z2 = panelBackgroundState.mDisableRoundedCorners;
        Path path2 = panelBackgroundState.mPath;
        RectF rectF = panelBackgroundState.mRect;
        if (z2) {
            path2.addRect(rectF, Path.Direction.CW);
        } else {
            path2.addRoundRect(rectF, getBorderRadii(), Path.Direction.CW);
        }
        PanelBackgroundState panelBackgroundState2 = this.mDrawableState;
        canvas.drawPath(panelBackgroundState2.mPath, panelBackgroundState2.mBackgroundPaint);
        PanelBackgroundState panelBackgroundState3 = this.mDrawableState;
        if (z) {
            path = panelBackgroundState3.mPath;
            paint = panelBackgroundState3.mGradientFillPaintBottomBezel;
        } else {
            boolean z3 = panelBackgroundState3.mIsControlBar;
            Path path3 = panelBackgroundState3.mPath;
            if (z3) {
                canvas.drawPath(path3, panelBackgroundState3.mGradientFillPaintControlBarLeft);
                PanelBackgroundState panelBackgroundState4 = this.mDrawableState;
                path = panelBackgroundState4.mPath;
                paint = panelBackgroundState4.mGradientFillPaintControlBarRight;
            } else {
                canvas.drawPath(path3, panelBackgroundState3.mGradientFillPaintBottom);
                PanelBackgroundState panelBackgroundState5 = this.mDrawableState;
                canvas.drawPath(panelBackgroundState5.mPath, panelBackgroundState5.mGradientFillPaintLeft);
                PanelBackgroundState panelBackgroundState6 = this.mDrawableState;
                path = panelBackgroundState6.mPath;
                paint = panelBackgroundState6.mGradientFillPaintRight;
            }
        }
        canvas.drawPath(path, paint);
    }
}