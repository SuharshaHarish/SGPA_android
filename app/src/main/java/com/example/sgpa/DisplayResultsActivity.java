package com.example.sgpa;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

//import android.graphics.fonts.Font;


public class DisplayResultsActivity extends AppCompatActivity {

    private static String FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/Results.pdf" ;
    private static String fileName;
    File file;
    StudentDetails stud;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        final Context context = getApplicationContext();

        TableLayout Results =  findViewById(R.id.tableLayoutResults);

        TableRow row_heading = new TableRow(this);
        row_heading.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        row_heading.setBackgroundColor(BLACK);

        TextView tv1_heading = new TextView(this);

        tv1_heading.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv1_heading.setTextSize(20);
        tv1_heading.setBackgroundColor(WHITE);
        TableRow.LayoutParams lp1_heading = (TableRow.LayoutParams) tv1_heading.getLayoutParams();
        lp1_heading.setMargins(1,1,1,1);
        tv1_heading.setGravity(Gravity.CENTER);
        tv1_heading.setLayoutParams(lp1_heading);
        tv1_heading.setTextColor(Color.argb(255,255,255,255));
        tv1_heading.setBackgroundColor(Color.argb(255,33,33,33));
        tv1_heading.setText("Subject");
        row_heading.addView(tv1_heading);

        TextView tv2_heading = new TextView(this);

        tv2_heading.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TableRow.LayoutParams lp2_heading = (TableRow.LayoutParams) tv2_heading.getLayoutParams();
        lp2_heading.setMargins(1,1,1,1);
        tv2_heading.setLayoutParams(lp2_heading);
        tv2_heading.setBackgroundColor(WHITE);
        tv2_heading.setGravity(Gravity.CENTER);
        tv2_heading.setTextSize(20);
        tv2_heading.setTextColor(Color.argb(255,255,255,255));
        tv2_heading.setBackgroundColor(Color.argb(255,33,33,33));
        tv2_heading.setText("Credits");
        row_heading.addView(tv2_heading);

        TextView tv3_heading = new TextView(this);

        tv3_heading.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TableRow.LayoutParams lp3_heading = (TableRow.LayoutParams) tv3_heading.getLayoutParams();
        lp3_heading.setMargins(1,1,1,1);
        tv3_heading.setLayoutParams(lp3_heading);
        tv3_heading.setGravity(Gravity.CENTER);
        tv3_heading.setBackgroundColor(WHITE);
        tv3_heading.setTextSize(20);
        tv3_heading.setTextColor(Color.argb(255,255,255,255));
        tv3_heading.setBackgroundColor(Color.argb(255,33,33,33));
        tv3_heading.setText("Grade");
        row_heading.addView(tv3_heading);
        Results.setStretchAllColumns(true);
        Results.addView(row_heading,0);

        Intent in = getIntent();
        final StudentResults [] student = (StudentResults[]) in.getSerializableExtra("com.example.sgpa.Results");

        TextView result1 = findViewById(R.id.textViewSGPA);

        float credit =0;
        float ctotal = 0;
        float cptotal = 0;
        final int num_sub = student.length;

        for(int i=0;i<num_sub;i++) {
            String grade1 = student[i].grade;
            char grade = grade1.charAt(0);
            String subject = student[i].subject;

            try{
                credit = Float.parseFloat(student[i].credits);
            }
            catch (NumberFormatException e){
                credit = 0;
            }
            ctotal = ctotal + credit;


            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            TextView tv1 = new TextView(this);

            tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tv1.setTextSize(20);
            tv1.setTextColor(Color.argb(255,0,0,0));
            tv1.setBackgroundColor(Color.argb(255,238, 238, 238));
            TableRow.LayoutParams lp1 = (TableRow.LayoutParams) tv1.getLayoutParams();
            lp1.setMargins(1,1,1,1);
            tv1.setGravity(Gravity.CENTER);
            tv1.setLayoutParams(lp1);
            tv1.setText(subject);
            row.addView(tv1);

            TextView tv2 = new TextView(this);

            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            TableRow.LayoutParams lp2 = (TableRow.LayoutParams) tv1.getLayoutParams();
            lp2.setMargins(1,1,1,1);
            tv2.setLayoutParams(lp2);
            tv2.setGravity(Gravity.CENTER);
            tv2.setTextSize(20);
            tv2.setText(student[i].credits);
            tv2.setTextColor(Color.argb(255,0,0,0));
            tv2.setBackgroundColor(Color.argb(255,238, 238, 238));
            row.addView(tv2);


            TextView tv3 = new TextView(this);

            tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            TableRow.LayoutParams lp3 = (TableRow.LayoutParams) tv1.getLayoutParams();
            lp3.setMargins(1,1,1,1);
            tv3.setLayoutParams(lp3);
            tv3.setGravity(Gravity.CENTER);
            tv3.setBackgroundColor(Color.argb(255,238, 238, 238));
            tv3.setTextSize(20);
            tv3.setText(grade1);


            switch (grade) {
                case 'S':
                    cptotal += credit * 10;
                    tv3.setTextColor(Color.argb(255,255,191,0));
                    break;
                case 'A':
                    cptotal += credit * 9;
                    tv3.setTextColor(Color.argb(255,255,110,39));
                    break;
                case 'B':
                    cptotal += credit * 8;

                    tv3.setTextColor(Color.argb(255,124,0,255));
                    break;
                case 'C':
                    cptotal += credit * 7;
                    tv3.setTextColor(Color.argb(255,62,193,213));
                    break;
                case 'D':
                    cptotal += credit * 5;
                    tv3.setTextColor(Color.argb(255,100,221,23));
                    break;
                case 'E':
                    cptotal += credit * 4;
                    tv3.setTextColor(Color.argb(255,33,100,103));
                    break;
                case 'F':
                    cptotal += credit * 0;
                    tv3.setTextColor(Color.argb(255,237,28,36));
                    break;
                case 'X':
                    cptotal += credit * 0;
                    tv3.setTextColor(Color.argb(255,158,158,158));
                    break;
            }

            tv3.setTypeface(null, Typeface.BOLD);
            row.addView(tv3);
            Results.setBackgroundColor(BLACK);
            Results.setStretchAllColumns(true);
            Results.addView(row,i+1);
//            Log.d("Credit",Float.toString(credit));
//            Log.d("CValue",grade1);
        }
//        Log.d("CValue",Float.toString(ctotal));
//        Log.d("CPValue",Float.toString(cptotal));
        final float sgpa = cptotal/ctotal;
        result1.setText("SGPA = "+sgpa);

//      Printing the results
        final Button buttonPrint = (Button)findViewById(R.id.buttonPrint);
        buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editTextName = (EditText) findViewById(R.id.editTextViewName);
                final EditText editTextRegNo = (EditText) findViewById(R.id.editTextViewRegNo);
                final EditText editTextClg = (EditText) findViewById(R.id.editTextViewClg);
//                TextView textViewDetails= findViewById(R.id.textViewDetails);
//                textViewDetails.setVisibility(View.VISIBLE);
                editTextName.setVisibility(View.VISIBLE);
                editTextRegNo.setVisibility(View.VISIBLE);
                editTextClg.setVisibility(View.VISIBLE);
                Button buttonSubmit = (Button)findViewById(R.id.buttonSubmit);
                buttonSubmit.setVisibility(View.VISIBLE);
                buttonPrint.setVisibility(View.GONE);
                Toast.makeText(context,"Please enter your Details",Toast.LENGTH_SHORT).show();
            }
        });

        final Button buttonSubmit = (Button)findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {

//                buttonPrint.setVisibility(View.GONE);
                String name;
                String reg_no;
                String clg;
                final EditText editTextName = findViewById(R.id.editTextViewName);
                final EditText editTextRegNo = findViewById(R.id.editTextViewRegNo);
                final EditText editTextClg = findViewById(R.id.editTextViewClg);
//                editTextName.setVisibility(View.VISIBLE);
//                editTextRegNo.setVisibility(View.VISIBLE);
//                editTextClg.setVisibility(View.VISIBLE);
//                Button buttonPrint = (Button)findViewById(R.id.buttonPrint);
                String requiredPermission = "android.permission.WRITE_EXTERNAL_STORAGE";
                int checkVal = context.checkCallingOrSelfPermission(requiredPermission);

                if (checkVal!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show();
                }




                if((editTextName.getText().toString().isEmpty()) || (editTextRegNo.getText().toString().isEmpty()) ||(editTextClg.getText().toString().isEmpty())) {
//
                    name="";
                    reg_no="";
                    clg="";
                }

                else{
                    name = editTextName.getText().toString();
                    reg_no =editTextRegNo.getText().toString();
                    clg = editTextClg.getText().toString();
                    stud = new StudentDetails(name,reg_no,clg);
                }

//                buttonPrint.setVisibility(View.VISIBLE);
                    try {
                        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Calcutta"));
                        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
                        Date now = new Date();
//                        Log.d("Date",formatter.format(now));
                        String fileName =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/Results-"+ formatter.format(now)+".pdf";
                        Document document = new Document();

                        PdfWriter.getInstance(document, new FileOutputStream(fileName));

//                        PdfWriter.getInstance(document, new FileOutputStream(FILE));

                        document.open();
                        addMetaData(document);
                        addContent(document);
                        document.close();


                        File dir = new File("//sdcard//Download//");

                        File file = new File(dir, "/Results-"+ formatter.format(now)+".pdf");


                        DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                        if (downloadManager!=null) {

                            downloadManager.addCompletedDownload(file.getName(), file.getName(), true, "application/pdf", file.getAbsolutePath(), file.length(), true);
                            Toast.makeText(context, "Download Success", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

            }

            private void addMetaData(Document document) {
                document.addTitle("My first PDF");
                document.addSubject("Using iText");
                document.addKeywords("Java, PDF, iText");
                document.addAuthor("Suharsha");
                document.addCreator("Suharsha");
            }

            private void addContent(Document document) throws DocumentException {

                Paragraph preface = new Paragraph();
                // We add one empty line
                addEmptyLine(preface, 1);
                // Lets write a big header
                Paragraph Title = new Paragraph("Result Sheet", catFont);
                Title.setAlignment(Element.ALIGN_CENTER);
                preface.add(Title);

                Paragraph paragraph = new Paragraph();
                addEmptyLine(paragraph, 2);
                preface.add(paragraph);

                preface.add(new Paragraph("Name : "+stud.name,subFont));
                preface.add(new Paragraph("Register no : "+stud.reg_no,subFont));
                preface.add(new Paragraph("College : "+stud.clg,subFont));

                Paragraph paragraph1 = new Paragraph();
                addEmptyLine(paragraph1, 5);
                preface.add(paragraph1);


                PdfPTable table = new PdfPTable(3);

                PdfPCell c1 = new PdfPCell(new Phrase("Subject",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

//                c1.setBorderColor(BaseColor.GRAY);
//                c1.setPadding(4);
//                c1.setSpacing(4);
//                c1.setBorderWidth(1);

                c1 = new PdfPCell(new Phrase("Credits",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Grade",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                table.setHeaderRows(1);

                for (StudentResults studentResults : student) {

                    String grade1 = studentResults.grade;
                    String subject = studentResults.subject;
                    float credit;

                    try {
                        credit = Float.parseFloat(studentResults.credits);
                    } catch (NumberFormatException e) {
                        credit = (float) 0;
                    }

                    c1 = new PdfPCell(new Phrase(subject));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(grade1));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(Float.toString(credit)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                }

                // add a table
                preface.add(table);

                Paragraph paragraph2 = new Paragraph();
                addEmptyLine(paragraph2, 5);
                preface.add(paragraph2);

                Paragraph paragraph3 = new Paragraph("SGPA = " + sgpa, subFont);
                paragraph3.setAlignment(Element.ALIGN_CENTER);
                preface.add(paragraph3);

                // now add all this to the document
                document.add(preface);

            }

            private void addEmptyLine(Paragraph paragraph, int number) {
                for (int i = 0; i < number; i++) {
                    paragraph.add(new Paragraph(" "));
                }
            }


        });
    }
}
