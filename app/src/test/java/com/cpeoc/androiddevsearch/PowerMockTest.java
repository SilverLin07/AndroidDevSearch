package com.cpeoc.androiddevsearch;

import com.cpeoc.androiddevsearch.fruit.Banana;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.powermock.reflect.Whitebox;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-23 16:10
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */

//@RunWith(PowerMockRunner.class)
//@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
public class PowerMockTest {

    @Rule
    public PowerMockRule mRule = new PowerMockRule();

    @Test
    @PrepareForTest({Banana.class})
    public void testStaticMethod() {
        PowerMockito.mockStatic(Banana.class);
        Mockito.when(Banana.getColor()).thenReturn("green");
        Whitebox.setInternalState(Banana.class, "color", "yellow");
        Assert.assertEquals("yellow", Banana.getColor());
    }

    @Test
    @PrepareForTest({Banana.class})
    public void testPrivateMethod() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenCallRealMethod();
        PowerMockito.when(mBanana, "getFlavor").thenReturn("sweet");
        Assert.assertEquals("Color yellow Flavor sweet", mBanana.getBananaInfo());
        PowerMockito.verifyPrivate(mBanana).invoke("getFlavor");
    }

    @Test
    @PrepareForTest({Banana.class})
    public void testSkipMethod() throws Exception {
        Banana banana = new Banana();
//        PowerMockito.suppress(PowerMockito.method(Banana.class, "getFlavor"));
        MemberModifier.field(Banana.class, "flavor").set(banana, "sour");
        Assert.assertEquals("Color yellow Flavor sour", banana.getBananaInfo());
    }

    @Test
    @PrepareForTest({Banana.class})
    public void testFinalMethod() {
        Banana banana = PowerMockito.mock(Banana.class);
        PowerMockito.when(banana.isLike()).thenReturn(false);
        Assert.assertFalse(banana.isLike());
    }

    @Test
    @PrepareForTest({Banana.class})
    public void testNewClass() throws Exception{
        Banana banana = PowerMockito.mock(Banana.class);
        PowerMockito.when(banana.getName()).thenReturn("banana");
        PowerMockito.whenNew(Banana.class).withNoArguments().thenReturn(banana);
        Banana mBanana = new Banana();
        Assert.assertEquals("banana", mBanana.getName());
    }
}
