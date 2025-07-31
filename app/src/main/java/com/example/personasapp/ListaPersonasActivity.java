package com.example.personasapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class ListaPersonasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Personas> lista;
    PersonaAdapter adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lista = new ArrayList<>();
        adapter = new PersonaAdapter(lista, persona -> {
            Intent intent = new Intent(ListaPersonasActivity.this, DetallePersonaActivity.class);
            intent.putExtra("id", persona.getId());
            intent.putExtra("nombres", persona.getNombres());
            intent.putExtra("apellidos", persona.getApellidos());
            intent.putExtra("correo", persona.getCorreo());
            intent.putExtra("fechanac", persona.getFechanac());
            intent.putExtra("foto", persona.getFoto());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Personas");
        cargarPersonas();
    }

    private void cargarPersonas() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lista.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Personas p = data.getValue(Personas.class);
                    if (p != null) lista.add(p);
                }
                adapter.updateList(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FIREBASE", "Error al cargar lista", error.toException());
            }
        });
    }
}
