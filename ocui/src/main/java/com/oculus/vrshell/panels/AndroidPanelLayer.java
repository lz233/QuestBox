package com.oculus.vrshell.panels;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class AndroidPanelLayer {
    public static final int MAX_LAYER_HEIGHT_DIP = 1000;
    public static final int MAX_LAYER_WIDTH_DIP = 1000;
    public static final int PANEL_SHAPE_EQUIRECT_180 = 13;
    public static final int PANEL_SHAPE_EQUIRECT_360 = 12;
    public static final int PANEL_SHAPE_FLAT = 1;
    public static final int PANEL_SHAPE_HIDDEN = 0;
    public static final int PANEL_SHAPE_LANDSCAPE_CYLINDER = 2;
    public static final int PANEL_SHAPE_PORTRAIT_CYLINDER = 3;
    public static final int PANEL_SHAPE_SCREEN = 14;
    public static final int PANEL_STEREO_LEFT_RIGHT = 1;
    public static final int PANEL_STEREO_MONO = 0;
    public static final int PANEL_STEREO_TOP_BOTTOM = 2;
    public static final String TAG = "AndroidPanelLayer";
    public Activity mActivity;
    public View mContentView;
    //public final Context mContext;
    public long mCurrentSizeRequestVersion;
    public int mHeightInPixels;
    public boolean mLayerActive;
    public LayerResizeListener mLayerResizeListener;
    public LayerStateListener mLayerStateListener;
    public Runnable mLayerStateListenerRunnable;
    //public final Handler mMainHandler;
    public long mPendingSizeRequestVersion;
    public int mPressedDeviceCount;
    public Spec mSpec;
    public boolean mSupersampled;
    //public final Surface mSurface;
    public boolean mVisibility;
    public int mWidthInPixels;

    /* loaded from: classes.dex */
    public enum BorderRadiusType {
        All("all"),
        Left("left"),
        Right("right"),
        Top("top"),
        Bottom("bottom");
        
        public final String mIPCString;

        public final String getIPCString() {
            return this.mIPCString;
        }

        BorderRadiusType(String str) {
            this.mIPCString = str;
        }
    }

    /* loaded from: classes.dex */
    public enum HitTestingBehavior {
        HIT_TESTABLE,
        NOT_HIT_TESTABLE
    }

    /* loaded from: classes.dex */
    public interface LayerResizeListener {
        void onResize();
    }

    /* loaded from: classes.dex */
    public interface LayerStateListener {
        void onShow();
    }

    /* loaded from: classes.dex */
    public enum ResizeBehavior {
        NONE,
        WRAP_CONTENT_WIDTH,
        WRAP_CONTENT_HEIGHT,
        WRAP_CONTENT_WIDTH_HEIGHT
    }

    /*public AndroidPanelLayer(Context context, Surface surface, Spec spec, FrameCommandChannel frameCommandChannel, ShellFeatureSets shellFeatureSets, ICursorService iCursorService, LayerStateListener layerStateListener, LayerResizeListener layerResizeListener) {
        this(context, surface, spec, frameCommandChannel, shellFeatureSets, iCursorService, layerStateListener, layerResizeListener, null);
    }*/

    public static void applyEventOffset(MotionEvent motionEvent, int[] iArr) {
        int i = iArr[0];
        if (i == 0 && iArr[1] == 0) {
            return;
        }
        motionEvent.offsetLocation(-i, -iArr[1]);
    }

    public static void reverseEventOffset(MotionEvent motionEvent, int[] iArr) {
        int i = iArr[0];
        if (i == 0 && iArr[1] == 0) {
            return;
        }
        motionEvent.offsetLocation(i, iArr[1]);
    }

    /* loaded from: classes.dex */
    public enum Shape {
        Hidden(0),
        Flat(1),
        LandscapeCylinder(2),
        PortraitCylinder(3),
        Equirect360(12),
        Equirect180(13),
        Screen(14);
        
        public final int value;

        Shape(int i) {
            this.value = i;
        }

        public static Shape fromValue(int i) {
            Shape[] values;
            for (Shape shape : values()) {
                if (i == shape.value) {
                    return shape;
                }
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public final class Spec {
        public final float densityScale;
        public final int height;
        public final HitTestingBehavior hitTestingBehavior;
        public final String name;
        public final ResizeBehavior resizeBehavior;
        public Shape shape;
        public Stereo stereo;
        public final boolean supersampled;
        public final int themeResourceId;
        public final float translateXInPixels;
        public final float translateYInPixels;
        public final int width;

        public Spec(String str, int i, int i2, ResizeBehavior resizeBehavior, HitTestingBehavior hitTestingBehavior, Shape shape, int i3) {
            this.name = str;
            this.width = i;
            this.height = i2;
            this.resizeBehavior = resizeBehavior;
            this.hitTestingBehavior = hitTestingBehavior;
            this.shape = shape;
            this.stereo = Stereo.Mono;
            this.themeResourceId = i3;
            this.supersampled = false;
            this.translateXInPixels = 0.0f;
            this.translateYInPixels = 0.0f;
            this.densityScale = 1.0f;
        }

        public Spec(String str, int i, int i2, ResizeBehavior resizeBehavior, HitTestingBehavior hitTestingBehavior, Shape shape, Stereo stereo, float f, float f2, float f3, boolean z, int i3) {
            this.name = str;
            this.width = i;
            this.height = i2;
            this.resizeBehavior = resizeBehavior;
            this.hitTestingBehavior = hitTestingBehavior;
            this.shape = shape;
            this.stereo = stereo;
            this.themeResourceId = i3;
            this.translateXInPixels = f;
            this.translateYInPixels = f2;
            this.densityScale = f3;
            this.supersampled = z;
        }

        public Spec(String str, int i, int i2, ResizeBehavior resizeBehavior, HitTestingBehavior hitTestingBehavior, Shape shape, float f, float f2, float f3, boolean z, int i3) {
            this.name = str;
            this.width = i;
            this.height = i2;
            this.resizeBehavior = resizeBehavior;
            this.hitTestingBehavior = hitTestingBehavior;
            this.shape = shape;
            this.stereo = Stereo.Mono;
            this.themeResourceId = i3;
            this.translateXInPixels = f;
            this.translateYInPixels = f2;
            this.densityScale = f3;
            this.supersampled = z;
        }
    }

    /* loaded from: classes.dex */
    public enum Stereo {
        Mono(0),
        LeftRight(1),
        TopBottom(2);
        
        public final int value;

        Stereo(int i) {
            this.value = i;
        }
    }

    public static boolean supportsActivity(Spec spec) {
        return "#main".equals(spec.name);
    }

    public final View getContentView() {
        return this.mContentView;
    }

    public final float getDensityScale() {
        return this.mSpec.densityScale;
    }

    public final int getHeightInPixels() {
        return this.mHeightInPixels;
    }
    public final String getName() {
        return this.mSpec.name;
    }

    public final ResizeBehavior getResizeBehavior() {
        return this.mSpec.resizeBehavior;
    }

    public final Shape getShape() {
        return this.mSpec.shape;
    }

    public final Stereo getStereo() {
        return this.mSpec.stereo;
    }

    public final float getTranslateXInPixels() {
        return this.mSpec.translateXInPixels;
    }

    public final float getTranslateYInPixels() {
        return this.mSpec.translateYInPixels;
    }

    public final boolean getVisibility() {
        return this.mVisibility;
    }

    public final int getWidthInPixels() {
        return this.mWidthInPixels;
    }
    public final void handleResizeCallback() {
        LayerResizeListener layerResizeListener = this.mLayerResizeListener;
        if (layerResizeListener != null) {
            layerResizeListener.onResize();
        }
    }

    public final boolean isSupersampled() {
        return this.mSupersampled;
    }

    public final void onPanelSizeRequested() {
        this.mPendingSizeRequestVersion++;
    }

    public final void onPanelSizedResponse() {
        long j = this.mPendingSizeRequestVersion;
        long j2 = this.mCurrentSizeRequestVersion + 1;
        this.mCurrentSizeRequestVersion = j2;
        this.mCurrentSizeRequestVersion = Math.min(j, j2);
        View view = this.mContentView;
        if (view != null) {
            view.invalidate();
        }
    }

    public final void resetPanelSizeRequestVersions() {
        this.mCurrentSizeRequestVersion = this.mPendingSizeRequestVersion;
    }

    public final void setLayerResizeListener(LayerResizeListener layerResizeListener) {
        this.mLayerResizeListener = layerResizeListener;
    }

    public final void setVisibility(boolean z) {
        this.mVisibility = z;
    }

}