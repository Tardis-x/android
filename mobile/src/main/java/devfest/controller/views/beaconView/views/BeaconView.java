package devfest.controller.views.beaconView.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import devfest.controller.views.beaconView.models.Coordinates;

public class BeaconView extends View {

    private static final int CONTAINER_BORDERS = 1; // adds extra space for view container

    private int color;
    private float length;
    private float width;

    private Coordinates userCoordinates;

    private List<Coordinates> beaconsCoordinates;
    private int parentWidth;
    private int parentHeight;

    private Paint rectPaint = new Paint();
    private Paint userPaint = new Paint();
    private Paint beaconPaint = new Paint();

    public BeaconView(Context context) {
        super(context);
    }

    public BeaconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeaconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawFrame(canvas);  // for using drawable as a frame just comment this line and add drawable in XML

        for (Coordinates coordinates : beaconsCoordinates)
            drawBeacon((float) coordinates.getX(), (float) coordinates.getY(), canvas);

        drawUser((float) userCoordinates.getX(), (float) userCoordinates.getY(), canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentWidth, parentHeight);
//        setMeasuredDimension((int) width + CONTAINER_BORDERS, (int) length + CONTAINER_BORDERS);
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setStrokeColor(int color) {
        this.color = color;
    }

    public void setUserCoordinates(Coordinates userCoordinates) {
        this.userCoordinates = userCoordinates;
    }

    public void setBeaconsCoordinates(List<Coordinates> beaconsCoordinates) {
        this.beaconsCoordinates = beaconsCoordinates;
    }

    private void drawFrame(Canvas canvas) {
        rectPaint.setColor(color);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(3);
//        canvas.drawRect(0, 0, width, length, rectPaint);
        canvas.drawRect(0, 0, parentWidth, parentHeight, rectPaint);
    }

    private void drawBeacon(float x, float y, Canvas canvas) {
        beaconPaint.setColor(Color.RED);
        beaconPaint.setStyle(Paint.Style.STROKE);
        beaconPaint.setStrokeWidth(3);
        canvas.drawCircle(x, y, 10, beaconPaint);
    }

    private void drawUser(float x, float y, Canvas canvas) {
        userPaint.setColor(Color.GREEN);
        userPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, 15, userPaint);
    }
}
