import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;  
 

class MyPanel extends JPanel {
   
 
    boolean inizializzati = false;

    private JLabel testo;   //testo per il saldo
    static int DIM_BASE = 15; //dimensione base degli oggetti 
    static double SALDO_INIZIO = 1000;
    public Font font;
    static Ostacolo[] ostacoli = new Ostacolo[150];
    static Moltiplicatore[] moltiplicatori = new Moltiplicatore[16];
    ArrayList<Pallina> palline = new ArrayList<>(); //dichiarazione vettore dinamico di palline
    Punteggio punteggio = new Punteggio(SALDO_INIZIO); //inizializza saldo tot 
    JTextField textField;

    public MyPanel(){
        this.setBackground(new Color(0, 80, 139));
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        MyKeyAdapter keyboard = new MyKeyAdapter(this);
        addKeyListener(keyboard);
        testo = new JLabel(SALDO_INIZIO+"€"); //inizia scrivendo il saldo iniziale (valore costante tra le variabili in cima)
        testo.setForeground(Color.WHITE);
        testo.setBounds(10, 10, 200, 30);   //posizione del testo VA MODIFICATA, PER ORA IN CIMA ANDRA MESSO NELLA BARRA A SINISTRA
        add(testo);
        PassaggioDati.cancellaDati();
    }

    public void setTextField(JTextField textField) { //Prende l'oggetto textField per poter ottenere il valore scritto dentro
        this.textField = textField;
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
            g.setColor(Color.WHITE);
            g.fillOval(OstX,OstY,DIM_BASE,DIM_BASE);
            g.setColor(Color.GRAY);
            g.drawOval(OstX,OstY,DIM_BASE,DIM_BASE);   
        }
        
        // Disegna moltiplicatori
        for(int i = 0; i < moltiplicatori.length; i++){
            int MolX = moltiplicatori[i].getPosX();
            int MolY = moltiplicatori[i].getPosY();
            switch ((int)(moltiplicatori[i].valore*10)) {
                case 580:  g.setColor(new Color(128,0,128));  break;
                case 145:  g.setColor(new Color(139,0,0));  break;
                case 56:  g.setColor(new Color(255,0,0));   break;
                case 35:  g.setColor(new Color(255,140,0));   break;
                case 18:  g.setColor(new Color(255,165,0));    break;
                case 10:  g.setColor(new Color(255,163,62));  break;
                case 5:  g.setColor(new Color(255,255,0));  break;
                case 3:  g.setColor(new Color(255,255,224));  break;
            }
            g.fillRoundRect(MolX, MolY, DIM_BASE*3, DIM_BASE*3, 5, 5);
            g.setColor(Color.BLACK);
            g.drawRoundRect(MolX, MolY, DIM_BASE*3, DIM_BASE*3, 5, 5);

            //setta il font per il drawString
            font = new Font("Cambria", Font.BOLD, 12);
            g.setColor(Color.BLACK);

            g.setFont(font);
            g.setColor(Color.BLACK);
            if(moltiplicatori[i].valore < 10){                          //stampo le lable su i moltiplicatori, se il valore ha una cifra decimale lo stampa più in la per centrarlo
                String testoValore = moltiplicatori[i].valore + "x";    //controllo decimali
                g.drawString(testoValore, MolX + 10, MolY + 25);
            }
            else{
                String testoValore = moltiplicatori[i].valore + "x";
                g.drawString(testoValore, MolX + 6, MolY + 25);
            }

        }
        
        // Disegna le palline nel vettore dinamico   
        for(Pallina pallina : palline) //for each perchè per ora non ci interessa l'indice poi in caso cambio
        {
            if(pallina != null) 
            {
                g.setColor(Color.black);
                g.drawOval((int)pallina.getX(), (int)pallina.getY(),pallina.getDiametro(), pallina.getDiametro());
                g.setColor(Color.gray);
                g.fillOval((int)pallina.getX(), (int)pallina.getY(),pallina.getDiametro(), pallina.getDiametro());
            }
        }    
    }  

    public void generaPallina() {
        int larghezza = getWidth();
        int offset = (int) (Math.random() * (DIM_BASE*3));
        int segno = (int) (Math.random() * 2);

        int soldiScommessi = 0;
        try {
            soldiScommessi = Integer.parseInt(textField.getText());
        } catch (Exception e) {
            soldiScommessi = 0;
        }
        if(controllaSaldo(soldiScommessi)){//se il saldo va in negativo non fa partire la puntata
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
        }else{
            testo.setText("PUNTATA TROPPO ALTA!!!");
            repaint();
        }

    }
    
    public void stampaPunteggio(double puntataAffiliata){ //Richiamare sta funzione passandogli i soldi scommessi e per stamparli (quando premi il pulsante passo la puntata negativa così la toglie, quando la pallina arriva passo la puntataAffiliata che cambia in base al moltiplicatore che ha colpito)
        punteggio.cambiaSaldo(puntataAffiliata);
        double punti = punteggio.saldoTot;
        testo.setText(punti + "€");
    }
    
    public boolean controllaSaldo(double puntataAffiliata){
        if(punteggio.saldoTot - puntataAffiliata < 0){
            return false;
        }else{
            return true;
        }
    }

    public int getTotalePerValore(double valoreCercato) {
    int totale = 0;
    for (Moltiplicatore m : moltiplicatori) 
    {
        if (m != null && Math.abs(m.valore - valoreCercato) < 0.01)//if perchè una volta ho avuto problemi con l'arrotondamento dei double
        {
            totale += m.Contatore;
        }  
    }
    return totale;
}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1400,600);
    }
}
