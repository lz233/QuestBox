package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.drawerlayout.widget.DrawerLayout;

/* loaded from: classes.dex */
public class OCPanel extends OCPanelBackground {
    public PanelDrawerLayout mDrawerLayout;
    //public OCSideSheet mSideSheet;

    public OCPanel(Context context) {
        this(context, null);
    }

    /* loaded from: classes.dex */
    public class PanelDrawerLayout extends DrawerLayout {
        public PanelDrawerLayout(Context context) {
            super(context);
            setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            setDrawerElevation(0.0f);
            setScrimColor(0);
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getActionMasked() == 1) {
                closeDrawers();
                return true;
            }
            return super.onTouchEvent(motionEvent);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view.equals(this.mDrawerLayout)) {
            view = this.mDrawerLayout;
        } else {
            PanelDrawerLayout panelDrawerLayout = this.mDrawerLayout;
            if (panelDrawerLayout != null) {
                panelDrawerLayout.addView(view, i, layoutParams);
                return;
            }
        }
        super.addView(view, i, layoutParams);
    }

    /*public OCSideSheet getSideSheet() {
        OCSideSheet oCSideSheet = this.mSideSheet;
        if (oCSideSheet == null) {
            OCSideSheet oCSideSheet2 = new OCSideSheet(this.mDrawerLayout);
            this.mSideSheet = oCSideSheet2;
            return oCSideSheet2;
        }
        return oCSideSheet;
    }*/

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeDrawerLayout() {
        PanelDrawerLayout panelDrawerLayout = new PanelDrawerLayout(getContext());
        this.mDrawerLayout = panelDrawerLayout;
        addView(panelDrawerLayout);
        //this.mSideSheet = null;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view == this.mDrawerLayout) {
            post(new Runnable() { // from class: com.oculus.ocui.OCPanel$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OCPanel.this.initializeDrawerLayout();
                }
            });
        }
    }

    public OCPanel(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public OCPanel(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setClipToOutline(true);
        initializeDrawerLayout();
    }

    public OCPanel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}