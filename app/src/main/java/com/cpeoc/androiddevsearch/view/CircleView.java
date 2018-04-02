package com.cpeoc.androiddevsearch.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.cpeoc.androiddevsearch.R;
import com.cpeoc.androiddevsearch.util.Utils;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-01 10:07
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class CircleView extends View {
    private int mColor;
    private float mRadius;
    private Paint mPaint;
    private Scroller scroller;
    private int mLastX, mLastY;
    private int mX = 0, mY = 0;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.BLACK);
        mRadius = a.getDimension(R.styleable.CircleView_radius, (int)Utils.dp2px(context, 100));
        a.recycle();
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.BLACK);
        mRadius = a.getDimension(R.styleable.CircleView_radius, (int)Utils.dp2px(context, 100));
        a.recycle();
        init();
    }

    public void init() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int mWidth = (int)Utils.dp2px(getContext(), 200);
        int mHeight = (int)Utils.dp2px(getContext(), 200);
        if (widthSpecMode == EXACTLY) {
            mWidth = widthSpecSize;
        }
        if (heightSpecMode == EXACTLY) {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        float radius = Math.min(width, height) / 2;
        radius = mRadius > radius ? radius : mRadius;
        canvas.drawCircle(width / 2 + paddingLeft, height /2 + paddingTop, radius, mPaint);
    }

//    public void smoothScrollTo(int destX, int destY) {
//        int scrollX = getScrollX();
//        int delta = destX - scrollX;
//
//        //1000ms内滑向destX
//        scroller.startScroll(scrollX, 0, delta, 0, 1000);
//    }
//
//    @Override
//    public void computeScroll() {
//        super.computeScroll();
//        if (scroller.computeScrollOffset()) {
//            scrollTo(scroller.getCurrX(), scroller.getCurrY());
//            postInvalidate();
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                ObjectAnimator.ofFloat(this, "translationX", mX, mX + deltaX).start();
                ObjectAnimator.ofFloat(this, "translationY", mY, mY + deltaY).start();
                mX += deltaX;
                mY += deltaY;
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }
}
