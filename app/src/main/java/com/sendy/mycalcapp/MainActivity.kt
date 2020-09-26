package com.sendy.mycalcapp

import android.annotation.SuppressLint
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sendy.calcservice.ICalc
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    protected var addService: ICalc? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonAdd.setOnClickListener {

            if (v1.editableText.toString().isEmpty() || v2.editableText.toString().isEmpty()) {
                Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val v1 = v1.editableText.toString().toInt()
            val v2 = v2.editableText.toString().toInt()

            try {

                vResult.setText("${addService?.add(v1, v2)}")

            } catch (e: RemoteException) {
                Log.e("TAG", "onCreate: ", e)
                vResult.setText("Error ${e.localizedMessage}")
            }
        }


        buttonDivide.setOnClickListener {

            if (v1.editableText.toString().isEmpty() || v2.editableText.toString().isEmpty()) {
                Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val v1 = v1.editableText.toString().toInt()
            val v2 = v2.editableText.toString().toInt()

            try {

                vResult.setText("${addService?.divide(v1, v2)}")

            } catch (e: RemoteException) {
                Log.e("TAG", "onCreate: ", e)
                vResult.setText("Error ${e.localizedMessage}")
            }
        }

        buttonSubtract.setOnClickListener {

            if (v1.editableText.toString().isEmpty() || v2.editableText.toString().isEmpty()) {
                Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val v1 = v1.editableText.toString().toInt()
            val v2 = v2.editableText.toString().toInt()

            try {

                vResult.setText("${addService?.subtract(v1, v2)}")

            } catch (e: RemoteException) {
                Log.e("TAG", "onCreate: ", e)
                vResult.setText("Error ${e.localizedMessage}")
            }
        }

        buttonMultiply.setOnClickListener {
            if (v1.editableText.toString().isEmpty() || v2.editableText.toString().isEmpty()) {
                Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val v1 = v1.editableText.toString().toInt()
            val v2 = v2.editableText.toString().toInt()

            try {

                vResult.setText("${addService?.multiply(v1, v2)}")

            } catch (e: RemoteException) {
                Log.e("TAG", "onCreate: ", e)
                vResult.setText("Error ${e.localizedMessage}")
            }
        }

    }

    override fun onStart() {
        super.onStart()
        initConnection()
    }

    private fun initConnection() {
        if (addService == null) {

            val intent = Intent("com.sendy.mycalcapp.MyServer")
            //intent.action = "service.multiply"
            intent.setPackage("com.sendy.mycalcapp")

            // binding to remote service
            bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private var serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Toast.makeText(applicationContext, "Service Connected", Toast.LENGTH_LONG).show()
            addService = ICalc.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Toast.makeText(applicationContext, "Service DisConnected", Toast.LENGTH_LONG).show()
            addService = null
        }
    }

    open fun createExplicitFromImplicitIntent(context: Context, implicitIntent: Intent?): Intent? {
        //Retrieve all services that can match the given intent
        val pm: PackageManager = context.packageManager
        val resolveInfo: List<*>? = pm.queryIntentServices(implicitIntent, 0)

        //Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size != 1) {
            return null
        }

        //Get component info and create ComponentName
        val serviceInfo = resolveInfo[0] as ResolveInfo
        val packageName = serviceInfo.serviceInfo.packageName
        val className = serviceInfo.serviceInfo.name
        val component = ComponentName(packageName, className)

        //Create a new intent. Use the old one for extras and such reuse
        val explicitIntent = Intent(implicitIntent)

        //Set the component to be explicit
        explicitIntent.component = component
        return explicitIntent
    }

    override fun onResume() {
        super.onResume()
        initConnection()
    }

    override fun onStop() {
        super.onStop()
        // Unbind from the service
        unbindService(serviceConnection)
    }

}