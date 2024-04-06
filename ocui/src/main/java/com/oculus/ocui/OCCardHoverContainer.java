package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCCardHoverContainer extends ConstraintLayout {
    public Drawable mBackground;
    public Placeholder mCardFooterPlaceholder;
    public OCCardFooter mCardFooterView;
    public int mFooterMargin;

    private void setupEventHandlerForChildViews() {
    }

    public OCCardHoverContainer(Context context) {
        super(context);
        init(null, R.attr.ocCardStyle, 0);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Context context = getContext();
        View.inflate(context, R.layout.occard_hover, this);
        View findViewById = findViewById(R.id.card_hover_footer_placeholder);
        this.mCardFooterPlaceholder = (Placeholder) findViewById;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCCard, i, i2);
        try {
            this.mBackground = obtainStyledAttributes.getDrawable(R.styleable.OCCard_ocCardHoverBackground);
            this.mFooterMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCCard_ocCardHoverFooterMargin, 0);
            obtainStyledAttributes.recycle();
            setBackground(this.mBackground);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
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
            view.setPadding(i, i, i, i);
        }
        setupEventHandlerForChildViews();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view == this.mCardFooterView) {
            this.mCardFooterView = null;
        }
    }

    public OCCardHoverContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    public OCCardHoverContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public OCCardHoverContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, R.attr.ocCardStyle, 0);
    }
}