package com.example.personasapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etApellido, etCorreo, etFecha, etId, etFoto;
    Button btnGuardar, btnVer;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etFecha = findViewById(R.id.etFecha);
        etId = findViewById(R.id.etId);
        etFoto = findViewById(R.id.etFoto);


        btnGuardar = findViewById(R.id.btnGuardar);
        btnVer = findViewById(R.id.btnVer);

        databaseReference = FirebaseDatabase.getInstance().getReference("Personas");

        btnGuardar.setOnClickListener(view -> guardarPersona());
        btnVer.setOnClickListener(view -> verPersonas());
    }

    private void guardarPersona() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String fecha = etFecha.getText().toString().trim();
        String fotoUrl = etFoto.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = databaseReference.push().getKey();

        Personas persona = new Personas(id, nombre, apellido, correo, fecha, fotoUrl);

        databaseReference.child(id).setValue(persona)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Persona guardada", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show());
    }

    private void actualizarPersona() {
        String id = etId.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(this, "Debes ingresar un ID para actualizar", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference ref = databaseReference.child(id);
        ref.child("nombres").setValue(etNombre.getText().toString());
        ref.child("apellidos").setValue(etApellido.getText().toString());
        ref.child("correo").setValue(etCorreo.getText().toString());
        ref.child("fechanac").setValue(etFecha.getText().toString());

        Toast.makeText(this, "Persona actualizada", Toast.LENGTH_SHORT).show();
    }

    private void eliminarPersona() {
        String id = etId.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(this, "Debes ingresar un ID para eliminar", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseReference.child(id).removeValue()
                .addOnSuccessListener(aVoid ->
                        Toast.makeText(this, "Persona eliminada", Toast.LENGTH_SHORT).show());
    }

    private void verPersonas() {
        Intent intent = new Intent(MainActivity.this, ListaPersonasActivity.class);
        startActivity(intent);
    }
}