package com.zwonb.rvitemdecoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zyb on 2017/6/23.
 */

public class RVItemDivider extends RecyclerView.ItemDecoration {

    private final int dividerHeight;
    private final Paint mPaint;
    private final Bitmap bitmap;

    public RVItemDivider(Context mContext) {
        dividerHeight = 1;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.BLACK);

        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_photo);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position != 0) {
            outRect.top = 1;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        //第一个不画，i从1开始
        for (int i = 1; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int top = view.getTop() + parent.getTop() - dividerHeight;
            int bottom = view.getTop();
            //画上分割线
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        //画在item view上面
        for (int i = 0; i < 3; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            //只画前三个item view
            if (position < 3) {
                c.drawBitmap(bitmap, view.getLeft(), view.getTop(), mPaint);
            }
        }
    }
}
