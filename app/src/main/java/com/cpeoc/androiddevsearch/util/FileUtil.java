package com.cpeoc.androiddevsearch.util;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-04-17 10:37
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;

/**
 * 系统参数类，保存系统常变量，通常情况下只允许被一次赋值
 *
 * @author imatrix
 */
public class FileUtil {

    private static String appHome;

    private Context mContext;
    private boolean exist;
    private String appHomePath;

    public String mkTempDir() throws SdCardNotExistedException {
        return mkdir("temp");
    }

    public FileUtil(Context context) {
        this.mContext = context;
        this.exist = initAppHomeDir();
    }

    /***
     * @return 图片路径
     * @describe 通过图片uri得到图片的路径
     * @params uri
     */
    public String getImagePathByUri(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, projection, null, null, null);
        int columnIndex = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imgPath = cursor
                .getString(columnIndex);
        cursor.close();
        return imgPath;
    }


    /**
     * 检查系统SD卡的可用性，并创建程序的专属文件夹
     */
    private boolean initAppHomeDir() {
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);            //判断sd卡是否存在

        if (!sdCardExist) {
            Toast.makeText(mContext, "设备SD卡不可用", Toast.LENGTH_SHORT);
            return false;
        }

        if (TextUtils.isEmpty(appHome))
            appHome = mContext.getApplicationContext().getPackageName();

        appHomePath = Environment.getExternalStorageDirectory() + File.separator + appHome;

        File file = new File(appHomePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return true;
    }

    public String mkdir(String dir) throws SdCardNotExistedException {
        if (!exist) {
            Toast.makeText(mContext, "设备SD卡不可用", Toast.LENGTH_SHORT);
            throw new SdCardNotExistedException();
        }

        String path = appHomePath + "/" + dir;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * @return
     * @describe 获取临时图片路径
     */
    public String buildTempImgPath() throws SdCardNotExistedException {
        return mkTempDir() + "/tempImg.jpg";
    }


    public class SdCardNotExistedException extends Throwable {
        SdCardNotExistedException() {
            super("设备SD卡不可用");
        }
    }

    public static String getType(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
