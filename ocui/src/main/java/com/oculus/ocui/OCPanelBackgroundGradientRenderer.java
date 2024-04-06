package com.oculus.ocui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class OCPanelBackgroundGradientRenderer implements GLSurfaceView.Renderer {
    public final PanelGradientConfig gradientConfig;
    public float height;
    public final Resources resources;
    public Square square;
    public float width;

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glViewport(0, 0, i, i2);
        this.width = i;
        this.height = i2;
    }

    public final Bitmap getGradientBitmap(int i, int i2) {
        PixelBuffer pixelBuffer = new PixelBuffer(i, i2, this);
        Bitmap bitmap = pixelBuffer.getBitmap();
        pixelBuffer.destroy();
        return bitmap;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        Square square = this.square;
        if (square == null) {
            Intrinsics.throwUninitializedPropertyAccessException("square");
            throw null;
        } else {
            square.draw(this.width, this.height);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.square = new Square(this.resources, this.gradientConfig);
    }

    public OCPanelBackgroundGradientRenderer(Resources resources, PanelGradientConfig panelGradientConfig) {
        this.resources = resources;
        this.gradientConfig = panelGradientConfig;
    }
}