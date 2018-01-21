package com.example.yasserahmed.stockmartbakala.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yasserahmed.stockmartbakala.R;

import java.util.ArrayList;

/**
 * Created by yasser ahmed on 1/21/2018.
 */

public class Notifications_Adapters extends RecyclerView.Adapter<Notifications_Adapters.NotificViewHolder> {

    private ArrayList<Notifications_class> notifications_classes;
    private  int rowLayout;
    Context context;

    public Notifications_Adapters(ArrayList<Notifications_class> notifications_classes, int rowLayout, Context context) {
        this.notifications_classes = notifications_classes;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public NotificViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NotificViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificViewHolder holder, int position) {

        if (position==0){
            holder.notif_background.setBackgroundResource(R.mipmap.notf_item_background);
        }
       
        holder.txt1.setText(notifications_classes.get(position).getOffer());
        holder.txt2.setText(notifications_classes.get(position).getDetails());
        holder.txt3.setText(notifications_classes.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return notifications_classes.size();
    }

    public class NotificViewHolder extends RecyclerView.ViewHolder{

    LinearLayout notif_background;
    TextView txt1,txt2,txt3;


    public NotificViewHolder(View view) {
        super(view);
        notif_background=(LinearLayout)view.findViewById(R.id.notif_background);
        txt1=(TextView)view.findViewById(R.id.txt1);
        txt2=(TextView)view.findViewById(R.id.txt2);
        txt3=(TextView)view.findViewById(R.id.txt3);
    }
}


}
