import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel; 

class MyPanel extends JPanel {
 
    boolean inizializzati = false;

    static int DIM_BASE = 15; //dimensione base degli oggetti 
    
    static Ostacolo[] ostacoli = new Ostacolo[150];


    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        MyKeyAdapter keyboard = new MyKeyAdapter(this);
        addKeyListener(keyboard);
        for (int i = 0; i < ostacoli.length; i++) {                               
            ostacoli[i] = new Ostacolo(i, DIM_BASE, getWidth(), 150);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        //PER ORA E' COMMENTATO PERCHE' HO COMMENTATO LA RESIZABLE, COSI'
        //CAMBIA LA POSIZIONE DEI PUNTI SE CAMBI LA DIMENSIONE DELLA FINESTRA
        //if (!inizializzati) {       //Inizializza gli oggetti tanto restano uguali basta crearli una volta, 
            for (int i = 0; i < ostacoli.length; i++) {
                ostacoli[i] = new Ostacolo(i, DIM_BASE, getWidth(), ostacoli.length); 
            } 
            inizializzati = true; 
        //}
        for(int i = 0; i < 150; i++){
            int OstX = ostacoli[i].getX();
            int OstY = ostacoli[i].getY();
            g.setColor(Color.RED);
            g.fillOval(OstX,OstY,DIM_BASE,DIM_BASE);
            g.setColor(Color.BLACK);
            g.drawOval(OstX,OstY,DIM_BASE,DIM_BASE);
        }

    }  

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000,800);
    }

    
}
