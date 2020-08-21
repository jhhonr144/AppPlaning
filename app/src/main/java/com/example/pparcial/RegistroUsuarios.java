package com.example.pparcial;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class RegistroUsuarios extends AppCompatActivity {


    private EditText campoId,campoNombre,campoTelefono,txtApellido,txtCorreo,txtReferencia,urlimg;
    private ImageView imagen;
    private Spinner Spinnerarea,SpinnerDia;


    public ImageView imag,img2;
    public Button CargarImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        txtApellido=(EditText)findViewById(R.id.apellidos);
        campoNombre= (EditText) findViewById(R.id.nombres);
        campoId= (EditText) findViewById(R.id.cedula);
        campoTelefono= (EditText) findViewById(R.id.telefono);
        txtCorreo=(EditText)findViewById(R.id.correo);
        txtReferencia=(EditText)findViewById(R.id.referencia);
        imagen = (ImageView)findViewById(R.id.imageen);
        urlimg= (EditText) findViewById(R.id.url);






        imag = (ImageView)findViewById(R.id.imageen);


        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }




        });





    }

    public void onClick(View view) {
        registrarUsuarios();
        //registrarUsuariosSql();
    }



    private void registrarUsuarios() {
        ConexionSQLite conn=new ConexionSQLite(this,"clientes",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO,txtApellido.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());
        values.put(Utilidades.CAMPO_CORREO,txtCorreo.getText().toString());
        values.put(Utilidades.CAMPO_REFERENCIA,txtReferencia.getText().toString());
        values.put(Utilidades.CAMPO_IMAGEN,urlimg.getText().toString());








        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){


            Uri path = data.getData();
            imagen.setImageURI(path);



            Bitmap bitmap = imagen.getDrawingCache();








        }
    }
    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);
    }




}
