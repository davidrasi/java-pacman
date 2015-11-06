package data;

/**
 * Esta clase representa el laberinto con una determinada Anchura
 * y Altura, en la que cada celda puede estar VACIA, contener
 * un BLOQUE_CON_COCO (muro) o LIMITE (limite de los fantasmas)
 */
public class Laberinto{
    public static final int VACIA           = 0;
    public static final int BLOQUE          = 1;
    public static final int LIMITE          = 2;
    public static final int PUNTO           = 3;
    public static final int PUNTO_GRANDE    = 4;

    
    private int anchura;
    private int altura;
    
    private int[][] celdas;
    
    /**
     * Crea espacio para una rejilla con anchura igual a w  y altura
     * igual a h.
     * @param w anchura de la nueva Laberinto
     * @param h altura de la nueva Laberinto
     */
    public Laberinto(int w,int h){
        anchura=w;
        altura=h;
        celdas= new int[anchura][altura];
        initRejilla();
    }
    
    /**
     * Devuelve la anchura de la rejilla.
     * @return la anchura de la rejilla
     */
    public int getAnchura(){
        return anchura;
    }
    
    /**
     * Devuelve la altura de la rejilla.
     * @return la altura de la rejilla
     */
    public int getAltura(){
        return altura;
    }
       
    /**
     * Establece el tipo de celda en las coordenadas x e y del Laberinto
     *  @param x coordenada x (columna)
     * @param y coordenada y (fila)
     * @param valor el tipo de celda (VACIA, LIMITE o BLOQUE_CON_COCO)
     */
    public void assignTipoCelda(int x,int y,int valor){
        celdas[x][y]= valor ;
    }
    
    /**
     * Obtiene el tipo de celda en las coordenadas x e y de esta Laberinto
     * @param x coordenada x (columna)
     * @param y coordenada y (fila)
     * @return el tipo de Celda en la coordenada x,y.
     */
    
    public int getTipoCelda(int x,int y){
        return celdas[x][y];
    }

    /**
     * Cambia el tipo de celda
     * @param x coordenada x (columna)
     * @param y coordenada y (fila)
     * @param tipo tipo de celda nuevo
     */

    public void setTipoCelda(int x,int y,int tipo){
         celdas[x][y] = tipo ;
    }
    
    
    String rejilla[]= { // Array de Strings que representa el estado inicial del laberinto
        
        "BBBBBBBBBBBBBBBBBBBBBBBBBBBB",
        "Bo...........BB...........oB",
        "B.BBBB.BBBBB.BB.BBBBB.BBBB.B",
        "B.BBBB.BBBBB.BB.BBBBB.BBBB.B",
        "B.BBBB.BBBBB.BB.BBBBB.BBBB.B",
        "B..........................B",
        "B.BBBB.BB.BBBBBBBB.BB.BBBB.B",
        "B.BBBB.BB.BBBBBBBB.BB.BBBB.B",
        "B......BB....BB....BB......B",
        "BBBBBB.BBBBB.BB.BBBBB.BBBBBB",
        "     B.BBBBB.BB.BBBBB.B     ",
        "     B.BB..........BB.B     ",
        "     B.BB.BBppppBB.BB.B     ",
        "BBBBBB.BB.B      B BB.BBBBBB",
        "Bo........B      B........oB",
        "BBBBBB.BB.B      B.BB.BBBBBB",
        "     B.BB.BBBBBBBB.BB.B     ",
        "     B.BB..      ..BB.B     ",
        "     B.BB.BBBBBBBB.BB.B     ",
        "BBBBBB.BB.BBBBBBBB.BB.BBBBBB",
        "B............BB............B",
        "B.BBBB.BBBBB.BB.BBBBB.BBBB.B",
        "B.BBBB.BBBBB.BB.BBBBB.BBBB.B",
        "B...BB................BB...B",
        "BBB.BB.BB.BBBBBBBB.BB.BB.BBB",
        "BBB.BB.BB.BBBBBBBB.BB.BB.BBB",
        "B......BB....BB....BB......B",
        "B.BBBBBBBBBB.BB.BBBBBBBBBB.B",
        "B.BBBBBBBBBB.BB.BBBBBBBBBB.B",
        "Bo........................oB",
        "BBBBBBBBBBBBBBBBBBBBBBBBBBBB",  
    };



    /**
     *Inicializa el laberinto (rejilla) según el array de strings inicial representado
     */
    public void initRejilla(){

        int i,j;

        for (i = 0; i<rejilla.length; i++){
            
            String columna = rejilla[i];

            for (j = 0; j<columna.length(); j++){

                char letra = columna.charAt(j);

                switch (letra){
                    case 'B':
                        celdas[j][i]=BLOQUE;
                        break;
                    case ' ':
                        celdas[j][i]=VACIA;
                        break;
                    case 'p':
                        celdas[j][i] = LIMITE;
                        break;
                    case '.':
                        celdas[j][i] = PUNTO;
                        break;
                    case 'o':
                        celdas[j][i] = PUNTO_GRANDE;
                        break;

                }

            }

        }



    }
    
    /**
     * Pone las celdas de la figura como ocupadas en la rejilla en las posiciones correspondientes
     * @param fig La Figura que queremos copiar en la rejilla
     * @return Devuelve true si tras copiar la figura en la rejilla se ocupa alguna celda de
     * las 4 primeras filas. false en otro caso
     */ 
    boolean copiaFiguraEnRejilla(Figura fig){
        
        Elemento elemento;
        boolean valorDevuelto=false;
        
        for(int i=0;i<fig.getNElements();i++){
            elemento=fig.getElementAt(i);
            if(elemento.getFila()+fig.getYOrigen()<6)
                valorDevuelto=true;
            celdas[elemento.getColumna()+fig.getXOrigen()][elemento.getFila()+fig.getYOrigen()]=LIMITE;
        }
        return valorDevuelto;
    }   
    
    /**
     * Indica si al mover la figura una celda según la direccion indicada, se chocará con
     * algún muro del laberinto y por tanto no podra realizar movimiento
     * @param fig la Figura que queremos comprobar si se chocará
     * @param direccion de movimiento (Figura.ABAJO,Figura.DERECHA o FIGURA.IZQUIERDA o FIGURA.ARIBA)
     * @return true si se choca, false en caso contrario
     */
    public boolean seChoca(Figura fig, int direccion){
        
          Elemento elemento;

        for (int i = 0; i < fig.getNElements(); i++) {
            elemento = fig.getElementAt(i);

            if (direccion == Figura.ABAJO) {

                if (celdas[elemento.getColumna() + fig.getXOrigen()][elemento.getFila() + fig.getYOrigen() + 1] != VACIA
                     && celdas[elemento.getColumna() + fig.getXOrigen()][elemento.getFila() + fig.getYOrigen() + 1] != PUNTO
                     && celdas[elemento.getColumna() + fig.getXOrigen()][elemento.getFila() + fig.getYOrigen() + 1] != PUNTO_GRANDE) {
                    //System.out.println("------------se choca por ABAJO-------------");
                    return true;
                }

            } else if (direccion == Figura.IZQUIERDA) {

                if (celdas[elemento.getColumna() + fig.getXOrigen() - 1][elemento.getFila() + fig.getYOrigen()] != VACIA
                     && celdas[elemento.getColumna() + fig.getXOrigen() - 1][elemento.getFila() + fig.getYOrigen()] != PUNTO
                     && celdas[elemento.getColumna() + fig.getXOrigen() - 1][elemento.getFila() + fig.getYOrigen()] != PUNTO_GRANDE) {
                    //System.out.println("------------se choca por IZQUIERDA-------------");
                    return true;
                }

            } else if (direccion == Figura.DERECHA) {

                if (celdas[elemento.getColumna() + fig.getXOrigen() + 1][elemento.getFila() + fig.getYOrigen()] != VACIA
                     && celdas[elemento.getColumna() + fig.getXOrigen() + 1][elemento.getFila() + fig.getYOrigen()] != PUNTO
                     && celdas[elemento.getColumna() + fig.getXOrigen() + 1][elemento.getFila() + fig.getYOrigen()] != PUNTO_GRANDE  ){
                    //System.out.println("------------se choca por DERECHA-------------: "+celdas[elemento.getColumna() + fig.getXOrigen() + 1][elemento.getFila() + fig.getYOrigen()]);
                    return true;
                }
                
            }else if (direccion == Figura.ARRIBA) {

                if (celdas[elemento.getColumna() + fig.getXOrigen()][elemento.getFila() + fig.getYOrigen()-1] != VACIA
                     && celdas[elemento.getColumna() + fig.getXOrigen()][elemento.getFila() + fig.getYOrigen()-1] != PUNTO
                     && celdas[elemento.getColumna() + fig.getXOrigen()][elemento.getFila() + fig.getYOrigen()-1] != PUNTO_GRANDE) {
                    //System.out.println("------------se choca por ARRIBA-------------: "+celdas[elemento.getColumna() + fig.getXOrigen()][elemento.getFila() + fig.getYOrigen()-1]);
                    
                    return true;
                }

            }

        }

        return false;
    }
    
}

 
