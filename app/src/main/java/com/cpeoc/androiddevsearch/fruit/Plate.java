package com.cpeoc.androiddevsearch.fruit;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-08 15:43
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class Plate<T> {
    private T item;
    public Plate(T t) {
        item = t;
    }

    public void setItem(T t) {
        item = t;
    }

    public T getItem() {
        return item;
    }
}
