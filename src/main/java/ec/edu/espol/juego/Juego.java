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

    public boolean agregarFichaLinea(Ficha ficha, Jugador jugador) {
    if (!(ficha instanceof FichaComodin)) {
        if (lineaJuego.isEmpty()) {
            lineaJuego.add(ficha);
            
            
            return true;
        } else {
            int valorInicio = this.obtenerValorInicioLinea();
            int valorFin = this.obtenerValorFinLinea();
            if (ficha.getLado2() == valorInicio) {
                lineaJuego.add(0, ficha);
                return true;
                
            } else if (ficha.getLado1() == valorFin) {
                lineaJuego.add(ficha);
                
                return true;
            } else {
                return false;
            }
        }
    } else {
        if (lineaJuego.isEmpty()) {
            lineaJuego.add(ficha);
            System.out.print("Ingrese el valor del lado1: ");
            int lado1 = sc.nextInt();
            System.out.print("Ingrese el valor del lado2: ");
            int lado2 = sc.nextInt();
            FichaComodin f = (FichaComodin) ficha;
            f.setLado1(lado1);
            f.setLado2(lado2);
            return true;
        } else {
            System.out.print("Ingrese la posición (Inicio o Fin): ");
            String posicion = sc.next().toLowerCase();
            if (posicion.equals("inicio")) {
                System.out.print("Ingrese el valor del lado1: ");
                int lado1 = sc.nextInt();
                FichaComodin f = (FichaComodin) ficha;
                f.setLado1(lado1);
                lineaJuego.add(0, f);
                
                return true;
            } else if (posicion.equals("fin")) {
                System.out.print("Ingrese el valor del lado2: ");
                int lado2 = sc.nextInt();
                FichaComodin f = (FichaComodin) ficha;
                f.setLado2(lado2);
                lineaJuego.add(f);
                
                return true;
            } else {
                return false;
            }
        }
    }
    }   

    // Colocar Juego Maquina, jugar y main

  
}
