package com.example.appws;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Modificar implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private int id_extraido;

    private String nombre_medicamento, fecha_vencimiento;
    private int cantidad;
    private double precio;

    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;

    public void modificarclase(Context context, int id_extraido, String nombre_medicamento, int cantidad, double precio, String fecha_vencimiento){
        this.context = context;
        this.id_extraido= id_extraido;
        this.nombre_medicamento = nombre_medicamento;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha_vencimiento = fecha_vencimiento;

        System.out.println("----------------- "+fecha_vencimiento);

        try {
            String url = InsertarActivity.IP_SERVER + "php_sw/Actualizar.php?nombre_medicamento="+nombre_medicamento+"&cantidad="+cantidad+"&precio="+precio+"&fecha_vencimiento="+fecha_vencimiento+"&id="+id_extraido;
            requestQueue = Volley.newRequestQueue(context);
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);

        } catch (Exception e) {
            Toast.makeText(context, "Ha ocurrido un error "+e, Toast.LENGTH_SHORT).show();
            System.out.println("----------------------" +e);
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "Error / "+error, Toast.LENGTH_SHORT).show();
        System.out.println("---------------- Error/ " + error);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(context, "Datos Modificados Correctamente", Toast.LENGTH_SHORT).show();
        System.out.println("---------------- eliminado correctamente" + response);
    }

}
