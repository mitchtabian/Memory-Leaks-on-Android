package com.codingwithmitch.memoryleaks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);


        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask myAsyncTask = new MyAsyncTask(mTextView);
                myAsyncTask.execute();

            }
        });


    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void>{

        private TextView mTextView;

        public MyAsyncTask(TextView textView) {
            this.mTextView = textView;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mTextView.setText("some text");
            onDestroy();
        }
    }
}































