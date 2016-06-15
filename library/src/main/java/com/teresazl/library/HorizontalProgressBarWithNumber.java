package com.teresazl.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

/**
 * Created by teresa on 2016-6-14.
 */
public class HorizontalProgressBarWithNumber extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
    private static final int DEFAULT_REACHED_COLOR = DEFAULT_TEXT_COLOR;
    private static final int DEFAULT_REACHED_HEIGHT = 2;
    private static final int DEFAULT_UNREACHED_COLOR = 0xFFd3d6da;
    private static final int DEFAULT_UNREACHED_HEIGHT = 2;
    private static final int DEFAULT_SIZE_TEXT_PADDING = 10;

    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected int mTextPadding = dp2px(DEFAULT_SIZE_TEXT_PADDING);
    protected int mReachedColor = DEFAULT_REACHED_COLOR;
    protected int mReachedHeight = dp2px(DEFAULT_REACHED_HEIGHT);
    protected int mUnReachedColor = DEFAULT_UNREACHED_COLOR;
    protected int mUnReachedHeight = dp2px(DEFAULT_UNREACHED_HEIGHT);

    protected Paint mPaint = new Paint();
    protected int mRealWidth;

    public HorizontalProgressBarWithNumber(Context context) {
        this(context, null);
    }

    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        obtainStyledAttrs(attrs);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        boolean isPaintUnReached = true;
        float radio = getProgress() * 1.0f / getMax();
        float progressPosX = radio * mRealWidth;

        String text = getProgress() + "%";
        int textWidth = (int) mPaint.measureText(text);

        if (progressPosX + textWidth > mRealWidth) {
            progressPosX = mRealWidth - textWidth;
        }

        if (progressPosX + textWidth + mTextPadding > mRealWidth) {
            isPaintUnReached = false;
        }

        // 画已经完成的进度
        float endX = progressPosX - mTextPadding;
        if (endX > 0) {
            mPaint.setColor(mReachedColor);
            mPaint.setStrokeWidth(mReachedHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        // 画文字
        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent() + mPaint.ascent()) / 2);
        canvas.drawText(text, progressPosX, y, mPaint);

        // 画还未完成的进度
        if (isPaintUnReached) {
            mPaint.setColor(mUnReachedColor);
            mPaint.setStrokeWidth(mUnReachedHeight);
            canvas.drawLine(progressPosX + textWidth + mTextPadding, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);

        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 测量高度
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            // MeasureSpec.UNSPECIFIED
            float textHeight = mPaint.descent() - mPaint.ascent();
            result = (int) (getPaddingTop() + getPaddingBottom() + Math.max(Math.max(mUnReachedHeight, mReachedHeight), Math.abs(textHeight)));

            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    /**
     * 从资源文件获取数据
     *
     * @param attrs
     */
    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBarWithNumber);

        mTextColor = typedArray
                .getColor(R.styleable.HorizontalProgressBarWithNumber_progress_text_color, DEFAULT_TEXT_COLOR);

        mTextSize = (int) typedArray
                .getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_size, mTextSize);

        mTextPadding = (int) typedArray
                .getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_padding, mTextPadding);

        mReachedColor = typedArray
                .getColor(R.styleable.HorizontalProgressBarWithNumber_progress_reached_color, mTextColor);

        mReachedHeight = (int) typedArray
                .getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_reached_height, mReachedHeight);

        mUnReachedColor = typedArray
                .getColor(R.styleable.HorizontalProgressBarWithNumber_progress_unreached_color, DEFAULT_UNREACHED_COLOR);

        mUnReachedHeight = (int) typedArray
                .getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_unreached_height, mUnReachedHeight);

        typedArray.recycle();
    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());

    }

}
