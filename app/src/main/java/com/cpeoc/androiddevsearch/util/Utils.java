package com.cpeoc.androiddevsearch.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-01 10:34
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class Utils {
    public static float dp2px(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
