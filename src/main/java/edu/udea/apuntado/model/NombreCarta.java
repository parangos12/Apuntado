package edu.udea.apuntado.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pedro Arango Sánchez
 * @author David Andrés Montoya Castaño
 * @course Técnicas de Programación y Laboratorio [2554307 - G01] 
 */
public enum NombreCarta { 
    NINGUNO(0,0,0), 
    ACE(10,1,2), 
    DOS(2,2,3), 
    TRES(3,3,4), 
    CUATRO(4,4,5), 
    CINCO(5,5,6), 
    SEIS(6,6,7), 
    SIETE(7,7,8), 
    OCHO(8,8,9), 
    NUEVE(9,9,10), 
    DIEZ(10,10,11), 
    JACK(10,11,12), 
    QUEEN(10,12,13), 
    KING(10,13,1); 

    private final int valorCarta;
    private final int valorOrden;
    private final int siguienteValor;
    
    NombreCarta(int valorCarta,int valorOrden,int siguienteValor){
        this.valorCarta=valorCarta;
        this.valorOrden=valorOrden;
        this.siguienteValor=siguienteValor;
    }
    
    public static int getPuntajePorOrden(int ordenCarta){
        List<Integer> cartas=List.of(1,11,12,13);
        if(cartas.contains(ordenCarta)) return 10;
        else return ordenCarta;
    }
    
    
    public Integer getValorCarta(){
        return valorCarta;
    }
    
    public int getValorOrden(){
        return valorOrden;
    }
    
    public int getSiguienteValor(){
        return siguienteValor;
    }

}

