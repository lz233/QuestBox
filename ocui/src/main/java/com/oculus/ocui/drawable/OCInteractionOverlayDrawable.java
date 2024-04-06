package com.oculus.ocui.drawable;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import com.oculus.ocui.ThemedAttribute;
import com.oculus.ocui.animation.OCAnimationHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCInteractionOverlayDrawable extends DrawableWrapper {
    public static final int STATE_HOVERED = 1;
    public static final int STATE_NONE = 0;
    public static final int STATE_PRESSED = 2;
    public int mCurrentState;
    public DrawableState mDrawableState;
    public boolean mIsQdsInteractionEnabled;
    public int mLastState;
    public Bitmap mMaskBitmap;
    public Canvas mMaskCanvas;
    public boolean mMaskPrepared;
    public BitmapShader mMaskShader;
    public boolean mMutated;
    public int mOverlayAlpha;
    public ValueAnimator mOverlayAnimator;
    public final Path mOverlayMaskPath;
    public Paint mOverlayPaint;

    /* loaded from: classes.dex */
    public final class DrawableState extends Drawable.ConstantState {
        public int mHoverOverlayColor;
        public ThemedAttribute mHoverOverlayColorAttr;
        public int mPressOverlayColor;
        public ThemedAttribute mPressOverlayColorAttr;
        public boolean mUseBitmapMasking;
        public ThemedAttribute mUseBitmapMaskingThemeAttr;
        public Drawable.ConstantState mWrappedDrawableState;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final boolean canApplyTheme() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWrappedDrawableState(Drawable drawable) {
            if (drawable != null) {
                this.mWrappedDrawableState = drawable.getConstantState();
            }
        }

        public final boolean canConstantState() {
            return this.mWrappedDrawableState != null;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            Drawable.ConstantState constantState = this.mWrappedDrawableState;
            if (constantState != null) {
                return constantState.getChangingConfigurations();
            }
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            Drawable drawable;
            Drawable.ConstantState constantState = this.mWrappedDrawableState;
            if (constantState != null) {
                drawable = constantState.newDrawable(resources);
            } else {
                drawable = null;
            }
            return new OCInteractionOverlayDrawable(this, drawable);
        }

        public DrawableState(DrawableState drawableState, Drawable drawable) {
            this.mHoverOverlayColor = 0;
            this.mPressOverlayColor = 0;
            setWrappedDrawableState(drawable);
            if (drawableState != null) {
                this.mHoverOverlayColorAttr = drawableState.mHoverOverlayColorAttr;
                this.mUseBitmapMaskingThemeAttr = drawableState.mUseBitmapMaskingThemeAttr;
                this.mPressOverlayColorAttr = drawableState.mPressOverlayColorAttr;
                this.mHoverOverlayColor = drawableState.mHoverOverlayColor;
                this.mPressOverlayColor = drawableState.mPressOverlayColor;
                this.mUseBitmapMasking = drawableState.mUseBitmapMasking;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return newDrawable(null);
        }
    }

    public OCInteractionOverlayDrawable(Drawable drawable) {
        this(null, drawable);
    }

    private int getAlphaForState(int i) {
        int i2;
        if (i != 1) {
            if (i != 2) {
                return 0;
            }
            i2 = this.mDrawableState.mPressOverlayColor;
        } else {
            i2 = this.mDrawableState.mHoverOverlayColor;
        }
        return Color.alpha(i2);
    }

    public static int getAnimationDuration(int i, int i2) {
        int i3;
        if (i != 0) {
            if (i != 1) {
                i3 = i == 2 ? 70 : 70;
            } else if (i2 == 2) {
                return 100;
            }
            return 140;
        }
        i3 = 200;
        if (i2 != 1) {
            return 140;
        }
        return i3;
    }

    public static Interpolator getInterpolator(int i, int i2) {
        if (i != 0 && (i != 1 ? !(i != 2 || i2 != 1) : i2 == 2)) {
            return OCAnimationHelper.INTERPOLATOR_SNAPPY_IN;
        }
        return OCAnimationHelper.INTERPOLATOR_SMOOTH_IN;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    public static int getInteractionsState(boolean z, boolean z2) {
        if (z) {
            return 2;
        }
        return ((z2 ? 1 : 0) != 0) ? 1 : 0;
    }

    private Paint getOverlayPaint() {
        if (this.mOverlayPaint == null) {
            Paint paint = new Paint(1);
            this.mOverlayPaint = paint;
            paint.setStyle(Paint.Style.FILL);
            DrawableState drawableState = this.mDrawableState;
            int i = drawableState.mHoverOverlayColor;
            if (i == 0) {
                i = drawableState.mPressOverlayColor;
            }
            this.mOverlayPaint.setColorFilter(new PorterDuffColorFilter(i | (-16777216), PorterDuff.Mode.SRC_IN));
            this.mOverlayPaint.setColor(0);
        }
        return this.mOverlayPaint;
    }

    private void moveToOverlayState(int i, int i2, boolean z) {
        if (i != i2) {
            if (this.mIsQdsInteractionEnabled && z) {
                int alphaForState = getAlphaForState(i);
                ValueAnimator valueAnimator = this.mOverlayAnimator;
                if (valueAnimator != null && valueAnimator.isStarted()) {
                    this.mOverlayAnimator.cancel();
                    alphaForState = this.mOverlayAlpha;
                }
                ValueAnimator ofInt = ValueAnimator.ofInt(alphaForState, getAlphaForState(i2));
                this.mOverlayAnimator = ofInt;
                ofInt.setInterpolator(getInterpolator(i, i2));
                this.mOverlayAnimator.setDuration(getAnimationDuration(i, i2));
                this.mOverlayAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.oculus.ocui.drawable.OCInteractionOverlayDrawable$$ExternalSyntheticLambda0
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        OCInteractionOverlayDrawable.this.m3321x23f60fc4(valueAnimator2);
                    }
                });
                this.mOverlayAnimator.start();
                return;
            }
            ValueAnimator valueAnimator2 = this.mOverlayAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
                this.mOverlayAnimator = null;
            }
            this.mOverlayAlpha = getAlphaForState(i2);
            super.invalidateSelf();
        }
    }

    private void updateOverlayMaskPathFromDrawableShapeIfNeeded() {
        if (!this.mMaskPrepared) {
            this.mMaskPrepared = true;
            this.mOverlayMaskPath.reset();
            if (!getBounds().isEmpty() && getDrawable() != null) {
                Drawable drawable = getDrawable();
                Outline outline = new Outline();
                drawable.getOutline(outline);
                float radius = outline.getRadius();
                Rect $outlined$0$72902f21e3345faf = new Rect();
                boolean rect = outline.getRect($outlined$0$72902f21e3345faf);
                if (radius < 0.0f && !rect) {
                    this.mMaskPrepared = false;
                    this.mDrawableState.mUseBitmapMasking = true;
                    return;
                }
                this.mOverlayMaskPath.addRoundRect($outlined$0$72902f21e3345faf.left, $outlined$0$72902f21e3345faf.top, $outlined$0$72902f21e3345faf.right, $outlined$0$72902f21e3345faf.bottom, radius, radius, Path.Direction.CW);
            }
        }
    }

    private void updateOverlayShaderIfNeeded() {
        BitmapShader bitmapShader;
        if (!this.mMaskPrepared) {
            this.mMaskPrepared = true;
            Paint overlayPaint = getOverlayPaint();
            Rect bounds = getBounds();
            if (!bounds.isEmpty() && getDrawable() != null) {
                Bitmap bitmap = this.mMaskBitmap;
                if (bitmap != null && bitmap.getWidth() == bounds.width() && this.mMaskBitmap.getHeight() == bounds.height()) {
                    this.mMaskBitmap.eraseColor(0);
                } else {
                    Bitmap bitmap2 = this.mMaskBitmap;
                    if (bitmap2 != null) {
                        bitmap2.recycle();
                    }
                    Bitmap createBitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ALPHA_8);
                    this.mMaskBitmap = createBitmap;
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    this.mMaskShader = new BitmapShader(createBitmap, tileMode, tileMode);
                    this.mMaskCanvas = new Canvas(this.mMaskBitmap);
                }
                Canvas canvas = this.mMaskCanvas;
                if (canvas != null) {
                    int i = bounds.left;
                    int i2 = bounds.top;
                    canvas.translate(-i, -i2);
                    getDrawable().draw(this.mMaskCanvas);
                    this.mMaskCanvas.translate(i, i2);
                }
                bitmapShader = this.mMaskShader;
            } else {
                Bitmap bitmap3 = this.mMaskBitmap;
                if (bitmap3 == null) {
                    return;
                }
                bitmap3.recycle();
                bitmapShader = null;
                this.mMaskBitmap = null;
                this.mMaskCanvas = null;
                this.mMaskShader = null;
            }
            overlayPaint.setShader(bitmapShader);
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.mDrawableState.canApplyTheme();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        DrawableState drawableState = this.mDrawableState;
        if (drawableState == null || !drawableState.canConstantState()) {
            return null;
        }
        return drawableState;
    }

    public int getHoverOverlayColor() {
        return this.mDrawableState.mHoverOverlayColor;
    }

    public int getOverlayAlphaForTest() {
        return this.mOverlayAlpha;
    }

    public ValueAnimator getOverlayAnimatorForTest() {
        return this.mOverlayAnimator;
    }

    public int getPressOverlayColor() {
        return this.mDrawableState.mPressOverlayColor;
    }

    public boolean isUseBitmapMasking() {
        return this.mDrawableState.mUseBitmapMasking;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                drawable = drawable.mutate();
            }
            this.mDrawableState = new DrawableState(this.mDrawableState, drawable);
            this.mMutated = true;
        }
        return this;
    }

    public void setHoverOverlayColor(int i) {
        this.mDrawableState.mHoverOverlayColor = i;
    }

    public void setPressOverlayColor(int i) {
        this.mDrawableState.mPressOverlayColor = i;
    }

    public void setUseBitmapMasking(boolean z) {
        this.mDrawableState.mUseBitmapMasking = z;
    }

    private void drawOverlayWithBitmapMasking(Canvas canvas) {
        updateOverlayShaderIfNeeded();
        if (this.mMaskPrepared && this.mMaskBitmap != null) {
            Paint overlayPaint = getOverlayPaint();
            Rect bounds = getBounds();
            overlayPaint.setAlpha(this.mOverlayAlpha);
            canvas.drawRect(bounds, overlayPaint);
        }
    }

    private void drawOverlayWithShapeDetection(Canvas canvas) {
        if (getDrawable() != null) {
            updateOverlayMaskPathFromDrawableShapeIfNeeded();
            if (!this.mMaskPrepared) {
                if (this.mDrawableState.mUseBitmapMasking) {
                    drawOverlayWithBitmapMasking(canvas);
                    return;
                }
                return;
            }
            Paint overlayPaint = getOverlayPaint();
            overlayPaint.setAlpha(this.mOverlayAlpha);
            if (this.mOverlayMaskPath.isEmpty()) {
                return;
            }
            canvas.drawPath(this.mOverlayMaskPath, overlayPaint);
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(R.style.OCInteractionOverlayDrawableStyle, R.styleable.OCInteractionOverlayDrawable);
        try {
            ThemedAttribute themedAttribute = this.mDrawableState.mHoverOverlayColorAttr;
            if (themedAttribute != null) {
                themedAttribute.applyTheme(theme);
                DrawableState drawableState = this.mDrawableState;
                drawableState.mHoverOverlayColor = drawableState.mHoverOverlayColorAttr.getColor(obtainStyledAttributes.getColor(R.styleable.OCInteractionOverlayDrawable_ocInteractionOverlayHover, drawableState.mHoverOverlayColor));
            }
            ThemedAttribute themedAttribute2 = this.mDrawableState.mPressOverlayColorAttr;
            if (themedAttribute2 != null) {
                themedAttribute2.applyTheme(theme);
                DrawableState drawableState2 = this.mDrawableState;
                drawableState2.mPressOverlayColor = drawableState2.mPressOverlayColorAttr.getColor(obtainStyledAttributes.getColor(R.styleable.OCInteractionOverlayDrawable_ocInteractionOverlayPress, drawableState2.mPressOverlayColor));
            }
            ThemedAttribute themedAttribute3 = this.mDrawableState.mUseBitmapMaskingThemeAttr;
            if (themedAttribute3 != null) {
                themedAttribute3.applyTheme(theme);
                DrawableState drawableState3 = this.mDrawableState;
                drawableState3.mUseBitmapMasking = drawableState3.mUseBitmapMaskingThemeAttr.getBoolean(obtainStyledAttributes.getBoolean(R.styleable.OCInteractionOverlayDrawable_ocInteractionUseBitmapMask, drawableState3.mUseBitmapMasking));
            }
            obtainStyledAttributes.recycle();
            this.mIsQdsInteractionEnabled = true;
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mOverlayAlpha > 0) {
            if (this.mDrawableState.mUseBitmapMasking) {
                drawOverlayWithBitmapMasking(canvas);
            } else {
                drawOverlayWithShapeDetection(canvas);
            }
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray obtainStyledAttributes;
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        if (theme == null) {
            obtainStyledAttributes = resources.obtainAttributes(attributeSet, R.styleable.OCInteractionOverlayDrawable);
        } else {
            obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, R.styleable.OCInteractionOverlayDrawable, 0, R.style.OCInteractionOverlayDrawableStyle);
        }
        try {
            this.mDrawableState.mHoverOverlayColorAttr = new ThemedAttribute(obtainStyledAttributes.peekValue(R.styleable.OCInteractionOverlayDrawable_ocInteractionOverlayHover));
            DrawableState drawableState = this.mDrawableState;
            drawableState.mHoverOverlayColor = drawableState.mHoverOverlayColorAttr.getColor(drawableState.mHoverOverlayColor);
            drawableState.mPressOverlayColorAttr = new ThemedAttribute(obtainStyledAttributes.peekValue(R.styleable.OCInteractionOverlayDrawable_ocInteractionOverlayPress));
            DrawableState drawableState2 = this.mDrawableState;
            drawableState2.mPressOverlayColor = drawableState2.mPressOverlayColorAttr.getColor(drawableState2.mPressOverlayColor);
            drawableState2.mUseBitmapMaskingThemeAttr = new ThemedAttribute(obtainStyledAttributes.peekValue(R.styleable.OCInteractionOverlayDrawable_ocInteractionUseBitmapMask));
            DrawableState drawableState3 = this.mDrawableState;
            drawableState3.mUseBitmapMasking = obtainStyledAttributes.getBoolean(R.styleable.OCInteractionOverlayDrawable_ocInteractionUseBitmapMask, drawableState3.mUseBitmapMasking);
            obtainStyledAttributes.recycle();
            int depth = xmlPullParser.getDepth();
            Drawable drawable = null;
            while (true) {
                int next = xmlPullParser.next();
                if (next == 1) {
                    break;
                } else if (next == 3) {
                    if (xmlPullParser.getDepth() <= depth) {
                        break;
                    }
                } else if (next == 2) {
                    drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
                }
            }
            if (drawable != null) {
                setDrawable(drawable);
                return;
            }
            throw new IllegalArgumentException("No drawable specified for <com.oculus.ocui.drawable.OCInteractionOverlayDrawable");
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void invalidateSelf(boolean z) {
        super.invalidateSelf();
        if (z) {
            this.mMaskPrepared = false;
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        moveToOverlayState(this.mLastState, this.mCurrentState, false);
    }

    /* renamed from: lambda$moveToOverlayState$0$com-oculus-ocui-drawable-OCInteractionOverlayDrawable  reason: not valid java name */
    public /* synthetic */ void m3321x23f60fc4(ValueAnimator valueAnimator) {
        int intValue;
        intValue = ((Number) valueAnimator.getAnimatedValue()).intValue();
        this.mOverlayAlpha = intValue;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int i = this.mCurrentState;
        this.mLastState = i;
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16843623) {
                z2 = true;
            } else if (i2 == 16842919) {
                z = true;
            }
        }
        int interactionsState = getInteractionsState(z, z2);
        this.mCurrentState = interactionsState;
        boolean z3 = false;
        if (interactionsState != i) {
            z3 = true;
            moveToOverlayState(i, interactionsState, true);
        }
        if (!onStateChange && !z3) {
            return false;
        }
        return true;
    }

    @Override // android.graphics.drawable.DrawableWrapper
    public void setDrawable(Drawable drawable) {
        super.setDrawable(drawable);
        DrawableState drawableState = this.mDrawableState;
        if (drawableState != null) {
            drawableState.setWrappedDrawableState(drawable);
        }
    }

    public OCInteractionOverlayDrawable(DrawableState drawableState, Drawable drawable) {
        super(drawable);
        this.mLastState = 0;
        this.mCurrentState = 0;
        this.mOverlayMaskPath = new Path();
        this.mDrawableState = new DrawableState(drawableState, drawable);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        invalidateSelf(true);
    }

    public OCInteractionOverlayDrawable() {
        this(null, null);
    }
}