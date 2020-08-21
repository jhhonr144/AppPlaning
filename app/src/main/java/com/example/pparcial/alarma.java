package com.example.pparcial;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class alarma extends AppCompatActivity {

    Spinner comboPersonas;
    Spinner comboProducto;

    ArrayList<String> listaPersonas;
    ArrayList<String> listaProductos;

    ArrayList<Usuarios> personasList;
    ArrayList<Productos> productosList;

    ConexionSQLite conn,conn2,conn3;

    TextView txtPagar,txtDebe;



    private EditText t4, t3,t5,t6,t7,abono,anotaciones;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;
    private ContentValues registro;
    private EditText tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate,calcular;
    // date
    private int year;
    private int month;
    private int day;

    private String imei;

    static final int DATE_DIALOG_ID = 999;
    // hora
    private int minute, resultado,aux2;
    private int hour;
    private TimePicker timePicker;
    private TextView textViewTime;
    private Button button;
    private static final int TIME_DIALOG_ID = 998;
    Calendar calendario = Calendar.getInstance();
    int hora, min,dia,mes,ano,hora11;
    String cadenaF, cadenaH,fecha_sistema,hora_sistema;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        servicio();

        conn=new ConexionSQLite(getApplicationContext(),"clientes",null,1);
        conn2=new ConexionSQLite(getApplicationContext(),"producto",null,1);
        conn3=new ConexionSQLite(getApplicationContext(),"venta",null,1);


        admin = new AdminSQLiteOpenHelper(this, vars.bd, null, vars.version);
        bd = admin.getWritableDatabase();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH)+1;
        ano = calendario.get(Calendar.YEAR);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        fecha_sistema=mes+"-"+dia+"-"+ano+" ";
        hora_sistema = hora+":"+min;
        setCurrentDateOnView();
        addListenerOnButton();
        // hora
        setCurrentTimeOnView();
        t3 = (EditText) findViewById(R.id.editText21);
        t5= (EditText) findViewById(R.id.editText22);
        t6= (EditText) findViewById(R.id.editText23);
        t7= (EditText) findViewById(R.id.editText24);

        abono=(EditText)findViewById(R.id.abono2);
        anotaciones=(EditText)findViewById(R.id.anotaciones2);







        comboPersonas= (Spinner) findViewById(R.id.spinnerCliente2);
        comboProducto= (Spinner) findViewById(R.id.spinnerProduc2);

        txtPagar= (TextView) findViewById(R.id.pagar2);
        txtDebe= (TextView) findViewById(R.id.debe2);


        consultarListaPersonas();
        consultarListaProductos();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);

        comboPersonas.setAdapter(adaptador);


        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaProductos);

        comboProducto.setAdapter(adaptador2);


        comboProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                    txtPagar.setText(productosList.get(position-1).getPrecio().toString());

                    imei =  productosList.get(position-1).getImei();



















                }else{
                    txtPagar.setText("");


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        abono.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //cuando preciona enter






                    return true;
                }
                return false;
            }
        });


        abono.addTextChangedListener(new TextWatcher() {

            // Antes de que el texto cambie (no debemos modificar nada aquí)
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            //Cuando esté cambiando...(no debemos modificar el texto aquí)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                int v1 = (int) Double.parseDouble(txtPagar.getText().toString());
                int v2 = Integer.parseInt(abono.getText().toString()) ;
                double total = v1-v2;


                String resu=String.valueOf(total);

                txtDebe.setText(resu);

            }

            /*
             * Aquí el texto ya ha cambiado completamente, tenemos el texto actualizado en pocas palabras
             *

             * */
            @Override
            public void afterTextChanged(Editable s) {
                String elNuevoTexto = s.toString();
                // Hacer lo que sea con elNuevoTexto

            }
        });





    }
    public void servicio() {

        startService(new Intent(this, MyAlarmReceiver.class));
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis(); //first run of alarm is immediate // aranca la palicacion
        int intervalMillis = 1 * 3 * 1000; //3 segundos
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);

    }


    public void llenar111(View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, vars.bd, null, vars.version);
        SQLiteDatabase bd = admin.getReadableDatabase();
        bd = admin.getWritableDatabase();
        registro = new ContentValues();
        registro.put("encabezado", t3.getText().toString());
        registro.put("mensaje", t5.getText().toString());//nombre del campo
        registro.put("fecha", t6.getText().toString());
        registro.put("hora", t7.getText().toString());
        bd.insert("alarma", null, registro);//nombre de la tabla
        bd.close();

        registrarVenta();
        t3.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        Toast.makeText(this, "alarma registrada", Toast.LENGTH_LONG).show();



    }
    public void setCurrentTimeOnView() {

        textViewTime = (EditText) findViewById(R.id.editText24);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);



    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.button28);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });
        // hora

        button = (Button) findViewById(R.id.button29);
        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }
    // hora

    public void setCurrentDateOnView() {

        tvDisplayDate = (EditText) findViewById(R.id.editText23);
        dpResult = (DatePicker) findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case TIME_DIALOG_ID:



                return new TimePickerDialog(this, timePickerListener, hour, minute,false);

        }
        return null;
    }
    // hora
    private TimePickerDialog.OnTimeSetListener timePickerListener =  new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

            hour = selectedHour;

            minute = selectedMinute;
            textViewTime.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);

        }



    };


    private static String padding_str(int c) {

        if (c >= 10)
            return String.valueOf(c);

        else

            return "0" + String.valueOf(c);
    }

    // hora

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };


    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuarios persona=null;
        personasList =new ArrayList<Usuarios>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona=new Usuarios();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setApellido(cursor.getString(2));



            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Apellido",persona.getApellido());





            personasList.add(persona);

        }
        obtenerLista();
    }


    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add(" ");

        for(int i=0;i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getNombre()+"  "+personasList.get(i).getApellido());
        }

    }

    private void consultarListaProductos() {
        SQLiteDatabase db=conn2.getReadableDatabase();

        Productos productos=null;
        productosList=new ArrayList<Productos>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ConexionSQLite.TABLA_PRODUCTO,null);

        while (cursor.moveToNext()){
            productos=new Productos();
            productos.setId(cursor.getString(0));
            productos.setNombre(cursor.getString(1));
            productos.setPrecio(cursor.getString(2));
            productos.setImei(cursor.getString(3));







            productosList.add(productos);

        }
        obtenerListaProducto();
    }

    public void deleteCliente() {
        SQLiteDatabase db=conn2.getReadableDatabase();


        String where = Utilidades.CAMPO_IMEI + "= ?";
        db.delete(Utilidades.TABLA_PRODUCTO, where, new String[]{String.valueOf(imei)});




    }

    private void obtenerListaProducto() {
        listaProductos=new ArrayList<String>();
        listaProductos.add(" ");

        for(int i=0;i<productosList.size();i++){
            listaProductos.add(productosList.get(i).getNombre()+"   "+productosList.get(i).getImei());
        }

    }


    private void registrarVenta() {
        ConexionSQLite conn3=new ConexionSQLite(this,"venta",null,1);

        SQLiteDatabase db=conn3.getWritableDatabase();

        ContentValues values=new ContentValues();


        values.put(Utilidades.CAMPO_NOMBREPRO,comboProducto.getSelectedItem().toString());

        values.put(Utilidades.CAMPO_NOMBRECLIE,comboPersonas.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_TOTALPAGAR,txtPagar.getText().toString());
        values.put(Utilidades. CAMPO_TOTALDEBE,txtDebe.getText().toString());
        values.put(Utilidades. CAMPO_TOTALABONO,abono.getText().toString());
        values.put(Utilidades. CAMPO_ANOTACIONES,anotaciones.getText().toString());
        values.put(Utilidades. CAMPO_HORA,t7.getText().toString());
        values.put(Utilidades. CAMPO_FECHA,t6.getText().toString());
        values.put(Utilidades. CAMPO_TITULO,t3.getText().toString());
        values.put(Utilidades. CAMPO_MENSAJE,t5.getText().toString());





        Long idResultante=db.insert(Utilidades.TABLA_VENTA,Utilidades.CAMPO_ID_VENTA,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();


        deleteCliente();
    }




}