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
abstract class HabitacionDAO {
    
    private int id;
    private String habitacion;
    private String nivel;
    private String numero;
    private String capacidad;
    
    
    public HabitacionDAO(){}
    
    public HabitacionDAO(int id, String habitacion, String nivel, String numero, String capacidad) {
        this.id = id;
        this.habitacion = habitacion;
        this.nivel = nivel;
        this.numero = numero;
        this.capacidad = capacidad;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
    
    public int agregar (){ return 0;}
    public int modificar (){return 0;}
    public int eliminar (){return 0;}
    
}
