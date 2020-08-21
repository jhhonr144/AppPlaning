package com.example.pparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recordatorios extends AppCompatActivity {

    ListView listViewPersonas,listViewApellido,listViewTelefono;



    ArrayList<String> listaInformacion;
    ArrayList<String> listaInformacionP;
    ArrayList<String> listaInformacionV;

    ArrayList<Ventas> listaUsuarios;
    ArrayList<Ventas> listaApellido;
    ArrayList<Ventas> listaTelefono;


    ConexionSQLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);

        conn=new ConexionSQLite(getApplicationContext(),"venta",null,1);

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

                String informacion="Producto: "+listaUsuarios.get(pos).getNombreProducto()+"\n";
                informacion+="Cliente: "+listaUsuarios.get(pos).getNombreCliente()+"\n";
                informacion+="Total: "+listaUsuarios.get(pos).getTotalPagar()+"\n";
                informacion+="Debe: "+listaUsuarios.get(pos).getTotalDebe()+"\n";
                informacion+="Abono: "+listaUsuarios.get(pos).getTotalAbono()+"\n";
                informacion+="Anotaciones: "+listaUsuarios.get(pos).getAnotaciones()+"\n";
                informacion+="Hora: "+listaUsuarios.get(pos).getHora()+"\n";
                informacion+="Fecha: "+listaUsuarios.get(pos).getFecha()+"\n";
                informacion+="Titulo: "+listaUsuarios.get(pos).getTitulo()+"\n";
                informacion+="Mensaje: "+listaUsuarios.get(pos).getMensaje()+"\n";


                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Ventas user=listaUsuarios.get(pos);



                Bundle bundle=new Bundle();
                bundle.putSerializable("venta",user);


            }
        });
    }



    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Ventas ventas=null;
        listaUsuarios=new ArrayList<Ventas>();
        listaApellido=new ArrayList<Ventas>();
        listaTelefono=new ArrayList<Ventas>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ConexionSQLite.TABLA_VENTA,null);

        while (cursor.moveToNext()){
            ventas=new Ventas();

            ventas.setIdventa(cursor.getString(0));
            ventas.setNombreProducto(cursor.getString(1));
            ventas.setNombreCliente(cursor.getString(2));
            ventas.setTotalPagar(cursor.getString(3));
            ventas.setTotalDebe(cursor.getString(4));
            ventas.setTotalAbono(cursor.getString(5));
            ventas.setAnotaciones(cursor.getString(6));
            ventas.setHora(cursor.getString(7));
            ventas.setFecha(cursor.getString(8));
            ventas.setTitulo(cursor.getString(9));
            ventas.setMensaje(cursor.getString(10));





            listaUsuarios.add(ventas);
            listaApellido.add(ventas);
            listaTelefono.add(ventas);
        }
        obtenerLista();
        obtenerLista2();
        obtenerLista3();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getTitulo());
        }

    }
    private void obtenerLista2() {
        listaInformacionP=new ArrayList<String>();

        for (int i=0; i<listaApellido.size();i++){
            listaInformacionP.add(listaApellido.get(i).getHora());
        }

    }
    private void obtenerLista3() {
        listaInformacionV=new ArrayList<String>();

        for (int i=0; i<listaTelefono.size();i++){
            listaInformacionV.add(listaTelefono.get(i).getFecha());
        }

    }
}
