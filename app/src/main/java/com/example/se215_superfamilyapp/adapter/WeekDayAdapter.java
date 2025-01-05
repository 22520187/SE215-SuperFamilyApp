package com.example.se215_superfamilyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.R;

import java.util.List;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.WeekDayViewHolder> {

    private final Context context;
    private final List<String> weekDays;

    public WeekDayAdapter(Context context, List<String> weekDays) {
        this.context = context;
        this.weekDays = weekDays;
    }

    @NonNull
    @Override
    public WeekDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_week_day, parent, false);
        return new WeekDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekDayViewHolder holder, int position) {
        String day = weekDays.get(position);
        holder.tvWeekDay.setText(day);
    }

    @Override
    public int getItemCount() {
        return weekDays.size();
    }

    public static class WeekDayViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeekDay;

        public WeekDayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeekDay = itemView.findViewById(R.id.tv_week_day);
        }
    }
}
