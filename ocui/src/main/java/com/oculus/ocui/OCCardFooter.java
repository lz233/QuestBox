package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCCardFooter extends ConstraintLayout {
    public Drawable mBackground;
    public OCButton mButton;
    public Placeholder mButtonPlaceholder;
    public Placeholder mIconPlaceholder;
    public OCTextView mSubtitleTextview;
    public OCTextView mTitleTextView;

    private void setupEventHandlerForChildViews() {
    }


    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitleTextview.setText(charSequence);
        OCTextView oCTextView = this.mSubtitleTextview;
        int i = View.VISIBLE;
        if (TextUtils.isEmpty(charSequence)) {
            i = View.GONE;
        }
        oCTextView.setVisibility(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitleTextView.setText(charSequence);
    }

    public OCCardFooter(Context context) {
        super(context);
        init(null, R.attr.ocCardStyle, 0);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Context context = getContext();
        View.inflate(context, R.layout.occard_footer, this);
        View findViewById = findViewById(R.id.card_footer_image_placeholder);
        this.mIconPlaceholder = (Placeholder) findViewById;
        View findViewById2 = findViewById(R.id.card_footer_title);
        this.mTitleTextView = (OCTextView) findViewById2;
        View findViewById3 = findViewById(R.id.card_footer_subtitle);
        this.mSubtitleTextview = (OCTextView) findViewById3;
        View findViewById4 = findViewById(R.id.card_footer_button_placeholder);
        this.mButtonPlaceholder = (Placeholder) findViewById4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCCard, i, i2);
        try {
            this.mBackground = obtainStyledAttributes.getDrawable(R.styleable.OCCard_ocCardFooterBackground);
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
        if (view instanceof ImageView) {
            this.mIconPlaceholder.setContentId(view.getId());
        } else if (view instanceof OCButton) {
            this.mButtonPlaceholder.setContentId(view.getId());
            this.mButton = (OCButton) view;
        }
        setupEventHandlerForChildViews();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view == this.mButton) {
            this.mButton = null;
        }
    }

    public OCCardFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    public OCCardFooter(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public OCCardFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, R.attr.ocCardStyle, 0);
    }
}