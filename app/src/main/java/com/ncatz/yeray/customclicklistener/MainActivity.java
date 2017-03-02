package com.ncatz.yeray.customclicklistener;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CLICK_LISTENER = "clickListener";
    private static final String MULTIPLE_CLICK_LISTENER = "multipleClickListener";
    private Button btTest1;
    private Button btTest2,btTest3,btTest4;
    private OneClickListener oneClickListener;
    private OneClickMultiple oneClickMultiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btTest1 = (Button) findViewById(R.id.btTest1);
        btTest2 = (Button) findViewById(R.id.btTest2);
        btTest3 = (Button) findViewById(R.id.btTest3);
        btTest4 = (Button) findViewById(R.id.btTest4);
        /* END EXAMPLE 1*/
        /*
        *  Button that can't be clicked twice at the same time.
        *  You must set manually when it is possible to be clicked again, calling oneClickListener.setClicked(false);
        */
        if (savedInstanceState != null) {
            oneClickListener = (OneClickListener) savedInstanceState.getSerializable(CLICK_LISTENER);
        } else {
            oneClickListener = new OneClickListener() {
                @Override
                protected void onOneClick() {
                    Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            oneClickListener.setClicked(false);
                            Toast.makeText(MainActivity.this, "OnClickFinish", Toast.LENGTH_SHORT).show();
                        }
                    }, 10000);
                }

                @Override
                protected void onDoubleClick() {
                    Toast.makeText(MainActivity.this, "You can't click now you scrub", Toast.LENGTH_SHORT).show();
                }
            };
        }
        btTest1.setOnClickListener(oneClickListener);
        /* END EXAMPLE 1*/

        /* EXAMPLE 2*/
        /*
        *  Multiple views that can't be clicked at the same time
        */
        if (savedInstanceState != null) {
            oneClickMultiple = (OneClickMultiple) savedInstanceState.getSerializable(MULTIPLE_CLICK_LISTENER);
        } else {
            oneClickMultiple = new OneClickMultiple();
        }
        btTest2.setOnClickListener(oneClickMultiple.addListener("btTest2", new OneClickMultipleListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "OnClickMultiple 1", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        oneClickMultiple.setClicked(false);
                        Toast.makeText(MainActivity.this, "OnClickMultiple finished", Toast.LENGTH_SHORT).show();
                    }
                }, 10000);
            }

            @Override
            public void onDoubleClick() {
                Toast.makeText(MainActivity.this, "You can't click now you scrub", Toast.LENGTH_SHORT).show();
            }
        }));

        btTest3.setOnClickListener(oneClickMultiple.addListener("btTest3", new OneClickMultipleListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "OnClickMultiple 2", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        oneClickMultiple.setClicked(false);
                        Toast.makeText(MainActivity.this, "OnClickMultiple finished", Toast.LENGTH_SHORT).show();
                    }
                }, 10000);
            }

            @Override
            public void onDoubleClick() {
                Toast.makeText(MainActivity.this, "You can't click now you scrub", Toast.LENGTH_SHORT).show();
            }
        }));


        btTest4.setOnClickListener(oneClickMultiple.addListener("btTest4", new OneClickMultipleListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "OnClickMultiple 3", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        oneClickMultiple.setClicked(false);
                        Toast.makeText(MainActivity.this, "OnClickMultiple finished", Toast.LENGTH_SHORT).show();
                    }
                }, 10000);
            }

            @Override
            public void onDoubleClick() {
                Toast.makeText(MainActivity.this, "You can't click now you scrub", Toast.LENGTH_SHORT).show();
            }
        }));
        /* END EXAMPLE 2*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CLICK_LISTENER,oneClickListener);
        outState.putSerializable(MULTIPLE_CLICK_LISTENER,oneClickMultiple);
    }
}
