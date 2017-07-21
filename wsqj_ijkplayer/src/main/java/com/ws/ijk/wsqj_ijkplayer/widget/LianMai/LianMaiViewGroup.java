package com.ws.ijk.wsqj_ijkplayer.widget.LianMai;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by WangShuo on 2017/7/20.
 */

public class LianMaiViewGroup extends FrameLayout {

    private ViewDragHelper mDragger;
    private View mLMvideoGroup;

    public LianMaiViewGroup(@NonNull Context context) {
        super(context);
        init();
    }

    public LianMaiViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LianMaiViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDragger = ViewDragHelper.create(this, 1f, new MyHelperCallback());
        setBackgroundColor(Color.TRANSPARENT);
    }



    private class MyHelperCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx)
        {
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - mLMvideoGroup.getWidth() - leftBound;

            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy)
        {
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - mLMvideoGroup.getHeight() - topBound;

            final int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }

        @Override
        public int getViewHorizontalDragRange(View child)
        {
            return getMeasuredWidth()-child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child)
        {
            return getMeasuredHeight()-child.getMeasuredHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll()
    {
        if(mDragger.continueSettling(true))
        {
            invalidate();
        }
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLMvideoGroup=getChildAt(0);
    }
}
