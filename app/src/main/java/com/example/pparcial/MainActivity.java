package com.example.pparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {
    FloatingActionMenu menuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        ConexionSQLite conn = new ConexionSQLite(this,"clientes",null,1);

    }

    public void registrarusu(View view) {
        Intent miIntent=null;
        miIntent=new Intent(MainActivity.this,RegistroUsuarios.class);
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }

    public void registrarProduc(View view) {
        Intent miIntent=null;
        miIntent=new Intent(MainActivity.this,RegistroProductos.class);
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }



    public void spinner(View view) {
        Intent intent = new Intent(getBaseContext(), alarma.class);
        startActivity(intent);
    }


    public void consultarLista(View view) {
        Intent miIntent=null;
        miIntent=new Intent(MainActivity.this,lista.class);
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }

    public void consultarListaVentas(View view) {
        Intent miIntent=null;
        miIntent=new Intent(MainActivity.this,ListaVentas.class);
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }

    public void Recordatorio(View view) {
        Intent miIntent=null;
        miIntent=new Intent(MainActivity.this,Recordatorios.class);
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainActivity.this,RegistroUsuarios.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }
}
