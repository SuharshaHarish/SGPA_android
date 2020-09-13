package com.example.sgpa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class MainActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    public static MainActivity parentContext;

    EditText [] editTextSubjects = new EditText[9];
    EditText [] editTextCredits = new EditText[9];
    Spinner [] spinnerGrades= new Spinner[9];
    int num_sub=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

//        Can be deleted
        Button calculator =  findViewById(R.id.button);
        calculator.setVisibility(View.GONE);
        TextView result = findViewById(R.id.result);
        result.setVisibility(View.GONE);

        parentLinearLayout =  findViewById(R.id.parent_linear_layout);
        parentContext = MainActivity.this;

        final Spinner spinnerSubject = (Spinner) findViewById(R.id.spinnerSubject);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSubject = ArrayAdapter.createFromResource(this,
                R.array.sub_array,R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSubject.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spinnerSubject.setAdapter(adapterSubject);

        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

//              For displaying correct number of fields
                if(parentLinearLayout.getChildCount()>3) {
                    parentLinearLayout.removeViewsInLayout(1,num_sub);
                }

                String num = String.valueOf(parent.getItemAtPosition(position));
                num_sub = Integer.parseInt(num);
                Log.d("NUM_SUB",num);

                if (num_sub != 0)
                {
                    Button calculator = (Button) findViewById(R.id.button);
                    calculator.setVisibility(View.VISIBLE);
                }


                for (int i = 0; i< num_sub;i++)
                {
                    Log.d("i",String.valueOf(i));
                    // Parent layout
                    LayoutInflater inflater = getLayoutInflater();
                    final View rowView = inflater.inflate(R.layout.sub_view, null);
                    // Add the new row before the add field button.
                    parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() -3);

//                  For displaying Grade Spinner
                    Spinner spinnerGrade = (Spinner) findViewById(R.id.spinnerGrade);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapterGrade = ArrayAdapter.createFromResource(parentContext,
                            R.array.grade_array, R.layout.spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapterGrade.setDropDownViewResource(R.layout.spinner_item);
//

                    EditText editTextSubject = (EditText)findViewById(R.id.editTextSubject);
                    EditText editTextCredit = (EditText)findViewById(R.id.editTextCredit);

                    switch (i){
                        case 0:editTextSubject.setId(R.id.editTextSubject0);editTextCredit.setId(R.id.editTextCredit0);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade0);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 1:editTextSubject.setId(R.id.editTextSubject1);editTextCredit.setId(R.id.editTextCredit1);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade1);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 2:editTextSubject.setId(R.id.editTextSubject2);editTextCredit.setId(R.id.editTextCredit2);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade2);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 3:editTextSubject.setId(R.id.editTextSubject3);editTextCredit.setId(R.id.editTextCredit3);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade3);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 4:editTextSubject.setId(R.id.editTextSubject4);editTextCredit.setId(R.id.editTextCredit4);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade4);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 5:editTextSubject.setId(R.id.editTextSubject5);editTextCredit.setId(R.id.editTextCredit5);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade5);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 6:editTextSubject.setId(R.id.editTextSubject6);editTextCredit.setId(R.id.editTextCredit6);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade6);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 7:editTextSubject.setId(R.id.editTextSubject7);editTextCredit.setId(R.id.editTextCredit7);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade7);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 8:editTextSubject.setId(R.id.editTextSubject8);editTextCredit.setId(R.id.editTextCredit8);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade8);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                        case 9:editTextSubject.setId(R.id.editTextSubject9);editTextCredit.setId(R.id.editTextCredit9);editTextSubjects [i]= editTextSubject;
                            editTextCredits [i]= editTextCredit;spinnerGrade.setId(R.id.spinnerGrade9);spinnerGrades [i]= spinnerGrade;spinnerGrades[i].setAdapter(adapterGrade);
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Button calculate = (Button) findViewById(R.id.button);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentResults [] student = new StudentResults[num_sub];

                for(int i=0;i<num_sub;i++) {

                    EditText editTextCredit = editTextCredits[i];
                    EditText editTextSubject = editTextSubjects[i];
                    Spinner spinnerGrade = spinnerGrades[i];
                    String grade1 = spinnerGrade.getSelectedItem().toString();
//                  Creating object student
                    student[i] = new StudentResults(editTextSubject.getText().toString(), editTextCredit.getText().toString(),grade1);
                }
//              Launch New Activity
                Intent showResults = new Intent(getApplicationContext(),DisplayResultsActivity.class);
                showResults.putExtra("com.example.sgpa.Results", student);
                startActivity(showResults);
            }
        });

    }

}

