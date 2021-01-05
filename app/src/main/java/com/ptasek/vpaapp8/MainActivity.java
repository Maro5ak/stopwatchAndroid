package com.ptasek.vpaapp8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final String filename = "hugabuga.txt";
    EditText text1, text2;
    Button btnSave, btnLoad, btnAppend, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        btnSave = findViewById(R.id.btnSave);
        btnAppend = findViewById(R.id.btnAppend);
        btnClear = findViewById(R.id.btnClear);
        btnLoad = findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try(FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE)){
                    fos.write(text1.getText().toString().getBytes());
                    Toast.makeText(MainActivity.this, "Le file: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    public void handleLoad(View v) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(filename)));
        String str;
        while((str = br.readLine()) != null){
            text2.append(str + "\n");
        }
    }

    public void handleAppend(View v){
        try(FileOutputStream fos = openFileOutput(filename, MODE_APPEND)){
            fos.write("\n".getBytes());
            fos.write(text1.getText().toString().getBytes());
            Toast.makeText(MainActivity.this, "Le file appended: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handleClear(View v){
        text1.setText("");
        text2.setText("");
    }

}