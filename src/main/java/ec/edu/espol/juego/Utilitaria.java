/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto1pmio;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author leoza
 */
public class Utilitaria {
     public static ArrayList<Ficha> crearManoJugador() {
        ArrayList<Ficha> mano = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int lado1 = random.nextInt(6) + 1;
            int lado2 = random.nextInt(6) + 1;
            mano.add(new Ficha(lado1, lado2));
        }

        mano.add(new FichaComodin());
        return mano;
    }   
}
