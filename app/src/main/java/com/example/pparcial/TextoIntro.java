package com.example.pparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class TextoIntro extends AppCompatActivity {
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_intro);

        imagen =(ImageView)findViewById(R.id.imageView2);

        imagen.setBackgroundColor(Color.parseColor("#FFFFFF"));




    }
    public void onClick(View v) {

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);


    }

}
