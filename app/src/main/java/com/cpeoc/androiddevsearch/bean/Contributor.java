package com.cpeoc.androiddevsearch.bean;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-06 14:52
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class Contributor {
    public String login;
    public String id;
    public String html_url;

    public String getName() {
        return id;
    }

    public String getId(String id) {
        return "id: " + id;
    }
}
