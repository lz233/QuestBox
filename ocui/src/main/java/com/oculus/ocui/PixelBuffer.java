package com.oculus.ocui;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class PixelBuffer {
    public final Companion Companion = new Companion();
    public static final int GLES_VERSION = 2;
    public static final String TAG;
    public EGLDisplay display;
    public EGLContext eglContext;
    public int height;
    public GLSurfaceView.Renderer renderer;
    public EGLSurface surface;
    public final String threadOwner;
    public int width;

    /* loaded from: classes.dex */
    public final class Companion {
        public final String getTAG() {
            return PixelBuffer.TAG;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public Companion() {
        }
    }

    public PixelBuffer(int i, int i2, GLSurfaceView.Renderer renderer) {
        this.width = i;
        this.height = i2;
        String name = Thread.currentThread().getName();
        this.threadOwner = name;
        initializeEglContext();
        initializeRenderer(renderer);
    }

    private final void initializeEglContext() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.display = eglGetDisplay;
        String str = "display";
        if (!eglGetDisplay.equals(EGL14.EGL_NO_DISPLAY)) {
            int[] iArr = new int[2];
            EGLDisplay eGLDisplay = this.display;
            if (eGLDisplay != null) {
                if (EGL14.eglInitialize(eGLDisplay, iArr, 0, iArr, 1)) {
                    int[] iArr2 = {12351, 12430, 12321, 1, 12329, 0, 12352, 4, 12339, 1, 12344};
                    EGLConfig[] eGLConfigArr = new EGLConfig[1];
                    int[] iArr3 = new int[1];
                    EGLDisplay eGLDisplay2 = this.display;
                    if (eGLDisplay2 != null) {
                        EGL14.eglChooseConfig(eGLDisplay2, iArr2, 0, eGLConfigArr, 0, 1, iArr3, 0);
                        if (iArr3[0] != 0) {
                            EGLConfig eGLConfig = eGLConfigArr[0];
                            int[] iArr4 = {12375, this.width, 12374, this.height, 12344};
                            EGLDisplay eGLDisplay3 = this.display;
                            if (eGLDisplay3 != null) {
                                EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay3, eGLConfig, iArr4, 0);
                                this.surface = eglCreatePbufferSurface;
                                int[] iArr5 = {12440, 2, 12344};
                                EGLDisplay eGLDisplay4 = this.display;
                                if (eGLDisplay4 != null) {
                                    EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay4, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr5, 0);
                                    this.eglContext = eglCreateContext;
                                    EGLDisplay eGLDisplay5 = this.display;
                                    if (eGLDisplay5 != null) {
                                        EGLSurface eGLSurface = this.surface;
                                        str = "surface";
                                        if (eGLSurface != null) {
                                            EGL14.eglMakeCurrent(eGLDisplay5, eGLSurface, eGLSurface, eglCreateContext);
                                            return;
                                        }
                                    }
                                }
                            }
                        } else {
                            //throw Outlined$0$0$3.$outlined$0$f0bec18fe1dd2b43("No configs found");
                        }
                    }
                } else {
                    //throw Outlined$0$0$3.$outlined$0$f0bec18fe1dd2b43(OutlinedStringBuilders.concat("eglInitialize failed ", EGL14.eglGetError()));
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        }
        //throw Outlined$0$0$3.$outlined$0$f0bec18fe1dd2b43(OutlinedStringBuilders.concat("eglGetDisplay failed ", EGL14.eglGetError()));
    }

    static {
        String simpleName = new ClassReference(PixelBuffer.class).getSimpleName();
        if (simpleName == null) {
            simpleName = "PixelBuffer";
        }
        TAG = simpleName;
    }

    private final Bitmap flipBitmapY(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return createBitmap;
    }

    private final void initializeRenderer(GLSurfaceView.Renderer renderer) {
        this.renderer = renderer;
        if (!Intrinsics.areEqual(Thread.currentThread().getName(), this.threadOwner)) {
            Log.e(TAG, "setRenderer: This thread does not own the OpenGL context.");
            return;
        }
        renderer.onSurfaceCreated(null, null);
        renderer.onSurfaceChanged(null, this.width, this.height);
    }

    public final void destroy() {
        EGLDisplay eGLDisplay = this.display;
        String str = "display";
        if (eGLDisplay != null) {
            EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
            EGLDisplay eGLDisplay2 = this.display;
            if (eGLDisplay2 != null) {
                EGLSurface eGLSurface2 = this.surface;
                if (eGLSurface2 == null) {
                    str = "surface";
                } else {
                    EGL14.eglDestroySurface(eGLDisplay2, eGLSurface2);
                    EGLDisplay eGLDisplay3 = this.display;
                    if (eGLDisplay3 != null) {
                        EGLContext eGLContext = this.eglContext;
                        if (eGLContext == null) {
                            str = "eglContext";
                        } else {
                            EGL14.eglDestroyContext(eGLDisplay3, eGLContext);
                            EGLDisplay eGLDisplay4 = this.display;
                            if (eGLDisplay4 != null) {
                                EGL14.eglTerminate(eGLDisplay4);
                                return;
                            }
                        }
                    }
                }
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException(str);
        throw null;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final Bitmap getBitmap() {
        if (!Intrinsics.areEqual(Thread.currentThread().getName(), this.threadOwner)) {
            Log.e(TAG, "getBitmap: This thread does not own the OpenGL context.");
            return null;
        }
        GLSurfaceView.Renderer renderer = this.renderer;
        if (renderer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("renderer");
            throw null;
        }
        renderer.onDrawFrame(null);
        ByteBuffer order = ByteBuffer.allocate(this.width * this.height * 4).order(ByteOrder.nativeOrder());
        GLES20.glReadPixels(0, 0, this.width, this.height, 6408, 5121, order);
        Bitmap createBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(order);
        return flipBitmapY(createBitmap);
    }
}