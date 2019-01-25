package com.omarAndsattar.customtimepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.omarAndsattar.timepickerdialog.SelectedTimeEvent;
import com.omarAndsattar.timepickerdialog.TimePickerDialog;
import com.omarAndsattar.timepickerdialog.TimeSelectedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements TimeSelectedListener {

    @BindView(R.id.tv_selected_time)TextView tv_selected_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_pick_time)
    public void pickTime() {
        new TimePickerDialog().show(getSupportFragmentManager(), "DIALOG_TIME_PICKER");
    }



    @Override
    public void onTimeSelected(SelectedTimeEvent selectedTime) {
        if(selectedTime!=null)
            tv_selected_time.setText(selectedTime.toString());
    }
}
