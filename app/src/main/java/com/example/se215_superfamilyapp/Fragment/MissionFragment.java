package com.example.se215_superfamilyapp.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.example.se215_superfamilyapp.AddMission;
import com.example.se215_superfamilyapp.GeneralReport;
import com.example.se215_superfamilyapp.MainActivity;
import com.example.se215_superfamilyapp.MissionReport;
import com.example.se215_superfamilyapp.R;
import com.example.se215_superfamilyapp.SubmitReport;

public class MissionFragment extends Fragment {
    private int state=1;
    private Button general;
    private Button personal;
    private Button members;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mission, container, false);
        general= view.findViewById(R.id.general);
        personal = view.findViewById(R.id.personal);
        members = view.findViewById(R.id.members);
        setWatchReport(view);

        return view;
    }
    public void setWatchReport(View view)
    {
      view.findViewById(R.id.watch_report_1).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(state==2) {
                  Intent intent = new Intent(getContext(), GeneralReport.class);
                  startActivity(intent);
                  return;
              }

              Toast.makeText(getContext(), "No reported", Toast.LENGTH_SHORT).show();
          }
      });
        view.findViewById(R.id.watch_report_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state==2) {
                    Intent intent = new Intent(getContext(), GeneralReport.class);
                    startActivity(intent);
                    return;
                }

                Toast.makeText(getContext(), "No reported", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.watch_report_2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(state==2) {
                    Intent intent = new Intent(getContext(), GeneralReport.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getContext(), MissionReport.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.fab_add_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddMission.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.personal).setOnClickListener(new View.OnClickListener() {
            com.google.android.material.floatingactionbutton.FloatingActionButton addBtn=view.findViewById(R.id.fab_add_event) ;
            @Override
            public void onClick(View v) {
                state=1;

                general.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                general.setTextColor(Color.parseColor("#000000"));

                members.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                members.setTextColor(Color.parseColor("#000000"));

                personal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5B7FFE")));
                personal.setTextColor(Color.parseColor("#ffffff"));
                addBtn.setVisibility(View.VISIBLE);

            }
        });
        view.findViewById(R.id.general).setOnClickListener(new View.OnClickListener() {
            com.google.android.material.floatingactionbutton.FloatingActionButton addBtn=view.findViewById(R.id.fab_add_event) ;
            @Override
            public void onClick(View v) {
                state=2;
                personal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                personal.setTextColor(Color.parseColor("#000000"));

                members.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                members.setTextColor(Color.parseColor("#000000"));

                general.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5B7FFE")));
                general.setTextColor(Color.parseColor("#ffffff"));
                addBtn.setVisibility(View.GONE);



            }
        });
        view.findViewById(R.id.members).setOnClickListener(new View.OnClickListener() {
            com.google.android.material.floatingactionbutton.FloatingActionButton addBtn=view.findViewById(R.id.fab_add_event) ;
            @Override
            public void onClick(View v) {
                state=3;
                general.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                general.setTextColor(Color.parseColor("#000000"));

                personal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                personal.setTextColor(Color.parseColor("#000000"));

                members.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5B7FFE")));
                members.setTextColor(Color.parseColor("#ffffff"));

                addBtn.setVisibility(View.GONE);
            }
        });
    }
}
