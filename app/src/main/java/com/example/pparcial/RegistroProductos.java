package com.example.pparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistroProductos extends AppCompatActivity {

    private EditText prodNombre,campoPrecio,imei;
    private Button Consultar ;

    ListView listViewProducto,listViewApellido,listViewTelefono;
    ArrayList<String> listaInformacion;
    ArrayList<Productos> listaUsuarios;




    ArrayList<String> listaInformacionP;
    ArrayList<String> listaInformacionV;


    ArrayList<Productos> listaApellido;
    ArrayList<Productos> listaTelefono;

    ConexionSQLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        prodNombre=(EditText)findViewById(R.id.nombreProducto);
        campoPrecio= (EditText) findViewById(R.id.precio);
        imei= (EditText) findViewById(R.id.txtImei);
        Consultar =(Button)findViewById(R.id.agregarProducto);

        listViewApellido= (ListView) findViewById(R.id.listViewNombrep);
        listViewTelefono= (ListView) findViewById(R.id.listViewPrecio);

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuarios();
                obtenerLista();

            }
        });


        conn=new ConexionSQLite(getApplicationContext(),"producto",null,1);

        listViewProducto= (ListView) findViewById(R.id.listViewProduc);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewProducto.setAdapter(adaptador);

        ArrayAdapter adaptador2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacionP);
        listViewApellido.setAdapter(adaptador2);

        ArrayAdapter adaptador3=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacionV);
        listViewTelefono.setAdapter(adaptador3);
        listViewProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="Id: "+listaUsuarios.get(pos).getId()+"\n";
                informacion+="Nombre: "+listaUsuarios.get(pos).getNombre()+"\n";
                informacion+="Precio: "+listaUsuarios.get(pos).getPrecio()+"\n";
                informacion+="Imei: "+listaUsuarios.get(pos).getImei()+"\n";



                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Productos user=listaUsuarios.get(pos);



                Bundle bundle=new Bundle();
                bundle.putSerializable("producto",user);


            }
        });






    }

    public void onClick(View view) {


    }

    private void registrarUsuarios() {
        ConexionSQLite conn=new ConexionSQLite(this,"producto",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_PRODUCTO,prodNombre.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO,campoPrecio.getText().toString());
        values.put(Utilidades.CAMPO_IMEI,imei.getText().toString());



        Long idResultante=db.insert(Utilidades.TABLA_PRODUCTO,Utilidades.CAMPO_ID_PRODUCTO,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();



        consultarListaPersonas();
        obtenerLista();


    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Productos productos=null;
        listaUsuarios=new ArrayList<Productos>();
        listaApellido=new ArrayList<Productos>();
        listaTelefono=new ArrayList<Productos>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ConexionSQLite.TABLA_PRODUCTO,null);

        while (cursor.moveToNext()){
            productos=new Productos();
            productos.setId(cursor.getString(0));
            productos.setNombre(cursor.getString(1));
            productos.setPrecio(cursor.getString(2));
            productos.setImei(cursor.getString(3));




            listaUsuarios.add(productos);
            listaApellido.add(productos);
            listaTelefono.add(productos);
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
            listaInformacionP.add(listaApellido.get(i).getPrecio());
        }

    }
    private void obtenerLista3() {
        listaInformacionV=new ArrayList<String>();

        for (int i=0; i<listaTelefono.size();i++){
            listaInformacionV.add(listaTelefono.get(i).getImei());
        }

    }
}
