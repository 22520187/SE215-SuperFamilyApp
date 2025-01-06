package com.example.se215_superfamilyapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RepeatActivity extends AppCompatActivity {
    private int flag=0;
    private ImageButton btnBack;
    private MaterialButton btnSave;
    private EditText repeatInterval, afterOccurrences, endDateInput;
    private Spinner repeatUnit;
    private ToggleButton btMon, btTue, btWed, btThu, btFri, btSat, btSun;
    private CheckBox endNever, radioOn, endAfterOccurrences;
    LinearLayout lo_on_date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);

        // Map UI components
        lo_on_date= findViewById(R.id.lo_on_date);
        btnBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_save);
        repeatInterval = findViewById(R.id.repeat_interval);
        repeatUnit = findViewById(R.id.repeat_unit);
        btMon = findViewById(R.id.btMon);
        btTue = findViewById(R.id.btTue);
        btWed = findViewById(R.id.btWed);
        btThu = findViewById(R.id.btThu);
        btFri = findViewById(R.id.btFri);
        btSat = findViewById(R.id.btSat);
        btSun = findViewById(R.id.btSun);

        endNever = findViewById(R.id.end_never);
        radioOn = findViewById(R.id.radioOn);
        endAfterOccurrences = findViewById(R.id.end_after_occurrences);


        afterOccurrences = findViewById(R.id.after_occurrences);
        endDateInput = findViewById(R.id.editTextDate);

        repeatInterval.setInputType(InputType.TYPE_CLASS_NUMBER);
        afterOccurrences.setInputType(InputType.TYPE_CLASS_NUMBER);
        // Set up CheckBox logic to behave like RadioButton
        endNever.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                radioOn.setChecked(false);
                endAfterOccurrences.setChecked(false);
                selectedId=0;
            }
        });

        radioOn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                endNever.setChecked(false);
                endAfterOccurrences.setChecked(false);
                selectedId=1;
            }
        });

        endAfterOccurrences.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                endNever.setChecked(false);
                radioOn.setChecked(false);
                selectedId=2;
            }
        });
        // Setup Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.repeat_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatUnit.setAdapter(adapter);
        repeatUnit.setSelection(2);
        repeatUnit.setDropDownVerticalOffset(10);
        repeatUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("week")) {
                    lo_on_date.setVisibility(View.VISIBLE);
                } else {
                    lo_on_date.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        endDateInput.setInputType(InputType.TYPE_NULL);
        endDateInput.setOnClickListener(view -> showDatePickerDialog());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });
        btnSave.setOnClickListener(view -> saveRepeatOptions());
    }

    private void saveRepeatOptions() {

        String interval = repeatInterval.getText().toString();
        String unit = repeatUnit.getSelectedItem().toString();
        List<String> selectedDays = getSelectedDays();
        String endRepeat = getSelectedEndRepeatOption();

        StringBuilder result = new StringBuilder("Repeat every " + interval + " " + unit);
        if (unit.equals("week")) {
            result.append(" On ");
            for (String day : selectedDays) {
                result.append(day).append(", ");
            }
            if (!selectedDays.isEmpty()) {
                result.setLength(result.length() - 2);
            }
        }
        result.append(", end repeat: ").append(endRepeat);
        Log.d("Repeat", result.toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra("repeatOptions", result.toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    int selectedId=-1;
    private String getSelectedEndRepeatOption() {
        if (selectedId == 0) {
            return "Never";
        } else if (selectedId == 1) {
            return "On " + endDateInput.getText().toString();
        } else if (selectedId == 2) {
            return "After " + afterOccurrences.getText().toString() + " occurrences";
        }
        return "";
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    endDateInput.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    private List<String> getSelectedDays() {
        List<String> selectedDays = new ArrayList<>();
        if (btMon.isChecked()) selectedDays.add("Monday");
        if (btTue.isChecked()) selectedDays.add("Tuesday");
        if (btWed.isChecked()) selectedDays.add("Wednesday");
        if (btThu.isChecked()) selectedDays.add("Thursday");
        if (btFri.isChecked()) selectedDays.add("Friday");
        if (btSat.isChecked()) selectedDays.add("Saturday");
        if (btSun.isChecked()) selectedDays.add("Sunday");
        return selectedDays;
    }
}
