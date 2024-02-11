/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.udea.apuntado.model;

/**
 *
 * @author pedro_arango
 */
public enum NombreCarta { 
    NINGUNO(0,0), 
    ACE(10,1), 
    DOS(2,2), 
    TRES(3,3), 
    CUATRO(4,4), 
    CINCO(5,5), 
    SEIS(6,6), 
    SIETE(7,7), 
    OCHO(8,8), 
    NUEVE(9,9), 
    DIEZ(10,10), 
    JACK(10,11), 
    QUEEN(10,12), 
    KING(10,13); 

    private final int valorCarta;
    private final int valorOrden;

    NombreCarta(int valorCarta,int valorOrden){
        this.valorCarta=valorCarta;
        this.valorOrden=valorOrden;
    }
    
    public int getValorCarta(){
        return valorCarta;
    }
    
    public int getValorOrden(){
        return valorOrden;
    }
}