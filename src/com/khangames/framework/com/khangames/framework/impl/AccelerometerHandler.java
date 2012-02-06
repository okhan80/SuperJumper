package com.khangames.framework.com.khangames.framework.impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/13/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccelerometerHandler implements SensorEventListener {
    float accelX;
    float accelY;
    float accelZ;
    
    public AccelerometerHandler(Context context) {
        SensorManager manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }
    
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        accelX = sensorEvent.values[0];
        accelY = sensorEvent.values[1];
        accelZ = sensorEvent.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public float getAccelX() {
        return accelX;
    }

    public float getAccelY() {
        return accelY;
    }

    public float getAccelZ() {
        return accelZ;
    }
}
