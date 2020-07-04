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

public class Eliminar implements Response.Listener<JSONObject>, Response.ErrorListener  {

    Context context;
    int id;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;


    public void eliminarClase(Context context, int id){
        this.context = context;
        this.id = id;

        String url;

        try {
            url = InsertarActivity.IP_SERVER + "php_sw/Eliminar.php?id=" + id;
            requestQueue = Volley.newRequestQueue(context);
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this  ,this);
            requestQueue.add(jsonObjectRequest);

        }
        catch (Exception e){
            Toast.makeText(context, "Error Desconocido---------------", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(context, "Datos Eliminados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "Error Volley Eliminar", Toast.LENGTH_SHORT).show();
    }


}
