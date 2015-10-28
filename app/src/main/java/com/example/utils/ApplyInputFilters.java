package com.example.utils;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by nadeem on 19-06-2015.
 */
public class ApplyInputFilters implements InputFilter {

    private String blockCharacterSet;

    public ApplyInputFilters(String blockCharacterSet) {
        this.blockCharacterSet = blockCharacterSet;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
          if (source != null && !blockCharacterSet.contains(("" + source))) {
            return "";
        }
        return null;
    }
}
