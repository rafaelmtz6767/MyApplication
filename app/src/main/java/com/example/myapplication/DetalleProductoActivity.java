package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleProductoActivity extends AppCompatActivity {

    private TextView txtNombre, txtPrecio, txtStock, txtDisponibilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        // Enlazamos los componentes del XML
        txtNombre = findViewById(R.id.txtDetalleNombre);
        txtPrecio = findViewById(R.id.txtDetallePrecio);
        txtStock = findViewById(R.id.txtDetalleStock);
        txtDisponibilidad = findViewById(R.id.txtDetalleDisponibilidad);

        // Recuperamos los datos enviados desde ProductosActivity a través del Intent
        String nombre = getIntent().getStringExtra("PROD_NOMBRE");
        int precio = getIntent().getIntExtra("PROD_PRECIO", 0);
        int stock = getIntent().getIntExtra("PROD_STOCK", 0);
        boolean disponible = getIntent().getBooleanExtra("PROD_DISPONIBILIDAD", false);

        // Seteamos la información en los TextViews correspondientes
        txtNombre.setText(nombre);
        txtPrecio.setText("$" + precio);
        txtStock.setText("Stock disponible: " + stock + " unidades");

        if (disponible) {
            txtDisponibilidad.setText("Estado: Artículo Disponible");
            txtDisponibilidad.setTextColor(android.graphics.Color.parseColor("#28A745")); // Verde
        } else {
            txtDisponibilidad.setText("Estado: Agotado Temporalmente");
            txtDisponibilidad.setTextColor(android.graphics.Color.parseColor("#DC3545")); // Rojo
        }
    }
}