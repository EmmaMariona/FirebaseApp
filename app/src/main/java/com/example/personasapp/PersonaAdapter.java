package com.example.personasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    private List<Personas> personaList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Personas persona);
    }

    public PersonaAdapter(List<Personas> personaList, OnItemClickListener listener) {
        this.personaList = personaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.persona_item, parent, false);
        return new PersonaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Personas persona = personaList.get(position);
        holder.bind(persona, listener);
    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

    public void updateList(List<Personas> newList) {
        personaList = newList;
        notifyDataSetChanged();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCorreo, tvFecha;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }

        public void bind(Personas persona, OnItemClickListener listener) {
            tvNombre.setText(persona.getNombres() + " " + persona.getApellidos());
            tvCorreo.setText(persona.getCorreo());
            tvFecha.setText(persona.getFechanac());

            itemView.setOnClickListener(v -> listener.onItemClick(persona));
        }
    }
}