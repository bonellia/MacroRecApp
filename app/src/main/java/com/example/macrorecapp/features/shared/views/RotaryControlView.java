package com.example.macrorecapp.features.shared.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.macrorecapp.R;

public class RotaryControlView extends View {

    //
    // Shared members
    //

    //Offset = -90 indicates that the progress starts from 12 o'clock.
    private static final int ANGLE_OFFSET = -90;

    public static int INVALID_VALUE = -1;

    public static final int MAX = 360;
    public static final int MIN = 0;
    private int mMin = MIN;
    private int mMax = MAX;

    // Camera seekbar members

    // Default value of camera start position in degrees.
    public static final int DEFAULT_START = 298;

    // Default value of camera finish position in degrees.
    public static final int DEFAULT_FINISH = 90;

    // Default value of total camera movement in degrees.
    public static final int DEFAULT_MOVE = 172;

    // The current position value for start camera in degrees.
    private int mCurrentCamStartValue = DEFAULT_START;

    // The current position value for end camera in degrees./
    private int mCurrentCamFinishValue = DEFAULT_FINISH;

    // The value of total movement in degrees.
    private int mTotalMove = DEFAULT_MOVE;

    // The increment/decrement value for each movement of progress.
    private int mStep = 1;

    // The Drawable for the camera start position thumb.
    private Drawable mCamStartThumb;

    // The Drawable for the camera finish position thumb.
    private Drawable mCamFinishThumb;

    private int mProgressWidth = 12;
    private int mArcWidth = 12;

    // The movement direction. Clockwise or Counter-clockwise.
    private boolean mClockwise = true;
    private boolean mEnabled = true;

    // The counts of point update to determine whether to change previous progress.
    private int mUpdateTimes = 0;
    private float mPreviousProgress = -1;
    private float mCurrentProgress = 0;

    // Determine whether maximum point is reached or not.
    private boolean isMax = false;

    // Determine whether minimum point is reached or not.
    private boolean isMin = false;

    private int mArcRadius = 0;
    private RectF mArcRect = new RectF();
    private Paint mArcPaint;

    private float mProgressSweep = 0;
    private Paint mProgressPaint;

    private float mTextSize = 72;
    private Paint mTextPaint;
    private Rect mTextRect = new Rect();

    private int mTranslateX;
    private int mTranslateY;

    // (x, y) coordinates of camera start thumb.
    private int mCamStartThumbX;
    private int mCamStartThumbY;

    // (x, y) coordinates of camera finish thumb.
    private int mCamFinishThumbX;
    private int mCamFinishThumbY;

    // The current touch angle of arc.
    private double mTouchAngle;

    // The listener of the view.
    private OnRotaryControlViewChangeListener mOnRotaryControlViewChangeListener;

    //
    // Target seekbar members
    //

    // Status
    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_RADIAN = "status_radian";

    // Default dimension in dp/pt
    private static final float DEFAULT_GAP_BETWEEN_CIRCLE_AND_LINE = 30;
    private static final float DEFAULT_NUMBER_SIZE = 10;
    private static final float DEFAULT_LINE_WIDTH = 0.5f;
    private static final float DEFAULT_CIRCLE_BUTTON_RADIUS = 18;
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
    private float mCurrentRadian;
    private float mCurrentRadian1;
    private float mCurrentRadian2;
    private float mPreRadian;
    private boolean mInCircleButton;
    private boolean mInCircleButton1;
    private boolean mInCircleButton2;
    private boolean ismInCircleButton;
    private int mCurrentTime; // seconds

    private OnTimeChangedListener mListener;

    public RotaryControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

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

        // Solve the target version related to shadow
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); // use this, when targetSdkVersion is greater than or equal to api 14

        float density = getResources().getDisplayMetrics().density;

        // Defaults, may need to link this into theme settings
        int arcColor = ContextCompat.getColor(context, R.color.color_arc);
        int progressColor = ContextCompat.getColor(context, R.color.color_progress);
        int textColor = ContextCompat.getColor(context, R.color.color_text);
        mProgressWidth = (int) (mProgressWidth * density);
        mArcWidth = (int) (mArcWidth * density);
        mTextSize = (int) (mTextSize * density);

        mCamStartThumb = ContextCompat.getDrawable(context, R.drawable.ic_cam_start);
        mCamFinishThumb = ContextCompat.getDrawable(context, R.drawable.ic_cam_finish);

        if (attrs != null) {
            // Attribute initialization
            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.RotaryControlView, 0, 0);

            Drawable camStartThumbIcon = a.getDrawable(R.styleable.RotaryControlView_camStartThumb);
            if (camStartThumbIcon != null)
                mCamStartThumb = camStartThumbIcon;

            Drawable camFinishThumbIcon = a.getDrawable(R.styleable.RotaryControlView_camFinishThumb);
            if (camFinishThumbIcon != null)
                mCamFinishThumb = camFinishThumbIcon;

            int camStartThumbIconHalfWidth = camStartThumbIcon.getIntrinsicWidth() / 2;
            int camStartThumbIconIconHalfHeight = mCamStartThumb.getIntrinsicHeight() / 2;
            mCamStartThumb.setBounds(-camStartThumbIconHalfWidth, -camStartThumbIconIconHalfHeight, camStartThumbIconHalfWidth,
                    camStartThumbIconIconHalfHeight);

            int camFinishThumbIconHalfWidth = camStartThumbIcon.getIntrinsicWidth() / 2;
            int camFinishThumbIconIconHalfHeight = mCamFinishThumb.getIntrinsicHeight() / 2;
            mCamFinishThumb.setBounds(-camFinishThumbIconHalfWidth, -camFinishThumbIconIconHalfHeight, camFinishThumbIconHalfWidth,
                    camFinishThumbIconIconHalfHeight);

            mCurrentCamStartValue = a.getInteger(R.styleable.RotaryControlView_startDegree, mCurrentCamStartValue);
            mCurrentCamFinishValue = a.getInteger(R.styleable.RotaryControlView_finishDegree, mCurrentCamFinishValue);
            mStep = a.getInteger(R.styleable.RotaryControlView_step, mStep);

            mProgressWidth = (int) a.getDimension(R.styleable.RotaryControlView_progressWidth, mProgressWidth);
            progressColor = a.getColor(R.styleable.RotaryControlView_progressColor, progressColor);

            mArcWidth = (int) a.getDimension(R.styleable.RotaryControlView_arcWidth, mArcWidth);
            arcColor = a.getColor(R.styleable.RotaryControlView_arcColor, arcColor);

            mTextSize = (int) a.getDimension(R.styleable.RotaryControlView_textSize, mTextSize);
            textColor = a.getColor(R.styleable.RotaryControlView_textColor, textColor);

            mClockwise = a.getBoolean(R.styleable.RotaryControlView_clockwise,
                    mClockwise);
            mEnabled = a.getBoolean(R.styleable.RotaryControlView_enabled, mEnabled);
            a.recycle();
        }

        // Range check
        mCurrentCamStartValue = (mCurrentCamStartValue > mMax) ? mMax : mCurrentCamStartValue;
        mCurrentCamStartValue = (mCurrentCamStartValue < mMin) ? mMin : mCurrentCamStartValue;
        mCurrentCamFinishValue = (mCurrentCamFinishValue > mMax) ? mMax : mCurrentCamFinishValue;
        mCurrentCamFinishValue = (mCurrentCamFinishValue < mMin) ? mMin : mCurrentCamFinishValue;

        mProgressSweep = (float) mTotalMove / valuePerDegree();

        mArcPaint = new Paint();
        mArcPaint.setColor(arcColor);
        mArcPaint.setAntiAlias(true);
        DashPathEffect dashPath = new DashPathEffect(new float[]{5,5}, (float)1.0);
        mArcPaint.setPathEffect(dashPath);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcWidth);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(progressColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mProgressWidth);

        mTextPaint = new Paint();
        mTextPaint.setColor(textColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mTextSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!mClockwise) {
            canvas.scale(-1, 1, mArcRect.centerX(), mArcRect.centerY());
        }

        // Draw the text
        String textPoint = String.valueOf(mTotalMove);
        mTextPaint.getTextBounds(textPoint, 0, textPoint.length(), mTextRect);
        // center the text
        int xPos = getWidth() / 2 - mTextRect.width() / 2;
        int yPos = (int) ((mArcRect.centerY()) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2));
        canvas.drawText(String.valueOf(mTotalMove), xPos, yPos, mTextPaint);

        // Draw the arc and progress.
        canvas.drawArc(mArcRect, ANGLE_OFFSET, 360, false, mArcPaint);
        canvas.drawArc(mArcRect, ANGLE_OFFSET, mProgressSweep, false, mProgressPaint);



        if (mEnabled) {
            // Draw the indicator icons
            canvas.translate(mTranslateX - mCamStartThumbX, mTranslateY - mCamStartThumbY);
            mCamStartThumb.draw(canvas);
            canvas.translate(mTranslateX - mCamFinishThumbX, mTranslateY - mCamFinishThumbY);
            mCamFinishThumb.draw(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int min = Math.min(width, height);

        mTranslateX = (int) (width * 0.5f);
        mTranslateY = (int) (height * 0.5f);

        int arcDiameter = min - getPaddingLeft();
        mArcRadius = arcDiameter / 2;
        float top = height / 2 - (arcDiameter / 2);
        float left = width / 2 - (arcDiameter / 2);
        mArcRect.set(left, top, left + arcDiameter, top + arcDiameter);

        updateThumbPositions();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mEnabled) {
            // 阻止父View去攔截onTouchEvent()事件，確保touch事件可以正確傳遞到此層View。
            this.getParent().requestDisallowInterceptTouchEvent(true);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mOnRotaryControlViewChangeListener != null)
                        mOnRotaryControlViewChangeListener.onStartTrackingTouch(this);
                    updateOnTouch(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    updateOnTouch(event);
                    break;
                case MotionEvent.ACTION_UP:
                    if (mOnRotaryControlViewChangeListener != null)
                        mOnRotaryControlViewChangeListener.onStopTrackingTouch(this);
                    setPressed(false);
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    if (mOnRotaryControlViewChangeListener != null)
                        mOnRotaryControlViewChangeListener.onStopTrackingTouch(this);
                    setPressed(false);
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return true;
        }
        return false;
    }

    // Use trigonometry to cal radian.
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

    public interface OnRotaryControlViewChangeListener {

        /**
         * Notification that the point value has changed.
         *
         * @param rotaryControlView The RotaryControlView view whose value has changed
         * @param points     The current point value.
         * @param fromUser   True if the point change was triggered by the user.
         */
        void onPointsChanged(RotaryControlView rotaryControlView, int points, boolean fromUser);

        void onStartTrackingTouch(RotaryControlView dashedCircleSeekbar);

        void onStopTrackingTouch(RotaryControlView dashedCircleSeekbar);
    }

    public void setOnRotaryControlViewChangeListener(OnRotaryControlViewChangeListener onRotaryControlViewChangeListener) {
        mOnRotaryControlViewChangeListener = onRotaryControlViewChangeListener;
    }

    public interface OnTimeChangedListener {
        void start(String starting);

        void end(String ending);
    }

    private float valuePerDegree() {
        return (float) (mMax) / 360.0f;
    }

    private void updateOnTouch(MotionEvent event) {
        setPressed(true);
        mTouchAngle = convertTouchEventPointToAngle(event.getX(), event.getY());
        int progress = convertAngleToProgress(mTouchAngle);
        updateProgress(progress, true);
    }

    private double convertTouchEventPointToAngle(float xPos, float yPos) {
        // Transform touch coordinate into component coordinate.
        float x = xPos - mTranslateX;
        float y = yPos - mTranslateY;

        x = (mClockwise) ? x : -x;
        double angle = Math.toDegrees(Math.atan2(y, x) + (Math.PI / 2));
        angle = (angle < 0) ? (angle + 360) : angle;
        return angle;
    }

    private int convertAngleToProgress(double angle) {
        return (int) Math.round(valuePerDegree() * angle);
    }

    private void updateProgress(int progress, boolean fromUser) {

        // Detect points change closed to max or min.
        final int maxDetectValue = (int) ((double) mMax * 0.95);
        final int minDetectValue = (int) ((double) mMax * 0.05) + mMin;

        mUpdateTimes++;
        if (progress == INVALID_VALUE) {
            return;
        }

        // Avoid accidentally touch to become max from original point.
        if (progress > maxDetectValue && mPreviousProgress == INVALID_VALUE) {
            return;
        }

        // Record previous and current progress change.
        if (mUpdateTimes == 1) {
            mCurrentProgress = progress;
        } else {
            mPreviousProgress = mCurrentProgress;
            mCurrentProgress = progress;
        }

//		if (mPreviousProgress != mCurrentProgress)
//			System.out.printf("Progress (%d)(%f) %.0f -> %.0f (%s, %s)\n",
//					progress, mTouchAngle,
//					mPreviousProgress, mCurrentProgress,
//					isMax ? "Max" : "",
//					isMin ? "Min" : "");

        mPoints = progress - (progress % mStep);

        /**
         * Determine whether reach max or min to lock point update event.
         *
         * When reaching max, the progress will drop from max (or maxDetectPoints ~ max
         * to min (or min ~ minDetectPoints) and vice versa.
         *
         * If reach max or min, stop increasing / decreasing to avoid exceeding the max / min.
         */

        if (mUpdateTimes > 1 && !isMin && !isMax) {
            if (mPreviousProgress >= maxDetectValue && mCurrentProgress <= minDetectValue &&
                    mPreviousProgress > mCurrentProgress) {
                isMax = true;
                progress = mMax;
                mPoints = mMax;
//				System.out.println("Reach Max " + progress);
                if (mOnRotaryControlViewChangeListener != null) {
                    mOnRotaryControlViewChangeListener
                            .onPointsChanged(this, progress, fromUser);
                    return;
                }
            } else if ((mCurrentProgress >= maxDetectValue
                    && mPreviousProgress <= minDetectValue
                    && mCurrentProgress > mPreviousProgress) || mCurrentProgress <= mMin) {
                isMin = true;
                progress = mMin;
                mPoints = mMin;
//				Log.d("Reach", "Reach Min " + progress);
                if (mOnRotaryControlViewChangeListener != null) {
                    mOnRotaryControlViewChangeListener
                            .onPointsChanged(this, progress, fromUser);
                    return;
                }
            }
            invalidate();
        } else {

            // Detect whether decreasing from max or increasing from min, to unlock the update event.
            // Make sure to check in detect range only.
            if (isMax & (mCurrentProgress < mPreviousProgress) && mCurrentProgress >= maxDetectValue) {
//				System.out.println("Unlock max");
                isMax = false;
            }
            if (isMin
                    && (mPreviousProgress < mCurrentProgress)
                    && mPreviousProgress <= minDetectValue && mCurrentProgress <= minDetectValue
                    && mPoints >= mMin) {
//				Log.d("Unlock", String.format("Unlock min %.0f, %.0f\n", mPreviousProgress, mCurrentProgress));
                isMin = false;
            }
        }


        progress = (progress > mMax) ? mMax : progress;
        progress = (progress < mMin) ? mMin : progress;

        if (mOnRotaryControlViewChangeListener != null) {
            progress = progress - (progress % mStep);

            mOnRotaryControlViewChangeListener
                    .onPointsChanged(this, progress, fromUser);
        }

        mProgressSweep = (float) progress / valuePerDegree();
//			if (mPreviousProgress != mCurrentProgress)
//				System.out.printf("-- %d, %d, %f\n", progress, mPoints, mProgressSweep);
        updateIndicatorIconPosition();
        invalidate();

    }

    private void updateThumbPositions() {
        mCamStartThumbX = (int) (mArcRadius * Math.cos(Math.toRadians(mCurrentCamStartValue)));
        mCamStartThumbY = (int) (mArcRadius * Math.sin(Math.toRadians(mCurrentCamStartValue)));
        mCamFinishThumbX = (int) (mArcRadius * Math.cos(Math.toRadians(mCurrentCamFinishValue)));
        mCamFinishThumbY = (int) (mArcRadius * Math.sin(Math.toRadians(mCurrentCamFinishValue)));
    }

    public int getProgressWidth() {
        return mProgressWidth;
    }
}
