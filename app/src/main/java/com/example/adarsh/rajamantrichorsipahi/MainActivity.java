package com.example.adarsh.rajamantrichorsipahi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);
        final EditText ed1 = (EditText) findViewById(R.id.edittext1);
        final EditText ed2 = (EditText) findViewById(R.id.edittext2);
        final EditText ed3 = (EditText) findViewById(R.id.edittext3);
        final EditText ed4 = (EditText) findViewById(R.id.edittext4);
        final TextView tv = (TextView) findViewById(R.id.tv);
        Button tx = (Button) findViewById(R.id.button);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/xenippa1.ttf");
        tx.setTypeface(custom_font);
        ed1.setTypeface(custom_font);
        ed2.setTypeface(custom_font);
        ed3.setTypeface(custom_font);
        ed4.setTypeface(custom_font);
        tv.setTypeface(custom_font);
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText("");
                ed1.setCursorVisible(true);
            }
        });
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed2.setText("");
                ed2.setCursorVisible(true);
            }
        });
        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed3.setText("");
                ed3.setCursorVisible(true);
            }
        });
        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed4.setText("");
                ed4.setCursorVisible(true);
            }
        });
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().length() == 0)
                    Toast.makeText(getApplicationContext(), "Enter Player 1 Name", Toast.LENGTH_SHORT).show();
                else if (ed2.getText().length() == 0)
                    Toast.makeText(getApplicationContext(), "Enter Player 2 Name", Toast.LENGTH_SHORT).show();
                else if (ed3.getText().length() == 0)
                    Toast.makeText(getApplicationContext(), "Enter Player 3 Name", Toast.LENGTH_SHORT).show();
                else if (ed4.getText().length() == 0)
                    Toast.makeText(getApplicationContext(), "Enter Player 4 Name", Toast.LENGTH_SHORT).show();
                else {

                    Intent intent = new Intent(MainActivity.this, guess.class);
                    intent.putExtra("edittext1", ed1.getText().toString());
                    intent.putExtra("edittext2", ed2.getText().toString());
                    intent.putExtra("edittext3", ed3.getText().toString());
                    intent.putExtra("edittext4", ed4.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
