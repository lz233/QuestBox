package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCPanelBackground extends FrameLayout {
    public OCPanelBackgroundRipple mBackgroundRipple;

    public OCPanelBackground(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        super.onInterceptHoverEvent(motionEvent);
        OCPanelBackgroundRipple oCPanelBackgroundRipple = this.mBackgroundRipple;
        if (oCPanelBackgroundRipple != null) {
            oCPanelBackgroundRipple.handleHoverOrTouchEvent(motionEvent);
            return false;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        OCPanelBackgroundRipple oCPanelBackgroundRipple = this.mBackgroundRipple;
        if (oCPanelBackgroundRipple != null) {
            oCPanelBackgroundRipple.handleHoverOrTouchEvent(motionEvent);
            return false;
        }
        return false;
    }

    public OCPanelBackground(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public OCPanelBackground(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int attributeResourceValue;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OCPanel, 0, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        //if (OCConfiguration.getInstance().isNewPanelGradientEnabled()) {
        if (false) {
            //addView(new OCPanelBackgroundGLGradientView(context, attributeSet), layoutParams);
        } else {
            if (attributeSet == null) {
                attributeResourceValue = R.drawable.ocpanel_background;
            } else {
                attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "background", R.drawable.ocpanel_background);
            }
            setBackgroundResource(attributeResourceValue);
        }
        try {
            if (obtainStyledAttributes.getBoolean(R.styleable.OCPanel_showRipple, false)) {
                //OCConfiguration.getInstance();
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public OCPanelBackground(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}