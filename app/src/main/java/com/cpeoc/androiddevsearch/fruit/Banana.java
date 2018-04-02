package com.cpeoc.androiddevsearch.fruit;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-23 16:21
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class Banana extends Fruit {
    private static String color = "yellow";
    private String flavor = "sweet";

    public static String getColor() {
        return color;
    }

    private String getFlavor() {
        return flavor;
    }

    public String getBananaInfo() {
        return "Color " + color + " Flavor " + getFlavor();
    }

    public final boolean isLike() {
        return true;
    }
}
