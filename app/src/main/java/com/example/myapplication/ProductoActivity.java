package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ProductosActivity extends AppCompatActivity {

    // Declaración de variables para controlar la vista de productos
    private TextView txtTituloCategoria;
    private RecyclerView recyclerProductos;
    private DatabaseReference databaseReference;
    private List<Producto> listaProductos;
    private ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // Vinculación de los componentes del archivo XML
        txtTituloCategoria = findViewById(R.id.txtTituloCategoria);
        recyclerProductos = findViewById(R.id.recyclerProductos);

        // Configura el RecyclerView en forma de lista vertical (un elemento debajo del otro)
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));

        // 1. Recibe el parámetro enviado desde 'InicioActivity' a través del Intent
        String categoria = getIntent().getStringExtra("CATEGORIA_SELECCIONADA");
        // Coloca el nombre de la categoría seleccionada en el TextView del encabezado
        txtTituloCategoria.setText(categoria);

        // 2. Apunta Firebase específicamente al subnodo de la categoría seleccionada (ej: "Productos/Accesorios")
        databaseReference = FirebaseDatabase.getInstance().getReference("Productos").child(categoria);

        // Inicializa la lista que contendrá objetos de tipo Producto
        listaProductos = new ArrayList<>();

        // Invoca el método para leer los productos correspondientes de Firebase
        cargarProductos();
    }

    /**
     * Descarga los productos pertenecientes únicamente a la categoría seleccionada.
     */
    private void cargarProductos() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Limpia la lista de productos antes de llenarla
                listaProductos.clear();

                // Recorre todos los productos dentro del nodo de la categoría actual (ej: prod_1, prod_2)
                for (DataSnapshot productoSnapshot : snapshot.getChildren()) {
                    // Convierte de forma automática el objeto JSON mapeado de Firebase a nuestra clase 'Producto'
                    Producto producto = productoSnapshot.getValue(Producto.class);
                    if (producto != null) {
                        // Agrega el producto transformado a la colección
                        listaProductos.add(producto);
                    }
                }

                // Inicializa el adaptador encargado de renderizar las tarjetas de productos
                adapter = new ProductoAdapter(listaProductos);
                // Vincula el adaptador con el RecyclerView para refrescar la interfaz visual
                recyclerProductos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Registra el error en la consola si la consulta es rechazada
                Log.e("FirebaseError", "Error al cargar productos", error.toException());
            }
        });
    }
}