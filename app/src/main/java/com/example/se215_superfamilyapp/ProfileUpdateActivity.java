package com.example.se215_superfamilyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ProfileUpdateActivity extends AppCompatActivity {


    private EditText etDateOfBirth;
    private ImageView ivArrowDob;

    private EditText etChangePhoneNumber;
    private ImageView ivArrowCpN;

    private EditText etChangeEmail;
    private ImageView ivArrowCM;

    private EditText etCurrentPass;
    private EditText etNewtPass;

    private TextView txCurrentPass;

    private TextView txNewPass;
    private ImageView ivArrowCp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        // Ánh xạ các thành phần
        etDateOfBirth = findViewById(R.id.et_date_of_birth);
        etChangePhoneNumber = findViewById(R.id.et_phone_number);
        etChangeEmail = findViewById(R.id.et_email);
        etCurrentPass = findViewById(R.id.et_current_pass);
        etNewtPass = findViewById(R.id.et_new_pass);
        txCurrentPass = findViewById(R.id.tx_current_pass);
        txNewPass = findViewById(R.id.tx_new_pass);

        ivArrowDob = findViewById(R.id.iv_arrow_dob);
        ivArrowCpN = findViewById(R.id.iv_arrow_cpn);
        ivArrowCM = findViewById(R.id.iv_arrow_ce);
        ivArrowCp = findViewById(R.id.iv_arrow_cp);

        // Sự kiện nhấn mũi tên
        ivArrowDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDateOfBirth.getVisibility() == View.GONE) {
                    etDateOfBirth.setVisibility(View.VISIBLE); // Hiển thị EditText
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_up); // Đổi mũi tên lên
                } else {
                    etDateOfBirth.setVisibility(View.GONE); // Ẩn EditText
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_down); // Đổi mũi tên xuống
                }
            }
        });


        ivArrowCpN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etChangePhoneNumber.getVisibility() == View.GONE) {
                    etChangePhoneNumber.setVisibility(View.VISIBLE); // Hiển thị EditText
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_up); // Đổi mũi tên lên
                } else {
                    etChangePhoneNumber.setVisibility(View.GONE); // Ẩn EditText
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_down); // Đổi mũi tên xuống
                }
            }
        });

        ivArrowCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etChangeEmail.getVisibility() == View.GONE) {
                    etChangeEmail.setVisibility(View.VISIBLE); // Hiển thị EditText
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_up); // Đổi mũi tên lên
                } else {
                    etChangeEmail.setVisibility(View.GONE); // Ẩn EditText
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_down); // Đổi mũi tên xuống
                }
            }
        });


        ivArrowCp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCurrentPass.getVisibility() == View.GONE) {
                    etCurrentPass.setVisibility(View.VISIBLE);
                    etNewtPass.setVisibility(View.VISIBLE);
                    txCurrentPass.setVisibility(View.VISIBLE);
                    txNewPass.setVisibility(View.VISIBLE);
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_up); // Đổi mũi tên lên
                } else {
                    etCurrentPass.setVisibility(View.GONE);
                    etNewtPass.setVisibility(View.GONE);
                    txCurrentPass.setVisibility(View.GONE);
                    txNewPass.setVisibility(View.GONE);
//                    ivArrowDob.setImageResource(R.drawable.ic_arrow_down); // Đổi mũi tên xuống
                }
            }
        });


    }

}
