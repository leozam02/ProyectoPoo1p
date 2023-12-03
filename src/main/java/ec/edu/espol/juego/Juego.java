/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto1pmio;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author: Jaren, Leonardo, Bruno
 */

public class Juego {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Ficha> lineaJuego;
    Scanner sc = new Scanner(System.in);

    public Juego() {
        jugadores = new ArrayList<>();
        lineaJuego = new ArrayList<>();
    }
    
    public void agregarJugador(String nombre) {
        ArrayList<Ficha> manoJugador = Utilitaria.crearManoJugador();
        Jugador jugador = new Jugador(nombre, manoJugador);
        jugadores.add(jugador);
    }
    
    public int obtenerValorInicioLinea() {
        return lineaJuego.get(0).getLado1();
    }

    public int obtenerValorFinLinea() {
        return lineaJuego.get(lineaJuego.size() - 1).getLado2();
    }
  
    public void mostrarLinea() {
        for (int i = 0; i < lineaJuego.size(); i++) {
            System.out.print(lineaJuego.get(i));
            if (i < lineaJuego.size() - 1) 
                System.out.print(" - ");
        }
        System.out.println();
    }

  
}
