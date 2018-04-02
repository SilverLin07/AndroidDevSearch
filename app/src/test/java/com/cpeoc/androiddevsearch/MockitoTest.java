package com.cpeoc.androiddevsearch;

import com.cpeoc.androiddevsearch.bean.Contributor;
import com.cpeoc.androiddevsearch.bean.Manager;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-23 14:43
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class MockitoTest {
    @Spy
    Contributor mContributor2;
    @Mock
    Contributor mContributor;
    @Spy
    Contributor mContributor1;

    @InjectMocks
    Manager mManager;

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    public void stub() {
//        doReturn("www.google.com").when(mContributor).getName();
        when(mContributor.getId(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return "print " + invocation.getArguments()[0].toString();
            }
        });
        when(mContributor.getName()).thenReturn("C");
        when(mContributor1.getName()).thenReturn("C1");
        when(mContributor2.getName()).thenReturn("C2");
    }

//    @Test
//    public void testIsNotNull() {
//        stub();
//
////        assertNotNull(mContributor);
////        assertEquals(mContributor.getName(), "www.google.com");
////        System.out.println("" + mContributor.getName());
//        System.out.println(mContributor.getId("me"));
//    }

//    @Test
//    public void testVerifyAfter() {
//        mContributor.getId("me");
//        mContributor.getId("you");
//        mContributor.getId("you");
//        verify(mContributor, after(1000)).getId("me");
//        verify(mContributor, atLeast(2)).getId("you");
//    }

    @Test
    public void testInOrder() {
//        System.out.println(mContributor1.getName());
//        mContributor1.getId("");
//        InOrder mInorder = inOrder(mContributor1, mContributor2);
//        mInorder.verify(mContributor1).getName();
//        mInorder.verify(mContributor1).getId("");

        stub();
        System.out.println(mManager.getName());
    }
}
