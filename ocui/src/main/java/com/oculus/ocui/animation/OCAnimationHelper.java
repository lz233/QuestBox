package com.oculus.ocui.animation;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCAnimationHelper {
    public static final int DURATION_FAST_FADE_IN_MILLIS = 100;
    public static final int DURATION_FAST_FADE_OUT_MILLIS = 70;
    public static final int DURATION_MEDIUM_IN_MILLIS = 200;
    public static final int DURATION_MEDIUM_OUT_MILLIS = 140;
    public static final PathInterpolator INTERPOLATOR_SNAPPY_IN = new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f);
    public static Interpolator INTERPOLATOR_SMOOTH_IN = new PathInterpolator(0.4f, 0.0f, 0.1f, 1.0f);

    public static Animation flyIn(Context context) {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setDuration(context.getResources().getInteger(R.integer.ocanimation_fast2));
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.snappy_in));
        animationSet.addAnimation(translateAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.smooth_in));
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static Animation flyOut(Context context) {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setDuration(context.getResources().getInteger(R.integer.ocinteractions_fast2_out));
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.snappy_out));
        animationSet.addAnimation(translateAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.smooth_out));
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static StateListAnimator buttonPress(Context context) {
        return AnimatorInflater.loadStateListAnimator(context, R.animator.ocbutton_statelist_animator);
    }
}