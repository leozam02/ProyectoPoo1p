/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego;
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

    //------------------------------------------------------------------------
    
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

    //------------------------------------------------------------------------
    
    public boolean JuegoMaquina(){
        Random random = new Random();
        Jugador maquina = jugadores.get(1);

        // Crear una copia de la lista de fichas de la máquina
        ArrayList<Ficha> copiaManoMaquina = new ArrayList<>(maquina.getMano());

        for (Ficha ficha : copiaManoMaquina) {
        
        
            if (!(ficha instanceof FichaComodin)) {
                if (agregarFichaLinea(ficha, maquina)) {
                maquina.removerFicha(ficha);
                System.out.println("La maquina jugó");
                return true;  // Jugada realizada
                }
            } else {
                int ladorandom=random.nextInt(1)+1;
                if(ladorandom==1){
                    FichaComodin f = (FichaComodin) ficha;
                    // Modifica los lados con valores aleatorios
                    int lado1 = random.nextInt(6) + 1;      
                    f.setLado1(lado1);
                    lineaJuego.add(0, f);
                    maquina.removerFicha(ficha);
                    System.out.println("La maquina jugó");// Modifica la lista original
                    return true;
                }  // Jugada realizada
                else{
                    FichaComodin f = (FichaComodin) ficha;
                    // Modifica los lados con valores aleatorios
                    int lado2 = random.nextInt(6) + 1;      
                    f.setLado2(lado2);
                    lineaJuego.add( f);
                    maquina.removerFicha(ficha);  // Modifica la lista original
                    System.out.println("La maquina jugó");
                    return true;
                }
              }
        }

    // Si llega aquí, la máquina no pudo jugar
        return false;
    }
    
    //------------------------------------------------------------------------
    
    public void jugar() {
        while (true){
            for (Jugador jugador : jugadores){
                System.out.println("-------------------------------------");
                System.out.println("\nTurno de " + jugador.getNombre());
                System.out.println("Su mano es:");
                jugador.imprimirMano();
                System.out.println("\n");
                System.out.println("La linea de juego es:");
                mostrarLinea();

                if (jugador == jugadores.get(0)) {
                    // Turno del jugador 
                    System.out.println("Índice de ficha para jugar (0 es el primero): ");
                    int indexFicha = sc.nextInt();
                    Ficha fichaAJugar = jugador.getMano(indexFicha);
                    if (agregarFichaLinea(fichaAJugar, jugador)){
                        System.out.println("Movimiento Válido.");
                        jugador.removerFicha(fichaAJugar);
                        System.out.println("Nueva Linea Juego");
                        //System.out.println("\n");
                        mostrarLinea();
                    }else{
                        System.out.println("Movimiento Inválido. Inténtalo de nuevo.");
                        System.out.println("\nSi usted cree que se equivoco "
                                + " presione cualquier letra pero queda penalizado - La Maquina sigue su turno. "
                                + "Si usted cree que no puede"
                                + " continuar presione SALIR");
                        String r=sc.next();
                        if (r.equals("SALIR")){
                            return;
                        } 
                    }
                }else{
                     // Turno de la máquina
                    boolean jugadaRealizada = JuegoMaquina();
                    if (!jugadaRealizada){
                        System.out.println("\nLa Máquina no puede jugar. Fin del juego. GANASTE!!");
                        return;
                    }
                  }

                if (jugador.getMano().isEmpty()){
                    System.out.println(jugador.getNombre() + " se quedó sin fichas. ¡Ganó!");
                    return;
                }
            }

            // Verificar si ambos jugadores no pueden jugar
            if (jugadores.get(0).getMano().isEmpty() && jugadores.get(1).getMano().isEmpty()) {
                System.out.println("Ambos jugadores no pueden jugar. Fin del juego.");
                return;
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("    BIENVENIDO AL JUEGO DOMINO"
                + "\n--------------------------------------");
        System.out.print("\nIngrese el nombre del jugador: ");
        
        String nombreJugador = sc.nextLine();
        String nombreMaquina="Maquina";
        
        Juego juego = new Juego();
        
        juego.agregarJugador(nombreJugador);
        juego.agregarJugador(nombreMaquina);
        
        juego.jugar();
    }
}
