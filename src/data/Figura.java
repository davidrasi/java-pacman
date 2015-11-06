

package data;

import java.util.Vector;

/**
 * Representa la pieza del clásico comecocos.
 * Además, existen cuatro tipos de direcciones que puede seguir dicha figura.
 * @author David Ramírez Sierra
 */
public class Figura {
    public static final int IZQUIERDA         = 0;
    public static final int DERECHA           = 1;
    public static final int ABAJO             = 2;
    public static final int ARRIBA            = 3;

    public static int direction           = 3;

    
    
    
    
    private Vector<Elemento> elements;
    private int xorigen;
    private int yorigen;
    private static int tipo;
    /** 
     * Creates a new instance of Figura 
     * @param fila0 utilizado para definir las celdas ocupadas por esta Figura
     */
    public Figura(int fila0){//,int fila1,int fila2,int fila3) {
        elements = new Vector<Elemento>();
        addElements(0,fila0);
    }
    
    /**
     * Genera una nueva Figura y su posición icial
     * @return la nueva Figura generada 
     */
    public static Figura nuevaFigura(){
        Figura fig=null;
        
            tipo = 0;
            fig = new Figura(0xF);

        fig.xorigen=13; // posicion inicial del comecocos al comienzo de la partida
        fig.yorigen=17;
        
        return fig;
    }

    public int getTipo() {
        return tipo;
    }


    /**
     * Cambia la dirección que sigue el comecocos
     *@param la dirección nueva que sigue
     */
    public void setDireccion(int dir){
        direction = dir;
    }
   
    /**
     * Añade los Elementos ocupadas en la fila indicada como parámetro  a la Figura actual
     * @param fila la fila
     * @param valor entero usado como máscara hexadecimal para indicar las casillas ocupadas en la fila (ver el constructor)
     */
    private void addElements(int fila, int valor){
        if((valor & 0xF)>0) elements.addElement(new Elemento(fila,0));
    }
    
    /**
     * Obtiene el número de Elementos (celdas) que forma la Figura actual
     * @return el número de Elementos (celdas) que forma la Figura actual
     */
    public int getNElements(){
        return elements.size();
    }
    
    /**
     * Obtiene el Elemento en la posición pos de la Figura actual
     * @param pos la posición
     * @return el Elemento en la posición pos de la Figura actual
     */
    public Elemento getElementAt(int pos){
        return (Elemento)elements.elementAt(pos);
    }
    
    /**
     * Obtiene la posición x respecto al origen de coordenadas de la Rejilla de la Figura actual
     * @return la posición x respecto al origen de coordenadas de la Rejilla de la Figura actual
     */
    public int getXOrigen(){
        return xorigen;
    }
 
    /**
     * Obtiene la posición y respecto al origen de coordenadas de la Rejilla de la Figura actual
     * @return la posición y respecto al origen de coordenadas de la Rejilla de la Figura actual
     */
    public int getYOrigen(){
        return yorigen;
    }
    
    /**
     * Mueve la Figura actual una casilla en la dirección indicado por direccion (ABAJO,IZQUIERDA o ARRIBA)
     * @param direccion la dirección de movimiento (ABAJO,IZQUIERDA o ARRIBA)
     */
    public void mueve(int direccion){

        if(direccion==ABAJO){
            direction = ABAJO;
            yorigen++;
        }else if(direccion==IZQUIERDA){
            direction = IZQUIERDA;
            xorigen--;
        }else if(direccion==DERECHA){
            direction = DERECHA;
            xorigen++;
        }else if (direccion==ARRIBA){
            direction = ARRIBA;
            yorigen--;
        }
    }
       
}
 
