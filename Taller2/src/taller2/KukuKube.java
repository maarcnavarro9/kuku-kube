/*
MARC NAVARRO AMENGUAL
 */

package taller2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//                             CLASE KukuKube                                 //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////    

public class KukuKube {
    //ATRIBUTOS
    //declaración panel de contenidos
    private Container panelContenidos;
    //declaración JFrame ventana
    private JFrame ventana;
    //declaración paneles
    private JPanel panelVisualizacion, panelStandby;
    //declaración etiquetas
    private JLabel etiquetaNomNivelesPartida,etiquetaValNivelesPartida,etiquetaNomNivelesRestantes,
            etiquetaValNivelesRestantes,etiquetaNomNivelActual,etiquetaValNivelActual,
            etiquetaNombrePuntuacion,etiquetaValPuntuacion;
    //declaración objeto Tablero
    private Tablero panelJuego;
    //declaración variables de tipo entero para almacenar los datos que introduzca el usuario
    private int numNiveles,complejidad;
    //declaración variable para controlar la dimensión del tablero
    private int contadorPartida=2;
    //declaración variable booleana para saber si el usuario se encuentra en partida
    private boolean partidaEnCurso=false;
    //declaración variables de tipo entero para gestionar el valor de las etiquetas
    private int nivelesPartida,nivelesRestantes,nivelActual=1,puntuacion=0;
    
    
    //método main
    public static void main(String [] args) {
        new KukuKube().metodoPrincipal();
    }
    
    //método metodoPrincipal
    private void metodoPrincipal() {
        //instanciación contenedor JFrame 
        ventana=new JFrame();
        //título contenedor pruebaBotones
        ventana.setTitle("TALLER 2 - PROGRAMACIÓN II - 2021-2022 - UIB");
        //asignación a panelContenidos del panel de contenidos del contenedor
        //JFrame
        panelContenidos=ventana.getContentPane();
        //llamada al método inicializacion
        inicializacion();
    }
    
    private void inicializacion() {
        
////////////////////////////////////////////////////////////////////////////////                                                                    //
//                SEPARADORES JSplitPane DE LA INTERFACE                      //
////////////////////////////////////////////////////////////////////////////////        
        
//DECLARACIÓN SEPARADORES JSplitPane DE LA INTERFACE
        JSplitPane separador1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separador2= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separador3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);        

        
////////////////////////////////////////////////////////////////////////////////
//                      PANEL panelSuperior y COMPONENTES                     //
////////////////////////////////////////////////////////////////////////////////

/////////DECLARACIÓN CONTENEDOR JPanel panelSuperior
        JPanel panelSuperior = new JPanel();
        //asignación administrador de Layout
        panelSuperior.setLayout(new GridLayout());
        //asignar primer separador
        panelSuperior.add(separador1);
        
////////COMPONENTE JMenuBar barraMenu
        JMenuBar barraMenu = new JMenuBar();
        //asignar barraMenu en la posición superior del separador1
        separador1.setTopComponent(barraMenu);
        
////////COMPONENTE JMenu menu
        JMenu menu = new JMenu("MENU");
        //inclusión de la componente JMenu menu en JMenuBar barraMenu
        barraMenu.add(menu);

////////COMPONENTE JMenuItem nuevaPartidaMenu
        JMenuItem nuevaPartidaMenu = new JMenuItem("NUEVA PARTIDA");
        //inclusión de la componente JMenuItem nuevaPartidaMenu en JMenu menu
        menu.add(nuevaPartidaMenu);
        //ASIGNACIÓN DEL GESTOR DE EVENTO gestorEvento A LA COMPONENTE
        nuevaPartidaMenu.addActionListener(new manipuladorEventosFuncionalidades());
        
////////COMPONENTE JMenuItem salirMenu
        JMenuItem salirMenu = new JMenuItem("SALIR");
        //inclusión de la componente JMenuItem salirMenu en JMenu menu
        menu.add(salirMenu);
        //ASIGNACIÓN DEL GESTOR DE EVENTO gestorEvento A LA COMPONENTE
        salirMenu.addActionListener(new manipuladorEventosFuncionalidades());
        
/////////DECLARACIÓN CONTENEDOR JPanel panelInformacion
        JPanel panelInformacion = new JPanel();
        //asignación administrador GridLayout al contenedor
        panelInformacion.setLayout(new GridLayout(2,2));
        //establecer el fondo de color negro
        panelInformacion.setBackground(Color.black);
        //asignar panelInformacion a la posición inferior del separador1
        separador1.setBottomComponent(panelInformacion);
     
/////////DECLARACIÓN CONTENEDOR JPanel panelInformativo1
        JPanel panelInformativo1 = new JPanel();
        //establecer fondo de color negro
        panelInformativo1.setBackground(Color.black);
        //asignación administrador Layout al contenedor
        panelInformativo1.setLayout(new FlowLayout(FlowLayout.LEFT,5,3));
        //inclusión de la componente JPanel panelInformativo1 en el contenedor JPanel panelInformacion
        panelInformacion.add(panelInformativo1);
        
/////////DECLARACIÓN CONTENEDOR JPanel panelInformativo2
        JPanel panelInformativo2 = new JPanel();
        //establecer fondo de color negro
        panelInformativo2.setBackground(Color.black);
        //asignación administrador Layout al contenedor
        panelInformativo2.setLayout(new FlowLayout(FlowLayout.RIGHT,5,3));
        //inclusión de la componente JPanel panelInformativo2 en el contenedor JPanel panelInformacion
        panelInformacion.add(panelInformativo2);

/////////DECLARACIÓN CONTENEDOR JPanel panelInformativo3
        JPanel panelInformativo3 = new JPanel();
        //establecer fondo de color negro
        panelInformativo3.setBackground(Color.black);
        //asignación administrador Layout al contenedor
        panelInformativo3.setLayout(new FlowLayout(FlowLayout.LEFT,5,3));
        //inclusión de la componente JPanel panelInformativo3 en el contenedor JPanel panelInformacion
        panelInformacion.add(panelInformativo3);

/////////DECLARACIÓN CONTENEDOR JPanel panelInformativo4
        JPanel panelInformativo4 = new JPanel();
        //establecer fondo de color negro
        panelInformativo4.setBackground(Color.black);
        //asignación administrador Layout al contenedor
        panelInformativo4.setLayout(new FlowLayout(FlowLayout.RIGHT,5,3));
        //inclusión de la componente JPanel panelInformativo4 en el contenedor JPanel panelInformacion
        panelInformacion.add(panelInformativo4);
        
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaNomNivelesPartida
        etiquetaNomNivelesPartida = new JLabel("NIVELES PARTIDA ");
        //establecer fondo de color blanco
        etiquetaNomNivelesPartida.setBackground(Color.white);
        //establecer opacidad
        etiquetaNomNivelesPartida.setOpaque(true);
        //establecer tipo de letra
        etiquetaNomNivelesPartida.setFont(new Font("arial", Font.BOLD, 17));
        
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaValNivelesPartida
        etiquetaValNivelesPartida = new JLabel("000");
        //establecer fondo de color blanco
        etiquetaValNivelesPartida.setBackground(Color.white);
        //establecer TRAZADO de color rojo
        etiquetaValNivelesPartida.setForeground(Color.red);
        //establecer opacidad
        etiquetaValNivelesPartida.setOpaque(true);
        //establecer tipo de letra
        etiquetaValNivelesPartida.setFont(new Font("arial", Font.BOLD, 17));
        
        //inclusión de las componentes JLabel en la componente JPanel panelInformativo1
        panelInformativo1.add(etiquetaNomNivelesPartida);
        panelInformativo1.add(etiquetaValNivelesPartida);
        
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaNomNivelesRestantes
        etiquetaNomNivelesRestantes = new JLabel("NIVELES RESTANTES ");
        //establecer fondo de color blanco
        etiquetaNomNivelesRestantes.setBackground(Color.white);
        //establecer opacidad
        etiquetaNomNivelesRestantes.setOpaque(true);
        //establecer tipo de letra
        etiquetaNomNivelesRestantes.setFont(new Font("arial", Font.BOLD, 17));
        
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaValNivelesRestantes        
        etiquetaValNivelesRestantes = new JLabel("000");
        //establecer fondo de color blanco
        etiquetaValNivelesRestantes.setBackground(Color.white);
        //establecer TRAZADO de color rojo
        etiquetaValNivelesRestantes.setForeground(Color.red);
        //establecer opacidad
        etiquetaValNivelesRestantes.setOpaque(true);
        //establecer tipo de letra
        etiquetaValNivelesRestantes.setFont(new Font("arial", Font.BOLD, 17));
        
        //inclusión de las componentes JLabel en la componente JPanel panelInformativo2
        panelInformativo2.add(etiquetaNomNivelesRestantes);
        panelInformativo2.add(etiquetaValNivelesRestantes);
        
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaNomNivelActual
        etiquetaNomNivelActual = new JLabel("NIVEL ACTUAL       ");
        //establecer fondo de color blanco
        etiquetaNomNivelActual.setBackground(Color.white);
        //establecer opacidad
        etiquetaNomNivelActual.setOpaque(true);
        //establecer tipo de letra
        etiquetaNomNivelActual.setFont(new Font("arial", Font.BOLD, 17));
       
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaValNivelActual        
        etiquetaValNivelActual = new JLabel("000");
        //establecer fondo de color blanco
        etiquetaValNivelActual.setBackground(Color.white);
        //establecer TRAZADO de color rojo
        etiquetaValNivelActual.setForeground(Color.red);
        //establecer opacidad
        etiquetaValNivelActual.setOpaque(true);
        //establecer tipo de letra
        etiquetaValNivelActual.setFont(new Font("arial", Font.BOLD, 17));
        
        //inclusión de las componentes JLabel en la componente JPanel panelInformativo3
        panelInformativo3.add(etiquetaNomNivelActual);
        panelInformativo3.add(etiquetaValNivelActual);
        
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaNombrePuntuacion
        etiquetaNombrePuntuacion = new JLabel("PUNTUACIÓN               ");
        //establecer fondo de color blanco
        etiquetaNombrePuntuacion.setBackground(Color.white);
        //establecer opacidad
        etiquetaNombrePuntuacion.setOpaque(true);
        //establecer tipo de letra
        etiquetaNombrePuntuacion.setFont(new Font("arial", Font.BOLD, 17));
        
/////////INSTANCIACIÓN COMPONENTE JLABEL etiquetaValPuntuacion        
        etiquetaValPuntuacion = new JLabel("000");
        //establecer fondo de color blanco
        etiquetaValPuntuacion.setBackground(Color.white);
        //establecer TRAZADO de color rojo
        etiquetaValPuntuacion.setForeground(Color.red);
        //establecer opacidad
        etiquetaValPuntuacion.setOpaque(true);
        //establecer tipo de letra
        etiquetaValPuntuacion.setFont(new Font("arial", Font.BOLD, 17));
        
        //inclusión de las componentes JLabel en la componente JPanel panelInformativo4
        panelInformativo4.add(etiquetaNombrePuntuacion);
        panelInformativo4.add(etiquetaValPuntuacion);
        
        
////////////////////////////////////////////////////////////////////////////////
//                      PANEL panelVisualizacion y COMPONENTES                //
////////////////////////////////////////////////////////////////////////////////

/////////INSTANCIACIÓN CONTENEDOR JPanel panelVisualizacion
        panelVisualizacion = new JPanel();
        //asignación administrador de Layout
        panelVisualizacion.setLayout(new CardLayout());
        
/////////INSTANCIACIÓN CONTENEDOR JPanel panelStandby
        panelStandby = new JPanel();
        //asignación administrador de Layout
        panelStandby.setLayout(new BorderLayout());
        
//////////DECLARACIÓN COMPONENTE JLabel imagenUIB i escalado de la imagen para reducir su tamaño
        ImageIcon imagenUIB = new ImageIcon("uib.gif");
        Image tamaño = imagenUIB.getImage();
        tamaño = tamaño.getScaledInstance(imagenUIB.getIconWidth()*2/3, imagenUIB.getIconHeight()*2/3,0);
        imagenUIB.setImage(tamaño);
        JLabel gif = new JLabel(imagenUIB);
        
        //INTRODUCCIÓN COMPONENTE JLabel imagenUIB EN EL CONTENEDOR JPanel panelStandby
        //EN LA ZONA CENTRAL DEL ADMINISTRADOR BorderLayout
        panelStandby.add(gif,BorderLayout.CENTER);  
        panelVisualizacion.add(panelStandby,"PANEL STANDBY");
   
        
////////////////////////////////////////////////////////////////////////////////
//                      PANEL panelBotones y COMPONENTES                      //
////////////////////////////////////////////////////////////////////////////////        
        
/////////DECLARACIÓN CONTENEDOR JPanel panelBotones
        JPanel panelBotones = new JPanel();
        //asignación administrador de Layout
        panelBotones.setLayout(new GridLayout(1,2));

////////COMPONENTE JButton nuevaPartidaBoton
        JButton nuevaPartidaBoton = new JButton("NUEVA PARTIDA");
        //asignación COLOR TRAZADO
        nuevaPartidaBoton.setForeground(Color.WHITE);
        //asignación COLOR FONDO
        nuevaPartidaBoton.setBackground(Color.BLACK);
        //inclusión de la componente JButton nuevaPartidaBoton en el contenedor JPanel panelBotones
        panelBotones.add(nuevaPartidaBoton);  
        //ASIGNACIÓN DEL GESTOR DE EVENTO gestorEvento A LA COMPONENTE
        nuevaPartidaBoton.addActionListener(new manipuladorEventosFuncionalidades());
        
////////COMPONENTE JButton salirBoton
        JButton salirBoton = new JButton("SALIR");
        //asignación COLOR TRAZADO
        salirBoton.setForeground(Color.WHITE);
        //asignación COLOR FONDO
        salirBoton.setBackground(Color.BLACK);
        //inclusión de la componente JButton salirBoton en el contenedor JPanel panelBotones
        panelBotones.add(salirBoton);
        //ASIGNACIÓN DEL GESTOR DE EVENTO gestorEvento A LA COMPONENTE
        salirBoton.addActionListener(new manipuladorEventosFuncionalidades());
    
////////////////////////////////////////////////////////////////////////
//   DISTRIBUCIÓN SEPARADORES, CONTENEDORES Y COMPONENTES SEGÚN EL    //
//   DISEÑO DE LA INTERFACE                                           //
////////////////////////////////////////////////////////////////////////

////////INCLUSIÓN DE LA COMPONENTE JSPLITPANE separador2 EN LA ZONA NORTE DEL BorderLayout 
////////EN EL PANEL DE CONTENIDOS DEL CONTENEDOR JFrame
separador2.add(panelSuperior);
panelContenidos.add(separador2,BorderLayout.NORTH);

////////INCLUSIÓN DEL CONTENEDOR JPanel panelVisualizacion EN LA ZONA CENTRAL DEL BorderLayout 
////////EN EL PANEL DE CONTENIDOS DEL CONTENEDOR JFrame
panelContenidos.add(panelVisualizacion, BorderLayout.CENTER);

////////INCLUSIÓN DE LA COMPONENTE JSPLITPANE separador3 EN LA ZONA SUR DEL BorderLayout 
////////EN EL PANEL DE CONTENIDOS DEL CONTENEDOR JFrame
separador3.setBottomComponent(panelBotones);
panelContenidos.add(separador3,BorderLayout.SOUTH);


////////////////////////////////////////////////////////////////////////////////
//                  ÚLTIMAS INTERVENCIONES CONTENEDOR JFrame                  //
////////////////////////////////////////////////////////////////////////////////  
        //DIMENSIONAMIENTO DEL CONTENEDOR JFrame ventana 
        ventana.pack();
        //CENTRADO DEL CONTENEDOR ventana EN EL CENTRO DE LA PANTALLA
        ventana.setLocationRelativeTo(null);
        //ACTIVACIÓN DEL CIERRE INTERACTIVO VENTANA DE WINDOWS EN EL CONTENEDOR 
        //JFrame ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //bloquear su redimensión
        ventana.setResizable(false);
        //VISUALIZACIÓN CONTENEDOR JFrame ventana
        ventana.setVisible(true);
    }
    
    //MÉTODOS FUNCIONALES
    private void solicitarDatosPartida() {
        try {
            String [] literalesIntroduccion={"NÚMERO NIVELES(1-10)","COMPLEJIDAD(1-10)"};
            literalesIntroduccion=new lecturaDatosJDialog(ventana,literalesIntroduccion).getDatosTexto();
            //verificar que el usuario introduzca un número y que este no sea mayor que 11
            if ((literalesIntroduccion!=null)&&(Integer.parseInt(literalesIntroduccion[0])<11
                    &&Integer.parseInt(literalesIntroduccion[1])<11&&(Integer.parseInt(literalesIntroduccion[0])>0)
                    &&(Integer.parseInt(literalesIntroduccion[1])>0))) {
                    //parsear valor introducido por el usuario
                    numNiveles=Integer.parseInt(literalesIntroduccion[0]);
                    //mostrar por pantalla valor seleccionado
                    System.out.println("NÚMERO NIVELES:"+numNiveles);
                    //asignar el número de niveles totales a la variable nivelesRestantes
                    nivelesRestantes=numNiveles;
                    //parsear valor introducido por el usuario
                    complejidad=Integer.parseInt(literalesIntroduccion[1]);
                    //mostrar por pantalla valor seleccionado
                    System.out.println("COMPLEJIDAD:"+complejidad);
                    
                }else {
                    //salta excepcion
                    throw new InsercionDatosExcepcion("Datos Introducidos Incorrectos");     
                }    
            
        }catch (InsercionDatosExcepcion error) {
            //llamamos al mismo método para que el usuario esté obligado a ingresar datos
            solicitarDatosPartida();
            System.out.println("\nERROR: " + error.toString());
        }catch (Exception error) {
            //llamamos al mismo método para que el usuario esté obligado a ingresar datos
            solicitarDatosPartida();
            System.out.println("\nERROR: " + error.toString());
        }                                                                                              
    }
    
    //método datoToString posibilita la correcta visualización en los JLabel
    private String datoToString(int dato) {
        if(dato<10) {
            return "00"+dato;
        }else if (dato<100) {
            return "0"+dato;
        }else {
            return dato+"";
        }
    }
    
    //método clearPanelInformativo hace un reset del panel informativo
    private void clearPanelInformativo() {
        int cero=0;
        etiquetaValNivelesPartida.setText(datoToString(cero));
        etiquetaValNivelesRestantes.setText(datoToString(cero));
        etiquetaValNivelActual.setText(datoToString(cero));
        etiquetaValPuntuacion.setText(datoToString(cero));
        //dejar preparados los datos de las etiquetas para el próximo nivel
        nivelesRestantes=numNiveles;
        nivelActual=1;
        puntuacion=0;
    }
    
    //método setPanelInformativo permite establecer el valor de las etiquetas
    //informativas en función del nivel en el que estemos y la puntuación que tengamos
    private void setPanelInformativo(boolean dato) {
        if(nivelesRestantes!=0) {
            --nivelesRestantes;
        }
        etiquetaValNivelesPartida.setText(datoToString(numNiveles));
        if (dato) {
            etiquetaValNivelesRestantes.setText(datoToString(nivelesRestantes));
            etiquetaValNivelActual.setText(datoToString(nivelActual++));
        }
        etiquetaValPuntuacion.setText(datoToString(puntuacion));
    }
    
    //gestor de eventos MouseListener para verificar cuando se pulsa un botón casilla
    public MouseListener eventosRaton() {
        MouseListener accion=new MouseListener(){
            @Override
            public void mousePressed(MouseEvent evento) {
                //DECLARACIONES LOCALES
                int botonSeleccionado=panelJuego.getBotonSeleccionado();
                int posicionBotonDiferente=panelJuego.getPosicionBotonDiferente();
                CardLayout local = (CardLayout)(panelVisualizacion.getLayout());
                int numeroComponente=0;
                
                //verificar que casilla se ha seleccionado
                for (;numeroComponente<panelJuego.getBotonesLength();numeroComponente++) {
                    if (evento.getSource()==panelJuego.getBotones()[numeroComponente]) break;
                }
                botonSeleccionado=numeroComponente;
                panelJuego.getBotones()[botonSeleccionado].botonIncorrecto();
                
                //verificar si la casilla seleccionada coincide con la que tiene el color diferente
                if(botonSeleccionado==posicionBotonDiferente) {
                    panelJuego.getBotones()[botonSeleccionado].botonCorrecto();
                    JOptionPane.showMessageDialog(ventana,"¡¡¡CUADRADO SELECCIONADO CORRECTO!!!");
                    //verificar si el contador de partida ya ha realizado todos los niveles
                    if(contadorPartida<=numNiveles) {
                        //crear nuevo tablero
                        panelJuego = new Tablero(++contadorPartida,eventosRaton(),complejidad);
                        //actualizar puntuación
                        puntuacion=puntuacion+nivelActual;
                        //llamar al método para actualizar las etiquetas
                        setPanelInformativo(true);
                        //mostrar el nuevo tablero
                        panelVisualizacion.add(panelJuego,"PANEL JUEGO");
                        local.show(panelVisualizacion, "PANEL JUEGO");
                    }else {
                        puntuacion=puntuacion+nivelActual;
                        //actualizar los valores de las etiquetas
                        setPanelInformativo(false);
                        //mostrar mensaje
                        JOptionPane.showMessageDialog(ventana,"¡¡¡LA PARTIDA HA TERMINADO CON "
                                + "UN TOTAL DE " + puntuacion + " PUNTOS!!!");
                        //limpiar la información de las etiquetas del panelInformativo
                        clearPanelInformativo();
                        //mostrar el nuevo tablero
                        local.show(panelVisualizacion, "PANEL STANDBY");
                        //establecer fin de partida
                        partidaEnCurso=false;
                    }      
                }else {
                    panelJuego.getBotones()[posicionBotonDiferente].botonCorrecto();
                    //mostrar mensaje
                    JOptionPane.showMessageDialog(ventana,"¡¡¡CUADRADO SELECCIONADO ERRÓNEO!!!");
                    //verificar si el contador de partida ya ha realizado todos los niveles
                    if(contadorPartida<=numNiveles) {
                        //crear nuevo tablero
                        panelJuego = new Tablero(++contadorPartida,eventosRaton(),complejidad);
                        panelVisualizacion.add(panelJuego,"PANEL JUEGO");
                        //actualizar los valores de las etiquetas
                        setPanelInformativo(true);
                        //mostrar el nuevo tablero
                        local.show(panelVisualizacion, "PANEL JUEGO");
                    }else {
                        //mostrar mensaje
                        JOptionPane.showMessageDialog(ventana,"¡¡¡LA PARTIDA HA TERMINADO CON "
                                + "UN TOTAL DE " + puntuacion + " PUNTOS!!!");
                        //limpiar la información de las etiquetas del panelInformativo
                        clearPanelInformativo();
                        //mostrar el nuevo tablero
                        local.show(panelVisualizacion, "PANEL STANDBY");
                        //establecer fin de partida
                        partidaEnCurso=false;
                    }
                } 
            }
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}   
        };
        return accion;
    }
    
    //gestor de eventos general para organizar el funcionamiento del programa
    private class manipuladorEventosFuncionalidades implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento)  { 
                CardLayout local = (CardLayout)(panelVisualizacion.getLayout());
                switch (evento.getActionCommand()) {
                    
                    case "NUEVA PARTIDA":       //verificar si la partida sigue en curso
                                                if(!partidaEnCurso) {
                                                //empezar partida nueva
                                                partidaEnCurso=true;
                                                //solicitar datos
                                                solicitarDatosPartida();
                                                //establecer contador de niveles a 1
                                                contadorPartida=1;
                                                //crear tablero
                                                panelJuego = new Tablero(++contadorPartida,eventosRaton(),complejidad);
                                                panelVisualizacion.add(panelJuego,"PANEL JUEGO");
                                                //actualizar los valores de las etiquetas
                                                setPanelInformativo(true);
                                                //visualización en el panelInterfaces del
                                                //contenedor asociado al literal "NUEVA PARTIDA"
                                                local.show(panelVisualizacion, "PANEL JUEGO");
                                            }else {
                                                //mostrar mensaje
                                                JOptionPane.showMessageDialog(ventana,
                                                        "¡¡¡NO SE PUEDE INICIAR UNA PARTIDA "
                                                                + "HABIENDO UNA PARTIDA EN CURSO!!!");
                                            }
                                            break;
                                            
                    case "SALIR":           System.exit(0);
                                            break;
                }
        }
    };
}
