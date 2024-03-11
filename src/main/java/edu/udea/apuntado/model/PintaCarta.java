package edu.udea.apuntado.model;

/**
 * @author Pedro Arango Sánchez
 * @author David Andrés Montoya Castaño
 * @course Técnicas de Programación y Laboratorio [2554307 - G01] 
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
