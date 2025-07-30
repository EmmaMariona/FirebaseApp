package com.example.personasapp;

public class Personas {
    private String id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String fechanac;

    public Personas() {} // Obligatorio para Firebase

    public Personas(String id, String nombres, String apellidos, String correo, String fechanac) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechanac = fechanac;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getFechanac() { return fechanac; }
    public void setFechanac(String fechanac) { this.fechanac = fechanac; }
}

