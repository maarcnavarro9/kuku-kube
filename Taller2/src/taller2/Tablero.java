/*
CLASE Tablero
 */

package taller2;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JPanel;

public class Tablero extends JPanel {
    //DECLARACIÓN DE ATRIBUTOS
    //declaración array de objetos Casilla
    private Casilla [] botones;
    //declaración botonSeleccionado para saber si la casilla ha sido seleccionada
    private int botonSeleccionado;
    //declaración variable entera para saber el número de casillas que debe
    //haber en el tablero
    private int numeroBotones;
    //declaración objetos Color para obtener el color de la casilla correspondiente
    private Color colorPrincipal,colorSecundario;
    //declaración variable de tipo entero para saber la posición de la casilla a encontrar
    private int posicionBotonDiferente;
    //declaración objeto Random
    private Random random = new Random();
    //declaración variable para almacenar la complejidad
    private int complejidad=10;
    private int complejidadReal;
    
    //MÉTODO CONSTRUCTOR
    public Tablero(int dimension,MouseListener dato1, int dato2) {
        super();
        numeroBotones=dimension*dimension;
        setLayout(new GridLayout(dimension,dimension,2,2));
        botones=new Casilla[numeroBotones];
        setSize(265, 450);
        inicializacion(dato1,dato2);
    }
    
    //método inicializacion crea el tablero de juego
    public void inicializacion(MouseListener dato1, int dato2) {
        //DECLARACIONES
        //llamada al método crearColores
        crearColores(dato2);
        //bucle para crear las casillas del tablero
        for (int i=0;i<numeroBotones;i++) {
            botones[i]=new Casilla(colorPrincipal);
            botones[i].addMouseListener(dato1);
            add(botones[i]);
        }
        //crear la casilla a encontrar
        posicionBotonDiferente=random.nextInt(numeroBotones);
        botones[posicionBotonDiferente].setColor(colorSecundario);
        botonSeleccionado=0; 
    }
    
    //método crearColores lleva a cabo la obtención de el color del tablero i
    //en función de la dificultad seleccionada, crea el color de la casilla a encontrar
    private void crearColores(int dato) {
        //obtener número random
        int red=random.nextInt(255);
        int green=random.nextInt(255);
        int blue=random.nextInt(255);
        
        complejidadReal=11-dato;
        
        if(red<2*complejidadReal+10) {
            red=2*complejidadReal+10;
        }
        if(green<2*complejidadReal+10) {
            green=2*complejidadReal+10;
        }
        if(blue<2*complejidadReal+10) {
            blue=2*complejidadReal+10;
        }
        
        //crear colores
        colorPrincipal=new Color(red,green,blue);
        //colorSecundario=Color.black;
        colorSecundario=new Color(red-(2*complejidadReal+10),green-(2*complejidadReal+10),blue-(2*complejidadReal+10));
        //NO SE PASAR SA COMPLEJIDAD
    }
    
    //MÉTODOS GET necesarios para llevar a cabo el mouseListener en el programa principal
    public int getBotonesLength() {
        return botones.length;
    }
    
    public Casilla [] getBotones() {
        return botones;
    }
    
    public int getBotonSeleccionado() {
        return botonSeleccionado;
    }
    
    public int getPosicionBotonDiferente() {
        return posicionBotonDiferente;
    }
}
