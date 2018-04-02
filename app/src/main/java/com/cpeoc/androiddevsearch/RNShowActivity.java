package com.cpeoc.androiddevsearch;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-04-02 10:33
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class RNShowActivity extends ReactActivity {
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(new MainReactPackage());
    }

    @Override
    protected boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
    }

    @Override
    protected String getMainComponentName() {
        return "RN_Demo";
    }
}
