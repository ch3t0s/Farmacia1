package com.example.appws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appws.modeloVO.Medicamento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Modificar_Eliminar_Activity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private ArrayList<Medicamento> listamedicamento;
    private EditText id2,nombre, cantidad, precio, fecha;

    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    Eliminar eliminar = new Eliminar();
    Modificar modificar = new Modificar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__eliminar_);

        id2=findViewById(R.id.Idtxt);
        nombre=findViewById(R.id.Nombretxt);
        cantidad=findViewById(R.id.Cantidadtxt);
        precio=findViewById(R.id.preciotxt);
        fecha=findViewById(R.id.fechatxt);

        requestQueue = Volley.newRequestQueue(this);


    }

    public void consultaCompletaSW() {
        String url;
        url = InsertarActivity.IP_SERVER + "/php_sw/Buscar.php?id="+id2.getText().toString();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error" + error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
     this.buscar(response);

    }

    public void buscar (JSONObject respuesta){
        Medicamento medicamento = new Medicamento();

        //OBTENCION DE LA RESPUESTA DE LOS REGISTROS OBTENIDOS POR LA CONSULTA EN EL PHP
        JSONArray jsonArray = respuesta.optJSONArray("tbl_medicamento");
        JSONObject jsonObject= null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            medicamento.setNombre_medicamento(jsonObject.optString("nombre_medicamento"));
            medicamento.setCantidad(jsonObject.optInt("cantidad"));
            medicamento.setPrecio(jsonObject.optDouble("precio"));
            medicamento.setFecha_vencimiento(jsonObject.optString("fecha_vencimiento"));

        } catch (Exception e) {
            Toast.makeText(this, "Error Desconocido", Toast.LENGTH_SHORT).show();
        }
        nombre.setText(medicamento.getNombre_medicamento());
        cantidad.setText(medicamento.getCantidad()+"");
        precio.setText(medicamento.getPrecio()+"");
        fecha.setText(medicamento.getFecha_vencimiento());
    }

    public void onclick3(View view) {
        switch (view.getId()) {
            case R.id.btnBuscar:
                consultaCompletaSW();
                break;
            case R.id.Eliminarbtn:
                eliminar.eliminarClase(getApplicationContext(), Integer.parseInt(id2.getText().toString()));
                id2.setText("");
                nombre.setText("");
                cantidad.setText("");
                precio.setText("");
                fecha.setText("");
                break;

            case R.id.Actualizarbtn:
                modificar.modificarclase(getApplicationContext(),Integer.parseInt(id2.getText().toString()),nombre.getText().toString(),Integer.parseInt(cantidad.getText().toString()),Double.parseDouble(precio.getText().toString()),fecha.getText().toString());
                id2.setText("");
                nombre.setText("");
                cantidad.setText("");
                precio.setText("");
                fecha.setText("");
                break;
        }

    }
}
