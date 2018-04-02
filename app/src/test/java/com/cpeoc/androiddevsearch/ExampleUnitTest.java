package com.cpeoc.androiddevsearch;

import android.support.annotation.IntDef;

import com.cpeoc.androiddevsearch.util.DateUtil;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(Parameterized.class)
public class ExampleUnitTest {
//    public static final int ADD = 1;
//    public static final int SUB = 2;
//    @IntDef({ADD, SUB})
//    public @interface Operation {
//
//    }
//    @Operation int operation;
//    public void operation(@Operation int operation) {
//        System.out.println("operation " + operation);
//    }

    private Date mDate;
    private long mLongDate = 1521771266000L;
    private String mStringDate;

    public ExampleUnitTest(String stringDate) {
        this.mStringDate = stringDate;
    }

    @Rule
    public MyRule mRule = new MyRule();

    @Before
    public void before() {
        mDate = new Date(mLongDate);
    }

    @After
    public void after() {
//        operation(2);
//        operation = ADD;
//        operation(operation);
    }

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.stream(new String[] {
                "2018-03-21 10:14:26",
                "2018-03-22 10:14:26",
                "2018-03-23 10:14:26",
                "2018-03-24 10:14:26"
        }).collect(Collectors.toList());
    }

    @Test
    public void testDateUtil() throws Exception {
//        assertEquals(mStringDate, DateUtil.stampToDate(mDate.getTime()));
//        assertEquals(mLongDate, DateUtil.dateToStamp(mStringDate));
        assertThat("not equal", mLongDate, equalTo(DateUtil.dateToStamp(mStringDate)));
    }
}