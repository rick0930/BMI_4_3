package com.demo.android.bmi_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity {
    private static final String TAG = "LifeCycle";
    private TextView view_result;
    private TextView view_suggest;
    private Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LifeCycle","Report.onCreate");
        setContentView(R.layout.activity_report);
        findViews();
        setListensers();
            try {
                double BMI = calcBMI();
                showResult(BMI);
            } catch (NullPointerException e) {
                Log.e(TAG, "error: " + e);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(Report.this, R.string.input_error,
                        Toast.LENGTH_SHORT).show();
            }
    }

    private void findViews() {
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
        button_back = (Button) findViewById(R.id.report_back);
    }

    //Listen for button clicks
    private void setListensers() {
        button_back.setOnClickListener(backMain);
    }

    private Button.OnClickListener backMain = new Button.OnClickListener() {
        public void onClick(View v) {
            // Close this Activity
            Report.this.finish();
        }
    };

    private double calcBMI() {
        Bundle bunde = this.getIntent().getExtras();
        double height = Double.parseDouble(bunde.getString("KEY_HEIGHT"))/100;
        double weight = Double.parseDouble(bunde.getString("KEY_WEIGHT"));
        return weight / (height * height);
    }

    private void showResult(double BMI) {
        DecimalFormat nf = new DecimalFormat("0.00");
        view_result.setText("Your BMI is " + nf.format(BMI));
        if (BMI > 25) {
            view_suggest.setText(R.string.advice_heavy);
        } else if (BMI < 20) {
            view_suggest.setText(R.string.advice_light);
        } else {
            view_suggest.setText(R.string.advice_average);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i("LifeCycle","Report.onStart");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i("LifeCycle","Report.onRestart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("LifeCycle","Report.onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("LifeCycle","Report.onPause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("LifeCycle","Report.onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i("LifeCycle","Report.onDestroy");
    }
}
