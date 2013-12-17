package be.billington.android.light.demo.activity;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.billington.android.light.LightSensorColorController;
import be.billington.android.light.demo.R;

public class DemoActivity extends Activity {

    private LightSensorColorController lightSensorColorController;
    private LinearLayout layout;
    private TextView text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        layout = (LinearLayout) findViewById(R.id.linearlayout);
        text = (TextView) findViewById(R.id.textLight);

        lightSensorColorController = new LightSensorColorController(getBaseContext(),
                SensorManager.SENSOR_DELAY_FASTEST, layout, 0xff003100, 0xff00FF00);
    }

    protected void onResume() {
        super.onResume();
        lightSensorColorController.onResume();
    }

    protected void onPause() {
        super.onPause();
        lightSensorColorController.onPause();
    }
}