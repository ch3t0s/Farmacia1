package com.example.appws;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;

public class InsertarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    public static final String IP_SERVER = "http://192.168.1.5/";
    private EditText txt1, txt2, txt3, txt4;


    //REALIZA LA CONEXION DIRECTA CON EL SW --php--
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        txt1=findViewById(R.id.txtNomMedi);
        txt2=findViewById(R.id.txtCantidad);
        txt3=findViewById(R.id.txtPrecio);
        txt4= findViewById(R.id.txtFecha);
        txt4.setEnabled(false);

        requestQueue = Volley.newRequestQueue(this);

    }

    public void insertarSW (){
        String  url;
        if (!txt1.getText().toString().isEmpty()&&!txt2.getText().toString().isEmpty()&&!txt3.getText().toString().isEmpty()&&!txt4.getText().toString().isEmpty()){

            try {
                url=IP_SERVER+"/php_sw/insertar_sw.php?nombre_medicamento="+txt1.getText().toString()+"&cantidad="+txt2.getText().toString()+"&precio="+txt3.getText().toString()
                +"&fecha_vencimiento="+txt4.getText().toString();

                jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                requestQueue.add(jsonObjectRequest);
            }
            catch (Exception e){
                Toast.makeText(this, "Error Desconocido", Toast.LENGTH_SHORT).show();
                System.err.println(e.getMessage());

            }

        }
        else{
            Toast.makeText(this, "Error Datos Faltantes", Toast.LENGTH_SHORT).show();
        }

    }


    //CALENDARIO PARA OBTENER FECHA Y HORA
    public final Calendar c = Calendar.getInstance();

    //VARIABLES PARA OBTENER LA FECHA
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    public void FechaCalendario(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? 0 + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? 0 + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Convertir anio
                String anio = String.valueOf(year);

                //Muestro la fecha con el formato deseado
                txt4.setText(mesFormateado + "/" + diaFormateado + "/" + year);


            }
        }, anio,mes,dia);
        recogerFecha.show();

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error referente a "+error, Toast.LENGTH_SHORT).show();
        System.err.println("----------------"+error);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Datos Ingresados Correctamente", Toast.LENGTH_SHORT).show();
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setEnabled(false);
    }





    public void onclick2(View view) {
        switch (view.getId()){
            case R.id.btninsertarM:
                this.insertarSW();
                break;
            case R.id.imgFecha:
                txt4.requestFocus();
                FechaCalendario();
                break;
        }

    }

}
