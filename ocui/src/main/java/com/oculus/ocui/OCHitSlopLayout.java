package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import fish.with.ocui.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class OCHitSlopLayout extends FrameLayout {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OCHitSlopLayout";
    public int attrOffsetBottom;
    public int attrOffsetEnd;
    public int attrOffsetStart;
    public int attrOffsetTop;
    public final int debugBackgroundColorResId;
    public final int debugDisabledBackgroundColorResId;
    public boolean enableEventInterception;
    public final boolean isAutoInsertHitSlop;
    public boolean isDebug;
    public boolean isMaintainMinimumSize;
    public boolean isRunRequestLayout;
    public final Rect tempChildRect;
    public final Rect tempContainerRect;

    /* loaded from: classes.dex */
    public static final class Companion {
        public final String getTAG() {
            return OCHitSlopLayout.TAG;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static /* synthetic */ OCHitSlopLayout insertHitSlop$default(Companion companion, View view, AttributeSet attributeSet, int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7, Object obj) {
            int i8 = i6;
            int i9 = i5;
            int i10 = i4;
            int i11 = i3;
            int i12 = i2;
            int i13 = i;
            AttributeSet attributeSet2 = attributeSet;
            if ((i7 & 2) != 0) {
                attributeSet2 = null;
            }
            if ((i7 & 4) != 0) {
                i13 = 0;
            }
            if ((i7 & 8) != 0) {
                i12 = 0;
            }
            if ((i7 & 16) != 0) {
                i11 = 0;
            }
            if ((i7 & 32) != 0) {
                i10 = 0;
            }
            if ((i7 & 64) != 0) {
                i9 = 0;
            }
            if ((i7 & 128) != 0) {
                i8 = 0;
            }
            return companion.insertHitSlop(view, attributeSet2, i13, i12, i11, i10, i9, i8, (i7 & 256) == 0 ? z : false);
        }

        public final OCHitSlopLayout insertHitSlop(View view) {
            return insertHitSlop(view, null, 0, 0, 0, 0, 0, 0, false);
        }

        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet) {
            return insertHitSlop(view, attributeSet, 0, 0, 0, 0, 0, 0, false);
        }

        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i) {
            return insertHitSlop(view, attributeSet, i, 0, 0, 0, 0, 0, false);
        }

        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2) {
            return insertHitSlop(view, attributeSet, i, i2, 0, 0, 0, 0, false);
        }

        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3) {
            return insertHitSlop(view, attributeSet, i, i2, i3, 0, 0, 0, false);
        }

        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4) {
            return insertHitSlop(view, attributeSet, i, i2, i3, i4, 0, 0, false);
        }

        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4, int i5) {
            return insertHitSlop(view, attributeSet, i, i2, i3, i4, i5, 0, false);
        }

        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4, int i5, int i6) {
            return insertHitSlop(view, attributeSet, i, i2, i3, i4, i5, i6, false);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: android.view.View */
        /* JADX WARN: Multi-variable type inference failed */
        public final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
            int i7;
            int i8;
            ViewGroup.MarginLayoutParams marginLayoutParams;
            ViewGroup.MarginLayoutParams marginLayoutParams2;
            ViewGroup.MarginLayoutParams marginLayoutParams3;
            ViewGroup.MarginLayoutParams marginLayoutParams4;
            ViewParent viewParent;
            if (view.getParent() != null && (view.getParent() instanceof ViewGroup) && view.getLayoutParams() != null) {
                if (view instanceof OCHitSlopLayout) {
                    Log.w(OCHitSlopLayout.TAG, "OCHitSlopLayout can not be added to another OCHitSlopLayout");
                    viewParent = (ViewParent) view;
                } else if (view.getParent() instanceof OCHitSlopLayout) {
                    Log.i(OCHitSlopLayout.TAG, "It already has OCHitSlopLayout as a parent");
                    ViewParent parent = view.getParent();
                    Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type com.oculus.ocui.OCHitSlopLayout");
                    viewParent = parent;
                } else {
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view.getLayoutParams().width, view.getLayoutParams().height);
                    ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                    int i9 = 0;
                    if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                        i7 = ((ViewGroup.MarginLayoutParams) layoutParams2).getMarginStart();
                    } else {
                        i7 = 0;
                    }
                    layoutParams.setMarginStart(i7);
                    ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
                    layoutParams.leftMargin = (!(layoutParams3 instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams4 = (ViewGroup.MarginLayoutParams) layoutParams3) == null) ? 0 : marginLayoutParams4.leftMargin;
                    ViewGroup.LayoutParams layoutParams4 = view.getLayoutParams();
                    layoutParams.topMargin = (!(layoutParams4 instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams3 = (ViewGroup.MarginLayoutParams) layoutParams4) == null) ? 0 : marginLayoutParams3.topMargin;
                    ViewGroup.LayoutParams layoutParams5 = view.getLayoutParams();
                    if (layoutParams5 instanceof ViewGroup.MarginLayoutParams) {
                        i8 = ((ViewGroup.MarginLayoutParams) layoutParams5).getMarginEnd();
                    } else {
                        i8 = 0;
                    }
                    layoutParams.setMarginEnd(i8);
                    ViewGroup.LayoutParams layoutParams6 = view.getLayoutParams();
                    layoutParams.rightMargin = (!(layoutParams6 instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams6) == null) ? 0 : marginLayoutParams2.rightMargin;
                    ViewGroup.LayoutParams layoutParams7 = view.getLayoutParams();
                    if ((layoutParams7 instanceof ViewGroup.MarginLayoutParams) && (marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams7) != null) {
                        i9 = marginLayoutParams.bottomMargin;
                    }
                    layoutParams.bottomMargin = i9;
                    Context context = view.getContext();
                    OCHitSlopLayout oCHitSlopLayout = new OCHitSlopLayout(context, attributeSet, i, i2, true);
                    oCHitSlopLayout.setLayoutParams(view.getLayoutParams());
                    view.setLayoutParams(layoutParams);
                    oCHitSlopLayout.setId(view.getId());
                    view.setId(View.generateViewId());
                    ViewParent parent2 = view.getParent();
                    Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.ViewGroup");
                    ViewGroup viewGroup = (ViewGroup) parent2;
                    int indexOfChild = viewGroup.indexOfChild(view);
                    viewGroup.removeViewAt(indexOfChild);
                    oCHitSlopLayout.addView(view);
                    viewGroup.addView(oCHitSlopLayout, indexOfChild);
                    view.getLayoutParams().width = layoutParams.width;
                    view.getLayoutParams().height = layoutParams.height;
                    ViewGroup.LayoutParams layoutParams8 = view.getLayoutParams();
                    Intrinsics.checkNotNull(layoutParams8, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                    ViewGroup.MarginLayoutParams marginLayoutParams5 = (ViewGroup.MarginLayoutParams) layoutParams8;
                    marginLayoutParams5.setMarginStart(layoutParams.getMarginStart());
                    marginLayoutParams5.leftMargin = layoutParams.leftMargin;
                    marginLayoutParams5.topMargin = layoutParams.topMargin;
                    marginLayoutParams5.setMarginEnd(layoutParams.getMarginEnd());
                    marginLayoutParams5.rightMargin = layoutParams.rightMargin;
                    marginLayoutParams5.bottomMargin = layoutParams.bottomMargin;
                    oCHitSlopLayout.setDebug(z);
                    oCHitSlopLayout.getLayoutParams().width = -2;
                    oCHitSlopLayout.getLayoutParams().height = -2;
                    oCHitSlopLayout.attrOffsetStart = i3;
                    oCHitSlopLayout.attrOffsetTop = i4;
                    oCHitSlopLayout.attrOffsetEnd = i5;
                    oCHitSlopLayout.attrOffsetBottom = i6;
                    oCHitSlopLayout.setMargins(view);
                    return oCHitSlopLayout;
                }
                return (OCHitSlopLayout) viewParent;
            }
            throw new IllegalStateException("The view either is not attached to a ViewGroup parent or is a root view");
        }

        public Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OCHitSlopLayout(Context context) {
        this(context, null, 0, 0, false);
    }

    public static final OCHitSlopLayout insertHitSlop(View view) {
        return Companion.insertHitSlop(view);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet) {
        return Companion.insertHitSlop(view, attributeSet);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i) {
        return Companion.insertHitSlop(view, attributeSet, i);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2) {
        return Companion.insertHitSlop(view, attributeSet, i, i2);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3) {
        return Companion.insertHitSlop(view, attributeSet, i, i2, i3);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4) {
        return Companion.insertHitSlop(view, attributeSet, i, i2, i3, i4);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4, int i5) {
        return Companion.insertHitSlop(view, attributeSet, i, i2, i3, i4, i5);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4, int i5, int i6) {
        return Companion.insertHitSlop(view, attributeSet, i, i2, i3, i4, i5, i6);
    }

    public static final OCHitSlopLayout insertHitSlop(View view, AttributeSet attributeSet, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        return Companion.insertHitSlop(view, attributeSet, i, i2, i3, i4, i5, i6, z);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    private final void updateBackground() {
        Resources resources;
        Resources resources2;
        int i;
        if (!this.isDebug) {
            resources = getContext().getResources();
            setBackground(resources.getDrawable(R.drawable.ochitslop_background, null));
            return;
        }
        boolean z = this.enableEventInterception;
        resources2 = getContext().getResources();
        if (z) {
            i = this.debugBackgroundColorResId;
        } else {
            i = this.debugDisabledBackgroundColorResId;
        }
        setBackground(resources2.getDrawable(i, null));
        getBackground().setAlpha(150);
    }

    public final boolean getEnableEventInterception() {
        return this.enableEventInterception;
    }

    public final boolean isDebug() {
        return this.isDebug;
    }

    public final boolean isMaintainMinimumSize() {
        return this.isMaintainMinimumSize;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.tempContainerRect.left = getPaddingLeft();
        this.tempContainerRect.top = getPaddingTop();
        int i5 = i3 - i;
        this.tempContainerRect.right = i5 - getPaddingRight();
        int i6 = i4 - i2;
        this.tempContainerRect.bottom = i6 - getPaddingBottom();
        View childAt = getChildAt(0);
        Gravity.apply(17, childAt.getMeasuredWidth(), childAt.getMeasuredHeight(), this.tempContainerRect, this.tempChildRect);
        Rect rect = this.tempChildRect;
        childAt.layout(rect.left, rect.top, rect.right, rect.bottom);

    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        ViewGroup.MarginLayoutParams marginLayoutParams2;
        ViewGroup.MarginLayoutParams marginLayoutParams3;
        ViewGroup.MarginLayoutParams marginLayoutParams4;
        if (getChildCount() == 1) {
            int i11 = 0;
            try {
                this.isRunRequestLayout = false;
                super.setPadding(0, 0, 0, 0);
                View childAt = getChildAt(0);
                if (childAt instanceof OCHitSlopWrappable) {
                    if (childAt.getVisibility() != View.GONE) {
                        measureChildWithMargins(childAt, i, 0, i2, 0);
                        int i12 = this.attrOffsetStart;
                        int i13 = this.attrOffsetTop;
                        int i14 = this.attrOffsetEnd;
                        int i15 = this.attrOffsetBottom;
                        int measuredWidth = childAt.getMeasuredWidth() + i12 + i14;
                        int measuredHeight = childAt.getMeasuredHeight() + i13 + i15;
                        if (this.isMaintainMinimumSize) {
                            if (measuredWidth < 48) {
                                int i16 = ((48 - measuredWidth) / 2) + 1;
                                i12 += i16;
                                i14 += i16;
                                measuredWidth = 48;
                            }
                            if (measuredHeight < 48) {
                                int i17 = ((48 - measuredHeight) / 2) + 1;
                                i13 += i17;
                                i15 += i17;
                                measuredHeight = 48;
                            }
                        }
                        super.setPadding(i12, i13, i14, i15);
                        if (this.isAutoInsertHitSlop) {
                            setMargins(childAt);
                        }
                        int max = Math.max(getSuggestedMinimumWidth(), measuredWidth);
                        i3 = Math.max(getSuggestedMinimumHeight(), measuredHeight);
                        i11 = max;
                    } else {
                        i3 = 0;
                    }
                    setMeasuredDimension(View.resolveSizeAndState(i11, i, childAt.getMeasuredState()), View.resolveSizeAndState(i3, i2, childAt.getMeasuredState() << 16));
                    return;
                }
                throw new IllegalStateException("OCHitSlopLayout's child has not implemented OCHitSlopWrappable");
            } finally {
                this.isRunRequestLayout = true;
            }
        }
        throw new IllegalStateException("OCHitSlopLayout can only have one child view");
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.isRunRequestLayout) {
            super.requestLayout();
        }
    }

    public final void setDebug(boolean z) {
        if (this.isDebug != z) {
            this.isDebug = z;
            updateBackground();
        }
    }

    public final void setEnableEventInterception(boolean z) {
        if (this.enableEventInterception != z) {
            this.enableEventInterception = z;
            updateBackground();
        }
    }

    public final void setHitSlopOffsets(int i, int i2, int i3, int i4) {
        this.attrOffsetStart = i;
        this.attrOffsetTop = i2;
        this.attrOffsetEnd = i3;
        this.attrOffsetBottom = i4;
        requestLayout();
    }

    public final void setMaintainMinimumSize(boolean z) {
        if (this.isMaintainMinimumSize != z) {
            this.isMaintainMinimumSize = z;
            requestLayout();
        }
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        if (i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
            Log.i(TAG, "Not allowed to set HitSlopLayout paddings");
        }
        super.setPadding(0, 0, 0, 0);
    }

    public static /* synthetic */ void setHitSlopOffsets$default(OCHitSlopLayout oCHitSlopLayout, int i, int i2, int i3, int i4, int i5, Object obj) {
        int $outlined$0$12d95b2365 = ((i5 & 1) != 0) ? 0 : i;
        int $outlined$0$12d9df78d0 = ((i5 & 2) != 0) ? 0 : i2;
        int $outlined$0$12dae823a6 = ((i5 & 4) != 0) ? 0 : i3;
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        oCHitSlopLayout.setHitSlopOffsets($outlined$0$12d95b2365, $outlined$0$12d9df78d0, $outlined$0$12dae823a6, i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i;
        int max;
        int i2;
        int max2;
        int i3;
        int max3;
        int i4;
        int max4;
        ViewGroup.MarginLayoutParams marginLayoutParams2;
        ViewGroup.MarginLayoutParams marginLayoutParams3;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if ((layoutParams instanceof ViewGroup.MarginLayoutParams) && (marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams) != null) {
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                i = ((ViewGroup.MarginLayoutParams) layoutParams2).getMarginStart();
            } else {
                i = 0;
            }
            max = Math.max(0, i - getPaddingLeft());
            marginLayoutParams.setMarginStart(max);
            marginLayoutParams.leftMargin = marginLayoutParams.getMarginStart();
            ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
            if ((layoutParams3 instanceof ViewGroup.MarginLayoutParams) && (marginLayoutParams3 = (ViewGroup.MarginLayoutParams) layoutParams3) != null) {
                i2 = marginLayoutParams3.topMargin;
            } else {
                i2 = 0;
            }
            max2 = Math.max(0, i2 - getPaddingTop());
            marginLayoutParams.topMargin = max2;
            ViewGroup.LayoutParams layoutParams4 = view.getLayoutParams();
            if (layoutParams4 instanceof ViewGroup.MarginLayoutParams) {
                i3 = ((ViewGroup.MarginLayoutParams) layoutParams4).getMarginEnd();
            } else {
                i3 = 0;
            }
            max3 = Math.max(0, i3 - getPaddingRight());
            marginLayoutParams.setMarginEnd(max3);
            marginLayoutParams.rightMargin = marginLayoutParams.getMarginEnd();
            ViewGroup.LayoutParams layoutParams5 = view.getLayoutParams();
            if ((layoutParams5 instanceof ViewGroup.MarginLayoutParams) && (marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams5) != null) {
                i4 = marginLayoutParams2.bottomMargin;
            } else {
                i4 = 0;
            }
            max4 = Math.max(0, i4 - getPaddingBottom());
            marginLayoutParams.bottomMargin = max4;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!(getParent() instanceof OCHitSlopLayout)) {
            return;
        }
        throw new IllegalStateException("Nested OCHitSlopLayout not allowed");
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        View.OnHoverListener onHitSlopOnHoverListener;
        super.onInterceptHoverEvent(motionEvent);
        if (!this.enableEventInterception) {
            return false;
        }
        View childAt = getChildAt(0);
        if ((childAt instanceof OCHitSlopWrappable) && childAt.isEnabled() && (onHitSlopOnHoverListener = ((OCHitSlopWrappable) childAt).getOnHitSlopOnHoverListener()) != null) {
            onHitSlopOnHoverListener.onHover(this, motionEvent);
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        return this.enableEventInterception;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        View childAt = getChildAt(0);
        if (childAt != null && childAt.isEnabled() && this.enableEventInterception) {
            if (motionEvent != null) {
                motionEvent.setLocation((motionEvent.getX() / getMeasuredWidth()) * childAt.getMeasuredWidth(), (motionEvent.getY() / getMeasuredHeight()) * childAt.getMeasuredHeight());
            }
            childAt.dispatchTouchEvent(motionEvent);
        }
        return this.enableEventInterception;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OCHitSlopLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OCHitSlopLayout(Context context, AttributeSet attributeSet, int i, int i2, boolean z) {
        super(context, attributeSet, i, i2);
        this.isAutoInsertHitSlop = z;
        this.isMaintainMinimumSize = true;
        this.enableEventInterception = true;
        this.isRunRequestLayout = true;
        this.debugBackgroundColorResId = R.color.oc_green_40;
        this.debugDisabledBackgroundColorResId = R.color.oc_red_60;
        this.tempContainerRect = new Rect();
        this.tempChildRect = new Rect();
        if (attributeSet != null) {
            int[] iArr = R.styleable.OCHitSlopLayout;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
            setMaintainMinimumSize(obtainStyledAttributes.getBoolean(R.styleable.OCHitSlopLayout_hitSlopIsMaintainMinimumSize, this.isMaintainMinimumSize));
            setDebug(obtainStyledAttributes.getBoolean(R.styleable.OCHitSlopLayout_hitSlopIsDebug, this.isDebug));
            setEnableEventInterception(obtainStyledAttributes.getBoolean(R.styleable.OCHitSlopLayout_hitSlopEnableEventInterception, this.enableEventInterception));
            this.attrOffsetStart = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCHitSlopLayout_hitSlopOffsetStart, this.attrOffsetStart);
            this.attrOffsetTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCHitSlopLayout_hitSlopOffsetTop, this.attrOffsetTop);
            this.attrOffsetEnd = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCHitSlopLayout_hitSlopOffsetEnd, this.attrOffsetEnd);
            this.attrOffsetBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCHitSlopLayout_hitSlopOffsetBottom, this.attrOffsetBottom);
            obtainStyledAttributes.recycle();
        }
        updateBackground();
        if (this.isDebug) {
            String str = TAG;
            StringBuilder $outlined$0$e8f334e7690f88d5 = new StringBuilder("init: attrOffsetStart=");
            $outlined$0$e8f334e7690f88d5.append(this.attrOffsetStart);
            $outlined$0$e8f334e7690f88d5.append(" attrOffsetTop=");
            $outlined$0$e8f334e7690f88d5.append(this.attrOffsetTop);
            $outlined$0$e8f334e7690f88d5.append(" attrOffsetEnd=");
            $outlined$0$e8f334e7690f88d5.append(this.attrOffsetEnd);
            $outlined$0$e8f334e7690f88d5.append(" attrOffsetBottom=");
            $outlined$0$e8f334e7690f88d5.append(this.attrOffsetBottom);
            Log.d(str, $outlined$0$e8f334e7690f88d5.toString());
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OCHitSlopLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, false);
    }

    public /* synthetic */ OCHitSlopLayout(Context context, AttributeSet attributeSet, int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? false : z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OCHitSlopLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, false);
    }
}