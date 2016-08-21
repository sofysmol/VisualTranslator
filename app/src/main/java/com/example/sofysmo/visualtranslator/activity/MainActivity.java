package com.example.sofysmo.visualtranslator.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.sofysmo.visualtranslator.utils.PreferencesManager;
import com.example.sofysmo.visualtranslator.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnKeyListener {
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

        preferencesManager=PreferencesManager.getInstance();
        preferencesManager.init(getApplicationContext());

        InitAllButtons();
        InitAllSpinners();
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            CardView card=(CardView) findViewById(R.id.output_card);
            card.setVisibility(View.VISIBLE);
            return true;

        }
        return false;
    }
    public void InitAllSpinners(){
        Spinner input_spinner=(Spinner) findViewById(R.id.input_spinner);
        Spinner output_spinner=(Spinner) findViewById(R.id.output_spinner);

        int in_value=preferencesManager.getIntSetting("input_spinner_pos");
        input_spinner.setSelection(in_value);
        int out_value=preferencesManager.getIntSetting("output_spinner_pos");
        output_spinner.setSelection(in_value);

        input_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Object item = parent.getItemAtPosition(pos);
                preferencesManager.saveSetting("input_spinner_pos",pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        input_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Object item = parent.getItemAtPosition(pos);
                preferencesManager.saveSetting("output_spinner_pos",pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_spinner.setAdapter(adapter);
        output_spinner.setAdapter(adapter);
    }
    public void InitAllButtons() {
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
                            Spinner input_spinner=(Spinner) findViewById(R.id.input_spinner);
                            Spinner output_spinner=(Spinner) findViewById(R.id.output_spinner);
                            int t=input_spinner.getSelectedItemPosition();
                            input_spinner.setSelection(output_spinner.getSelectedItemPosition());
                            output_spinner.setSelection(t);
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
                            EditText input_text=(EditText)findViewById(R.id.input_text);
                            input_text.setText("");
                            CardView input_card=(CardView) findViewById(R.id.output_card);
                            input_card.setVisibility(View.INVISIBLE);
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
                boolean selected=true;
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

