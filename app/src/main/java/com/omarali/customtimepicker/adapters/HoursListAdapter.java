package com.omarali.customtimepicker.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omarali.customtimepicker.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoursListAdapter extends RecyclerView.Adapter<HoursListAdapter.ViewHolder> {

    private ArrayList<String> hours = new ArrayList<>();
    private HoursClickListener hoursClickListener;

    public HoursListAdapter(HoursClickListener hoursClickListener , ArrayList<String> hours) {
        this.hours = hours;
        this.hoursClickListener = hoursClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        String hour= hours.get(position % hours.size());


        if(hour!=null)
            viewHolder.tv_hour.setText(hour);


        viewHolder.tv_hour.setOnClickListener(view -> {
            hoursClickListener.onHourClick(viewHolder.itemView , position);
        });



    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_text)
        TextView tv_hour;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public interface HoursClickListener{
        void onHourClick(View v, int position);
    }
}