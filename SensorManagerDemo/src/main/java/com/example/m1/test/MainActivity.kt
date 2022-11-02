package com.example.m1.test

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sensorManager: SensorManager? = null

    private var eventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            Log.i("jeff", "onSensorChanged event=" + Arrays.toString(event.values))
            when (event.sensor.type) {
                Sensor.TYPE_GRAVITY -> g_sensor.text =
                    getString(R.string.gravity, event.values[0], event.values[1], event.values[2])
                Sensor.TYPE_ORIENTATION -> o_sensor.text =
                    getString(R.string.orientation, event.values[0], event.values[1], event.values[2])
                Sensor.TYPE_ACCELEROMETER -> m_sensor.text =
                    getString(R.string.accelerometer, event.values[0], event.values[1], event.values[2])
                Sensor.TYPE_GYROSCOPE -> gy_sensor.text =
                    getString(R.string.gyroscope, event.values[0], event.values[1], event.values[2])
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            Log.i("jeff", "onAccuracyChanged sensor=" + sensor.name + ",accuracy=" + accuracy)
            when (sensor.type) {
                Sensor.TYPE_GRAVITY -> dis.text =
                    getString(R.string.gravity_sensor,  sensor.name , accuracy)
                Sensor.TYPE_ORIENTATION -> ois.text =
                    getString(R.string.direction_sensor, sensor.name, accuracy)
                Sensor.TYPE_ACCELEROMETER -> mis.text =
                    getString(R.string.acceleration_sensor, sensor.name, accuracy)
                Sensor.TYPE_GYROSCOPE -> gyis.text =
                    getString(R.string.gyroscope_sensor, sensor.name, accuracy)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    BallActivity::class.java
                )
            )
        }
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.let {
            val gSensor = it.getDefaultSensor(Sensor.TYPE_GRAVITY)
            val mSensor = it.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val oSensor = it.getDefaultSensor(Sensor.TYPE_ORIENTATION)
            val gySensor = it.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            it.registerListener(eventListener, gSensor, SensorManager.SENSOR_DELAY_NORMAL)
            it.registerListener(eventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL)
            it.registerListener(eventListener, oSensor, SensorManager.SENSOR_DELAY_NORMAL)
            it.registerListener(eventListener, gySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(eventListener)
    }

}