package com.example.se215_superfamilyapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.se215_superfamilyapp.ChatActivity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.LinearLayout;
import android.content.Intent;

import com.example.se215_superfamilyapp.R;

public class MessageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        View view = inflater.inflate(R.layout.activity_messager, container, false);

        // Tìm LinearLayout rlTony trong giao diện của Fragment
        LinearLayout rlTony = view.findViewById(R.id.rlTony);

        // Gắn sự kiện click
        rlTony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở ChatActivity
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
