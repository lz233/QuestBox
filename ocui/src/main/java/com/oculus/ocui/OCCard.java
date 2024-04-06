package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Placeholder;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCCard extends ConstraintLayout {
    public final View.OnClickListener mCardClickListener;
    public Placeholder mCardFooterPlaceholder;
    public OCCardFooter mCardFooterView;
    public ImageView mCardHeaderImageView;
    public int mCornerRadius;
    public int mFooterMargin;
    public int mFooterTopMargin;
    public OCCardHoverContainer mHoverView;
    public boolean mIsInHoveredState;
    public View.OnClickListener mOnClickListener;
    public View.OnHoverListener mOnHoverListener;
    public final ViewOutlineProvider mRoundedCornerCardOutline;
    public final Rect mTempRect;

    private void enterHoverState() {
        this.mIsInHoveredState = true;
        toggleHoverView(true);
    }

    private void exitHoverState() {
        this.mIsInHoveredState = false;
        toggleHoverView(false);
    }

    private boolean isCardHovered() {
        return this.mIsInHoveredState;
    }

    private boolean pointInHoverArea(float f, float f2) {
        ImageView imageView = this.mCardHeaderImageView;
        if (imageView == null) {
            return false;
        }
        imageView.getHitRect(this.mTempRect);
        return this.mTempRect.contains((int) f, (int) f2);
    }

    private void sendHoverEvent(MotionEvent motionEvent, int i) {
        if (this.mOnHoverListener != null) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(i);
            this.mOnHoverListener.onHover(this, obtain);
        }
    }

    private void setupEventHandlerForChildViews() {
        OCCardHoverContainer oCCardHoverContainer = this.mHoverView;
        OCCardFooter oCCardFooter = this.mCardFooterView;
    }

    private void toggleHoverView(boolean z) {
        OCCardHoverContainer oCCardHoverContainer = this.mHoverView;
        if (oCCardHoverContainer != null) {
            int i = View.INVISIBLE;
            if (z) {
                i = View.VISIBLE;
            }
            oCCardHoverContainer.setVisibility(i);
        }
    }

    public ImageView getCardHeaderImageView() {
        return this.mCardHeaderImageView;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @Override // android.view.View
    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }

    public OCCard(Context context) {
        super(context);
        this.mTempRect = new Rect();
        this.mRoundedCornerCardOutline = new ViewOutlineProvider() { // from class: com.oculus.ocui.OCCard.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), OCCard.this.mCornerRadius);
            }
        };
        this.mCardClickListener = new View.OnClickListener() { // from class: com.oculus.ocui.OCCard.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View.OnClickListener onClickListener = OCCard.this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        };
        init(null, R.attr.ocCardStyle, 0);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Context context = getContext();
        View.inflate(context, R.layout.occard, this);
        View findViewById = findViewById(R.id.card_header);
        this.mCardHeaderImageView = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.card_footer_placeholder);
        this.mCardFooterPlaceholder = (Placeholder) findViewById2;
        this.mCornerRadius = OCThemeUtil.getDimensionPixelSize(context, R.attr.ocCardBorderRadius, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCCard, i, i2);
        try {
            this.mFooterMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCCard_ocCardFooterMargin, 0);
            this.mFooterTopMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCCard_ocCardFooterTopMargin, 0);
            obtainStyledAttributes.recycle();
            this.mCardHeaderImageView.setClipToOutline(true);
            this.mCardHeaderImageView.setOutlineProvider(this.mRoundedCornerCardOutline);
            this.mCardHeaderImageView.setClickable(true);
            this.mCardHeaderImageView.setFocusable(true);
            this.mCardHeaderImageView.setOnClickListener(this.mCardClickListener);
            this.mCardHeaderImageView.setForeground(context.getDrawable(R.drawable.occard_selector_qds));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        int i;
        int action = motionEvent.getAction();
        if (!this.mIsInHoveredState) {
            i = 9;
            if ((action == 9 || action == 7) && pointInHoverArea(motionEvent.getX(), motionEvent.getY())) {
                enterHoverState();
                sendHoverEvent(motionEvent, i);
            }
        } else {
            i = 10;
            if (action == 10 || (action == 7 && !pointInHoverArea(motionEvent.getX(), motionEvent.getY()))) {
                exitHoverState();
                sendHoverEvent(motionEvent, i);
            } else {
                sendHoverEvent(motionEvent, 7);
            }
        }
        return super.onInterceptHoverEvent(motionEvent);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view.getId() == -1) {
            view.setId(View.generateViewId());
        }
        if (view instanceof OCCardFooter) {
            this.mCardFooterPlaceholder.setContentId(view.getId());
            this.mCardFooterView = (OCCardFooter) view;
            int i = this.mFooterMargin;
            view.setPadding(i, this.mFooterTopMargin, i, i);
        } else if (view instanceof OCCardHoverContainer) {
            this.mHoverView = (OCCardHoverContainer) view;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            constraintSet.connect(this.mHoverView.getId(), 3, 0, 3);
            constraintSet.connect(this.mHoverView.getId(), 6, 0, 6);
            constraintSet.connect(this.mHoverView.getId(), 4, R.id.card_header, 4);
            constraintSet.connect(this.mHoverView.getId(), 7, 0, 7);
            constraintSet.constrainWidth(this.mHoverView.getId(), 0);
            constraintSet.constrainHeight(this.mHoverView.getId(), 0);
            constraintSet.applyTo(this);
            view.setVisibility(View.INVISIBLE);
            view.setClipToOutline(true);
            view.setOutlineProvider(this.mRoundedCornerCardOutline);
            this.mCardHeaderImageView.setForeground(null);
        }
        setupEventHandlerForChildViews();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view == this.mHoverView) {
            this.mHoverView = null;
        } else if (view != this.mCardFooterView) {
        } else {
            this.mCardFooterView = null;
        }
    }

    public void showHoverLayoutForTest() {
        enterHoverState();
    }

    public OCCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTempRect = new Rect();
        this.mRoundedCornerCardOutline = new ViewOutlineProvider() { // from class: com.oculus.ocui.OCCard.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), OCCard.this.mCornerRadius);
            }
        };
        this.mCardClickListener = new View.OnClickListener() { // from class: com.oculus.ocui.OCCard.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View.OnClickListener onClickListener = OCCard.this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        };
        init(attributeSet, i, 0);
    }

    public OCCard(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTempRect = new Rect();
        this.mRoundedCornerCardOutline = new ViewOutlineProvider() { // from class: com.oculus.ocui.OCCard.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), OCCard.this.mCornerRadius);
            }
        };
        this.mCardClickListener = new View.OnClickListener() { // from class: com.oculus.ocui.OCCard.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View.OnClickListener onClickListener = OCCard.this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        };
        init(attributeSet, i, i2);
    }

    public OCCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTempRect = new Rect();
        this.mRoundedCornerCardOutline = new ViewOutlineProvider() { // from class: com.oculus.ocui.OCCard.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), OCCard.this.mCornerRadius);
            }
        };
        this.mCardClickListener = new View.OnClickListener() { // from class: com.oculus.ocui.OCCard.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View.OnClickListener onClickListener = OCCard.this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        };
        init(attributeSet, R.attr.ocCardStyle, 0);
    }
}