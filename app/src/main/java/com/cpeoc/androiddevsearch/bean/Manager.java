package com.cpeoc.androiddevsearch.bean;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-23 15:26
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class Manager {
    public Contributor mContributor;

    public Manager(Contributor contributor) {
        mContributor = contributor;
    }

    public String getName() {
        return mContributor.getName();
    }
}
