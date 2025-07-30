package com.example.personasapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetallePersonaActivity extends AppCompatActivity {

    EditText etNombre, etApellido, etCorreo, etFecha;
    Button btnActualizar, btnEliminar;
    DatabaseReference databaseReference;
    String idPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona); // este layout lo crearemos a continuación

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etFecha = findViewById(R.id.etFecha);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        // obtener datos enviados desde ListaPersonasActivity
        idPersona = getIntent().getStringExtra("id");
        etNombre.setText(getIntent().getStringExtra("nombres"));
        etApellido.setText(getIntent().getStringExtra("apellidos"));
        etCorreo.setText(getIntent().getStringExtra("correo"));
        etFecha.setText(getIntent().getStringExtra("fechanac"));

        databaseReference = FirebaseDatabase.getInstance().getReference("Personas").child(idPersona);

        btnActualizar.setOnClickListener(v -> actualizarPersona());
        btnEliminar.setOnClickListener(v -> eliminarPersona());
    }

    private void actualizarPersona() {
        databaseReference.child("nombres").setValue(etNombre.getText().toString());
        databaseReference.child("apellidos").setValue(etApellido.getText().toString());
        databaseReference.child("correo").setValue(etCorreo.getText().toString());
        databaseReference.child("fechanac").setValue(etFecha.getText().toString());

        Toast.makeText(this, "Persona actualizada", Toast.LENGTH_SHORT).show();
        finish(); // regresar al listado
    }

    private void eliminarPersona() {
        databaseReference.removeValue().addOnSuccessListener(aVoid -> {
            Toast.makeText(this, "Persona eliminada", Toast.LENGTH_SHORT).show();
            finish(); // regresar al listado
        });
    }
}