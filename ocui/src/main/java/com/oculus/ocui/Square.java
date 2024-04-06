package com.oculus.ocui;

import android.content.res.Resources;
import android.opengl.GLES20;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import fish.with.ocui.R;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public final class Square {
    public static final int COORDS_PER_VERTEX = 3;
    public final ShortBuffer drawOrderBuffer;
    public final PanelGradientConfig gradientConfig;
    public final int program;
    public final FloatBuffer vertexBuffer;
    public final int vertexStride;
    public final Companion Companion = new Companion();
    public static final float[] SQUARE_COORDINATES = {-1.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f};
    public static final short[] DRAW_ORDER_ARRAY = {0, 1, 2, 0, 2, 3};

    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AndroidPanelLayer.BorderRadiusType.values().length];
            try {
                iArr[AndroidPanelLayer.BorderRadiusType.Top.ordinal()]=1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AndroidPanelLayer.BorderRadiusType.Right.ordinal()]=2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AndroidPanelLayer.BorderRadiusType.Bottom.ordinal()]=3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AndroidPanelLayer.BorderRadiusType.Left.ordinal()]=4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final void draw(float f, float f2) {
        int $outlined$0$49b387585c6d7ba8;
        GLES20.glUseProgram(this.program);
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.program, "vPosition");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, this.vertexStride, (Buffer) this.vertexBuffer);
        int i = 1;
        GLES20.glUniform4fv(GLES20.glGetUniformLocation(this.program, "uBackgroundBaseColor"), 1, this.gradientConfig.backgroundColor, 0);
        GLES20.glUniform2f(GLES20.glGetUniformLocation(this.program, "uResolution"), f, f2);
        GLES20.glUniform1f(GLES20.glGetUniformLocation(this.program, "uYOffset"), this.gradientConfig.yOffset);
        GLES20.glUniform1f(GLES20.glGetUniformLocation(this.program, "uCornerRadius"), this.gradientConfig.cornerRadius);
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.program, "uCornerRadiiDirection");
        AndroidPanelLayer.BorderRadiusType borderRadiusType = this.gradientConfig.cornerRadiusType;
        if (borderRadiusType == null) {
            $outlined$0$49b387585c6d7ba8 = -1;
        } else {
            $outlined$0$49b387585c6d7ba8 = WhenMappings.$EnumSwitchMapping$0[borderRadiusType.ordinal()];
        }
        if ($outlined$0$49b387585c6d7ba8 != 1) {
            if ($outlined$0$49b387585c6d7ba8 != 2) {
                if ($outlined$0$49b387585c6d7ba8 != 3) {
                    i = 4;
                    if ($outlined$0$49b387585c6d7ba8 != 4) {
                        i = 0;
                    }
                } else {
                    i = 3;
                }
            } else {
                i = 2;
            }
        }
        GLES20.glUniform1i(glGetUniformLocation, i);
        GLES20.glUniform1i(GLES20.glGetUniformLocation(this.program, "uIsControlBar"), this.gradientConfig.isControlBar ? 1 : 0);
        GLES20.glDrawElements(4, DRAW_ORDER_ARRAY.length, 5123, this.drawOrderBuffer);
        GLES20.glDisableVertexAttribArray(glGetAttribLocation);
    }

    public Square(Resources resources, PanelGradientConfig panelGradientConfig) {
        this.gradientConfig = panelGradientConfig;
        float[] fArr = SQUARE_COORDINATES;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        this.vertexBuffer = asFloatBuffer;
        short[] sArr = DRAW_ORDER_ARRAY;
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(sArr.length * 2);
        allocateDirect2.order(ByteOrder.nativeOrder());
        ShortBuffer asShortBuffer = allocateDirect2.asShortBuffer();
        asShortBuffer.put(sArr);
        asShortBuffer.position(0);
        this.drawOrderBuffer = asShortBuffer;
        int glCreateProgram = GLES20.glCreateProgram();
        String loadAsString = loadAsString(resources, R.raw.ocpanelbackground_vertex_shader);
        int glCreateShader = GLES20.glCreateShader(35633);
        GLES20.glShaderSource(glCreateShader, loadAsString);
        GLES20.glCompileShader(glCreateShader);
        String loadAsString2 = loadAsString(resources, R.raw.ocpanelbackground_vertex_shader);
        int glCreateShader2 = GLES20.glCreateShader(35632);
        GLES20.glShaderSource(glCreateShader2, loadAsString2);
        GLES20.glCompileShader(glCreateShader2);
        GLES20.glAttachShader(glCreateProgram, glCreateShader);
        GLES20.glAttachShader(glCreateProgram, glCreateShader2);
        GLES20.glLinkProgram(glCreateProgram);
        this.program = glCreateProgram;
        this.vertexStride = 12;
    }

    private final String loadAsString(Resources resources, int i) {
        InputStream openRawResource = resources.openRawResource(i);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(Math.max((int) 8192, openRawResource.available()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteStreamsKt.copyTo(openRawResource, byteArrayOutputStream,8192);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Charset charset = StandardCharsets.UTF_8;
        return new String(byteArray, charset);
    }

    private final int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        return glCreateShader;
    }

    /* loaded from: classes.dex */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public Companion() {
        }
    }
}