package com.example.se215_superfamilyapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

public class MessagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messager);

        // Tìm RelativeLayout với id rlTony
        LinearLayout rlTony = findViewById(R.id.rlTony);

        // Thêm sự kiện click
        rlTony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở activity_chat
                Intent intent = new Intent(MessagerActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
