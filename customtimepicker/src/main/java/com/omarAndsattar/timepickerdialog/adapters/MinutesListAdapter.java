package com.omarAndsattar.timepickerdialog.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.omarAndsattar.timepickerdialog.R;
import java.util.ArrayList;


public class MinutesListAdapter extends RecyclerView.Adapter<MinutesListAdapter.ViewHolder> {

    private ArrayList<String> minutes = new ArrayList<>();
    private MinutesClickListener minutesClickListener;

    public MinutesListAdapter(MinutesClickListener minutesClickListener , ArrayList<String> minutes) {
        this.minutes = minutes;
        this.minutesClickListener = minutesClickListener;
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

        String minute = minutes.get(position % minutes.size());

        if(minute!=null)
            viewHolder.tv_minutes.setText(minute);


        viewHolder.tv_minutes.setOnClickListener(view -> {
            minutesClickListener.onMinutesClick(viewHolder.itemView , position);
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_minutes;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_minutes = itemView.findViewById(R.id.tv_text);
        }
    }


    public interface MinutesClickListener{
        void onMinutesClick(View v, int position);
    }
}