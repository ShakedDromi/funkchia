package com.example.shakeddromi.funkchia;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shakeddromi.funkchia.R;

import java.util.Random;

/**
 * @author Shaked Dromi
 * @since 10/12/19
 * ll- the Linear Layout object
 * colors[]- the color options
 * color[]- the RGB numbers array
 */
public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder adb;
    LinearLayout ll;
    final String[] colors={"Red","Green","Blue"};
    int[]color={0,0,0};
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.ll);
    }

    /**
     * creates the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * when the user tap on the "credits" button, he will go to the credits activity.
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if (st.equals("credits")) {
            si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

    /**
     * this method changes the background color to the one color that was clicked.
     */
    public void rgbcolor(View view) {
        color=new int[]{0,0,0};
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("1th option");
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which]=255;
                ll.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        adb.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad=adb.create();
        ad.show();
    }

    /**
     * this method changes the background color to a combination of the options that were clicked.
     */
    public void mix(View view) {
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("2nd option");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked)
                    color[which]=255;
                else if (color[which]==255)
                        color[which]=0;
                ll.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        adb.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad=adb.create();
        ad.show();
    }

    /**
     * this method cancels the background color- returns it ro white.
     */
    public void white(View view) {
        ll.setBackgroundColor(Color.WHITE);
    }

    /**
     * this method gets a message in an Alert Dialog edit text.
     * if the user clickes on "put in toast"- it shows the message in toast.
     * if the user clicks on "close"- it closes the alert dialog.
     */
    public void income(View view) {
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("4th option");
        final EditText et=new EditText(this);
        et.setHint("type anything");
        adb.setView(et);
        adb.setPositiveButton("put in toast", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str=et.getText().toString();
                Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNeutralButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad=adb.create();       ad.show();
    }
}