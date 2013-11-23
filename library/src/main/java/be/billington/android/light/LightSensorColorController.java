package be.billington.android.light;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

import com.nineoldandroids.animation.ArgbEvaluator;

public class LightSensorColorController implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor lightSensor;
    private View view;
    private int colorStart, colorEnd;
    private ArgbEvaluator ev;
    private int sensorDelay;

    public LightSensorColorController(Context context, int sensorDelay, View view, int colorStart, int colorEnd) {
        this.mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.view = view;
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
        this.sensorDelay = sensorDelay;
        this.ev = new ArgbEvaluator();
    }

    public void onResume() {
        lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor != null) {
            mSensorManager.registerListener(
                    this,
                    lightSensor,
                    sensorDelay);
        }
    }

    public void onPause() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            Float fraction = event.values[0] / lightSensor.getMaximumRange() * 100.0f;
            Integer result = (Integer) ev.evaluate(fraction, colorStart, colorEnd);
            String hexColor = "#" + Integer.toHexString(result);
            view.setBackgroundColor(Color.parseColor(hexColor));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}