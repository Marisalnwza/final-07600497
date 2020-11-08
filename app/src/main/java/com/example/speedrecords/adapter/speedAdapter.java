package com.example.speedrecords.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speedrecords.R;
import com.example.speedrecords.model.speed;

public class speedAdapter extends RecyclerView.Adapter<speedAdapter.MyViewHolder> {

    private Context mContext;
    private speed[] speeds;

    public speedAdapter(Context context, speed[] speeds) {
        this.mContext = context;
        this.speeds = speeds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        speed speed = speeds[position];

        holder.averageTextView.setText(speed.average);
        holder.inputTextView.setText(speed.distance);

//        holder.imageView.setImageResource(
//                speed.average >= 80 ? R.drawable.ic_launcher_background: R.drawable.red_cow
//        );
    }

    @Override
    public int getItemCount() {
        return speeds.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView averageTextView;
        TextView inputTextView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.averageTextView = itemView.findViewById(R.id.average_textview);
            this.inputTextView = itemView.findViewById(R.id.input_textView);
            this.imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
