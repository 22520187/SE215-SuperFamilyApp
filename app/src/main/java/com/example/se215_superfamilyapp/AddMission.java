package com.example.se215_superfamilyapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.model.MemberTag;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMission extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;

    private ImageButton btnBack;
    private TextView  tvEndTime, tvRepeat,tv_with_someone,tv_endTime;
    private Button btnSave;
    private EditText etEventName, etDescription;
    private com.example.se215_superfamilyapp.Adapter.MemberTagAdapter memberAdapter;
    private static final int PICK_IMAGE_REQUEST = 2;
    private boolean isUpdateImage=false;
    private ImageView imageView;

    private List<MemberTag> selectedMembers;
    RecyclerView rvMembers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_mission);
        imageView = findViewById(R.id.display_image);
        btnBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_send);
        etEventName = findViewById(R.id.mission_name);
        tv_endTime= findViewById(R.id.tv_end_time_real);
        tvEndTime = findViewById(R.id.tv_end_time);
        tvRepeat = findViewById(R.id.tv_repeat);
        etDescription = findViewById(R.id.et_description);
        tv_with_someone=findViewById(R.id.tv_with_someone);
        rvMembers=findViewById(R.id.rvMembers);
        selectedMembers = new ArrayList<>();
        selectedMembers.add(new MemberTag("", true));

        rvMembers=findViewById(R.id.rvMembers);
        selectedMembers = new ArrayList<>();
        selectedMembers.add(new MemberTag("", true)); // Add button
        memberAdapter = new com.example.se215_superfamilyapp.Adapter.MemberTagAdapter(selectedMembers, new com.example.se215_superfamilyapp.Adapter.MemberTagAdapter.OnMemberClickListener() {
            @Override
            public void onRemoveClick(int position) {
                selectedMembers.remove(position);
                memberAdapter.notifyItemRemoved(position);
                if(!selectedMembers.isEmpty()){
                    rvMembers.setVisibility(View.VISIBLE);
                    tv_with_someone.setVisibility(View.GONE);
                }
                else {
                    rvMembers.setVisibility(View.GONE);
                    tv_with_someone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAddClick() {
                showFamilyMembersBottomSheet();
            }
        }
        );
        rvMembers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMembers.setAdapter(memberAdapter);

        // Add event listeners
        setupListeners();
        setDefaultDate();



    }

    private void setupListeners() {
        // Back button functionality
        btnBack.setOnClickListener(view -> finish());

        // Save button functionality
        btnSave.setOnClickListener(view -> {
            String eventName = etEventName.getText().toString();
            String description = etDescription.getText().toString();

            if (eventName.isEmpty()) {
                Toast.makeText(this, "Mission name cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(selectedMembers.isEmpty())
            {
                Toast.makeText(this,"Please select at least one member to do this task!",Toast.LENGTH_SHORT).show();
                return;
            }
            finish();
            // You can save event details to the database here
            Toast.makeText(this, "Mission sent: " + eventName, Toast.LENGTH_SHORT).show();
        });

        // Start day click functionality


        tvEndTime.setOnClickListener(view -> {
            showDatePicker();
        });
        tv_endTime.setOnClickListener(view->{
            showEndTimeTimePicker();
        });

        tvRepeat.setOnClickListener(view -> {
            showRepeatOptionsDialog();
        });
        tv_with_someone.setOnClickListener(view -> {
            showFamilyMembersBottomSheet();
        });
    }
    private void showFamilyMembersBottomSheet() {
        String[] familyMembers = {"Jane Smith", "Loki Haul", "J Cole", "Rashfrosh"};

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        ListView lvMembers = bottomSheetView.findViewById(R.id.lv_members);
        TextView tvCancel = bottomSheetView.findViewById(R.id.tv_cancel);
        TextView tvAdd = bottomSheetView.findViewById(R.id.tv_add);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, familyMembers);
        lvMembers.setAdapter(adapter);

        tvCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());

        tvAdd.setOnClickListener(v -> {
            SparseBooleanArray checkedItems = lvMembers.getCheckedItemPositions();
            for (int i = 0; i < checkedItems.size(); i++) {
                int key = checkedItems.keyAt(i);
                if (checkedItems.get(key)) {
                    selectedMembers.add(selectedMembers.size() - 1, new MemberTag(familyMembers[key], false));
                }
            }
            if(!selectedMembers.isEmpty()){
                rvMembers.setVisibility(View.VISIBLE);
                tv_with_someone.setVisibility(View.GONE);
            }
            else {
                rvMembers.setVisibility(View.GONE);
                tv_with_someone.setVisibility(View.VISIBLE);
            }
            memberAdapter.notifyDataSetChanged();
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }

    private void setDefaultDate() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Set the default date in the TextView
        String currentDate = day + "/" + (month + 1) + "/" + year;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String repeatOptions = data.getStringExtra("repeatOptions");
            tvRepeat.setText(repeatOptions);
        }

        else  {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                imageView.setImageURI(selectedImageUri);
            }
        }
    }
    private void showRepeatOptionsDialog() {
        String[] repeatOptions = {"No repeat", "Everyday", "Everymonth", "Everyear", "Custom"};
        final int[] checkedItem = {-1};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(repeatOptions, checkedItem[0], (dialog, which) -> {
                    checkedItem[0] = which;
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    if (checkedItem[0] != -1) {
                        String selectedOption = repeatOptions[checkedItem[0]];
                        if (selectedOption.equals("Custom")) {
                            // Start RepeatActivity for custom repeat options
                            Intent intent = new Intent(this, RepeatActivity.class);
                            startActivityForResult(intent, 1);
                        } else {
                            tvRepeat.setText(selectedOption);
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDatePicker() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    // Format selected date (Month is 0-based, so add 1)
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    TextView endDate=findViewById(R.id.tv_end_time);

                    endDate.setText(selectedDate);


                    // Optional: Show a Toast with the selected date
                    Toast.makeText(this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                },
                year,
                month,
                day
        );

        // Show the DatePickerDialog
        datePickerDialog.show();
    }
    private void showStartTimeTimePicker() {
        // Get the current time
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (TimePicker view, int selectedHour, int selectedMinute) -> {
                    // Format selected time
                    String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);

                    // Display the selected time in the TextView


                    // Optional: Show a Toast with the selected time
                },
                hour,
                minute,
                true // Use 24-hour format
        );

        // Show the TimePickerDialog
        timePickerDialog.show();
    }
    private void showEndTimeTimePicker() {
        // Get the current time
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (TimePicker view, int selectedHour, int selectedMinute) -> {
                    // Format selected time
                    String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);

                    // Display the selected time in the TextView
                    tv_endTime.setText(selectedTime);

                    // Optional: Show a Toast with the selected time
                },
                hour,
                minute,
                true // Use 24-hour format
        );

        // Show the TimePickerDialog
        timePickerDialog.show();
    }
}