package com.zwonb.rvitemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

/**
 * Created by zyb on 2017/6/24.
 */

public class RVItemGroup extends RecyclerView.ItemDecoration {

    private Paint mDividerPaint;
    private Paint mGroupPaint;
    private Paint mLetterPaint;
    private List<String> letters;
    private static final int dividerHeight = 1;
    private static final int groupHeight = 50;
    private final float baseline;

    public RVItemGroup(List<String> letters) {
        this.letters = letters;

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mDividerPaint.setColor(0xFFCCCCCC);
        mGroupPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mGroupPaint.setColor(0xFFEBEBEB);
        mLetterPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mLetterPaint.setColor(0xFF333333);
        mLetterPaint.setTextSize(40);
        Paint.FontMetrics fontMetrics = mLetterPaint.getFontMetrics();
        baseline = (groupHeight - fontMetrics.bottom - fontMetrics.top) / 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isDifferentLetter(position)) {
            outRect.top = groupHeight;
        } else {
            outRect.top = dividerHeight;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            int bottom = view.getTop();
            if (isDifferentLetter(position)) {
                int top = view.getTop() - groupHeight;
                //画分组的第一个
                c.drawRect(left, top, right, bottom, mGroupPaint);
                c.drawText(letters.get(position), 40, bottom + baseline - groupHeight, mLetterPaint);
            } else {
                int top = view.getTop() - dividerHeight;
                //画分割线
                c.drawRect(left, top, right, bottom, mDividerPaint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top = parent.getPaddingTop();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            //i=0第一个可见的item
            if (i == 0) {

                if (isLastItem(position)) {
                    int changeBottom = view.getBottom() - groupHeight;
                    //最后一个item的底部在悬浮的底部上面时，重新计算坐标
                    if (changeBottom < top) {
                        top = changeBottom;
                    }
                }
                int bottom = top + groupHeight;

                c.drawRect(left, top, right, bottom, mGroupPaint);
                c.drawText(letters.get(position), 40, bottom + baseline - groupHeight, mLetterPaint);
            }
        }
    }

    /**
     * 判断是否是组内的最后一个
     */
    private boolean isDifferentLetter(int position) {
        if (position == 0) {
            return true;
        } else {
            String presentLetter = letters.get(position);
            String priorLetter = letters.get(position - 1);
            return !TextUtils.equals(presentLetter, priorLetter);
        }
    }

    /**
     * 判断是否是组内的最后一个item
     */
    private boolean isLastItem(int position) {
        letters.add(letters.get(letters.size() - 1));
        String presentLetter = letters.get(position);
        String nextLetter = letters.get(position + 1);
        return !TextUtils.equals(presentLetter, nextLetter);
    }
}
