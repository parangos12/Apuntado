/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udea.apuntado.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author pedro_arango
 */
public class Jugador {
    
    private Random r;
    private Carta[] cartas; 
    private Map<String,Integer> frecuenciaCartas;
    
    public Jugador(){
        // Iniciar el generador de numeros aleatorios
        r=new Random();
    }
    
    public void repartir(){
        //Instanciar las 10 cartas
        cartas=new Carta[10];
        for(int i=0;i<10;i++) 
            cartas[i]=new Carta(r); 
        reiniciarBaraja();
    }

    public void mostrar(JPanel pnl, boolean tapada){
        //limpiar el panel
        pnl.removeAll();
        //mostrar cada carta
        for(int i=0;i<10;i++){ 
            cartas[i].mostrarCarta(5+i*40, 5, pnl, tapada);} 
        //Redibujar el panel 
        pnl.repaint();
    }
    
    public String obtenerFiguras(){
        frecuenciaCartas=new HashMap<>();
        contarConcurrencias(); //Contamos las concurrencias de las cartas.
        
        String mensaje="";
        for (Map.Entry<String, Integer> carta : frecuenciaCartas.entrySet()) {
            Integer frecuencia = carta.getValue();
            if(frecuencia>=3){
            String nombreCarta = carta.getKey();
            mensaje+=Figura.values()[frecuencia].name()+" de "+nombreCarta+"\n";
            }
        }
        if(mensaje.equals("")) mensaje="No hay figuras";
        else mensaje="El jugador tiene las siguientes figuras:\n"+mensaje;
        
        return mensaje;
    }
    
    public Map<String,Integer> contarConcurrencias(){
        for (Carta carta : cartas) {
            String nombreCarta = carta.obtenerNombre().name();
            Integer frecuenciaCarta=frecuenciaCartas.getOrDefault(nombreCarta, 0)+1;
            frecuenciaCartas.put(nombreCarta,frecuenciaCarta);
        }
        return frecuenciaCartas;
    }

    private void reiniciarBaraja() {
        Carta.randomNumbers=new ArrayList<>();
    }
       
}














