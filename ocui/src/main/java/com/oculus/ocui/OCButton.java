package com.oculus.ocui;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCButton extends OCTextView implements OCHitSlopWrappable {
    public float defaultAlpha;
    public AttributeSet mAttrs;
    public Drawable mDrawableCenter;
    public boolean mDrawableCenterBoundsSet;
    public int mDrawableCenterPaddingPx;
    public boolean mForceDisableInteractionAnimation;
    public GlowEffect mGlowEffect;
    public boolean mHitSlopIsDebug;
    public boolean mHitSlopIsMaintainMinimumSize;
    public int mHitSlopOffsetBottom;
    public int mHitSlopOffsetEnd;
    public int mHitSlopOffsetStart;
    public int mHitSlopOffsetTop;
    public final boolean mIsQDSInteractionEnabled;
    public boolean mLoading;
    public Mode mMode;
    public View.OnClickListener mOnClickListener;
    public View.OnHoverListener mOnHitSlopOnHoverListener;
    public View.OnHoverListener mOnHoverListener;
    public AnimatedVectorDrawable mSpinner;
    public boolean mSpinnerBoundsSet;
    public Rect mTempRect;
    public boolean mTryAutoInsertHitSlop;

    /* loaded from: classes.dex */
    public enum Mode {
        PRIMARY,
        SECONDARY,
        SECONDARY_ON_MEDIA,
        BORDERLESS,
        BORDERLESS_ON_MEDIA,
        DANGER
    }

    public OCButton(Context context) {
        this(context, null);
    }

    public void disableInteractionAnimations() {
        this.mForceDisableInteractionAnimation = true;
        updateInteractionAnimations();
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public /* synthetic */ void enableHitSlopToInterceptMotionEvents(boolean z) {
        OCHitSlopWrappable.CC.$default$enableHitSlopToInterceptMotionEvents(this, z);
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public /* synthetic */ OCHitSlopLayout getParentHitSlopLayout() {
        return OCHitSlopWrappable.CC.$default$getParentHitSlopLayout(this);
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public /* synthetic */ boolean isParentHitSlop() {
        return OCHitSlopWrappable.CC.$default$isParentHitSlop(this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTryAutoInsertHitSlop && !isParentHitSlop()) {
            OCHitSlopLayout.Companion.insertHitSlop(this, this.mAttrs, 0, 0, this.mHitSlopOffsetStart, this.mHitSlopOffsetTop, this.mHitSlopOffsetEnd, this.mHitSlopOffsetBottom, this.mHitSlopIsDebug).setMaintainMinimumSize(this.mHitSlopIsMaintainMinimumSize);
        }
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public /* synthetic */ void setHitSlopMaintainMinimumSize(boolean z) {
        OCHitSlopWrappable.CC.$default$setHitSlopMaintainMinimumSize(this, z);
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public /* synthetic */ void setHitSlopVisibility(int i) {
        OCHitSlopWrappable.CC.$default$setHitSlopVisibility(this, i);
    }

    /* renamed from: com.oculus.ocui.OCButton$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$ocui$OCButton$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$oculus$ocui$OCButton$Mode = iArr;
            try {
                iArr[Mode.PRIMARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Mode.BORDERLESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Mode.BORDERLESS_ON_MEDIA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Mode.DANGER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Mode.SECONDARY_ON_MEDIA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Mode.SECONDARY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void setDrawableBounds(Drawable drawable, Canvas canvas) {
        Rect rect = this.mTempRect;
        if (rect == null) {
            rect = new Rect();
            this.mTempRect = rect;
        }
        canvas.getClipBounds(rect);
        int max = Math.max(0, Math.min(getWidth() - this.mDrawableCenterPaddingPx * 2, getHeight() - (this.mDrawableCenterPaddingPx * 2)) >> 1);
        int centerX = this.mTempRect.centerX();
        int centerY = this.mTempRect.centerY();
        drawable.setBounds(centerX - max, centerY - max, centerX + max, centerY + max);
    }

    private void updateInteractionAnimations() {
        if (this.mIsQDSInteractionEnabled) {
            StateListAnimator stateListAnimator = null;
            if (!this.mForceDisableInteractionAnimation && !TextUtils.isEmpty(getText())) {
                if (getStateListAnimator() == null) {
                    stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ocbutton_statelist_animator);
                } else {
                    return;
                }
            }
            setStateListAnimator(stateListAnimator);
        }
    }

    public Drawable getDrawableCenter() {
        return this.mDrawableCenter;
    }

    public Mode getMode() {
        return this.mMode;
    }

    public View.OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public View.OnHoverListener getOnHitSlopOnHoverListener() {
        return this.mOnHitSlopOnHoverListener;
    }

    public View.OnHoverListener getOnHoverListener() {
        return this.mOnHoverListener;
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public boolean getTryAutoInsertHitSlop() {
        return this.mTryAutoInsertHitSlop;
    }

    @Override
    // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (drawable != this.mSpinner && drawable != this.mDrawableCenter) {
            super.invalidateDrawable(drawable);
        } else {
            invalidate();
        }
    }

    public void onClickHaptics() {
    }

    public void setDrawableCenter(Drawable drawable) {
        this.mDrawableCenter = drawable;
        if (drawable != null) {
            setText((CharSequence) null);
            updateInteractionAnimations();
            this.mDrawableCenterBoundsSet = false;
        }
        applyDrawableTint();
        invalidate();
    }

    public void setDrawableCenterPadding(int i) {
        this.mDrawableCenterPaddingPx = i;
        this.mDrawableCenterBoundsSet = false;
        invalidate();
    }

    public void setGlow(float f) {
        GlowEffect glowEffect = this.mGlowEffect;
        if (glowEffect != null && glowEffect.setGlowFactor(f)) {
            invalidate();
        }
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public void setHitSlopDebug(boolean z) {
        this.mHitSlopIsDebug = z;
        OCHitSlopWrappable.CC.$default$setHitSlopDebug(this, z);
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public void setHitSlopOffsets(int i, int i2, int i3, int i4) {
        this.mHitSlopOffsetStart = i;
        this.mHitSlopOffsetTop = i2;
        this.mHitSlopOffsetEnd = i3;
        this.mHitSlopOffsetBottom = i4;
        OCHitSlopWrappable.CC.$default$setHitSlopOffsets(this, i, i2, i3, i4);
    }

    public void setLoading(boolean z) {
        this.mLoading = z;
        setEnabled(!z);
        if (this.mSpinner == null) {
            initializeSpinner();
        }
        AnimatedVectorDrawable animatedVectorDrawable = this.mSpinner;
        if (animatedVectorDrawable != null) {
            if (!this.mLoading) {
                animatedVectorDrawable.stop();
                this.mSpinnerBoundsSet = false;
            } else {
                animatedVectorDrawable.start();
            }
        }
        invalidate();
    }

    public void setMode(Mode mode) {
        int i;
        int i2;
        int i3;
        this.mMode = mode;
        int $outlined$0$49b387585c6d7ba8 = AnonymousClass1.$SwitchMap$com$oculus$ocui$OCButton$Mode[mode.ordinal()];
        if ($outlined$0$49b387585c6d7ba8 != 1) {
            if ($outlined$0$49b387585c6d7ba8 != 2) {
                if ($outlined$0$49b387585c6d7ba8 != 3) {
                    if ($outlined$0$49b387585c6d7ba8 != 4) {
                        i = R.drawable.ocbutton_secondary_on_media;
                        if ($outlined$0$49b387585c6d7ba8 != 5) {
                            i = R.drawable.ocbutton_secondary;
                        }
                    } else {
                        i = R.drawable.anytime_bar_end_call_button;
                    }
                } else {
                    i = R.drawable.ocbutton_borderless_on_media;
                }
            } else {
                i = R.drawable.ocbutton_borderless;
            }
            i2 = R.attr.ocPrimaryIcon;
            i3 = R.attr.ocSecondaryButtonText;
            Resources.Theme theme = getContext().getTheme();
            TypedValue $outlined$0$ec6da1aeeb717785 = new TypedValue();
            theme.resolveAttribute(i3, $outlined$0$ec6da1aeeb717785, true);
            setTextColor($outlined$0$ec6da1aeeb717785.data);
            theme.resolveAttribute(i2, $outlined$0$ec6da1aeeb717785, true);
            setCompoundDrawableTintList(ColorStateList.valueOf($outlined$0$ec6da1aeeb717785.data));
            setCompoundDrawableTintMode(PorterDuff.Mode.SRC_IN);
            setBackgroundResource(i);
        }
        i = R.drawable.ocbutton_primary;
        i2 = R.attr.ocControlIcon;
        i3 = R.attr.ocPrimaryButtonText;
        Resources.Theme theme2 = getContext().getTheme();
        TypedValue $outlined$0$ec6da1aeeb7177852 = new TypedValue();
        theme2.resolveAttribute(i3, $outlined$0$ec6da1aeeb7177852, true);
        setTextColor($outlined$0$ec6da1aeeb7177852.data);
        theme2.resolveAttribute(i2, $outlined$0$ec6da1aeeb7177852, true);
        setCompoundDrawableTintList(ColorStateList.valueOf($outlined$0$ec6da1aeeb7177852.data));
        setCompoundDrawableTintMode(PorterDuff.Mode.SRC_IN);
        setBackgroundResource(i);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public void setOnHitSlopOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHitSlopOnHoverListener = onHoverListener;
    }

    @Override // android.view.View
    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }

    @Override // com.oculus.ocui.OCHitSlopWrappable
    public void setTryAutoInsertHitSlop(boolean z) {
        this.mTryAutoInsertHitSlop = z;
    }

    private void applyDrawableTint() {
        ColorStateList compoundDrawableTintList = getCompoundDrawableTintList();
        PorterDuff.Mode compoundDrawableTintMode = getCompoundDrawableTintMode();
        AnimatedVectorDrawable animatedVectorDrawable = this.mSpinner;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.mutate();
            this.mSpinner.setTintList(compoundDrawableTintList);
            this.mSpinner.setTintMode(compoundDrawableTintMode);
        }
        Drawable drawable = this.mDrawableCenter;
        if (drawable != null) {
            drawable.mutate();
            this.mDrawableCenter.setTintList(compoundDrawableTintList);
            this.mDrawableCenter.setTintMode(compoundDrawableTintMode);
            if (this.mDrawableCenter.isStateful()) {
                this.mDrawableCenter.setState(getDrawableState());
            }
        }
    }

    private void initializeSpinner() {
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.ocspinner_indeterminate_circle_large);
        this.mSpinner = animatedVectorDrawable;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.setCallback(this);
        }
        applyDrawableTint();
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mDrawableCenter;
        if (drawable != null && drawable.isStateful() && this.mDrawableCenter.setState(getDrawableState())) {
            invalidateDrawable(this.mDrawableCenter);
        }
    }

    @Override // android.view.View
    public boolean isFocused() {
        if (!super.isHovered() && !super.isFocused()) {
            return false;
        }
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mDrawableCenter;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* renamed from: lambda$new$0$com-oculus-ocui-OCButton  reason: not valid java name */
    public /* synthetic */ void m3225lambda$new$0$comoculusocuiOCButton(View view) {
        onClickHaptics();
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044 A[RETURN] */
    /* renamed from: lambda$new$1$com-oculus-ocui-OCButton  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ boolean m3226lambda$new$1$comoculusocuiOCButton(View view, MotionEvent motionEvent) {
        View.OnHoverListener onHoverListener;
        if (!isClickable()) {
            return true;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked != 9) {
                if (actionMasked == 10) {
                    super.setAlpha(this.defaultAlpha);
                    super.setHovered(false);
                    super.setSelected(false);
                }
                return false;
            }
            this.defaultAlpha = super.getAlpha();
            super.setAlpha(1.0f);
        }
        super.setHovered(true);
        super.setSelected(true);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onDraw(Canvas canvas) {
        Drawable drawable;
        GlowEffect glowEffect;
        AnimatedVectorDrawable animatedVectorDrawable;
        super.onDraw(canvas);
        if (this.mLoading && (animatedVectorDrawable = this.mSpinner) != null) {
            if (!this.mSpinnerBoundsSet) {
                setDrawableBounds(animatedVectorDrawable, canvas);
                this.mSpinnerBoundsSet = true;
            }
            drawable = this.mSpinner;
        } else {
            Drawable drawable2 = this.mDrawableCenter;
            if (drawable2 != null) {
                if (!this.mDrawableCenterBoundsSet) {
                    setDrawableBounds(drawable2, canvas);
                    this.mDrawableCenterBoundsSet = true;
                }
                drawable = this.mDrawableCenter;
            }
            glowEffect = this.mGlowEffect;
            if (glowEffect != null) {
                glowEffect.draw(canvas);
                return;
            }
            return;
        }
        drawable.draw(canvas);
        glowEffect = this.mGlowEffect;
        if (glowEffect == null) {
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawableTintBlendMode(BlendMode blendMode) {
        super.setCompoundDrawableTintBlendMode(blendMode);
        applyDrawableTint();
    }

    @Override // android.widget.TextView
    public void setCompoundDrawableTintList(ColorStateList colorStateList) {
        super.setCompoundDrawableTintList(colorStateList);
        applyDrawableTint();
    }

    @Override // com.oculus.ocui.OCTextView, android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        updateInteractionAnimations();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        setHitSlopVisibility(i);
        super.setVisibility(i);
    }

    public OCButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public OCButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Mode mode;
        this.mSpinnerBoundsSet = false;
        this.mDrawableCenterBoundsSet = false;
        this.mMode = Mode.SECONDARY;
        this.defaultAlpha = 0.2f;
        this.mHitSlopIsMaintainMinimumSize = true;
        this.mHitSlopIsDebug = false;
        this.mHitSlopOffsetStart = 0;
        this.mHitSlopOffsetTop = 0;
        this.mHitSlopOffsetEnd = 0;
        this.mHitSlopOffsetBottom = 0;
        this.mOnHitSlopOnHoverListener = null;
        this.mTryAutoInsertHitSlop = false;
        this.mAttrs = attributeSet;
        this.mIsQDSInteractionEnabled = true;
        super.setOnClickListener(new View.OnClickListener() { // from class: com.oculus.ocui.OCButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OCButton.this.m3225lambda$new$0$comoculusocuiOCButton(view);
            }
        });
        View.OnHoverListener onHoverListener = new View.OnHoverListener() { // from class: com.oculus.ocui.OCButton$$ExternalSyntheticLambda1
            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCButton.this.m3226lambda$new$1$comoculusocuiOCButton(view, motionEvent);
            }
        };
        this.mOnHitSlopOnHoverListener = onHoverListener;
        super.setOnHoverListener(onHoverListener);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCButton, i, i2);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.OCButton_drawableCenter);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCButton_drawableCenterPadding, 0);
        Drawable background = getBackground();
        ColorStateList compoundDrawableTintList = getCompoundDrawableTintList();
        PorterDuff.Mode compoundDrawableTintMode = getCompoundDrawableTintMode();
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
        int integer = obtainStyledAttributes.getInteger(R.styleable.OCButton_mode, -1);
        if (integer != -1) {
            mode = Mode.values()[integer];
        } else {
            mode = this.mMode;
        }
        setMode(mode);
        this.mTryAutoInsertHitSlop = obtainStyledAttributes.getBoolean(R.styleable.OCButton_tryAutoInsertOCHitSlop, this.mTryAutoInsertHitSlop);
        this.mHitSlopIsMaintainMinimumSize = obtainStyledAttributes.getBoolean(R.styleable.OCButton_hitSlopIsMaintainMinimumSize, this.mHitSlopIsMaintainMinimumSize);
        this.mHitSlopIsDebug = obtainStyledAttributes.getBoolean(R.styleable.OCButton_hitSlopIsDebug, this.mHitSlopIsDebug);
        this.mHitSlopOffsetStart = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCButton_hitSlopOffsetStart, this.mHitSlopOffsetStart);
        this.mHitSlopOffsetTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCButton_hitSlopOffsetTop, this.mHitSlopOffsetTop);
        this.mHitSlopOffsetEnd = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCButton_hitSlopOffsetEnd, this.mHitSlopOffsetEnd);
        this.mHitSlopOffsetBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCButton_hitSlopOffsetBottom, this.mHitSlopOffsetBottom);
        obtainStyledAttributes.recycle();
        updateInteractionAnimations();
        if (background != null) {
            setBackground(background);
        }
        if (drawable != null) {
            setDrawableCenter(drawable);
        }
        setDrawableCenterPadding(dimensionPixelSize);
        if (compoundDrawableTintList != null) {
            setCompoundDrawableTintList(compoundDrawableTintList);
        }
        if (compoundDrawableTintMode != null) {
            setCompoundDrawableTintMode(compoundDrawableTintMode);
        }
        if (colorStateList != null) {
            setTextColor(colorStateList);
        }
    }

    public OCButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.ocButtonStyle);
    }
}