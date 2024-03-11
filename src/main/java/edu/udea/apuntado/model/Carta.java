package edu.udea.apuntado.model;

import java.util.*;
import javax.swing.*;

/**
 * @author Pedro Arango Sánchez
 * @author David Andrés Montoya Castaño
 * @course Técnicas de Programación y Laboratorio [2554307 - G01] 
 */
public class Carta {

    /* Atributo privado para almacenar el numero de la carta * 1= As de Trebol, 14= As de Pica, 27= As de Corazon, 40= As de Diamante, 
    * 2= 2 de Trebol, 15= 2 de Pica, 28= 2 de Corazon, 41= 2 de Diamante, 
    * 3= 3 de Trebol, 16= 3 de Pica, 29= 3 de Corazon, 42= 3 de Diamante, 
    * ... * 10= 10 de Trebol, 23= 10 de Pica, 36= 10 de Corazon, 49= 10 de Diamante, 
    * 11= J de Trebol, 24= J de Pica, 37= J de Corazon, 50= J de Diamante, 
    * 12= Q de Trebol, 25= Q de Pica, 38= Q de Corazon, 51= Q de Diamante, 
    * 13= K de Trebol, 26= K de Pica, 39= K de Corazon, 52= K de Diamante 
    * */
    private int indice;
    public static List<Integer> baraja=new ArrayList<>();
    
    //El número de la carta es generado aleatoriamente, pero tenemos que asegurar que no se repita la carta
    //a los distintos jugados. Ej: No se le puede entregar la carta 7 de Corazones a los 2 jugadores, solo hay UNA.
    public Carta(Random r) {
        while(true){
        indice = r.nextInt(52) + 1;
        if(!baraja.contains(indice)) {
            baraja.add(indice);
            break;
        }}
    }

    public PintaCarta obtenerPinta() {
        /* Obtiene la pinta que corresponde a la carta,
        basado en el rango en que se ubica el índice
         */
        if (indice <= 13) {
            return PintaCarta.TREBOL;
        } else if (indice <= 26) {
            return PintaCarta.PICA;
        } else if (indice <= 39) {
            return PintaCarta.CORAZON;
        } else {
            return PintaCarta.DIAMANTE;
        }
    }

    public NombreCarta obtenerNombre() {
        //Obtiene el nombre que corresponde al número de la carta
        int numero = indice % 13;
        if (numero == 0) {
            numero = 13;
        }
        return NombreCarta.values()[numero];
    }

    public void mostrarCarta(int x, int y, JPanel pnl, boolean tapada) {
        String nombreImagen;
        //Obtener el nombre del archivo de la carta
        if (tapada) {
            nombreImagen = "Tapada.jpg";
        } else {
           nombreImagen = "CARTA"+String.valueOf(indice)+".jpg";
        }
        //Cargar la imagen
        ImageIcon imagen = cargarImagen(nombreImagen);

        //Instanciar Label para mostrar la imagen
        JLabel lblCarta = new JLabel(imagen);
        //Definir posicion y dimensiones de la imagen
        lblCarta.setBounds(x, y, x + imagen.getIconWidth(), y + imagen.getIconHeight());
        //Mostrar la carta en la ventana 
        pnl.add(lblCarta);
    }

    public int obtenerIndice(){
        return this.indice;
    }
    
    public static void reiniciarBaraja(){
        baraja.clear();
    }

    public ImageIcon cargarImagen(String nombreImagen) {
    return new ImageIcon(getClass().getResource("/static/" + nombreImagen));
    }
}
