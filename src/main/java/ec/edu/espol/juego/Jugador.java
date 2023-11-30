/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego;

import java.util.ArrayList;

/**
 *
 * @author leoza
 */
public class Jugador {
    private String nombre;
    private ArrayList<Ficha> mano;
    
    public Jugador(String nombre, ArrayList<Ficha> mano){
        this.nombre=nombre;
        this.mano=mano;
    }

    public String getNombre() {
        return nombre;
    }

    public Ficha getMano(int i) {
        if (i >= 0 && i < mano.size()) {
            return mano.get(i);
        }
        return null;
    }
    
    
    public void imprimirMano() {
        for (int i = 0; i < mano.size(); i++) {
            System.out.print(mano.get(i));
        if (i < mano.size() - 1) 
            System.out.print("-");  
        }
    }
    public void removerFicha(Ficha ficha) {
        mano.remove(ficha);
    }

    public String toString(){
        return nombre;
    }
      
}
