package com.github.bijoysingh.starter.fragments;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.view.View;

/**
 * Abstract class which helps build bottom sheet fragments
 * Created by bijoy on 11/22/2017.
 */

public abstract class SimpleBottomSheetFragment extends BottomSheetDialogFragment {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  protected BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback =
      new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
          SimpleBottomSheetFragment.this.onStateChanged(bottomSheet, newState);
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
          SimpleBottomSheetFragment.this.onSlide(bottomSheet, slideOffset);
        }
      };

  @Override
  public void setupDialog(final Dialog dialog, int style) {
    View contentView = View.inflate(getContext(), getLayout(), null);
    dialog.setContentView(contentView);

    CoordinatorLayout.LayoutParams layoutParams =
        (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
    CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
    if (behavior != null && behavior instanceof BottomSheetBehavior) {
      ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
    }

    setupView(dialog);
  }

  /**
   * The layout which needs to be used for the dialog
   *
   * @return the layout resource id
   */
  @LayoutRes
  public abstract int getLayout();

  /**
   * Setup the view once inflated
   *
   * @param dialog the dialog with the view inflated
   */
  public abstract void setupView(Dialog dialog);

  /**
   * Callback for when the state of the bottom sheet is changed
   *
   * @param bottomSheet the bottom sheet view
   * @param newState    BottomSheetBehavior type, the new state
   */
  protected void onStateChanged(@NonNull View bottomSheet, int newState) {
    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
      dismiss();
    }
  }

  /**
   * Callback for when the slide occurs
   *
   * @param bottomSheet the bottom sheet view
   * @param slideOffset the offset of the slide
   */
  protected void onSlide(@NonNull View bottomSheet, float slideOffset) {
    // Nothing needs to be done which is special by default
  }
}

