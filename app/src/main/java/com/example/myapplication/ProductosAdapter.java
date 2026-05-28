package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Clase Adaptador encargado de conectar la lista de objetos 'Producto' con el diseño visual 'item_producto.xml'.
 */
public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> listaProductos;

    // Constructor que recibe la fuente de datos que se va a pintar en el RecyclerView
    public ProductoAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * Método encargado de "inflar" (construir e instanciar) el archivo XML de la celda individual.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Transforma el archivo layout XML (item_producto) en un objeto View dinámico de Java
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Método que une los datos de un objeto específico con los TextViews de una celda concreta.
     * Se ejecuta de forma automática para cada elemento visible en pantalla.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtiene el objeto Producto de la lista dependiendo de su posición actual en el scroll
        Producto producto = listaProductos.get(position);

        // Asigna los valores a los TextView correspondientes usando los getters específicos del modelo Producto
        holder.txtNombre.setText(producto.isNombre());
        holder.txtPrecio.setText("$" + producto.isPrecio());
        holder.txtStock.setText("Stock disponible: " + producto.isStock());
    }

    /**
     * Devuelve la cantidad total de elementos que tiene la lista.
     * Gracias a esto, el RecyclerView sabe cuántos vagones/filas debe pintar.
     */
    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    /**
     * Clase interna (ViewHolder) encargada de almacenar las referencias de los componentes de la celda.
     * Funciona como un optimizador de memoria evitando llamadas repetitivas a 'findViewById'.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtPrecio, txtStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Busca y guarda los IDs definidos dentro del archivo 'item_producto.xml'
            txtNombre = itemView.findViewById(R.id.txtNombreProducto);
            txtPrecio = itemView.findViewById(R.id.txtPrecioProducto);
            txtStock = itemView.findViewById(R.id.txtStockProducto);
        }
    }
}
