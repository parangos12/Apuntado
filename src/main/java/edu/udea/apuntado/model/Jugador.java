/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udea.apuntado.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    private Map<String, Integer> frecuenciaCartas;
    private Integer puntosJugador;
    private List<Integer> cartasUsadasEscalera;
    private Map<Integer,Integer> cartasUsadasGrupos;

    public Jugador() {
        // Iniciar el generador de numeros aleatorios
        r = new Random();
    }

    public void repartir() {
        //Instanciar las 10 cartas
        cartas = new Carta[10];
        for (int i = 0; i < 10; i++) {
            cartas[i] = new Carta(r);
        }
        reiniciarBaraja();
    }

    public void mostrar(JPanel pnl, boolean tapada) {
        //limpiar el panel
        pnl.removeAll();
        //mostrar cada carta
        for (int i = 0; i < 10; i++) {
            cartas[i].mostrarCarta(5 + i * 40, 5, pnl, tapada);
        }
        //Redibujar el panel 
        pnl.repaint();
    }

    //MEJORAR ESTO DE TERNAS,CUARTAS!!! CON UN SIMPLE FOR Y GETuBDUCE, se mira en q rango esta y se suma.
    public String obtenerFiguras() {
        cartasUsadasGrupos=new HashMap<>();
        frecuenciaCartas = new HashMap<>();
        contarConcurrencias(); //Contamos las concurrencias de las cartas.

        String mensaje = "";
        for (Map.Entry<String, Integer> carta : frecuenciaCartas.entrySet()) {
            Integer frecuencia = carta.getValue();
            if (frecuencia >= 3) {
                String nombreCarta = carta.getKey();
                mensaje += Figura.values()[frecuencia].name() + " de " + nombreCarta + "\n";
                Integer frecuenciaActual=cartasUsadasGrupos.getOrDefault(NombreCarta.valueOf(nombreCarta).getValorCarta(), 0);
                frecuenciaActual+=frecuencia;
                cartasUsadasGrupos.put(NombreCarta.valueOf(nombreCarta).getValorCarta(), frecuenciaActual);
            }
        }
        System.out.println("CARTAS USADAS EN GRUPOS");
        cartasUsadasGrupos.forEach((k,v)->System.out.println("k:"+k+" v:"+v));
        System.out.println("===================================================================");
        mensaje+=obtenerEscaleras();         //Posible escalera

        if (mensaje.equals("")) {
            mensaje = "No hay figuras";
        } else {
            mensaje = "El jugador tiene las siguientes figuras:\n" + mensaje;
        }

        return mensaje;
    }
    

    public Map<String, Integer> contarConcurrencias() {
        for (Carta carta : cartas) {
            String nombreCarta = carta.obtenerNombre().name();
            Integer frecuenciaCarta = frecuenciaCartas.getOrDefault(nombreCarta, 0) + 1;
            frecuenciaCartas.put(nombreCarta, frecuenciaCarta);
        }
        return frecuenciaCartas;
    }

    public String obtenerEscaleras() {
        cartasUsadasEscalera=new ArrayList<>();
        Map<PintaCarta, List<NombreCarta>> agrupamientosPorPinta = agruparCartasPorPinta();
        String message = "";
        for (Map.Entry<PintaCarta, List<NombreCarta>> entry : agrupamientosPorPinta.entrySet()) {
            PintaCarta pintaCarta = entry.getKey();
            List<NombreCarta> agrupadosXPinta = entry.getValue();
            if (agrupadosXPinta.size() >= 3) {
                agrupadosXPinta.sort((vl1, vl2) -> vl1.getValorOrden()- vl2.getValorOrden());
                agrupadosXPinta.addAll(agrupadosXPinta);
                int sumTemp=1;
                List<Integer> escaleraTest=new ArrayList<>();
                List<Integer> finalEscalera=new ArrayList<>(); //Almacenará escaleras por pinta. Pinta puede tener >1 escaleras.
                for (int i = 0; i < agrupadosXPinta.size()-1; i++) {
                    Integer index1 = agrupadosXPinta.get(i).getValorOrden();
                    Integer index2 = agrupadosXPinta.get(i + 1).getValorOrden();
                    if(index2-index1!=1 && index2-index1!=-12){
                        sumTemp=1;
                        escaleraTest.clear();
                    }else {
                        sumTemp++;
                        if(!escaleraTest.contains(index1)) escaleraTest.add(index1);
                        if(!escaleraTest.contains(index2)) escaleraTest.add(index2);
                        //TODO: POINT TO THE NOT COUNTED CARD.
                    }
                    if(sumTemp>=3) {
                        if(!finalEscalera.containsAll(escaleraTest)) finalEscalera.addAll(escaleraTest);
                        if(!message.contains("ESCALERA de " + pintaCarta.name()+"\n")){
                            message += "ESCALERA de " + pintaCarta.name()+"\n";
                        }
                    }
                }
                //Compruebe que si son las escaleras imprimiendo en consola...
                System.out.println("Escalera: "+finalEscalera.toString());
                cartasUsadasEscalera.addAll(finalEscalera);
            }
        }
        obtenerPuntosJugador();
        return message;
    }

    public Map<PintaCarta, List<NombreCarta>> agruparCartasPorPinta() {
        Map<PintaCarta, List<NombreCarta>> agrupamientosPorPinta = new HashMap<>();
        for (Carta carta : cartas) {
            PintaCarta pintaCarta = carta.obtenerPinta();
            NombreCarta nombreCarta = carta.obtenerNombre();
            List<NombreCarta> nombresCartas = agrupamientosPorPinta.getOrDefault(pintaCarta, new ArrayList<>());
            nombresCartas.add(nombreCarta);
            agrupamientosPorPinta.put(pintaCarta, nombresCartas);
        }
        return agrupamientosPorPinta;
    }
    
    private Integer obtenerPuntosJugador(){
        //Agrupar por valor carta
        Integer totalSuma=0;
        Map<Integer,Integer> agrupacionPorValor=new HashMap<>();
        for (int i = 0; i < cartas.length; i++) {
            Carta carta = cartas[i];
            Integer valorCarta=carta.obtenerNombre().getValorCarta();
            Integer frecuencia=agrupacionPorValor.getOrDefault(valorCarta,0);
            frecuencia++;
            agrupacionPorValor.put(valorCarta, frecuencia);
        }
        //System.out.println("ANTES DE ESCALERA");
        //agrupacionPorValor.forEach((k,v)->System.out.println("k:"+k+" v:"+v));
        for (Map.Entry<Integer, Integer> entry : cartasUsadasGrupos.entrySet()) {
            Integer valorCarta= entry.getKey();
            Integer frecuenciaCarta = entry.getValue();
            Integer nuevaFrecuencia=agrupacionPorValor.get(valorCarta)-frecuenciaCarta;
            agrupacionPorValor.put(valorCarta, nuevaFrecuencia);
        }
        
        //Restamos las cartas usadas en los demás grupos (Si ocurre q no se puede restar más, no hay problema, ya que 
        //las cartas NO son mutuamente excluyentes.
        for (int i = 0; i < cartasUsadasEscalera.size(); i++) {
            Integer cartaOrden = cartasUsadasEscalera.get(i);
            Integer valorCarta=NombreCarta.getPuntajePorOrden(cartaOrden);
            Integer nuevaFrecuencia=agrupacionPorValor.get(valorCarta)-1;
            agrupacionPorValor.put(valorCarta, nuevaFrecuencia);
        }
        //System.out.println("DESPUES DE ESCALERA");
        //agrupacionPorValor.forEach((k,v)->System.out.println("k:"+k+" v:"+v));
        
        //Sumamos los valores restantes de las cartas que no fueron seleccionadas.
        for (Map.Entry<Integer, Integer> entry : agrupacionPorValor.entrySet()) {
            Integer valorCarta = entry.getKey();
            Integer frecuencia = entry.getValue();
            if(frecuencia>0) totalSuma+=valorCarta*frecuencia;
        }
        System.out.println("Agrupaciones luego de restas: ");
        agrupacionPorValor.forEach((k,v)->System.out.println("k:"+k+" v:"+v));
        System.out.println("Total suma: "+totalSuma);
        return totalSuma;
        //Recorremos las figuras que SI son escaleras
    }
    
    
    private void reiniciarBaraja() {
        Carta.randomNumbers = new ArrayList<>();
    }

}
