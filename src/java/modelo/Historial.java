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
abstract class Historial {
   private int id_historial;
    private String descripcion;
    private String fecha;
    
    
    public Historial(){}
    public Historial(int id_historial, String descripcion, String fecha) {
        this.id_historial = id_historial;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    

    public int getId_Historial() {
        return id_historial;
    }

    public void setId_Historial(int id_historial) {
        this.id_historial = id_historial;
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
