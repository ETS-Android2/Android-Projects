package com.example.battlecruiser;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.jetbrains.annotations.NotNull;


public class OrientationData implements SensorEventListener {
    public SensorManager manager;
    public Sensor accelerometer;
    public Sensor magnometer;
    public Context context;

    public float [] accelOutput;
    public float [] magOutput;

    public float [] orientation = new float[3];
    public float[] getOrientation() {
        return orientation;
    }

    public float [] startOrientation = null;
    public float[] getStartOrientation() {
        return startOrientation;
    }

    public void newGame(){
        startOrientation = null;
    }

    public OrientationData(){
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public OrientationData(Context context1){
        manager = (SensorManager) context1.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void register(SensorEventListener sensorEventListener){
        manager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(sensorEventListener, magnometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause(){
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(@NotNull SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelOutput = sensorEvent.values;
        }else if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magOutput = sensorEvent.values;
        }if (accelOutput != null && magOutput != null){
            float [] R = new float[9];
            float [] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, accelOutput, magOutput);
            if (success){
                SensorManager.getOrientation(R, orientation);
                if (startOrientation == null){
                    startOrientation = new float[orientation.length];
                    System.arraycopy(orientation, 0,startOrientation, 0, orientation.length);
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
