package com.sendy.mycalcapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.sendy.calcservice.Calculations

class MyServer: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return Calculations()
    }

}