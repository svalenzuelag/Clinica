/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author josue
 */
abstract class CitaMedicaDAO {
    
    private int id;
    private String fechaCita;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    
    public CitaMedicaDAO(){}
    
    public CitaMedicaDAO(int id, String fechaCita, String nombres, String apellidos, String telefono, String correo) {
        this.id = id;
        this.fechaCita = fechaCita;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    public int agregar (){ return 0;}
    public int modificar (){return 0;}
    public int eliminar (){return 0;}
    
}
