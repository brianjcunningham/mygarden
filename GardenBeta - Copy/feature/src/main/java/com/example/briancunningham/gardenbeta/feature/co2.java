package com.example.briancunningham.gardenbeta.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class co2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();

        verynicedatapoint lovelydata = new verynicedatapoint(1,2,3,4,6,7,8,9,10,9,12,false);
        verynicedatapoint testranddata = new verynicedatapoint();
            Log.d("randotest","randomly gend airtemp level is " + testranddata.getAirtemplevel());
        Log.d("randotest","randomly gend amb humdty level is " + testranddata.getAmbienthumiditylevel());
        Log.d("randotest","randomly gend canopyheight level is " + testranddata.getCanopyheightlevel());
        Log.d("randotest","randomly gend co2 level is " + testranddata.getCo2level());
        Log.d("randotest","randomly gend do level is " + testranddata.getDolevel());


        TextView tvDeelon = findViewById(R.id.textViewdeelon);
        tvDeelon.setText(lovelydata.getDatapointdatetime());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Snackbar.make(, "asdasdasdasd", Snackbar.LENGTH_LONG)
        //  .setAction("Action", null).show();

        if (id == R.id.airtemplearn) {
            Intent myIntent = new Intent(co2.this, co2learn.class);
            co2.this.startActivity(myIntent);
            return true;
        }
        if (id == R.id.airtemptolerances) {
            Intent myIntent = new Intent(co2.this, co2tolerances.class);
            co2.this.startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
