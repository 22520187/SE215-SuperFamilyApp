package com.example.se215_superfamilyapp.Fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.EventActivity;
import com.example.se215_superfamilyapp.MemberActivity;
import com.example.se215_superfamilyapp.R;
import com.example.se215_superfamilyapp.Adapter.CalendarAdapter;
import com.example.se215_superfamilyapp.Adapter.EventAdapter;
import com.example.se215_superfamilyapp.Adapter.MemberAdapter;
import com.example.se215_superfamilyapp.Adapter.WeekDayAdapter;
import com.example.se215_superfamilyapp.model.Event;
import com.example.se215_superfamilyapp.model.Member;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {
    private Calendar calendar;
    private TextView tvMonthYear,tvSelectedMemberName;
    private GridView gvCalendar;
    private CalendarAdapter adapter;
    RecyclerView rvWeekDays,rv_event_list,rvMemberList;
    Button btn_general, btn_personal,btn_member;
    LinearLayout layout_member;
    com.google.android.material.floatingactionbutton.FloatingActionButton fab_add_event;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calendar, container, false);
        tvMonthYear = view.findViewById(R.id.tv_month_year);
        gvCalendar = view.findViewById(R.id.gv_calendar);
        ImageButton btnPrevious = view.findViewById(R.id.btn_previous);
        ImageButton btnNext = view.findViewById(R.id.btn_next);
        rvWeekDays = view.findViewById(R.id.rv_week_days);
        btn_general=view.findViewById(R.id.btn_general);
        btn_personal=view.findViewById(R.id.btn_personal);
        btn_member=view.findViewById(R.id.btn_member);
        rv_event_list=view.findViewById(R.id.rv_event_list);
        rvMemberList = view.findViewById(R.id.rv_member_list);
        tvSelectedMemberName = view.findViewById(R.id.tv_selected_member_name);
        layout_member=view.findViewById(R.id.lo_member);
        layout_member.setVisibility((View.GONE));
        fab_add_event=view.findViewById(R.id.fab_add_event);
        calendar = Calendar.getInstance();
        loadWeekDate();
        loadCalendar();
        setOnclickNavigate();
        loadEventList();
        setMemberView();
        btnPrevious.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, -1);
            loadCalendar();
        });

        btnNext.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, 1);
            loadCalendar();
        });
        fab_add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private List<Member> memberList;
    private MemberAdapter memberAdapter;
    @SuppressLint("SetTextI18n")
    private void setMemberView() {
        memberList = new ArrayList<>();
        memberList.add(new Member(R.drawable.ic_ava, "John Doe", 0));
        memberList.add(new Member(R.drawable.ic_ava, "Jane Smith", 0));
        memberList.add(new Member(1, "", 1));

        memberList.get(0).setIsSelected(1);
        tvSelectedMemberName.setVisibility(View.VISIBLE);
        tvSelectedMemberName.setText(memberList.get(0).getName() + "'s Schedule");
        rvMemberList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        memberAdapter = new MemberAdapter(memberList, new MemberAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Member member) {
                if (member.getUnshownCount() != 1) {
                    tvSelectedMemberName.setText(member.getName() + "'s Schedule");
                    int position = memberList.indexOf(member);
                    memberAdapter.selectMember(position);
                } else {
                    Intent intent = new Intent(getActivity(), MemberActivity.class);
                    startActivityForResult(intent, 1001);
                }

            }
        });
        rvMemberList.setAdapter(memberAdapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK && data != null) {
            String selectedMemberName = data.getStringExtra("selected_member_name");
            if (selectedMemberName != null) {
                tvSelectedMemberName.setVisibility(View.VISIBLE);
                tvSelectedMemberName.setText(selectedMemberName + "'s Schedule");
            }
        }
    }
    private void loadEventList() {
        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event("Family Meeting", "10:00 AM", "12:00 PM"));
        eventList.add(new Event("Dinner", "06:00 PM", "08:00 PM"));
        eventList.add(new Event("Workout", "07:00 AM", "08:00 AM"));

        EventAdapter eventAdapter = new EventAdapter(eventList);
        rv_event_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_event_list.setAdapter(eventAdapter);
    }

    private void setOnclickNavigate() {
        btn_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonStyle(btn_general);
                resetButtonStyle(btn_personal);
                resetButtonStyle(btn_member);
                layout_member.setVisibility(View.GONE);
                fab_add_event.setVisibility(View.VISIBLE);
            }
        });

        btn_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonStyle(btn_personal);
                resetButtonStyle(btn_general);
                resetButtonStyle(btn_member);
                layout_member.setVisibility(View.GONE);
                fab_add_event.setVisibility(View.VISIBLE);

            }
        });

        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonStyle(btn_member);
                resetButtonStyle(btn_general);
                resetButtonStyle(btn_personal);
                layout_member.setVisibility(View.VISIBLE);
                fab_add_event.setVisibility(View.GONE);
            }
        });

    }
    private void updateButtonStyle(Button button) {
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5B7FFE")));
        button.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void resetButtonStyle(Button button) {
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
        button.setTextColor(Color.parseColor("#000000"));
    }

    private void loadWeekDate() {
        List<String> weekDays = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        rvWeekDays.setLayoutManager(new GridLayoutManager(getContext(), 7));
        WeekDayAdapter adapter = new WeekDayAdapter(getContext(), weekDays);
        rvWeekDays.setAdapter(adapter);
    }

    private void loadCalendar() {
        // Lấy tháng và năm hiện tại
        String month = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
        int year = calendar.get(Calendar.YEAR);
        tvMonthYear.setText(month + " " + year);

        // Tạo danh sách ngày
        List<String> dates = new ArrayList<>();
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);

        // Lấy ngày bắt đầu của tuần (0: Chủ Nhật, 1: Thứ Hai, ...)
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1;

        // Xử lý tháng trước
        Calendar previousMonth = (Calendar) calendar.clone();
        previousMonth.add(Calendar.MONTH, -1);
        int lastDayOfPreviousMonth = previousMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = firstDayOfWeek - 1; i >= 0; i--) {
            dates.add(String.valueOf(lastDayOfPreviousMonth - i));
        }

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            dates.add(String.valueOf(i));
        }

        Calendar nextMonth = (Calendar) calendar.clone();
        nextMonth.add(Calendar.MONTH, 1);
        int nextMonthDay = 1;

        while (dates.size() < 35) {
            dates.add(String.valueOf(nextMonthDay));
            nextMonthDay++;
        }

        // Gán adapter cho GridView
        adapter = new CalendarAdapter(getContext(), dates,calendar);
        gvCalendar.setAdapter(adapter);
        gvCalendar.setOnItemClickListener((adapterView, view, position, id) -> {
            adapter.setSelectedPosition(position);
            adapter.notifyDataSetChanged();
        });
    }


}
