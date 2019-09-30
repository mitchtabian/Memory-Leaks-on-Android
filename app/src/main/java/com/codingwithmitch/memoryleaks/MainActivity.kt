package com.codingwithmitch.memoryleaks

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private var myAsyncTask: MyAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.go).setOnClickListener {
            if (myAsyncTask != null) {
                finish()
            }

            myAsyncTask = MyAsyncTask(this@MainActivity)
            myAsyncTask!!.execute()
        }
    }

    private inner class MyAsyncTask(context: Context) : AsyncTask<Void, Void, Void>() {


        //        private Context mContext;
        private val mContextRef: WeakReference<Context>


        init {
            //            this.mContext = context; // METHOD 1: CANCEL ASYNCTASK in onDestroy
            mContextRef = WeakReference(context) // METHOD 2: Use WeakReference
        }

        override fun doInBackground(vararg voids: Void): Void? {

            val icon = BitmapFactory.decodeResource(mContextRef.get()?.getResources(), R.drawable.ic_launcher_background)
            //            Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_launcher_background);

            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return null
        }
    }


    override fun onDestroy() {
        //        myAsyncTask.cancel(true);
        super.onDestroy()
    }
}































