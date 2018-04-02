package com.cpeoc.androiddevsearch.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Bitmap工具
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-28 14:35
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class BitmapUtil {
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth && height > reqHeight) {
            while (reqWidth * inSampleSize < width && reqHeight * inSampleSize < height) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
