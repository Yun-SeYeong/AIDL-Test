package com.example.aidl_test

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var serviceIntent: Intent
    private lateinit var mBinder: IRemoteService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                Log.d("Main", "onServiceConnected")
                mBinder = IRemoteService.Stub.asInterface(p1)
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                Log.d("Main", "onServiceDisconnected")
            }

        }

        findViewById<Button>(R.id.button).setOnClickListener {
            serviceIntent = Intent(applicationContext, RemoteService::class.java)
            bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            unbindService(connection)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            mBinder.basicTypes("test")
        }
    }
}