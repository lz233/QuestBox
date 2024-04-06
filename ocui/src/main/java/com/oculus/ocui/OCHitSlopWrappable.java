package com.oculus.ocui;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes.dex */
public interface OCHitSlopWrappable {
    public static final Companion Companion = OCHitSlopWrappable.Companion.$$INSTANCE;

    /* renamed from: com.oculus.ocui.OCHitSlopWrappable$-CC */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void setHitSlopOffsets$default(OCHitSlopWrappable oCHitSlopWrappable, int i, int i2, int i3, int i4, int i5, Object obj) {
            if (obj == null) {
                int $outlined$0$12d95b2365 = ((i5 & 1) != 0) ? 0 : i;
                int $outlined$0$12d9df78d0 = ((i5 & 2) != 0) ? 0 : i2;
                int $outlined$0$12dae823a6 = ((i5 & 4) != 0) ? 0 : i3;
                if ((i5 & 8) != 0) {
                    i4 = 0;
                }
                oCHitSlopWrappable.setHitSlopOffsets($outlined$0$12d95b2365, $outlined$0$12d9df78d0, $outlined$0$12dae823a6, i4);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setHitSlopOffsets");
        }

        public static void $default$enableHitSlopToInterceptMotionEvents(OCHitSlopWrappable oCHitSlopWrappable, boolean z) {
            OCHitSlopLayout parentHitSlopLayout = oCHitSlopWrappable.getParentHitSlopLayout();
            if (parentHitSlopLayout != null) {
                parentHitSlopLayout.setEnableEventInterception(z);
            }
        }

        public static OCHitSlopLayout $default$getParentHitSlopLayout(OCHitSlopWrappable oCHitSlopWrappable) {
            ViewParent parent = oCHitSlopWrappable.getParent();
            if (parent instanceof OCHitSlopLayout) {
                return (OCHitSlopLayout) parent;
            }
            return null;
        }

        public static boolean $default$isParentHitSlop(OCHitSlopWrappable oCHitSlopWrappable) {
            return oCHitSlopWrappable.getParent() instanceof OCHitSlopLayout;
        }

        public static void $default$setHitSlopDebug(OCHitSlopWrappable oCHitSlopWrappable, boolean z) {
            OCHitSlopLayout parentHitSlopLayout = oCHitSlopWrappable.getParentHitSlopLayout();
            if (parentHitSlopLayout != null) {
                parentHitSlopLayout.setDebug(z);
            }
        }

        public static void $default$setHitSlopMaintainMinimumSize(OCHitSlopWrappable oCHitSlopWrappable, boolean z) {
            OCHitSlopLayout parentHitSlopLayout = oCHitSlopWrappable.getParentHitSlopLayout();
            if (parentHitSlopLayout != null) {
                parentHitSlopLayout.setMaintainMinimumSize(z);
            }
        }

        public static void $default$setHitSlopOffsets(OCHitSlopWrappable oCHitSlopWrappable, int i, int i2, int i3, int i4) {
            OCHitSlopLayout parentHitSlopLayout = oCHitSlopWrappable.getParentHitSlopLayout();
            if (parentHitSlopLayout != null) {
                parentHitSlopLayout.setHitSlopOffsets(i, i2, i3, i4);
            }
        }

        public static void $default$setHitSlopVisibility(OCHitSlopWrappable oCHitSlopWrappable, int i) {
            OCHitSlopLayout parentHitSlopLayout = oCHitSlopWrappable.getParentHitSlopLayout();
            if (parentHitSlopLayout != null) {
                parentHitSlopLayout.setVisibility(i);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String TAG = "OCHitSlopWrappable";

        public final String getTAG() {
            return TAG;
        }
    }

    void enableHitSlopToInterceptMotionEvents(boolean z);

    View.OnHoverListener getOnHitSlopOnHoverListener();

    ViewParent getParent();

    OCHitSlopLayout getParentHitSlopLayout();

    boolean getTryAutoInsertHitSlop();

    boolean isParentHitSlop();

    void setHitSlopDebug(boolean z);

    void setHitSlopMaintainMinimumSize(boolean z);

    void setHitSlopOffsets(int i, int i2, int i3, int i4);

    void setHitSlopVisibility(int i);

    void setOnHitSlopOnHoverListener(View.OnHoverListener onHoverListener);

    void setTryAutoInsertHitSlop(boolean z);
}