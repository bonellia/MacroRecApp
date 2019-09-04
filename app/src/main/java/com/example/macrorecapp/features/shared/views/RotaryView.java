package com.example.macrorecapp.features.shared.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
    private static final String STATUS_RADIAN = "status_radian";

    // Default dimension in dp/pt
    private static final float DEFAULT_GAP_BETWEEN_CIRCLE_AND_LINE = 20;
    private static final float DEFAULT_NUMBER_SIZE = 10;
    private static final float DEFAULT_LINE_WIDTH = 0.5f;
    private static final float DEFAULT_CIRCLE_BUTTON_RADIUS = 12;
    private static final float DEFAULT_CIRCLE_STROKE_WIDTH = 1;
    private static final float DEFAULT_TIMER_NUMBER_SIZE = 38;
    private static final float DEFAULT_TIMER_TEXT_SIZE = 18;

    // Default color
    private static final int DEFAULT_CIRCLE_COLOR = 0xFFE9E2D9;
    private static final int DEFAULT_CIRCLE_BUTTON_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_LINE_COLOR = 0xFFB7072D;
    private static final int DEFAULT_HIGHLIGHT_LINE_COLOR = 0xFF68C5D7;
    private static final int DEFAULT_NUMBER_COLOR = 0xFF181318;
    private static final int DEFAULT_TIMER_NUMBER_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_TIMER_COLON_COLOR = 0xFFFA7777;
    private static final int DEFAULT_TIMER_TEXT_COLOR = 0x99F0F9FF;

    // Paint
    private Paint mCirclePaint;
    private Paint mHighlightLinePaint;
    private Paint mLinePaint;
    private Paint mCircleButtonPaint;
    private Paint mNumberPaint;
    private Paint mTimerNumberPaint;
    private Paint mTimerTextPaint;
    private Paint mTimerColonPaint;

    // Dimension
    private float mGapBetweenCircleAndLine;
    private float mNumberSize;
    private float mLineWidth;
    private float mCircleButtonRadius;
    private float mCircleStrokeWidth;
    private float mTimerNumberSize;
    private float mTimerTextSize;

    // Color
    private int mCircleColor;
    private int mCircleButtonColor;
    private int mLineColor;
    private int mHighlightLineColor;
    private int mNumberColor;
    private int mTimerNumberColor;
    private int mTimerTextColor;

    // Parameters
    private float mCx;
    private float mCy;
    private float mRadius;
    private float mCurrentCamStartRadian;
    private float mCurrentCamFinishRadian;
    private float mPreRadian;
    private boolean mInCamStartThumb;
    private boolean mInCamFinishThumb;
    private int camStartDegree;
    private int camFinishDegree;
    private boolean ismInCamStartThumb;
    private int mCurrentTime; // seconds
    private boolean mClockwise;

    private OnTimeChangedListener mListener;

    Bitmap bitmapStartCam;
    Bitmap bitmapFinishCam;

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

        Log.d(TAG, "initialize");
        // Set default dimension or read xml attributes
        mGapBetweenCircleAndLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_GAP_BETWEEN_CIRCLE_AND_LINE,
                getContext().getResources().getDisplayMetrics());
        mNumberSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_NUMBER_SIZE, getContext().getResources()
                .getDisplayMetrics());
        mLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_LINE_WIDTH, getContext().getResources()
                .getDisplayMetrics());
        mCircleButtonRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_BUTTON_RADIUS, getContext()
                .getResources().getDisplayMetrics());
        mCircleStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_STROKE_WIDTH, getContext()
                .getResources().getDisplayMetrics());
        mTimerNumberSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TIMER_NUMBER_SIZE, getContext()
                .getResources().getDisplayMetrics());
        mTimerTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TIMER_TEXT_SIZE, getContext()
                .getResources().getDisplayMetrics());
        mClockwise = a.getBoolean(R.styleable.RotaryView_isClockwise,
                mClockwise);

        // Set default color or read xml attributes
        mCircleColor = DEFAULT_CIRCLE_COLOR;
        mCircleButtonColor = DEFAULT_CIRCLE_BUTTON_COLOR;
        mLineColor = DEFAULT_LINE_COLOR;
        mHighlightLineColor = DEFAULT_HIGHLIGHT_LINE_COLOR;
        mNumberColor = DEFAULT_NUMBER_COLOR;
        mTimerNumberColor = DEFAULT_TIMER_NUMBER_COLOR;
        mTimerTextColor = DEFAULT_TIMER_TEXT_COLOR;

        // Init all paints
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighlightLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNumberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimerNumberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimerTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimerColonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // CirclePaint
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);

        // CircleButtonPaint
        mCircleButtonPaint.setColor(mCircleButtonColor);
        mCircleButtonPaint.setAntiAlias(true);
        mCircleButtonPaint.setStyle(Paint.Style.FILL);

        // LinePaint
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mCircleButtonRadius * 2 + 8);
        mLinePaint.setStyle(Paint.Style.STROKE);
        DashPathEffect dashPath = new DashPathEffect(new float[]{10,10}, (float)1.0);
        mLinePaint.setPathEffect(dashPath);

        // HighlightLinePaint
        mHighlightLinePaint.setColor(mHighlightLineColor);
        mHighlightLinePaint.setStrokeWidth(mLineWidth);

        // NumberPaint
        mNumberPaint.setColor(mNumberColor);
        mNumberPaint.setTextSize(mNumberSize);
        mNumberPaint.setTextAlign(Paint.Align.CENTER);
        mNumberPaint.setStyle(Paint.Style.STROKE);
        mNumberPaint.setStrokeWidth(mCircleButtonRadius * 2 + 8);

        // TimerNumberPaint
        mTimerNumberPaint.setColor(mTimerNumberColor);
        mTimerNumberPaint.setTextSize(mTimerNumberSize);
        mTimerNumberPaint.setTextAlign(Paint.Align.CENTER);

        // TimerTextPaint
        mTimerTextPaint.setColor(mTimerTextColor);
        mTimerTextPaint.setTextSize(mTimerTextSize);
        mTimerTextPaint.setTextAlign(Paint.Align.CENTER);

        // TimerColonPaint
        mTimerColonPaint.setColor(DEFAULT_TIMER_COLON_COLOR);
        mTimerColonPaint.setTextAlign(Paint.Align.CENTER);
        mTimerColonPaint.setTextSize(mTimerNumberSize);

        bitmapStartCam = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cam_start);
        bitmapFinishCam = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cam_finish);

        // Solve the target version related to shadow
        // setLayerType(View.LAYER_TYPE_SOFTWARE, null); // use this, when targetSdkVersion is greater than or equal to api 14
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        canvas.drawCircle(mCx, mCy, mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine, mNumberPaint);
        canvas.save();
        canvas.rotate(-90, mCx, mCy);
        RectF rect = new RectF(mCx - (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine
        ), mCy - (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine
        ), mCx + (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine
        ), mCy + (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine
        ));

        if (mCurrentCamFinishRadian > mCurrentCamStartRadian) {
            canvas.drawArc(rect, (float) Math.toDegrees(mCurrentCamFinishRadian), (float) Math.toDegrees(2 * (float) Math.PI) - (float) Math.toDegrees(mCurrentCamFinishRadian) + (float) Math.toDegrees(mCurrentCamStartRadian), false, mLinePaint);
        } else {
            canvas.drawArc(rect, (float) Math.toDegrees(mCurrentCamFinishRadian), (float) Math.toDegrees(mCurrentCamStartRadian) - (float) Math.toDegrees(mCurrentCamFinishRadian), false, mLinePaint);
        }
        canvas.restore();
        canvas.save();

        canvas.rotate((float) Math.toDegrees(mCurrentCamStartRadian), mCx, mCy);
        canvas.drawCircle(mCx, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine, 0.01f, mLinePaint);
        canvas.restore();
        // TimerNumber
        canvas.save();
        canvas.rotate((float) Math.toDegrees(mCurrentCamFinishRadian), mCx, mCy);
        canvas.drawCircle(mCx, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine, 0.01f, mLinePaint);
        canvas.restore();
        // TimerNumber
        canvas.save();


        if (ismInCamStartThumb) {
            canvas.rotate((float) Math.toDegrees(mCurrentCamStartRadian), mCx, mCy);
            canvas.drawCircle(mCx, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine, mCircleButtonRadius, mCircleButtonPaint);
            canvas.drawBitmap(bitmapStartCam, mCx-bitmapStartCam.getWidth()/2,getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine-bitmapStartCam.getHeight()/2, null);
            canvas.restore();
            // TimerNumber
            canvas.save();
            canvas.rotate((float) Math.toDegrees(mCurrentCamFinishRadian), mCx, mCy);
            canvas.drawCircle(mCx, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine, mCircleButtonRadius, mTimerColonPaint);
            canvas.drawBitmap(bitmapFinishCam, mCx-bitmapFinishCam.getWidth()/2,getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine-bitmapFinishCam.getHeight()/2, null);
            canvas.restore();
            // TimerNumber
            canvas.save();
        } else {
            canvas.rotate((float) Math.toDegrees(mCurrentCamStartRadian), mCx, mCy);
            canvas.drawCircle(mCx, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine, mCircleButtonRadius, mTimerColonPaint);
            canvas.drawBitmap(bitmapStartCam, mCx-bitmapStartCam.getWidth()/2, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine-bitmapStartCam.getHeight()/2,  null);
            canvas.restore();
            // TimerNumber
            canvas.save();
            canvas.rotate((float) Math.toDegrees(mCurrentCamFinishRadian), mCx, mCy);
            canvas.drawCircle(mCx, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine, mCircleButtonRadius, mCircleButtonPaint);
            canvas.drawBitmap(bitmapFinishCam, mCx-bitmapFinishCam.getWidth()/2, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine-bitmapFinishCam.getHeight()/2, null);
            canvas.restore();
            // TimerNumber
            canvas.save();
        }
        int x = (camFinishDegree-camStartDegree) / 10;
        int i;
        if (mClockwise){
            i = x<0 ? -x : x;
        }
        else{
            i = x<0 ? -x : 360-x;
        }

        canvas.drawText((i) + "" + (char) 0x00B0, mCx, mCy + getFontHeight(mTimerNumberPaint) / 2, mTimerNumberPaint);
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
                    ismInCamStartThumb = false;
                    mPreRadian = getRadian(event.getX(), event.getY());
                    Log.d(TAG, "Motion in camera start.");
                } else if (mInCamFinishThumb(event.getX(), event.getY()) && isEnabled()) {
                    mInCamFinishThumb = true;
                    ismInCamStartThumb = true;
                    mPreRadian = getRadian(event.getX(), event.getY());
                    Log.d(TAG, "Motion in camera finish.");
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
                    mCurrentTime = (int) (60 / (2 * Math.PI) * mCurrentCamStartRadian * 60);
                    camStartDegree = mCurrentTime;
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
                    mCurrentTime = (int) (60 / (2 * Math.PI) * mCurrentCamFinishRadian * 60);
                    camFinishDegree = mCurrentTime;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mInCamStartThumb && isEnabled()) {
                    mInCamStartThumb = false;
                } else if (mInCamFinishThumb && isEnabled()) {
                    mInCamFinishThumb = false;
                }
                break;
        }
        return true;
    }

    // Whether the down event inside circle button
    private boolean mInCamFinishThumb(float x, float y) {
        float r = mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine;
        float x2 = (float) (mCx + r * Math.sin(mCurrentCamFinishRadian));
        float y2 = (float) (mCy - r * Math.cos(mCurrentCamFinishRadian));
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < mCircleButtonRadius) {
            return true;
        }
        return false;
    }

    // Whether the down event inside circle button
    private boolean mInCamStartThumb(float x, float y) {
        float r = mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine;
        float x2 = (float) (mCx + r * Math.sin(mCurrentCamStartRadian));
        float y2 = (float) (mCy - r * Math.cos(mCurrentCamStartRadian));
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < mCircleButtonRadius) {
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
            this.mRadius = width / 2 - mCircleStrokeWidth / 2;
        } else {
            this.mRadius = width / 2 - (mCircleButtonRadius - mGapBetweenCircleAndLine -
                    mCircleStrokeWidth / 2);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_RADIAN, mCurrentCamStartRadian);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            mCurrentCamStartRadian = bundle.getFloat(STATUS_RADIAN);
            mCurrentTime = (int) (60 / (2 * Math.PI) * mCurrentCamStartRadian * 60);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setOnTimeChangedListener(OnTimeChangedListener listener) {
        if (null != listener) {
            this.mListener = listener;
        }
    }

    public interface OnTimeChangedListener {
        void start(String starting);

        void end(String ending);
    }
}