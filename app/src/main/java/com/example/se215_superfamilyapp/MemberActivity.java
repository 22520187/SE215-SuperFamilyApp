package com.example.se215_superfamilyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.Adapter.MemberNameAdapter;
import com.example.se215_superfamilyapp.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends AppCompatActivity {
    private RecyclerView rvMemberList;
    private MemberNameAdapter memberAdapter;
    private List<Member> memberList;
    private ImageButton btn_back;
    Button viewSchedule;
    String selectedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_member);
        rvMemberList = findViewById(R.id.rv_member_list);
        btn_back =findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewSchedule = findViewById(R.id.viewSchedule);
        viewSchedule.setVisibility(View.GONE);

        memberList = new ArrayList<>();
        memberList.add(new Member(R.drawable.ic_ava, "John Doe",0));
        memberList.add(new Member(R.drawable.ic_ava, "Jane Smith",0));
        memberList.add(new Member(R.drawable.ic_ava, "Alice Brown",0));
        memberList.add(new Member(R.drawable.ic_ava, "Chris Brown",0));


        rvMemberList.setLayoutManager(new LinearLayoutManager(this));
        memberAdapter = new MemberNameAdapter(memberList);
        rvMemberList.setAdapter(memberAdapter);
        memberAdapter.setOnMemberClickListener( selectedMember -> {
            // Show selected member name
            selectedName= selectedMember.getName();

            // Show the viewSchedule button
            viewSchedule.setVisibility(View.VISIBLE);
        });
        viewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedName != null) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selected_member_name", selectedName);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}