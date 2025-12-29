 import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel; 

class MyPanel extends JPanel {
 
    boolean inizializzati = false;

    static int DIM_BASE = 15; //dimensione base degli oggetti 
    
    static Ostacolo[] ostacoli = new Ostacolo[150];
    static Moltiplicatore[] moltiplicatori = new Moltiplicatore[16];
    ArrayList<Pallina> palline = new ArrayList<>(); //dichiarazione vettore dinamico di palline

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        MyKeyAdapter keyboard = new MyKeyAdapter(this);
        addKeyListener(keyboard);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        
        if (!inizializzati) {       
            for (int i = 0; i < ostacoli.length; i++) {
                ostacoli[i] = new Ostacolo(i, DIM_BASE, getWidth(), ostacoli.length); 
            } 
            for (int i = 0; i < moltiplicatori.length; i++) {                               
                moltiplicatori[i] = new Moltiplicatore(i, DIM_BASE);
            }
            inizializzati = true; 
        }
        
        // Disegna ostacoli
        for(int i = 0; i < ostacoli.length; i++){
            int OstX = ostacoli[i].getX();
            int OstY = ostacoli[i].getY();
            g.setColor(Color.RED);
            g.fillOval(OstX,OstY,DIM_BASE,DIM_BASE);
            g.setColor(Color.BLACK);
            g.drawOval(OstX,OstY,DIM_BASE,DIM_BASE);   
        }
        
        // Disegna moltiplicatori
        for(int i = 0; i < moltiplicatori.length; i++){
            int MolX = moltiplicatori[i].getPosX();
            int MolY = moltiplicatori[i].getPosY();
            g.setColor(Color.GREEN);
            g.fillRoundRect(MolX, MolY, DIM_BASE*3, DIM_BASE*3, 5, 5);
            g.setColor(Color.BLACK);
            g.drawRoundRect(MolX, MolY, DIM_BASE*3, DIM_BASE*3, 5, 5);
        }
        
        // Disegna le palline nel vettore dinamico   
        for(Pallina pallina : palline) //for each perchè per ora non ci interessa l'indice poi in caso cambio
        {
            if(pallina != null) 
            {
                g.setColor(Color.BLUE);
                g.fillOval((int)pallina.getX(), (int)pallina.getY(), 
                pallina.getDiametro(), pallina.getDiametro());
            }
        }    
    }  

    public void generaPallina() {
        int larghezza = getWidth();
        int offset = (int) (Math.random() * (DIM_BASE*3));
        int segno = (int) (Math.random() * 2);
        if(segno == 0)
        {
            offset = offset*(-1);
        }
        int randX = (larghezza/2) + offset;
        Pallina nuovaPallina = new Pallina(randX, 20, 1.0, this, DIM_BASE+5);
        palline.add(nuovaPallina);
        nuovaPallina.start();
        repaint();
    }
    
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1400,600);
    }
}