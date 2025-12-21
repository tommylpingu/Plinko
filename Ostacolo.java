public class Ostacolo extends Thread {
    
    int x;
    int y;
    int i;
    int puntiPrimaRiga = 3;

    public Ostacolo(int i, int DIM_BASE, int larghPannello, int totaleOstacoli) {
        this.i = i;
        x = posX(i, DIM_BASE, larghPannello, totaleOstacoli);
        y = posY(i, DIM_BASE);       
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    int posX(int i, int DIM_BASE, int larghPannello, int totaleOstacoli) {     //Genera la posizione x alla chiamata 
        int riga = calcolaRiga(i);
        int totPrecedenti = 0;


        for (int k = 0; k < riga; k++) {                //Trovare i punti prima di i
            totPrecedenti += puntiPrimaRiga + k;
        }
        int colonna = i - totPrecedenti;                // capisce di quale colonna fa parte i
        int puntiRiga = puntiPrimaRiga + riga;          // numero di punti nella riga
        int larghezzaRiga = (puntiRiga - 1) * (4*DIM_BASE);   //I punti vengongo distanziati di 4*DIM_BASE quindi le varie distanze dei precedenti hanno quello tra di loro

        int offsetRiga = (larghPannello - larghezzaRiga) / 2;      //Centra il triangolo nel panel

        return offsetRiga + colonna * (4*DIM_BASE);         //trova x prendendo l'offset per centrare i punti e sommandolo al valore della colonna per le distanze tra i punti
    }

    int posY(int i, int DIM_BASE) {         //Genera la posizione y alla chiamata 
        int riga = calcolaRiga(i);
        return riga * (3 * DIM_BASE) + 80;       //distanzia le righe y tra di loro di 3*DIM_BASE + 80 che è un offset a caso così non è attaccato in cima
    }

    private int calcolaRiga(int i) {        //Calcola la riga del punto i
        int riga = 0;       
        int puntiR = 0;
        while (puntiR + puntiPrimaRiga + riga <= i) {
            puntiR += puntiPrimaRiga + riga;    //per trovare i punti precedenti fa riga + punti primaRiga perchè è tipo 3 + 1 alla prima riga (partendo da 0)
            riga++;
        }
        return riga;
    }
}


 