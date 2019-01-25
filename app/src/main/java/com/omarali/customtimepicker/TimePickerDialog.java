package com.omarali.customtimepicker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.omarali.customtimepicker.adapters.AmPmListAdapter;
import com.omarali.customtimepicker.adapters.DatesListAdapter;
import com.omarali.customtimepicker.adapters.HoursListAdapter;
import com.omarali.customtimepicker.adapters.MinutesListAdapter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.khrystal.library.widget.CircleRecyclerView;
import me.khrystal.library.widget.CircularViewMode;
import me.khrystal.library.widget.ItemViewMode;
import me.khrystal.library.widget.RotateXScaleYViewMode;

public class TimePickerDialog extends DialogFragment implements DatesListAdapter.DateClickListener, HoursListAdapter.HoursClickListener, MinutesListAdapter.MinutesClickListener, AmPmListAdapter.AmPmClickListener {

    @BindView(R.id.rv_date_recycler)CircleRecyclerView dateRecycler;
    @BindView(R.id.rv_hours_recycler)CircleRecyclerView hoursRecycler;
    @BindView(R.id.rv_minutes_recycler)CircleRecyclerView minutesRecycler;
    @BindView(R.id.rv_am_pm_recycler)CircleRecyclerView am_pm_Recycler;

    ArrayList<String>dates = new ArrayList<>();
    ArrayList<String>hours = new ArrayList<>();
    ArrayList<String>minutes = new ArrayList<>();
    String[] Am_Pm = {"AM","PM"};

    SelectedTimeEvent selectedTimeEvent = new SelectedTimeEvent();

    public TimePickerDialog()  {
        // Required empty public constructor
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(getActivity() instanceof TimeSelectedListener){

            // get the centered day in screen which should be the selected day
            TextView tv_date =  dateRecycler.findViewAtCenter().findViewById(R.id.tv_date);
            String day = tv_date.getText().toString();

            selectedTimeEvent.setDay(day);


            // get the centered hour in screen which should be the selected hour
            TextView tv_hour =  hoursRecycler.findViewAtCenter().findViewById(R.id.tv_text);
            String hour = tv_hour.getText().toString();

            selectedTimeEvent.setHour(hour);


            // get the centered minute in screen which should be the selected minutes
            TextView tv_minutes =  minutesRecycler.findViewAtCenter().findViewById(R.id.tv_text);
            String minutes = tv_minutes.getText().toString();

            selectedTimeEvent.setMinutes(minutes);


            // get the centered am-pm in screen which should be the selected am-pm
            TextView tv_am_pm =  am_pm_Recycler.findViewAtCenter().findViewById(R.id.tv_text);
            String am_pm = tv_am_pm.getText().toString();

            selectedTimeEvent.setAm_Pm(am_pm);


            // send selectedTimeEvent object to the activity which started this dialog fragment
            ((TimeSelectedListener)getActivity()).onTimeSelected(selectedTimeEvent);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        setupDialog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomDialog);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.dialog_pick_time, container, false);
        ButterKnife.bind(this,view);

        setupDatesRecycler();

        setupHoursRecycler();

        setupMinutesRecycler();

        setupAmPmRecycler();



        return view;
    }


    /**
     * configure full width dialog with animations
     */
    private void setupDialog(){
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().setGravity(Gravity.BOTTOM | Gravity.BOTTOM);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.height= ViewGroup.LayoutParams.WRAP_CONTENT;

        getDialog().getWindow().setAttributes(p);
    }



    /**
     *
     * prepare the Dates list and setup Dates RecyclerView
     *
     */
    private void setupDatesRecycler(){

        // prepare dates list as String
        getDatesList();

        // set itemViewMode for the circular recycler view
        ItemViewMode itemViewMode = new CircularViewMode();

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity());
        dateRecycler.setLayoutManager(horizontalLayoutManager);

        dateRecycler.setViewMode(itemViewMode);
        dateRecycler.setNeedCenterForce(true); // when SCROLL_STATE_IDLE == state, nearly center itemView scroll to center
        dateRecycler.setNeedLoop(false); // default is true

        dateRecycler.setItemAnimator(new DefaultItemAnimator());
        DatesListAdapter datesListAdapter = new DatesListAdapter(this , dates);

        dateRecycler.setAdapter(datesListAdapter);
    }



    /**
     *
     * prepare the Hours list and setup Hours RecyclerView
     *
     */
    private void setupHoursRecycler(){

        // prepare hours list as String
        getHoursList();

        // set itemViewMode for the circular recycler view
        ItemViewMode itemViewMode = new RotateXScaleYViewMode();

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL , false);
        hoursRecycler.setLayoutManager(horizontalLayoutManager);

        hoursRecycler.setViewMode(itemViewMode);
        hoursRecycler.setNeedCenterForce(true); // when SCROLL_STATE_IDLE == state, nearly center itemView scroll to center
        hoursRecycler.setNeedLoop(true); // default is true

        hoursRecycler.setItemAnimator(new DefaultItemAnimator());
        HoursListAdapter hoursListAdapter = new HoursListAdapter(this , hours);

        hoursRecycler.setAdapter(hoursListAdapter);
    }




    /**
     *
     * prepare the Minutes list and setup Minutes RecyclerView
     *
     */
    private void setupMinutesRecycler(){
        // prepare hours list as String
        getMinutesList();

        // set itemViewMode for the circular recycler view
        ItemViewMode itemViewMode = new RotateXScaleYViewMode();

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL , false);
        minutesRecycler.setLayoutManager(horizontalLayoutManager);

        minutesRecycler.setViewMode(itemViewMode);
        minutesRecycler.setNeedCenterForce(true); // when SCROLL_STATE_IDLE == state, nearly center itemView scroll to center
        minutesRecycler.setNeedLoop(true); // default is true

        minutesRecycler.setItemAnimator(new DefaultItemAnimator());
        MinutesListAdapter hoursListAdapter = new MinutesListAdapter(this , minutes);

        minutesRecycler.setAdapter(hoursListAdapter);
    }




    /**
     *
     * prepare the Minutes list and setup Minutes RecyclerView
     *
     */
    private void setupAmPmRecycler(){

        // set itemViewMode for the circular recycler view
        ItemViewMode itemViewMode = new CircularViewMode();

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity());
        am_pm_Recycler.setLayoutManager(horizontalLayoutManager);

        am_pm_Recycler.setViewMode(itemViewMode);
        am_pm_Recycler.setNeedCenterForce(true); // when SCROLL_STATE_IDLE == state, nearly center itemView scroll to center
        am_pm_Recycler.setNeedLoop(false); // default is true

        am_pm_Recycler.setItemAnimator(new DefaultItemAnimator());
        AmPmListAdapter amPmListAdapter = new AmPmListAdapter(this , Am_Pm);

        am_pm_Recycler.setAdapter(amPmListAdapter);
    }




    /**
     *
     * get days between two dates and add the result to dates ArrayList
     *
     */
    private void getDatesList(){
        Date startDate = new Date("1/1/2019");
        Date endDate = new Date("30/12/2019");

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            // Do your job here with `date`.
            System.out.println(date);

            String month = new SimpleDateFormat("MMMM",Locale.getDefault()).format(date);  // sep;
            String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
            String day = (String) DateFormat.format("dd",   date); // 20
            String fullDate = dayOfTheWeek+ " "+month+ " "+day;

            dates.add(fullDate);
        }
    }



    /**
     *
     * get two digit hour from ( 01 -> 12 ) the result to hours ArrayList
     *
     */
    private void getHoursList(){
        DecimalFormat df = new DecimalFormat("00");
        for (int i=1;i<=12;i++)
            hours.add(df.format(i));
    }


    /**
     *
     * get two digit minutes from ( 00 -> 55 ) with 5 step and add the result to minutes ArrayList
     *
     */
    private void getMinutesList(){
        DecimalFormat df = new DecimalFormat("00");
        for(int i=0;i<=55;i+=5)
            minutes.add(df.format(i));
    }


    @OnClick(R.id.btn_done)
    public void done(){
        dismiss();
    }


    @Override
    public void onDayClick(View v, int position) {
        dateRecycler.smoothScrollToView(v);
    }


    @Override
    public void onHourClick(View v, int position) {
        hoursRecycler.smoothScrollToView(v);
    }


    @Override
    public void onMinutesClick(View v, int position) {
        minutesRecycler.smoothScrollToView(v);
    }


    @Override
    public void onAmPmClick(View v, int position) {
        am_pm_Recycler.smoothScrollToView(v);
    }
}
