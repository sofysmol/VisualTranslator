package com.example.sofysmo.visualtranslator.Activity.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.sofysmo.visualtranslator.Activity.Utils.PreferencesManager;
import com.example.sofysmo.visualtranslator.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemSelectedListener {
    PreferencesManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Spinner input_spinner=(Spinner) findViewById(R.id.input_spinner);
        Spinner output_spinner=(Spinner) findViewById(R.id.output_spinner);

        preferencesManager=PreferencesManager.getInstance();
        preferencesManager.init(getApplicationContext());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_spinner.setAdapter(adapter);
        output_spinner.setAdapter(adapter);
        input_spinner.setOnItemSelectedListener(this);
        output_spinner.setOnItemSelectedListener(this);
        InitAllButton();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dictionary) {
            Intent intent = new Intent(this, DictionaryActivity.class);

            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void InitAllButton() {
        final ImageView replaceImg = (ImageView) findViewById(R.id.replace_button);
        if (replaceImg != null) {
            replaceImg.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {

                            replaceImg.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            replaceImg.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGreyIcon));
                            break;
                        }
                    }

                    return true;
                }

            });
        }
        final ImageView delete_button = (ImageView) findViewById(R.id.delete_button);
        if (delete_button != null) {
            delete_button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            delete_button.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            delete_button.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGreyIcon));
                            break;
                        }
                    }
                    return true;
                }

            });
        }
        final ImageView picturs_button = (ImageView) findViewById(R.id.picturs_button);
        if (picturs_button != null) {
            picturs_button.setOnTouchListener(new View.OnTouchListener() {
                boolean selected=false;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            if(!selected) {
                                picturs_button.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                                selected=true;
                            }
                            else
                            {
                                picturs_button.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGreyIcon));
                                selected=false;
                            }
                            break;
                        }
                    }
                    return true;
                }

            });
        }
        final ImageView speak_output_button = (ImageView) findViewById(R.id.speak_output_button);
        if (speak_output_button != null) {
            speak_output_button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                                speak_output_button.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            speak_output_button.setColorFilter(Color.argb(255,255,255,255));
                            break;
                        }
                    }
                    return true;
                }

            });
        }
        final ImageView speak_input_button = (ImageView) findViewById(R.id.speak_input_button);
        if (speak_input_button != null) {
            speak_input_button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            speak_input_button.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            speak_input_button.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGreyIcon));
                            break;
                        }
                    }
                    return true;
                }

            });
        }
    }
}
