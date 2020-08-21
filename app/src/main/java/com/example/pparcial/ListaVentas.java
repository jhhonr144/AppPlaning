package com.example.pparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaVentas extends AppCompatActivity {

    ListView listViewVentas,listViewProdccto,listViewDebe;

    ArrayList<String> listaInformacion;
    ArrayList<String> listaInformacionP;
    ArrayList<String> listaInformacionV;

    ArrayList<Ventas> listaVentas;
    ArrayList<Ventas> listaProductos;
    ArrayList<Ventas> listaDebe;




    ConexionSQLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ventas);



        conn=new ConexionSQLite(getApplicationContext(),"venta",null,1);

        listViewVentas= (ListView) findViewById(R.id.spinnerVentas);

        listViewProdccto= (ListView) findViewById(R.id.spinerProducto);

        listViewDebe= (ListView) findViewById(R.id.spinerDebe);

        consultarListaPersonas();

        ArrayAdapter adaptador2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacionP);
        listViewProdccto.setAdapter(adaptador2);


        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewVentas.setAdapter(adaptador);

        ArrayAdapter adaptador3=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacionV);
        listViewDebe.setAdapter(adaptador3);


        listViewVentas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                String informacion="Producto: "+listaVentas.get(pos).getNombreProducto()+"\n";
                informacion+="Cliente: "+listaVentas.get(pos).getNombreCliente()+"\n";
                informacion+="Total: "+listaVentas.get(pos).getTotalPagar()+"\n";
                informacion+="Debe: "+listaVentas.get(pos).getTotalDebe()+"\n";
                informacion+="Abono: "+listaVentas.get(pos).getTotalAbono()+"\n";
                informacion+="Anotaciones: "+listaVentas.get(pos).getAnotaciones()+"\n";
                informacion+="Hora: "+listaVentas.get(pos).getHora()+"\n";
                informacion+="Fecha: "+listaVentas.get(pos).getFecha()+"\n";
                informacion+="Titulo: "+listaVentas.get(pos).getTitulo()+"\n";
                informacion+="Mensaje: "+listaVentas.get(pos).getMensaje()+"\n";




                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Ventas user=listaVentas.get(pos);



                Bundle bundle=new Bundle();
                bundle.putSerializable("venta",user);


            }
        });

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Ventas ventas=null;
        listaVentas=new ArrayList<Ventas>();
        listaProductos=new ArrayList<Ventas>();
        listaDebe=new ArrayList<Ventas>();
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




            listaVentas.add(ventas);
            listaProductos.add(ventas);
            listaDebe.add(ventas);
        }
        obtenerLista();
        obtenerLista2();
        obtenerLista3();
    }



    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaVentas.size();i++){
            listaInformacion.add(listaVentas.get(i).getNombreCliente());
        }

    }
    private void obtenerLista2() {

        listaInformacionP=new ArrayList<String>();

        for (int i=0; i<listaProductos.size();i++){
            listaInformacionP.add(listaProductos.get(i).getNombreProducto());
        }

    }

    private void obtenerLista3() {

        listaInformacionV=new ArrayList<String>();

        for (int i=0; i<listaDebe.size();i++){
            listaInformacionV.add(listaDebe.get(i).getTotalDebe());
        }

    }
}
