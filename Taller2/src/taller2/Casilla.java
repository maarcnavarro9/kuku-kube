/*
CLASE Casilla
 */

package taller2;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Casilla extends JButton {
    //DECLARACIÓN DE ATRIBUTOS
    private static final int dimX=100;
    private static final int dimY=100;
    
    //MÉTODO CONSTRUCTOR
    public Casilla(Color dato) {
        setSize(dimX, dimY);       
        setBackground(dato);
        setOpaque(true); 
    }
    
    //lleva a cabo la actualización del cuadrado correcto asignándole un
    //borde de color GREEN
    public void botonCorrecto() {
        setBorder(BorderFactory.createLineBorder(Color.GREEN,5));     
    } 

    //lleva a cabo la actualización del cuadrado erroneo asignándole un
    //borde de color RED
    public void botonIncorrecto() {
        setBorder(BorderFactory.createLineBorder(Color.RED,5));     
    } 
    
    //método setColor nos permite establecer el color de fondo
    public void setColor(Color color) {
        this.setBackground(color);
    }
}
