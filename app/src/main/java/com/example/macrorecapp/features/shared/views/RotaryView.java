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
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.macrorecapp.R;

import java.util.ArrayList;
import java.util.List;


public class RotaryView extends View {

    private static final String TAG = "RotaryView";

    // Status
    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_START_RADIAN = "status_start_radian";
    private static final String STATUS_FINISH_RADIAN = "status_finish_radian";

    // Default dimension in dp/pt
    private static final float DEFAULT_GAP_BETWEEN_CIRCLE_AND_LINE = 20;
    private static final float DEFAULT_CAM_ICON_RADIUS = 8;
    private static final float DEFAULT_TARGET_THUMB_RADIUS = 20;
    private static final float DEFAULT_CIRCLE_STROKE_WIDTH = 20;
    private static final float DEFAULT_TARGET_ARC_STROKE_WIDTH = 12;
    private static final float DEFAULT_TIMER_NUMBER_SIZE = 38;
    private static final float DEFAULT_TOTAL_MOVE_TEXT_SIZE = 40;
    private static final float DEFAULT_DIRECTION_TEXT_SIZE = 15;
    private static final float DEFAULT_SUBTEXT_SIZE = 12;
    private static final float DEFAULT_TARGET_THUMB_TEXT_SIZE = 18;

    // Default color
    private static final int DEFAULT_CIRCLE_COLOR = 0xFF6E6E6E;
    private static final int DEFAULT_SUBTEXT_COLOR = 0xFF6E6E6E;
    private static final int DEFAULT_TARGET_ARC_COLOR = 0xFFB7072D;
    private static final int DEFAULT_TOTAL_MOVE_NUMBER_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_DIRECTION_COLOR = 0x00FA7777;
    private static final int DEFAULT_DIRECTION_TEXT_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_TARGET_THUMB_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_TARGET_THUMB_TEXT_COLOR = 0xFF8F314D;

    RectF mTargetContainer;
    // Drawables as matrices.
    Bitmap bitmapStartCam;
    Bitmap bitmapFinishCam;
    Bitmap bitmapCw;
    Bitmap bitmapCcw;
    Matrix flipHorizontalMatrix;
    // Paint
    private Paint mCirclePaint;
    private Paint mTargetArcPaint;
    private Paint mTotalMovePaint;
    private Paint mTotalMoveTextPaint;
    private Paint mDirectionPaint;
    private Paint mDirectionTextPaint;
    private Paint mSubtextPaint;
    private Paint mTargetThumbPaint;
    private Paint mTargetThumbTextPaint;
    // Dimension
    private float mGapBetweenCircleAndLine;
    private float mCamIconRadius;
    private float mTargetThumbRadius;
    private float mCircleStrokeWidth;
    private float mTargetArcStrokeWidth;
    private float mTotalMoveSize;
    private float mTotalMoveTextSize;
    private float mDirectionTextSize;
    private float mTargetThumbTextSize;
    private float mSubtextSize;
    // Color
    private int mCircleColor;
    private int mTargetArcStrokeColor;
    private int mTotalMoveNumberColor;
    private int mDirectionTextColor;
    private int mSubtextColor;
    private int mTargetThumbColor;
    private int mTargetThumbTextColor;

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
    private static int mCurrentStartDegree;
    private static int mCurrentFinishDegree;
    private static boolean mClockwise;
    // Outer arc parameters
    private List<Integer> mTargetList;
    private int[] mTargetArray;
    private int mStartTargetDegree;

    // Constructors.
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

        Log.d(TAG, "Initializing view parameters.");

        // Set default dimensions or read xml attributes.
        mGapBetweenCircleAndLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_GAP_BETWEEN_CIRCLE_AND_LINE,
                getContext().getResources().getDisplayMetrics());
        mCamIconRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CAM_ICON_RADIUS,
                getContext().getResources().getDisplayMetrics());
        mTargetThumbRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TARGET_THUMB_RADIUS,
                getContext().getResources().getDisplayMetrics());
        mCircleStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_STROKE_WIDTH,
                getContext().getResources().getDisplayMetrics());
        mTargetArcStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TARGET_ARC_STROKE_WIDTH,
                getContext().getResources().getDisplayMetrics());
        mTotalMoveSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TIMER_NUMBER_SIZE,
                getContext().getResources().getDisplayMetrics());
        mTotalMoveTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TOTAL_MOVE_TEXT_SIZE,
                getContext().getResources().getDisplayMetrics());
        mDirectionTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DIRECTION_TEXT_SIZE,
                getContext().getResources().getDisplayMetrics());
        mSubtextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_SUBTEXT_SIZE,
                getContext().getResources().getDisplayMetrics());
        mTargetThumbTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TARGET_THUMB_TEXT_SIZE,
                getContext().getResources().getDisplayMetrics());
        mClockwise = a.getBoolean(R.styleable.RotaryView_isClockwise, mClockwise);

        // Set default colors or read xml attributes.
        mCircleColor = DEFAULT_CIRCLE_COLOR;
        mTargetArcStrokeColor = DEFAULT_TARGET_ARC_COLOR;
        mTotalMoveNumberColor = DEFAULT_TOTAL_MOVE_NUMBER_COLOR;
        mDirectionTextColor = DEFAULT_DIRECTION_TEXT_COLOR;
        mSubtextColor = DEFAULT_SUBTEXT_COLOR;
        mTargetThumbTextColor = DEFAULT_TARGET_THUMB_TEXT_COLOR;
        mTargetThumbColor = DEFAULT_TARGET_THUMB_COLOR;

        // Init all paints
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTotalMovePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTotalMoveTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDirectionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDirectionTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSubtextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetThumbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTargetThumbTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // CirclePaint
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        DashPathEffect dashPath1 = new DashPathEffect(new float[]{8, 24}, (float) 1.0);
        mCirclePaint.setPathEffect(dashPath1);

        // TargetArcPaint
        mTargetArcPaint.setColor(mTargetArcStrokeColor);
        mTargetArcPaint.setStrokeWidth(mTargetArcStrokeWidth);
        mTargetArcPaint.setStyle(Paint.Style.STROKE);

        // TargetThumbPaint
        mTargetThumbPaint.setColor(mTargetThumbColor);
        mTargetThumbPaint.setAntiAlias(true);
        mTargetThumbPaint.setStyle(Paint.Style.FILL);

        // TargetThumbTextPaint
        mTargetThumbTextPaint.setColor(mTargetThumbTextColor);
        mTargetThumbTextPaint.setTextAlign(Paint.Align.CENTER);
        mTargetThumbTextPaint.setTextSize(mTargetThumbTextSize);
        mTargetThumbTextPaint.setTypeface(Typeface.create("Roboto", Typeface.BOLD));

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

        // Optionally, these values could be set according to XML attributes.
        mCurrentCamStartRadian = (float) Math.toRadians(115);
        mCurrentCamFinishRadian = (float) Math.toRadians(0);
        mCurrentStartDegree = 115;
        mCurrentFinishDegree = 0;

        bitmapStartCam = BitmapFactory.decodeResource(getResources(), R.drawable.ic_cam_start);
        bitmapFinishCam = BitmapFactory.decodeResource(getResources(), R.drawable.ic_cam_finish);

        // Not using a separate resource for ccw icon. Will programmatically flip horizontally.
        bitmapCw = BitmapFactory.decodeResource(getResources(), R.drawable.ic_ccw_medium);
        // A matrix to multiply, flips horizontally.
        flipHorizontalMatrix = new Matrix();
        flipHorizontalMatrix.setScale(-1, 1);
        bitmapCcw = Bitmap.createBitmap(bitmapCw, 0, 0, bitmapCw.getWidth(), bitmapCw.getHeight(), flipHorizontalMatrix, true);

        // Get the position of targets.
        int targetsID = a.getResourceId(R.styleable.RotaryView_targetList, 0);
        mTargetArray = a.getResources().getIntArray(targetsID);

        mTargetList = new ArrayList<>();

        int x = 0;
        for (int i : mTargetArray) {
            Log.d("Target Retrieval.", "Adding " + i + " to mTargetList.");
            mTargetList.add(i);
            Log.d("Target Retrieval.", mTargetList.get(x++) + " is added to mTargetList.");
        }

        // By default, first element of TargetList is assigned as Start Target.
        mStartTargetDegree = mTargetList.get(0);

        // Create a RectF in order to draw Target Arc within.
        mTargetContainer = new RectF();

        // Solve the target version related to shadow
        // Using this, since targetSdkVersion (16) is greater than or equal to aPI 14.
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        canvas.drawCircle(mCx, mCy, mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine, mCirclePaint);
        canvas.save();

        // Draw Subtexts.
        canvas.drawText("Total Move", mCx, mCy, mSubtextPaint);
        canvas.save();
        canvas.drawText("Active Direction", mCx, mCy + bitmapCw.getHeight() + getFontHeight(mDirectionPaint) + getFontHeight(mSubtextPaint), mSubtextPaint);
        canvas.save();

        canvas.drawBitmap(bitmapStartCam,
                mCx + (mRadius - 2 * mCircleStrokeWidth - bitmapStartCam.getWidth() / 2f) * (float) Math.cos(mCurrentCamStartRadian) - bitmapStartCam.getWidth() / 2f,
                mCy + (mRadius - 2 * mCircleStrokeWidth - bitmapStartCam.getHeight() / 2f) * (float) Math.sin(mCurrentCamStartRadian) - bitmapStartCam.getHeight() / 2f,
                null);
        canvas.save();

        canvas.drawBitmap(bitmapFinishCam,
                mCx + (mRadius - 2 * mCircleStrokeWidth - bitmapFinishCam.getWidth() / 2f) * (float) Math.cos(mCurrentCamFinishRadian) - bitmapFinishCam.getWidth() / 2f,
                mCy + (mRadius - 2 * mCircleStrokeWidth - bitmapFinishCam.getHeight() / 2f) * (float) Math.sin(mCurrentCamFinishRadian) - bitmapFinishCam.getHeight() / 2f,
                null);


        // Draw total move text.
        canvas.drawText((getArcLength((int) Math.toDegrees(mCurrentCamStartRadian), (int) Math.toDegrees(mCurrentCamFinishRadian), mClockwise)) + "" + (char) 0x00B0, mCx, mCy - getFontHeight(mTotalMovePaint) / 2, mTotalMovePaint);


        // Draw direction icon.
        if (mClockwise) {
            // Note that the drawable resource shows counter clockwise direction by default.
            flipHorizontalMatrix.postTranslate(bitmapCw.getWidth(), 0);
            canvas.drawBitmap(bitmapCcw, mCx - bitmapCw.getWidth() / 2f, mCy + getFontHeight(mSubtextPaint), null);
        } else {
            canvas.drawBitmap(bitmapCw, mCx - bitmapCw.getWidth() / 2f, mCy + getFontHeight(mSubtextPaint), null);
        }

        // Draw direction text.
        if (mClockwise) {
            canvas.drawText("CW", mCx, mCy + bitmapCw.getHeight() + getFontHeight(mDirectionPaint) / 2 + getFontHeight(mSubtextPaint), mDirectionTextPaint);
        } else {
            canvas.drawText("CCW", mCx, mCy + bitmapCw.getHeight() + getFontHeight(mDirectionPaint) / 2 + getFontHeight(mSubtextPaint), mDirectionTextPaint);
        }
        canvas.save();

        // Draw Targets Arc
        // Set rectf values to determine boundaries of targets arc.
        mTargetContainer.set(mCx - mRadius, mCy - mRadius, mCx + mRadius, mCy + mRadius);
        int arcStart = mStartTargetDegree;
        int arcSweep = getTargetSweep();
        if (mClockwise) {
            Log.d(TAG, "Drawing arc with "+ arcSweep + " degrees sweep clockwise.");
            canvas.drawArc(mTargetContainer, arcStart, arcSweep, false, mTargetArcPaint);
        } else {
            Log.d(TAG, "Drawing arc with "+ arcSweep + " degrees sweep counter-clockwise.");
            canvas.drawArc(mTargetContainer, arcStart, -arcSweep, false, mTargetArcPaint);
        }
        canvas.save();

        // Placing target circles with text. Numbers represent list index.
        int targetNumber = 1;
        for (int i : mTargetList) {
            canvas.drawCircle(
                    mCx + mRadius * (float) Math.cos(Math.toRadians(i)),
                    mCy + mRadius * (float) Math.sin(Math.toRadians(i)),
                    mTargetThumbRadius,
                    mTargetThumbPaint
            );
            canvas.drawText(
                    "T" + (targetNumber),
                    mCx + mRadius * (float) Math.cos(Math.toRadians(i)),
                    mCy + mRadius * (float) Math.sin(Math.toRadians(i)) + getFontHeight(mTargetThumbTextPaint) / 2,
                    mTargetThumbTextPaint
            );
            targetNumber++;
            canvas.save();
        }
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // If the point in the circle button
                if (mInCamStartThumb(event.getX(), event.getY()) && isEnabled()) {
                    mInCamStartThumb = true;
                    mPreRadian = getRadian(event.getX(), event.getY());
                    Log.d(TAG, "Motion begins on camera start.");
                } else if (mInCamFinishThumb(event.getX(), event.getY()) && isEnabled()) {
                    mInCamFinishThumb = true;
                    mPreRadian = getRadian(event.getX(), event.getY());
                    Log.d(TAG, "Motion begins on camera finish.");
                }
                // If the point in the direction icon
                if (mInDirectionIcon(event.getX(), event.getY()) && isEnabled()) {
                    mInDirectionIcon = true;
                    Log.d(TAG, "Touch on direction icon.");
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
                    mCurrentStartDegree = (int) (360 / (2 * Math.PI) * mCurrentCamStartRadian) - 90;
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
                    mCurrentFinishDegree = (int) (360 / (2 * Math.PI) * mCurrentCamFinishRadian) - 90;
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
        if (mGapBetweenCircleAndLine + mCircleStrokeWidth >= mCamIconRadius) {
            this.mRadius = width / 2f - mCircleStrokeWidth / 2;
            this.mRadius = mRadius * 8 / 10;
        } else {
            this.mRadius = width / 2f - (mCamIconRadius - mGapBetweenCircleAndLine -
                    mCircleStrokeWidth / 2);
            this.mRadius = mRadius * 8 / 10;
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
            mCurrentStartDegree = (int) (360 / (2 * Math.PI) * mCurrentCamStartRadian);
            mCurrentFinishDegree = (int) (360 / (2 * Math.PI) * mCurrentCamFinishRadian);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void addTarget(int targetDegree) {
        // Currently not enforcing any rules on target's angle.
        // Note that right hand side is 0 degree and increase is clockwise.
        mTargetList.add(targetDegree % 360);
    }

    // A helper function to obtain distance over arc between two points.
    private static int getArcLength(int startDegree, int finishDegree, boolean isClockwise) {
        int x = (finishDegree - startDegree);
        int i;
        if (!isClockwise) {
            i = x < 0 ? -x : 360 - x;
        } else {
            i = x > 0 ? 360 - x : -x;
            i = 360 - i;
        }
        return i;
    }

    private float getFontHeight(Paint paint) {
        // FontMetrics sF = paint.getFontMetrics();
        // return sF.descent - sF.ascent;
        Rect rect = new Rect();
        paint.getTextBounds("1", 0, 1, rect);
        return rect.height();
    }

    // Whether the down event inside circle button
    private boolean mInCamStartThumb(float x, float y) {
        float x2 = mCx + (mRadius - 2 * mCircleStrokeWidth - bitmapStartCam.getWidth()) * (float) Math.cos(mCurrentCamStartRadian);
        float y2 = mCy + (mRadius - 2 * mCircleStrokeWidth - bitmapStartCam.getHeight()) * (float) Math.sin(mCurrentCamStartRadian);
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < bitmapStartCam.getHeight()) {
            return true;
        }
        return false;
    }

    // Whether the down event inside circle button
    private boolean mInCamFinishThumb(float x, float y) {
        float x2 = mCx + (mRadius - 2 * mCircleStrokeWidth - bitmapFinishCam.getWidth()) * (float) Math.cos(mCurrentCamFinishRadian);
        float y2 = mCy + (mRadius - 2 * mCircleStrokeWidth - bitmapFinishCam.getHeight()) * (float) Math.sin(mCurrentCamFinishRadian);
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < bitmapFinishCam.getHeight()) {
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
        if (left < x && x < right && top < y && y < bottom) {
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

    // Currently useless. May use to change start target.
    private int findStartTargetIndex(int targetDegree) {
        int x = 0;
        for (int i : mTargetList) {
            if (i == targetDegree) {
                // Assuming all targets to have unique degrees.
                // In case of duplicate degrees, first index will be returned.
                return x;
            }
            x++;
        }
        // No such target with given degree.
        return -1;
    }

    // Determines sweep amount to be used while drawing targets arc.
    private int getTargetSweep() {
        int arcStart = mStartTargetDegree;
        // Initially assuming no sweep will be performed. There could be one target only.
        int arcSweep = 0;
        // Next loop determines proper sweep amount that includes all targets.
        for(int i : mTargetList) {
            // If arc length between start degree and current degree is larger than previous amount, set is as sweep amount.
            if (i!=arcStart) {
                arcSweep = getArcLength(arcStart, i, mClockwise) > arcSweep ? getArcLength(arcStart, i, mClockwise) : arcSweep;
            }
        }
        return arcSweep;
    }

    public int getCurrentStartDegree() {
        return mCurrentStartDegree;
    }

    public void setCurrentStartDegree(int newCurrentStartDegree) {
        this.mCurrentStartDegree = newCurrentStartDegree;
    }

    public int getCurrentFinishDegree() {
        return mCurrentFinishDegree;
    }

    public void setCurrentFinishDegree(int newCurrentFinishDegree) {
        this.mCurrentFinishDegree = newCurrentFinishDegree;
    }

    public static int getTotalMoveInDegrees() {
        return getArcLength(mCurrentStartDegree, mCurrentFinishDegree, mClockwise);
    }

}