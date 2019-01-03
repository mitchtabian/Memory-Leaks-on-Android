package com.codingwithmitch.memoryleaks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myAsyncTask != null){
                    finish();
                }

                myAsyncTask = new MyAsyncTask(MainActivity.this);
                myAsyncTask.execute();

            }
        });
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void>{


//        private Context mContext;
        private WeakReference<Context> mContextRef;



        public MyAsyncTask(Context context) {
//            this.mContext = context; // METHOD 1: CANCEL ASYNCTASK in onDestroy
            mContextRef = new WeakReference<>(context); // METHOD 2: Use WeakReference
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap icon = BitmapFactory.decodeResource(mContextRef.get().getResources(),R.drawable.ic_launcher_background);
//            Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_launcher_background);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Override
    protected void onDestroy() {
//        myAsyncTask.cancel(true);
        super.onDestroy();
    }
}































