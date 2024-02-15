/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.udea.apuntado.model;

/**
 *
 * @author pedro_arango
 */
public enum PintaCarta {
    NINGUNO(0), 
    TREBOL(0), 
    PICA(13), 
    CORAZON(26), 
    DIAMANTE(39);
    
    private final int sumarIndice;
    
    private PintaCarta(int sumarIndice){
        this.sumarIndice=sumarIndice;
    }
    
    public int getSumarIndice(){
        return this.sumarIndice;
    }
}
