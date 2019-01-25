package com.omarAndsattar.timepickerdialog.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.omarAndsattar.timepickerdialog.R;
import java.util.ArrayList;

public class DatesListAdapter extends RecyclerView.Adapter<DatesListAdapter.ViewHolder> {

    private ArrayList<String> dates = new ArrayList<>();
    private DateClickListener dateClickListener;

    public DatesListAdapter(DateClickListener dateClickListener , ArrayList<String> dates) {
        this.dates = dates;
        this.dateClickListener = dateClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.date_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        String date= dates.get(position);

        if(date!=null)
            viewHolder.tv_date.setText(date);

        viewHolder.tv_date.setOnClickListener(view -> {
            dateClickListener.onDayClick(viewHolder.itemView , position);
        });
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }


    public interface DateClickListener{
        void onDayClick(View v, int position);
    }
}