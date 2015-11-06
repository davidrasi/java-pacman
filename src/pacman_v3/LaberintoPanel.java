

package pacman_v3;

import java.awt.Color;
import data.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Clase que representa el laberinto del juego 
 * @author davidramirezsierra
 */
public class LaberintoPanel extends javax.swing.JPanel {

    private PacmanFrame frame;
    private int anchoCelda = -1;



    /** Constructor sin parámetros que inicializa los componentes del panel del laberinto */
    public LaberintoPanel() {
        initComponents();        
    }

    /**
     *Constructor
     */
    public LaberintoPanel(PacmanFrame fr) {
        this();
        frame = fr;
        frame = fr;
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);

    }


    /**
     * Dibuja la Rejilla (Laberinto) dependiendo del tipo que tenga cada celda
     *
     * @param g graphics
     *
     */

    public void dibujaRejilla(java.awt.Graphics g) {

        int i, j;
        Laberinto rejilla = frame.getRejilla();

        int xoffset = (getWidth() - rejilla.getAnchura() * anchoCelda) / 2;


        for (i = 0; i < rejilla.getAnchura(); i++) {

            for (j = 0; j < rejilla.getAltura(); j++) {

                if (rejilla.getTipoCelda(i, j) == Laberinto.BLOQUE) {

                    g.setColor(Color.BLUE);
                    g.fillRect(xoffset + i * anchoCelda, j * anchoCelda, anchoCelda, anchoCelda);
                    g.setColor(Color.BLUE);
                    g.drawRect(xoffset + i * anchoCelda, j * anchoCelda, anchoCelda, anchoCelda);


                } else if (rejilla.getTipoCelda(i, j) == Laberinto.LIMITE) {

                    g.setColor(Color.BLACK);
                    g.fillRect(xoffset + i * anchoCelda, j * anchoCelda + anchoCelda / 2, anchoCelda, 1);
                    g.setColor(Color.WHITE);
                    g.drawRect(xoffset + i * anchoCelda, j * anchoCelda, anchoCelda, anchoCelda);


                } else if (rejilla.getTipoCelda(i, j) == Laberinto.VACIA) {


                    g.setColor(Color.WHITE);
                    g.drawRect(xoffset + i * anchoCelda, j * anchoCelda, anchoCelda, anchoCelda);

                } else if (rejilla.getTipoCelda(i, j) == Laberinto.PUNTO) {

                    g.setColor(Color.BLACK);
                    g.fillOval(xoffset + anchoCelda / 2 + i * anchoCelda, j * anchoCelda + anchoCelda / 2, 4, 4);


                } else if (rejilla.getTipoCelda(i, j) == Laberinto.PUNTO_GRANDE) {

                    g.setColor(Color.BLACK);
                    g.fillOval(xoffset + anchoCelda / 3 + i * anchoCelda, j * anchoCelda + anchoCelda / 3, 9, 9);

                }


            }

        }
    }


    /**
     * método sobreescrito de JComponent que dibuja la rejilla y la figura.
     * Para ello llama a los metodos dibjarRejilla y dibujarFigura tambien implementados en esta clase
     *
     * */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (anchoCelda == -1) {
            anchoCelda = Math.min(getWidth() / frame.getRejilla().getAnchura(), (getHeight()) / frame.getRejilla().getAltura());
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        dibujaRejilla(g);
        dibujaFigura(frame.getFigura(), g);


    }


    /**
     * dibuja la figura del comecocos dependiendo de donde esté situado y de la dirección que siga
     * @param fig la figura con sus correspondientes miembros actualizados
     * @param g graphics
     * */
    void dibujaFigura(Figura fig, java.awt.Graphics g) {

        if (fig != null) {
            Elemento elemento;
            Laberinto rejilla = frame.getRejilla();

            int xoffset = (getWidth() - rejilla.getAnchura() * anchoCelda) / 2 + fig.getXOrigen() * anchoCelda;
            int yoffset = fig.getYOrigen() * anchoCelda;

            for (int i = 0; i < fig.getNElements(); i++) {
                elemento = fig.getElementAt(i);

                g.setColor(Color.YELLOW);
                g.fillOval(xoffset + elemento.getColumna() * anchoCelda, yoffset + elemento.getFila() * anchoCelda, anchoCelda, anchoCelda);
                g.setColor(Color.BLACK);
                g.drawOval(xoffset + elemento.getColumna() * anchoCelda, yoffset + elemento.getFila() * anchoCelda, anchoCelda, anchoCelda);
                g.setColor(Color.RED);

                int b = 0;
                switch (frame.getFigura().direction) {

                    case Figura.ABAJO:
                        b = 12;
                        break;
                    case Figura.ARRIBA:
                        b = 3;
                        break;
                    case Figura.DERECHA:
                        b = 17;
                        break;
                    case Figura.IZQUIERDA:
                        b = 8;
                        break;


                }
                g.fillArc(xoffset + elemento.getColumna() * anchoCelda, yoffset + elemento.getFila() * anchoCelda,
                        anchoCelda, anchoCelda, anchoCelda * b, anchoCelda * 4);

                g.setColor(Color.RED);
                g.drawArc(xoffset + elemento.getColumna() * anchoCelda, yoffset + elemento.getFila() * anchoCelda,
                        anchoCelda, anchoCelda, anchoCelda * b, anchoCelda * 4);



            }
        }
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 500));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LaberintoPanel.this.mouseEntered(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaberintoPanel.this.keyPressed(evt);
                keyPressed2(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void keyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyPressed
        
        int tipoCelda = frame.getRejilla().getTipoCelda(frame.getFigura().getXOrigen(), frame.getFigura().getYOrigen());
        //System.out.println("Tipo celda: "+tipoCelda);


        if (tipoCelda == Laberinto.PUNTO || tipoCelda == Laberinto.PUNTO_GRANDE) {

            //System.out.println("come: "+frame.getFigura().getXOrigen()+" "+frame.getFigura().getYOrigen());
            System.out.println("punto");
            frame.getRejilla().setTipoCelda(frame.getFigura().getXOrigen(), frame.getFigura().getYOrigen(), Laberinto.VACIA);
            //frame.getPanel().repaint();
        }


        if (evt.getKeyCode() == KeyEvent.VK_LEFT && frame.getFigura().direction != Figura.IZQUIERDA) {

            if (!frame.getRejilla().seChoca(frame.getFigura(), Figura.IZQUIERDA)) {
                //System.out.println("izda");
                //frame.getFigura().mueve(Figura.IZQUIERDA);
                frame.getFigura().setDireccion(Figura.IZQUIERDA);
            }
            if (frame.getPanel() != null) {
                frame.getPanel().repaint();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT && frame.getFigura().direction != Figura.DERECHA) {
            if (!frame.getRejilla().seChoca(frame.getFigura(), Figura.DERECHA)) {
                //frame.getFigura().mueve(Figura.DERECHA);
                frame.getFigura().setDireccion(Figura.DERECHA);
                //System.out.println("drcha");
            }
            if (frame.getPanel() != null) {
                frame.getPanel().repaint();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP && frame.getFigura().direction != Figura.ARRIBA) {
                
            if (!frame.getRejilla().seChoca(frame.getFigura(), Figura.ARRIBA)) {
                //System.out.println("arriba");
                //frame.getFigura().mueve(Figura.ARRIBA);
                frame.getFigura().setDireccion(Figura.ARRIBA);
                if (frame.getPanel() != null) {
                    frame.getPanel().repaint();
                }
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN && frame.getFigura().direction != Figura.ABAJO) {
            if (!frame.getRejilla().seChoca(frame.getFigura(), Figura.ABAJO)) {
                //System.out.println("abajo");
                //frame.getFigura().mueve(Figura.ABAJO);
                frame.getFigura().setDireccion(Figura.ABAJO);
                if (frame.getPanel() != null) {
                    frame.getPanel().repaint();
                }
            }
        }

    }//GEN-LAST:event_keyPressed

    private void mouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseEntered
       
    }//GEN-LAST:event_mouseEntered

    private void keyPressed2(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyPressed2
        // TODO add your handling code here:
        //requestFocus();

        if(evt.getKeyCode() == KeyEvent.VK_SPACE)
            System.exit(0);
    }//GEN-LAST:event_keyPressed2

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
