package com.ptp.phamtanphat.multiplethread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final DataObject dataObject = new DataObject();
    Thread thread2, thread3, thread4;
    int loop = 0;
    boolean isActive = true;
    int nameThread = 2;  //mainThread = 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (dataObject) {
                    while (isActive) {
                        if (nameThread == 2) {
                            loop++;
                            int a = loop;
                            Log.d("BBB", "A " + a + " index " + loop);
                            dataObject.setA(a);
                            nameThread = 3;
                            dataObject.notifyAll();
                        } else {
                            try {
                                dataObject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };

        thread3 = new Thread() {
            @Override
            public void run() {
                synchronized (dataObject) {
                    while (isActive) {
                        if (nameThread == 3) {
                            int b = loop;
                            Log.d("BBB", "B " + b + " index " + loop);
                            dataObject.setB(b);
                            nameThread = 4;
                            dataObject.notifyAll();
                        } else {
                            try {
                                dataObject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        };

        thread4 = new Thread() {
            @Override
            public void run() {
                synchronized (dataObject) {
                    while (isActive) {
                        if (nameThread == 4) {
                            int c = dataObject.Tinhtong();
                            Log.d("BBB", "C " + c + " index " + loop);

                            if (c >= 5000) {
                                isActive = false;
                                return;
                            }

                            nameThread = 2;
                            dataObject.notifyAll();
                        } else {
                            try {
                                dataObject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };

        thread2.start();
        thread3.start();
        thread4.start();
    }


    @Override
    protected void onStop() {
        isActive = false;
        super.onStop();
    }

}
