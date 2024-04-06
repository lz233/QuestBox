package com.oculus.ocui;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;
import java.util.Map;

import fish.with.ocui.R;

/* loaded from: classes.dex */
public class OCTileButton extends ConstraintLayout {
    public final Map<Integer, Integer> TEXT_SIZE_MAP;
    public OCTextView mActiveIndicatorView;
    public ImageView mCTAIcon;
    public OCTextView mCTATextView;
    public OCTextView mLabelTextView;
    public View.OnClickListener mOnClickListener;
    public View.OnHoverListener mOnHoverListener;
    public OCTextView mSubTitleTextView;
    public ImageView mTitleIconImageView;
    public OCTextView mTitleTextView;

    /* loaded from: classes.dex */
    public enum TitleTextSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    /* renamed from: com.oculus.ocui.OCTileButton$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$ocui$OCTileButton$TitleTextSize;

        static {
            int[] iArr = new int[TitleTextSize.values().length];
            $SwitchMap$com$oculus$ocui$OCTileButton$TitleTextSize = iArr;
            try {
                iArr[TitleTextSize.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TitleTextSize.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TitleTextSize.LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void setTitleTextStyle(boolean z) {
        if (z) {
            OCTextView oCTextView = this.mTitleTextView;
            oCTextView.setTypeface(oCTextView.getTypeface(), Typeface.BOLD);
        }
    }

    /* renamed from: lambda$init$0$com-oculus-ocui-OCTileButton  reason: not valid java name */
    public /* synthetic */ void m3249lambda$init$0$comoculusocuiOCTileButton(View view) {
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public void setActiveIndicator(Drawable drawable) {
        this.mActiveIndicatorView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
    }

    public void setCtaText(String str) {
        this.mCTATextView.setText(str);
        this.mCTAIcon.setVisibility(TextUtils.isEmpty(str) ? View.GONE : View.VISIBLE);
    }

    public void setLabel(String str) {
        this.mLabelTextView.setText(str);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @Override // android.view.View
    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }

    public void setSubtitle(String str) {
        this.mSubTitleTextView.setText(str);
        this.mSubTitleTextView.setVisibility(TextUtils.isEmpty(str) ? View.GONE : View.VISIBLE);
    }

    public void setTitle(String str) {
        this.mTitleTextView.setText(str);
    }

    public void setTitleIcon(Drawable drawable) {
        this.mTitleIconImageView.setImageDrawable(drawable);
    }

    public OCTileButton(Context context) {
        super(context);
        Map<Integer, Integer> of = new HashMap<>(2);
        of.put(0, R.dimen.anytime_tablet_profile_text_size_small);
        of.put(1, R.dimen.abc_text_size_menu_header_material);
        of.put(2, R.dimen.octypography_body1_line_height);
        this.TEXT_SIZE_MAP = of;
        init(null, 0, 0);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Context context = getContext();
        View.inflate(context, R.layout.octile_button, this);
        View findViewById = findViewById(R.id.octile_title_icon_image);
        this.mTitleIconImageView = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.octile_button_label);
        this.mLabelTextView = (OCTextView) findViewById2;
        View findViewById3 = findViewById(R.id.octile_title);
        this.mTitleTextView = (OCTextView) findViewById3;
        View findViewById4 = findViewById(R.id.octile_subtitle);
        this.mSubTitleTextView = (OCTextView) findViewById4;
        View findViewById5 = findViewById(R.id.octile_active_indicator_view);
        this.mActiveIndicatorView = (OCTextView) findViewById5;
        View findViewById6 = findViewById(R.id.octile_cta_text);
        this.mCTATextView = (OCTextView) findViewById6;
        View findViewById7 = findViewById(R.id.octile_cta_icon);
        this.mCTAIcon = (ImageView) findViewById7;
        super.setOnClickListener(new View.OnClickListener() { // from class: com.oculus.ocui.OCTileButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OCTileButton.this.m3249lambda$init$0$comoculusocuiOCTileButton(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() { // from class: com.oculus.ocui.OCTileButton$$ExternalSyntheticLambda1
            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCTileButton.this.m3250lambda$init$1$comoculusocuiOCTileButton(view, motionEvent);
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCTileButton, i, i2);
        try {
            setCtaText(obtainStyledAttributes.getString(R.styleable.OCTileButton_ctaText));
            setLabel(obtainStyledAttributes.getString(R.styleable.OCTileButton_label));
            setSubtitle(obtainStyledAttributes.getString(R.styleable.OCTileButton_subtitleText));
            setTitle(obtainStyledAttributes.getString(R.styleable.OCTileButton_title));
            setTitleIcon(obtainStyledAttributes.getDrawable(R.styleable.OCTileButton_titleIcon));
            setTitleTextSize(this.TEXT_SIZE_MAP.getOrDefault(Integer.valueOf(obtainStyledAttributes.getInt(R.styleable.OCTileButton_titleTextSize, 1)), Integer.valueOf((int) R.dimen.abc_text_size_menu_header_material)).intValue());
            setTitleTextStyle(obtainStyledAttributes.getBoolean(R.styleable.OCTileButton_useBoldTitleText, false));
            setActiveIndicator(obtainStyledAttributes.getDrawable(R.styleable.OCTileButton_activeIndicator));
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.OCTileButton_background);
            if (drawable == null) {
                drawable = context.getDrawable(R.drawable.octile_button_background);
            }
            setBackground(drawable);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.active_call_bar_active_call_button_padding);
            setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            obtainStyledAttributes.recycle();
            setupInteractionAnimations();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void setupInteractionAnimations() {
        Context context = getContext();
        if (true) {
            setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, R.animator.ocbutton_statelist_animator));
        }
    }

    /* renamed from: lambda$init$1$com-oculus-ocui-OCTileButton  reason: not valid java name */
    public /* synthetic */ boolean m3250lambda$init$1$comoculusocuiOCTileButton(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 9) {
            if (action == 10) {
                setSelected(false);
            }
        } else {
            setSelected(true);
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener == null) {
            return false;
        }
        return onHoverListener.onHover(view, motionEvent);
    }

    @Override // android.view.View
    public void setActivated(boolean z) {
        super.setActivated(z);
        this.mActiveIndicatorView.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    public void setTitleTextSize(TitleTextSize titleTextSize) {
        int intValue;
        Resources resources = getResources();
        int $outlined$0$49b387585c6d7ba8 = AnonymousClass1.$SwitchMap$com$oculus$ocui$OCTileButton$TitleTextSize[titleTextSize.ordinal()];
        int i = 2;
        if ($outlined$0$49b387585c6d7ba8 != 1) {
            if ($outlined$0$49b387585c6d7ba8 == 2 || $outlined$0$49b387585c6d7ba8 != 3) {
                i = 1;
            }
        } else {
            i = 0;
        }
        OCTextView oCTextView = this.mTitleTextView;
        OCTextUtils oCTextUtils = OCTextUtils.getInstance();
        intValue = ((Number) this.TEXT_SIZE_MAP.getOrDefault(Integer.valueOf(i), Integer.valueOf((int) R.dimen.abc_text_size_menu_header_material))).intValue();
        oCTextView.setTextSize(oCTextUtils.getFontScaledSize(resources, resources.getDimensionPixelSize(intValue)));
    }

    public OCTileButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Map<Integer, Integer> of = new HashMap<>();
        of.put(0,R.dimen.anytime_tablet_profile_text_size_small);
        of.put(1,R.dimen.abc_text_size_menu_header_material);
        of.put(2,R.dimen.octypography_body1_line_height);
        this.TEXT_SIZE_MAP = of;
        init(attributeSet, i, 0);
    }

    private void setTitleTextSize(int i) {
        Resources resources = getResources();
        this.mTitleTextView.setTextSize(OCTextUtils.getInstance().getFontScaledSize(resources, resources.getDimensionPixelSize(i)));
    }

    public OCTileButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Map<Integer, Integer> of = new HashMap<>();
        of.put(0,R.dimen.anytime_tablet_profile_text_size_small);
        of.put(1,R.dimen.abc_text_size_menu_header_material);
        of.put(2,R.dimen.octypography_body1_line_height);
        this.TEXT_SIZE_MAP = of;
        init(attributeSet, i, i2);
    }

    public OCTileButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Map<Integer, Integer> of = new HashMap<>();
        of.put(0,R.dimen.anytime_tablet_profile_text_size_small);
        of.put(1,R.dimen.abc_text_size_menu_header_material);
        of.put(2,R.dimen.octypography_body1_line_height);
        this.TEXT_SIZE_MAP = of;
        init(attributeSet, 0, 0);
    }
}