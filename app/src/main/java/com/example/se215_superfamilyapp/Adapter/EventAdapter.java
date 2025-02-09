package com.example.se215_superfamilyapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.R;
import com.example.se215_superfamilyapp.model.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.tvEventName.setText(event.getEventName());
        holder.tvStartTime.setText(event.getStartTime());
        holder.tvEndTime.setText(event.getEndTime());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventName, tvStartTime, tvEndTime;

        public EventViewHolder(View itemView) {
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tv_event_name);
            tvStartTime = itemView.findViewById(R.id.tv_start_time);
            tvEndTime = itemView.findViewById(R.id.tv_end_time);
        }
    }
}
