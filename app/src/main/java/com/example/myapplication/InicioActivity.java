package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class InicioActivity extends AppCompatActivity {

    // Declaración de variables globales y componentes visuales
    private RecyclerView recyclerCategorias;
    private DatabaseReference databaseReference;
    private List<String> listaCategorias;
    private CategoriaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Vincula la actividad con su respectivo diseño XML
        setContentView(R.layout.activity_inicio);

        // Enlaza el componente visual RecyclerView del XML con el objeto Java
        recyclerCategorias = findViewById(R.id.recyclerCategorias);

        // Configura el RecyclerView para que muestre los elementos en una cuadrícula (Grid) de 2 columnas
        recyclerCategorias.setLayoutManager(new GridLayoutManager(this, 2));

        // Inicializa la estructura de datos (lista de tipo String) para almacenar los nombres de las categorías
        listaCategorias = new ArrayList<>();

        // Obtiene la referencia del nodo principal llamado "Productos" en Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Productos");

        // Ejecuta el método para descargar la información desde la base de datos
        cargarCategorias();
    }

    /**
     * Método encargado de realizar la consulta a Firebase y escuchar los cambios de datos.
     */
    private void cargarCategorias() {
        // addListenerForSingleValueEvent lee los datos de Firebase una sola vez (ideal para catálogos estáticos)
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Limpia la lista antes de agregar los datos para evitar duplicados en la interfaz
                listaCategorias.clear();

                // Iteramos sobre cada uno de los nodos "hijos" del nodo "Productos" (ej: Accesorios, Ropa, etc.)
                for (DataSnapshot categoriaSnapshot : snapshot.getChildren()) {
                    // .getKey() extrae directamente el nombre del nodo (la clave del JSON) y lo añade a la lista
                    listaCategorias.add(categoriaSnapshot.getKey());
                }

                // Inicializa el adaptador pasándole la lista cargada y la acción a realizar al pulsar una celda
                adapter = new CategoriaAdapter(listaCategorias, categoria -> {
                    // Código de navegación: Abre 'ProductosActivity' al hacer clic en una categoría
                    Intent intent = new Intent(InicioActivity.this, ProductosActivity.class);
                    // Pasa el nombre de la categoría seleccionada como un parámetro (Extra) a la siguiente pantalla
                    intent.putExtra("CATEGORIA_SELECCIONADA", categoria);
                    startActivity(intent);
                });

                // Le asigna el adaptador configurado al RecyclerView para que dibuje las celdas en pantalla
                recyclerCategorias.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Muestra un error en la consola (Logcat) en caso de que falle la conexión o no haya permisos de lectura
                Log.e("FirebaseError", "Error al cargar categorías", error.toException());
            }
        });
    }
}

