package com.example.personasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_item, parent, false);
        return new PersonaViewHolder(view);
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

    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCorreo, tvFecha;
        ImageView imgFoto;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            imgFoto = itemView.findViewById(R.id.imgFoto);
        }

        public void bind(final Personas persona, final OnItemClickListener listener) {
            tvNombre.setText(persona.getNombres() + " " + persona.getApellidos());
            tvCorreo.setText(persona.getCorreo());
            tvFecha.setText(persona.getFechanac());

            Glide.with(itemView.getContext())
                    .load(persona.getFoto())
                    .placeholder(R.drawable.default_profile)
                    .into(imgFoto);

            itemView.setOnClickListener(v -> listener.onItemClick(persona));
        }
    }
}