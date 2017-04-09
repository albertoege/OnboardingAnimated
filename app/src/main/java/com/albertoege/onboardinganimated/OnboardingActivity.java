package com.albertoege.onboardinganimated;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.albertoege.onboardinganimated.databinding.ActivityOnboardingBinding;

import static com.albertoege.onboardinganimated.Utils.dipToPixels;

public class OnboardingActivity extends AppCompatActivity {
    private static final int STARTUP_DELAY = 300;
    private static final int ANIM_LOGO_DURATION = 1000;
    private static final int ANIM_ITEMS_DURATION = 500;
    private static final int ITEM_DELAY = 300;

    private ActivityOnboardingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        animate();
        super.onWindowFocusChanged(true);
    }

    private void animate() {
        startLogoAnimation();
        startCustomItemAnimation(binding.tvTitle, 1 , ANIM_LOGO_DURATION);
        startCustomItemAnimation(binding.tvSubtitle, 2 , ANIM_ITEMS_DURATION);
        startCustomItemAnimation(binding.customViewButtons.btnChoice1, 3 , ANIM_ITEMS_DURATION);
        startCustomItemAnimation(binding.customViewButtons.btnChoice2, 4 , ANIM_ITEMS_DURATION);
    }

    private void startLogoAnimation(){
        float valuePixelFrom160dp = dipToPixels(getApplicationContext(), 160);
        ViewCompat.animate(binding.imgLogo)
                .translationY(-valuePixelFrom160dp)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_LOGO_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();
    }

    private void startCustomItemAnimation(View viewToAnimate, int itemDelayTimeMultiplier, int itemDelayExtraTime){
        ViewPropertyAnimatorCompat viewAnimator = ViewCompat.animate(viewToAnimate)
                .scaleY(1).scaleX(1)
                .setStartDelay((ITEM_DELAY * itemDelayTimeMultiplier) + itemDelayExtraTime)
                .setDuration(ANIM_ITEMS_DURATION);
        viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
    }
}
