package com.omarali.customtimepicker.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omarali.customtimepicker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AmPmListAdapter extends RecyclerView.Adapter<AmPmListAdapter.ViewHolder> {

    private String[] amPm;
    private AmPmClickListener amPmClickListener;

    public AmPmListAdapter(AmPmClickListener amPmClickListener , String[] amPm) {
        this.amPm = amPm;
        this.amPmClickListener = amPmClickListener;
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

        String am_pm = amPm[position];


        if(am_pm!=null)
            viewHolder.tv_am_pm.setText(am_pm);


        viewHolder.tv_am_pm.setOnClickListener(view -> {
            amPmClickListener.onAmPmClick(viewHolder.itemView , position);
        });

    }

    @Override
    public int getItemCount() {
        return amPm.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_text)
        TextView tv_am_pm;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public interface AmPmClickListener{
        void onAmPmClick(View v, int position);
    }
}