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
abstract class Consulta {
    
    private int id_consulta;
    private String descripcion;
    private String fecha;
    
    
    public Consulta(){}
    public Consulta(int id_consulta, String descripcion, String fecha) {
        this.id_consulta = id_consulta;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public int agregar (){ return 0;}
    public int modificar (){return 0;}
    public int eliminar (){return 0;}
    
    
}
