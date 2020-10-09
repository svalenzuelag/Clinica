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
public class Usuario {
    private int id;
    private int id_rol;
    private String usuario;
    private String pass;

    public Usuario() {
    }
    public Usuario(int id, int id_rol, String usuario, String pass) {
        this.id = id;
        this.id_rol = id_rol;
        this.usuario = usuario;
        this.pass = pass;
    }

   

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
