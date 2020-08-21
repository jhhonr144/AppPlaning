package com.example.pparcial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class lista extends AppCompatActivity {
    ListView listViewPersonas,listViewApellido,listViewTelefono;



    ArrayList<String> listaInformacion;
    ArrayList<String> listaInformacionP;
    ArrayList<String> listaInformacionV;

    ArrayList<Usuarios> listaUsuarios;
    ArrayList<Usuarios> listaApellido;
    ArrayList<Usuarios> listaTelefono;


    ConexionSQLite conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        conn=new ConexionSQLite(getApplicationContext(),"clientes",null,1);

        listViewPersonas= (ListView) findViewById(R.id.listViewNombre);
        listViewApellido= (ListView) findViewById(R.id.listViewApellido);
        listViewTelefono= (ListView) findViewById(R.id.listViewTelefono);


        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        ArrayAdapter adaptador2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacionP);
        listViewApellido.setAdapter(adaptador2);
        ArrayAdapter adaptador3=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacionV);
        listViewTelefono.setAdapter(adaptador3);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="Cedula: "+listaUsuarios.get(pos).getId()+"\n";
                informacion+="Nombre: "+listaUsuarios.get(pos).getNombre()+"\n";
                informacion+="Apellido: "+listaUsuarios.get(pos).getApellido()+"\n";
                informacion+="Telefono: "+listaUsuarios.get(pos).getTelefono()+"\n";
                informacion+="Correo: "+listaUsuarios.get(pos).getCorreo()+"\n";
                informacion+="Referencia: "+listaUsuarios.get(pos).getReferencia()+"\n";
                informacion+="Imagen: "+listaUsuarios.get(pos).getImagen()+"\n";


                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Usuarios user=listaUsuarios.get(pos);



                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);


            }
        });

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuarios usuario=null;
        listaUsuarios=new ArrayList<Usuarios>();
        listaApellido=new ArrayList<Usuarios>();
        listaTelefono=new ArrayList<Usuarios>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ConexionSQLite.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuario=new Usuarios();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            usuario.setTelefono(cursor.getString(3));
            usuario.setCorreo(cursor.getString(4));
            usuario.setReferencia(cursor.getString(5));
            usuario.setImagen(cursor.getString(6));




            listaUsuarios.add(usuario);
            listaApellido.add(usuario);
            listaTelefono.add(usuario);
        }
        obtenerLista();
        obtenerLista2();
        obtenerLista3();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getNombre());
        }

    }
    private void obtenerLista2() {
        listaInformacionP=new ArrayList<String>();

        for (int i=0; i<listaApellido.size();i++){
            listaInformacionP.add(listaApellido.get(i).getApellido());
        }

    }
    private void obtenerLista3() {
        listaInformacionV=new ArrayList<String>();

        for (int i=0; i<listaTelefono.size();i++){
            listaInformacionV.add(listaTelefono.get(i).getTelefono());
        }

    }
}
