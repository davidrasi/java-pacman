


package data;
//import javax.swing.JPanel;
import pacman_v3.*;
//import data.*;
//import java.awt.Color;

/**
 * Esta clase implementa una hebra que hace que se mueva continuamente .
 * La hebra se encarga también de ir refrescando la pantalla
 * donde se dibuja todos todos los elementos correspondientes segús sus nuevos estados.
 * Además controla si la Figura se choca contra un muro o contra las piezas ya colocadas.
 */
public class Mueve implements Runnable{
    private int delay;
    private boolean continuar=true;
    private boolean suspendFlag=true;
    private PacmanFrame frame;
    private int score = 0 ;
   
       
    /**
     * Constructor de la clase, que inicializa la referencia utilizadas por
     * la hebra al cpmecocos, establece el retardo en milisegundos
     * entre movimiento y movimiento de la Figura actual, y comienza a ejecutar
     * la hebra. 
     */
    public Mueve(PacmanFrame fr,int nivel){
        
        frame=fr;      
        delay= 100;//Retardo
        Thread t=new Thread(this); //Hebra       
        t.start();
        
    }


    /**
     *Inicializa la puntuación del comecocos a 0
     */
    public void inicializaScore() {
        this.score = 0;
    }

   
 
    
    /**
     * Código que constituye las sentencias de la hebra. En este caso, se encarga
     * de hacer que se mueva continuamente el comecocos
     * La hebra se encarga también de ir refrescando la pantalla
     * donde se dibuja todo, y los puntos acumulados. Además controla si
     * el comecocos choca contra un muro o se come cualquier punto,
     * Cuando el comecocos se come los diferentes puntos aumenta la puntuación y cambia el
     *tipo de la celda.
     */
    public void run(){
        try{
            while(continuar){
                synchronized(this){
                    while(suspendFlag){
                        wait();
                    }
                }
                
                Thread.sleep(delay);
               
                if (!frame.getRejilla().seChoca(frame.getFigura(), Figura.direction)) {

                    frame.getFigura().mueve(Figura.direction);

                    if (frame.getPanel() != null) {
                        frame.getPanel().repaint();
                    }
                    
                    int tipoCelda = frame.getRejilla().getTipoCelda(frame.getFigura().getXOrigen(), frame.getFigura().getYOrigen());
          
                    if (tipoCelda == Laberinto.PUNTO || tipoCelda == Laberinto.PUNTO_GRANDE) {

                        if (tipoCelda == Laberinto.PUNTO_GRANDE) {

                            System.out.println("Come gordo");
                            score += 40;
                        }
                        
                        score += 10;            
                        frame.setPuntuacion(score);                     
                        frame.getRejilla().setTipoCelda(frame.getFigura().getXOrigen(), frame.getFigura().getYOrigen(), Laberinto.VACIA);
                        frame.getPanel().repaint();
                    }
                    
                }


            }// end while(continuar)
        } catch (InterruptedException e){
            System.out.println("Hilo Pacman interrumpido");
        }
    }
    
    /**
     * Detiene momentaneamente la ejecución de la hebra, haciendo que la Figura(comecocos)
     * quede parada.
     */
    synchronized public void suspender(){
        frame.getPanel().repaint();
        suspendFlag=true;
    }
    
    /**
     * Metodo sincronizado. Reanuda el movimiento de la hebra y lo notifica.
     * La Figura actual vuelve  a moverse.
     */
    public synchronized void reanudar(){
        if(frame.getPanel()!=null)
            frame.getPanel().repaint();
        suspendFlag = false;
        notify();
    }
    
    /**
     * Termina la ejecución de la hebra.
     */
    public void parar(){
        continuar=false;
        
    }
    
    /**
     * Nos dice si la hebra está o no parada.
     * @return true si la hebra de movimiento está parada, false en otro caso
     */
    synchronized public boolean getParado(){
        return suspendFlag;
    }
    
}
 
