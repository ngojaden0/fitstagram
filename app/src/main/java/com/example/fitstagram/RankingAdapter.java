
package com.example.fitstagram;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.MyViewHolder> {

    Context context;
    ArrayList<user> list;

    public RankingAdapter(Context context, ArrayList<user> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RankingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingAdapter.MyViewHolder holder, int position) {

        user user = list.get(position);
        holder.username.setText(user.getUsername());
        holder.votes.setText(user.getTotal_points()+"");

        if (user.getTotal_points() >= 100 && user.getTotal_points() < 500)
        {
            holder.badge.setImageResource(R.drawable.bronze_badge);
        }
        else if (user.getTotal_points() >= 500 && user.getTotal_points() < 1000 )
        {
            holder.badge.setImageResource(R.drawable.silver_badge);
        }
        else if (user.getTotal_points() >= 1000)
        {
            holder.badge.setImageResource(R.drawable.gold_badge);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView username, votes;
        ImageView badge;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            votes = itemView.findViewById(R.id.votes);
            badge = (ImageView) itemView.findViewById(R.id.badge);

        }
    }
}