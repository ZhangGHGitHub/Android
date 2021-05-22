package com.example.constainlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BottomNavigationBehaviorFABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    public BottomNavigationBehaviorFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        child.setTranslationY(0.0f);
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        return this.updateButton(child, dependency);
    }

    private boolean updateButton(FloatingActionButton child, View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            final float olldTranslation = child.getTranslationY();
            float height = (float) dependency.getHeight();
            final float newTranslation = dependency.getTranslationY() - height;
            child.setTranslationY(newTranslation);
            return olldTranslation != newTranslation;
        }
        return false;
    }
}
