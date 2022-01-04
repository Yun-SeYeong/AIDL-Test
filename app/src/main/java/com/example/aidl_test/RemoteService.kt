package com.example.aidl_test

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class RemoteService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    private val binder = object : IRemoteService.Stub() {
        override fun basicTypes(
            aString: String?
        ) {
            if (!aString.isNullOrEmpty()) {
                Log.d("Main", aString)
            }
        }
    }

}