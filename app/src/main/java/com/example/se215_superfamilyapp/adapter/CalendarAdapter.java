package com.example.se215_superfamilyapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.example.se215_superfamilyapp.R;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends BaseAdapter {

    private final Context context;
    private final List<String> dates;
    private final LayoutInflater inflater;
    private final Calendar calendar;
    private int selectedPosition = -1;


    public CalendarAdapter(Context context, List<String> dates, Calendar calendar) {
        this.context = context;
        this.dates = dates;
        this.calendar = calendar;
        this.inflater = LayoutInflater.from(context);
        setDefaultSelectedPosition();
    }
    private void setDefaultSelectedPosition() {
        Calendar today = Calendar.getInstance();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

        int firstDayIndex = 0;
        while (!dates.get(firstDayIndex).equals("1")) {
            firstDayIndex++;
        }

        int relativePosition = firstDayIndex + todayDay - 1;
        if (relativePosition >= 0 && relativePosition < dates.size()) {
            selectedPosition = relativePosition;
        }
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_day, parent, false);
        }

        LinearLayout container = convertView.findViewById(R.id.container_dayInMonth);
        TextView tvDay = convertView.findViewById(R.id.dayInMonth);
        String dayText = dates.get(position);
        tvDay.setText(dayText);

        int firstDayIndex = 0;
        while (!dates.get(firstDayIndex).equals("1")) {
            firstDayIndex++;
        }
        int lastDayIndex = firstDayIndex + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;

        if (position < firstDayIndex || position > lastDayIndex) {
            tvDay.setTextColor(context.getResources().getColor(R.color.colorOutsideMonth));
        } else {
            tvDay.setTextColor(context.getResources().getColor(R.color.colorCurrentMonth));
        }

        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;

        int marginInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, context.getResources().getDisplayMetrics()
        );

        int dayWidth = (screenWidth - marginInPx) / 7;

        ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
        layoutParams.width = dayWidth;
        container.setLayoutParams(layoutParams);
        // current date
        Calendar today = Calendar.getInstance();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH);
        int todayYear = today.get(Calendar.YEAR);

        if (position >= firstDayIndex && position <= lastDayIndex) {
            int currentDay = Integer.parseInt(dayText);

            if (currentDay == todayDay &&
                    calendar.get(Calendar.MONTH) == todayMonth &&
                    calendar.get(Calendar.YEAR) == todayYear) {
                tvDay.setBackground(context.getResources().getDrawable(R.drawable.bg_current_date));
                tvDay.setTextColor(Color.parseColor("#ffffff"));
            }
        }
        // selected date
        if (position == selectedPosition) {
            container.setBackground(context.getResources().getDrawable(R.drawable.selected_border));
        } else {
            container.setBackground(context.getResources().getDrawable(R.drawable.normal_border));
        }

        return convertView;
    }
    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
    }

}
