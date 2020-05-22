package com.sa.easyandroidform.example;

import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class Utils {
    public static void switchVisibility(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static void initChipGroup(ChipGroup chipGroup, @Nullable String checkItem, List<String> list, View.OnClickListener onClickListener) {
        chipGroup.removeAllViews();
        for (String s : list) {
            Chip chip = (Chip) View.inflate(chipGroup.getContext(), R.layout.chip, null);
            chip.setText(s);
            chip.setCloseIconVisible(false);
            chip.setOnClickListener(onClickListener);
            chipGroup.addView(chip);
            if (checkItem != null && checkItem.equalsIgnoreCase(s)) {
                chipGroup.check(chip.getId());
            }
        }
    }
}
