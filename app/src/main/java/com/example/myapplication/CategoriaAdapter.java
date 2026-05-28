package com.example.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    private List<String> listaCategorias;
    private OnCategoriaClickListener listener;

    // 1. Interfaz para gestionar los clics desde el MainActivity
    public interface OnCategoriaClickListener {
        void onCategoriaClick(String categoria);
    }

    // Constructor del Adaptador
    public CategoriaAdapter(List<String> listaCategorias, OnCategoriaClickListener listener) {
        this.listaCategorias = listaCategorias;
        this.listener = listener;
    }

    // 2. Infla el diseño XML (item_categoria) que creamos en el Paso 1
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categoria, parent, false);
        return new ViewHolder(view);
    }

    // 3. Conecta los datos de la lista con los elementos visuales de la celda
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombreCategoria = listaCategorias.get(position);
        holder.txtNombre.setText(nombreCategoria);

        // Al hacer clic en toda la tarjeta, se ejecuta nuestro listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCategoriaClick(nombreCategoria);
            }
        });
    }

    // 4. Le dice al RecyclerView cuántos elementos debe renderizar
    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    // 5. El ViewHolder es el contenedor que "encuentra" los componentes del XML
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreCategoria);
        }
    }
}