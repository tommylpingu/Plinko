public class Ostacolo extends Thread {
    
    int x;
    int y;
    int i;
    int puntiPrimaRiga = 3;

    public Ostacolo(int i, int DIM_BASE, int panelWidth, int totaleOstacoli) {
        this.i = i;
        x = posX(i, DIM_BASE, panelWidth, totaleOstacoli);
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
    
    int posX(int i, int DIM_BASE, int panelWidth, int totaleOstacoli) {     //Genera la posizione x alla chiamata 
        int DX = 4 * DIM_BASE;

        // calcola riga e colonna
        int riga = calcolaRiga(i);
        int totPrecedenti = 0;
        for (int k = 0; k < riga; k++) {
            totPrecedenti += puntiPrimaRiga + k;
        }
        int colonna = i - totPrecedenti;

        // numero di punti in questa riga
        int puntiRiga = puntiPrimaRiga + riga;

        // larghezza della riga
        int larghezzaRiga = (puntiRiga - 1) * DX;

        // offset per centrare la riga rispetto al pannello
        int offsetRiga = (panelWidth - larghezzaRiga) / 2;

        return offsetRiga + colonna * DX;
    }

    int posY(int i, int DIM_BASE) {         //Genera la posizione y alla chiamata 
        int riga = calcolaRiga(i);
        return riga * (3 * DIM_BASE);
    }

    private int calcolaRiga(int i) {
        int riga = 0;
        int accumulati = 0;

        while (accumulati + puntiPrimaRiga + riga <= i) {
            accumulati += puntiPrimaRiga + riga;
            riga++;
        }
        return riga;
    }
}


 