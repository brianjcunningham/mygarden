package com.example.briancunningham.gardenbeta.feature;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.Date;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.String.valueOf;

public class data_activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        Intent mIntent = getIntent();
        String par_names = mIntent.getStringExtra("parameter_name");
        Log.d("consoleprinting",par_names);
        setTitle(par_names);


        ScrollView sv = (ScrollView) findViewById(R.id.scrviewdo) ;
        LinearLayout ll = (LinearLayout) findViewById(R.id.linlaydo);
        ll.setOrientation(LinearLayout.VERTICAL);

        MyAppApplication mApp = (MyAppApplication)getApplicationContext();
        int mrtesty = mApp.size();
        if (mrtesty!=0) {
            GraphView graph = (GraphView) findViewById(R.id.graph);

            int a = 1;
            int b = mApp.size();
            DataPoint[] values = new DataPoint[b];
            for (a = 0; a < b; a++) {
                Date xi = mApp.getDatapointinDateFormat(a);
                float yi = mApp.getDolevel(a);
                DataPoint v = new DataPoint(xi, yi);
                values[a] = v;
            }
            Log.d("consoleprinting", "exited the for loop");
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(values);
            Log.d("consoleprinting", "made new series");
            graph.addSeries(series);
            graph.getViewport().setScalable(true);
            graph.getViewport().setScrollable(true);
            graph.getViewport().setScalableY(true);
            graph.getViewport().setScrollableY(true);
            graph.getGridLabelRenderer().setVerticalAxisTitle("Dissolved Oxygen in PPM");
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Date/Time");

            // set date label formatter
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));

            TableLayout tbllay = (TableLayout) findViewById(R.id.tbllaydo); // declaring the table-layout from the XML

            final TextView[] datepoints = new TextView[b]; // create an empty array;
            final TextView[] measurepoints = new TextView[b]; // create an empty array;
            final TextView[] actionpoints = new TextView[b]; // create an empty array;

            for (int i = b-1; i >= 0; i--) {    // decrementing loop, so that it goes from most recent to oldest

                // creating new instances for each row and row-element
                final TableRow aNewTableRow = new TableRow(this);
                final TextView rowTextViewA = new TextView(this);
                final TextView rowTextViewB = new TextView(this);
                final TextView rowTextViewC = new TextView(this);

                // set some properties of rowTextView or something
                rowTextViewA.setText(mApp.getDatapointdatetime(i));
                rowTextViewA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                rowTextViewA.setPadding(20,0,0,0);
                String hamzilla = String.valueOf(mApp.getDolevel(i));
                hamzilla = hamzilla + " ppm";
                Log.d("dabug","it's setting hamzilla correctly");
                rowTextViewB.setText(hamzilla);
                rowTextViewB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                rowTextViewB.setPadding(0,0,0,0);
                Log.d("dabug","it's setting rowTextViewB correctly");
                rowTextViewC.setText(mApp.getAction(i));
                rowTextViewC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                rowTextViewC.setPadding(0,0,20,0);
                rowTextViewC.setHeight(60);

                // add the textview to the linearlayout
                aNewTableRow.addView(rowTextViewA); // adding first column element to the new row
                aNewTableRow.addView(rowTextViewB); // adding the second column element to the new row
                aNewTableRow.addView(rowTextViewC); // adding the third column element to the new row
                tbllay.addView(aNewTableRow);   // adding the new row to the table layout

            }
            String hamzilla = String.valueOf(mApp.getDolevel(b-1));
            hamzilla = hamzilla + " ppm";
            TextView nowtemp = findViewById(R.id.do_current_measurement);
            nowtemp.setText(hamzilla);
            TextView nowtime = findViewById(R.id.do_current_time);
            nowtime.setText(mApp.getDatapointdatetime(b-1));

        }
        else {
            TextView tv = new TextView(this);
            tv.setText("No Data Found!");
            ll.addView(tv);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_airtemp, menu);
        return true;
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }
