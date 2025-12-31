import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel; 

class MyPanel extends JPanel {
 
    boolean inizializzati = false;

    private JLabel testo;   //testo per il saldo

    static int DIM_BASE = 15; //dimensione base degli oggetti 
    static double SALDO_INIZIO = 1000;
    
    static Ostacolo[] ostacoli = new Ostacolo[150];
    static Moltiplicatore[] moltiplicatori = new Moltiplicatore[16];
    ArrayList<Pallina> palline = new ArrayList<>(); //dichiarazione vettore dinamico di palline
    Punteggio punteggio = new Punteggio(SALDO_INIZIO); //inizializza saldo tot 

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        MyKeyAdapter keyboard = new MyKeyAdapter(this);
        addKeyListener(keyboard);
        testo = new JLabel(SALDO_INIZIO+"€"); //inizia scrivendo il saldo iniziale (valore costante tra le variabili in cima)
        testo.setBounds(10, 10, 200, 30);   //posizione del testo VA MODIFICATA, PER ORA IN CIMA ANDRA MESSO NELLA BARRA A SINISTRA
        add(testo);
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
        int soldiScommessi = 10; //Questo valore dobbiamo cambiarlo in base a quanto vuole puntare l'utente 
        stampaPunteggio(-soldiScommessi); //chiami per togliere la puntata dal saldo
        if(segno == 0)
        {
            offset = offset*(-1);
        }
        int randX = (larghezza/2) + offset;                                                              
        Pallina nuovaPallina = new Pallina(randX, 20, soldiScommessi, this, DIM_BASE+5, DIM_BASE, moltiplicatori); //moltiplicatori è l'array di moltiplicatori che passo a pallina per farle calcolare il moltiplicatore da usare sulla puntata  
        palline.add(nuovaPallina);
        nuovaPallina.start();
        repaint();
    }
    
    public void stampaPunteggio(double puntataAffiliata){ //Richiamare sta funzione passandogli i soldi scommessi e per stamparli (quando premi il pulsante passo la puntata negativa così la toglie, quando la pallina arriva passo la puntataAffiliata che cambia in base al moltiplicatore che ha colpito)
        punteggio.cambiaSaldo(puntataAffiliata);
        double punti = punteggio.saldoTot;
        testo.setText(punti + "€");
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1400,600);
    }
}