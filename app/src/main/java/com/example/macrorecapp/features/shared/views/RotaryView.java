package com.example.macrorecapp.features.shared.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.macrorecapp.R;


public class RotaryView extends View {
    private static final String TAG = "RotaryView";

    // Status
    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_START_RADIAN = "status_start_radian";
    private static final String STATUS_FINISH_RADIAN = "status_finish_radian";

    // Default dimension in dp/pt
    private static final float DEFAULT_GAP_BETWEEN_CIRCLE_AND_LINE = 20;
    private static final float DEFAULT_CIRCLE_BUTTON_RADIUS = 8;
    private static final float DEFAULT_CIRCLE_STROKE_WIDTH = 24;
    private static final float DEFAULT_TIMER_NUMBER_SIZE = 38;
    private static final float DEFAULT_TOTAL_MOVE_TEXT_SIZE = 40;
    private static final float DEFAULT_DIRECTION_TEXT_SIZE = 15;
    private static final float DEFAULT_SUBTEXT_SIZE = 12;

    // Default color
    private static final int DEFAULT_CIRCLE_COLOR = 0xFF6E6E6E;
    private static final int DEFAULT_SUBTEXT_COLOR = 0xFF6E6E6E;
    private static final int DEFAULT_LINE_COLOR = 0xFFB7072D;
    private static final int DEFAULT_TOTAL_MOVE_NUMBER_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_DIRECTION_COLOR = 0x00FA7777;
    private static final int DEFAULT_DIRECTION_TEXT_COLOR = 0xFFFFFFFF;

    // Paint
    private Paint mCirclePaint;
    private Paint mLinePaint;
    private Paint mTotalMovePaint;
    private Paint mTotalMoveTextPaint;
    private Paint mDirectionPaint;
    private Paint mDirectionTextPaint;
    private Paint mSubtextPaint;

    // Dimension
    private float mGapBetweenCircleAndLine;
    private float mCircleButtonRadius;
    private float mCircleStrokeWidth;
    private float mTotalMoveSize;
    private float mTotalMoveTextSize;
    private float mDirectionTextSize;
    private float mSubtextSize;

    // Color
    private int mCircleColor;
    private int mLineColor;
    private int mTotalMoveNumberColor;
    private int mDirectionTextColor;
    private int mSubtextColor;

    // Parameters
    private float mCx;
    private float mCy;
    private float mRadius;
    private float mCurrentCamStartRadian;
    private float mCurrentCamFinishRadian;
    private float mPreRadian;
    private boolean mInCamStartThumb;
    private boolean mInCamFinishThumb;
    private boolean mInDirectionIcon;
    private int camStartDegree;
    private int camFinishDegree;
    private boolean ismInCamStartThumb;
    private int mCurrentStartDegree; // seconds
    private int mCurrentFinishDegree; // seconds
    private boolean mClockwise;

    Bitmap bitmapStartCam;
    Bitmap bitmapFinishCam;
    Bitmap bitmapCw;
    Bitmap bitmapCcw;
    Matrix flipHorizontalMatrix;

    public RotaryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    public RotaryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotaryView(Context context) {
        this(context, null);
    }

    private void initialize(Context context, AttributeSet attrs) {

        // Attribute initialization
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RotaryView, 0, 0);

        Log.d(TAG, " initializing...");
        // Set default dimension or read xml attributes
        mGapBetweenCircleAndLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_GAP_BETWEEN_CIRCLE_AND_LINE,
                getContext().getResources().getDisplayMetrics());
        mCircleButtonRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_BUTTON_RADIUS, getContext()
                .getResources().getDisplayMetrics());
        mCircleStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_STROKE_WIDTH, getContext()
                .getResources().getDisplayMetrics());
        mTotalMoveSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TIMER_NUMBER_SIZE, getContext()
                .getResources().getDisplayMetrics());
        mTotalMoveTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TOTAL_MOVE_TEXT_SIZE, getContext()
                .getResources().getDisplayMetrics());
        mDirectionTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DIRECTION_TEXT_SIZE, getContext()
                .getResources().getDisplayMetrics());
        mSubtextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_SUBTEXT_SIZE, getContext()
                .getResources().getDisplayMetrics());
        mClockwise = a.getBoolean(R.styleable.RotaryView_isClockwise, mClockwise);

        // Set default color or read xml attributes
        mCircleColor = DEFAULT_CIRCLE_COLOR;
        mLineColor = DEFAULT_LINE_COLOR;
        mTotalMoveNumberColor = DEFAULT_TOTAL_MOVE_NUMBER_COLOR;
        mDirectionTextColor = DEFAULT_DIRECTION_TEXT_COLOR;
        mSubtextColor = DEFAULT_SUBTEXT_COLOR;

        // Init all paints
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTotalMovePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTotalMoveTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDirectionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDirectionTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSubtextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // CirclePaint
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);
        DashPathEffect dashPath1 = new DashPathEffect(new float[]{8,24}, (float)1.0);
        mCirclePaint.setPathEffect(dashPath1);

        // LinePaint
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mCircleButtonRadius * 2 + 8);
        mLinePaint.setStyle(Paint.Style.STROKE);

        // TotalMovePaint
        mTotalMovePaint.setColor(mTotalMoveNumberColor);
        mTotalMovePaint.setTextAlign(Paint.Align.CENTER);
        mTotalMovePaint.setTextSize(mTotalMoveSize);

        // TotalMoveTextPaint
        mTotalMoveTextPaint.setColor(mDirectionTextColor);
        mTotalMoveTextPaint.setTextAlign(Paint.Align.CENTER);
        mTotalMoveTextPaint.setTextSize(mTotalMoveTextSize);

        // DirectionPaint
        mDirectionPaint.setColor(DEFAULT_DIRECTION_COLOR);
        mDirectionPaint.setTextAlign(Paint.Align.CENTER);
        mDirectionPaint.setTextSize(mTotalMoveSize);

        // DirectionTextPaint
        mDirectionTextPaint.setColor(mDirectionTextColor);
        mDirectionTextPaint.setTextAlign(Paint.Align.CENTER);
        mDirectionTextPaint.setTextSize(mDirectionTextSize);

        // SubtextPaint
        mSubtextPaint.setColor(mSubtextColor);
        mSubtextPaint.setTextAlign(Paint.Align.CENTER);
        mSubtextPaint.setTextSize(mSubtextSize);

        // Optionally, these values can be set according to XML attributes.
        mCurrentCamStartRadian = (float) Math.toRadians(315);
        mCurrentCamFinishRadian = (float) Math.toRadians(45);
        camStartDegree = 315;
        camFinishDegree = 45;

        bitmapStartCam = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cam_start);
        bitmapFinishCam = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cam_finish);

        // Not using a separate resource for ccw icon. Will programmatically flip horizontally.
        bitmapCw = BitmapFactory.decodeResource(getResources(),R.drawable.ic_ccw_medium);
        // A matrix to multiply, flips horizontally.
        flipHorizontalMatrix = new Matrix();
        flipHorizontalMatrix.setScale(-1,1);
        bitmapCcw = Bitmap.createBitmap(bitmapCw, 0, 0, bitmapCw.getWidth(), bitmapCw.getHeight(), flipHorizontalMatrix, true);


        // Solve the target version related to shadow
        // Using this, since targetSdkVersion (16) is greater than or equal to aPI 14.
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.i(TAG,"Drawing...");
        canvas.save();

        canvas.drawCircle(mCx, mCy, mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine, mCirclePaint);
        canvas.save();
        canvas.rotate(-90, mCx, mCy);
        canvas.restore();
        canvas.save();

        // Draw Subtexts.
        canvas.drawText("Total Move", mCx, mCy, mSubtextPaint);
        canvas.save();
        canvas.drawText("Active Direction", mCx, mCy + bitmapCw.getHeight() + getFontHeight(mDirectionPaint) + getFontHeight(mSubtextPaint), mSubtextPaint);
        canvas.save();

        if (!ismInCamStartThumb) {
            canvas.rotate((float) Math.toDegrees(mCurrentCamStartRadian), mCx, mCy);
            canvas.drawBitmap(bitmapStartCam, mCx-bitmapStartCam.getWidth()/2f,getMeasuredHeight()/2f - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine+bitmapStartCam.getHeight()/2f, null);
            canvas.restore();
            canvas.save();
            canvas.rotate((float) Math.toDegrees(mCurrentCamFinishRadian), mCx, mCy);
            canvas.drawBitmap(bitmapFinishCam, mCx-bitmapFinishCam.getWidth()/2f,getMeasuredHeight()/2f - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine+bitmapFinishCam.getHeight()/2f, null);
            canvas.restore();
            canvas.save();
        } else {
            canvas.rotate((float) Math.toDegrees(mCurrentCamStartRadian), mCx, mCy);
            canvas.drawBitmap(bitmapStartCam, mCx-bitmapStartCam.getWidth()/2f, getMeasuredHeight()/2f - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine+bitmapStartCam.getHeight()/2f,  null);
            canvas.restore();
            // TimerNumber
            canvas.save();
            canvas.rotate((float) Math.toDegrees(mCurrentCamFinishRadian), mCx, mCy);
            canvas.drawBitmap(bitmapFinishCam, mCx-bitmapFinishCam.getWidth()/2f, getMeasuredHeight()/2f - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine+bitmapFinishCam.getHeight()/2f, null);
            canvas.restore();
            // TimerNumber
            canvas.save();
        }

        // The total move value to be shown in the center.
        int x = (camFinishDegree-camStartDegree);
        int i;
        if (!mClockwise){
            i = x<0 ? -x : 360-x;
        }
        else{
            i = x>0 ? 360-x : -x;
            i = 360 - i;
        }
        // Draw total move text.
        canvas.drawText((i) + "" + (char) 0x00B0, mCx, mCy - getFontHeight(mTotalMovePaint) / 2, mTotalMovePaint);
        // Draw direction icon.
        if(mClockwise){
            // Note that the drawable resource shows counter clockwise direction by default.
            flipHorizontalMatrix.postTranslate(bitmapCw.getWidth(),0);
            canvas.drawBitmap(bitmapCcw, mCx - bitmapCw.getWidth()/2f, mCy + getFontHeight(mSubtextPaint), null);
        }
        else{
            canvas.drawBitmap(bitmapCw, mCx - bitmapCw.getWidth()/2f, mCy + getFontHeight(mSubtextPaint), null);
        }
        // Draw direction text.
        if(mClockwise){
            canvas.drawText("CW", mCx, mCy + bitmapCw.getHeight() + getFontHeight(mDirectionPaint) / 2 + getFontHeight(mSubtextPaint), mDirectionTextPaint);
        }
        else{
            canvas.drawText("CCW", mCx, mCy + bitmapCw.getHeight() + getFontHeight(mDirectionPaint) / 2 + getFontHeight(mSubtextPaint), mDirectionTextPaint);
        }
        canvas.restore();
        super.onDraw(canvas);
    }

    private float getFontHeight(Paint paint) {
        // FontMetrics sF = paint.getFontMetrics();
        // return sF.descent - sF.ascent;
        Rect rect = new Rect();
        paint.getTextBounds("1", 0, 1, rect);
        return rect.height();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // If the point in the circle button
                if (mInCamStartThumb(event.getX(), event.getY()) && isEnabled()) {
                    mInCamStartThumb = true;
                    // Reset ismInCamStartThumb value that was set true for further use.
                    ismInCamStartThumb = false;
                    mPreRadian = getRadian(event.getX(), event.getY());
                    Log.d(TAG, "Motion in camera start.");
                } else if (mInCamFinishThumb(event.getX(), event.getY()) && isEnabled()) {
                    mInCamFinishThumb = true;
                    ismInCamStartThumb = true;
                    mPreRadian = getRadian(event.getX(), event.getY());
                    Log.d(TAG, "Motion in camera finish.");
                }
                // If the point in the direction icon
                if (mInDirectionIcon(event.getX(), event.getY()) && isEnabled()) {
                    mInDirectionIcon = true;
                    Log.d(TAG, "Motion in direction icon.");
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (mInCamStartThumb && isEnabled()) {
                    float temp = getRadian(event.getX(), event.getY());
                    if (mPreRadian > Math.toRadians(270) && temp < Math.toRadians(90)) {
                        mPreRadian -= 2 * Math.PI;
                    } else if (mPreRadian < Math.toRadians(90) && temp > Math.toRadians(270)) {
                        mPreRadian = (float) (temp + (temp - 2 * Math.PI) - mPreRadian);
                    }
                    mCurrentCamStartRadian += (temp - mPreRadian);
                    mPreRadian = temp;
                    if (mCurrentCamStartRadian > 2 * Math.PI) {
                        mCurrentCamStartRadian -= (float) (2 * Math.PI);
                    }
                    if (mCurrentCamStartRadian < 0) {
                        mCurrentCamStartRadian += (float) (2 * Math.PI);
                    }
                    mCurrentStartDegree = (int) (360 / (2 * Math.PI) * mCurrentCamStartRadian);
                    camStartDegree = mCurrentStartDegree;
                    invalidate();
                } else if (mInCamFinishThumb && isEnabled()) {
                    float temp = getRadian(event.getX(), event.getY());
                    if (mPreRadian > Math.toRadians(270) && temp < Math.toRadians(90)) {
                        mPreRadian -= 2 * Math.PI;
                    } else if (mPreRadian < Math.toRadians(90) && temp > Math.toRadians(270)) {
                        mPreRadian = (float) (temp + (temp - 2 * Math.PI) - mPreRadian);
                    }
                    mCurrentCamFinishRadian += (temp - mPreRadian);
                    mPreRadian = temp;
                    if (mCurrentCamFinishRadian > 2 * Math.PI) {
                        mCurrentCamFinishRadian -= (float) (2 * Math.PI);
                    }
                    if (mCurrentCamFinishRadian < 0) {
                        mCurrentCamFinishRadian += (float) (2 * Math.PI);
                    }
                    mCurrentFinishDegree = (int) (360 / (2 * Math.PI) * mCurrentCamFinishRadian);
                    camFinishDegree = mCurrentFinishDegree;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mInCamStartThumb && isEnabled()) {
                    mInCamStartThumb = false;
                } else if (mInCamFinishThumb && isEnabled()) {
                    mInCamFinishThumb = false;
                }
                if (mInDirectionIcon && isEnabled()) {
                    mInDirectionIcon = false;
                    mClockwise = !mClockwise;
                    Log.d(TAG, "Finger up from direction icon.");
                    invalidate();
                }
                break;
        }
        return true;
    }

    // Whether the down event inside circle button
    private boolean mInCamStartThumb(float x, float y) {
        float r = mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine;
        float x2 = (float) (mCx + r * Math.sin(mCurrentCamStartRadian));
        float y2 = (float) (mCy - r * Math.cos(mCurrentCamStartRadian));
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < mCircleButtonRadius*7) {
            return true;
        }
        return false;
    }

    // Whether the down event inside circle button
    private boolean mInCamFinishThumb(float x, float y) {
        float r = mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine;
        float x2 = (float) (mCx + r * Math.sin(mCurrentCamFinishRadian));
        float y2 = (float) (mCy - r * Math.cos(mCurrentCamFinishRadian));
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < mCircleButtonRadius*4) {
            return true;
        }
        return false;
    }

    // whether the down even inside direction icon
    private boolean mInDirectionIcon(float x, float y) {
        float left = mCx - bitmapCw.getWidth();
        float right = mCx + bitmapCw.getWidth();
        float top = mCy;
        float bottom = mCy + bitmapCw.getHeight() + getFontHeight(mDirectionTextPaint) + 2 * getFontHeight(mSubtextPaint);
        if(left<x && x<right && top<y && y<bottom) {
            return true;
        }
        return false;
    }

    // Use tri to cal radian
    private float getRadian(float x, float y) {
        float alpha = (float) Math.atan((x - mCx) / (mCy - y));
        // Quadrant
        if (x > mCx && y > mCy) {
            // 2
            alpha += Math.PI;
        } else if (x < mCx && y > mCy) {
            // 3
            alpha += Math.PI;
        } else if (x < mCx && y < mCy) {
            // 4
            alpha = (float) (2 * Math.PI + alpha);
        }
        return alpha;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Ensure width = height
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.mCx = width / 2;
        this.mCy = height / 2;
        // Radius
        if (mGapBetweenCircleAndLine + mCircleStrokeWidth >= mCircleButtonRadius) {
            this.mRadius = width / 2f - mCircleStrokeWidth / 2;
            this.mRadius = mRadius * 9 / 10;
        } else {
            this.mRadius = width / 2f - (mCircleButtonRadius - mGapBetweenCircleAndLine -
                    mCircleStrokeWidth / 2);
            this.mRadius = mRadius * 9 / 10;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_START_RADIAN, mCurrentCamStartRadian);
        bundle.putFloat(STATUS_FINISH_RADIAN, mCurrentCamFinishRadian);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            mCurrentCamStartRadian = bundle.getFloat(STATUS_START_RADIAN);
            mCurrentCamFinishRadian = bundle.getFloat(STATUS_FINISH_RADIAN);
            mCurrentStartDegree = (int) (60 / (2 * Math.PI) * mCurrentCamStartRadian * 60);
            mCurrentFinishDegree = (int) (60 / (2 * Math.PI) * mCurrentCamFinishRadian * 60);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

}