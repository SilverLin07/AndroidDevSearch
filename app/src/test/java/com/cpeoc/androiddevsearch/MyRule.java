package com.cpeoc.androiddevsearch;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-23 11:01
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class MyRule implements TestRule {
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String methodName = description.getMethodName();
                System.out.println(methodName + " begin");
                base.evaluate();
                System.out.println(methodName + " end");
            }
        };
    }
}
