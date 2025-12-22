public class Pallina extends Thread {
private double x;
private double y;
private int diametro;
private double puntataAffiliata;
private double angoloAttuale = 90; // verso il basso 270 gradi a destra 0 gradi verso l'alto 90 gradi e a sinistra 180
private int velocita = 5;
private int gravita = 1; // per aumentare un po' la velocità quando va verso il basso
MyPanel f = null;

public Pallina(int x, int y, double puntataAffiliata, MyPanel f, int diametro) { 
    // Passo anche il diametro così se dobbiamo cambiarlo lo facciamo solo nel JPanel (DIM_BASE è il valore)
    this.x = x;
    this.y = y;
    this.puntataAffiliata = puntataAffiliata;
    this.f = f;
    this.diametro = diametro;
}

public double getAngoloAttuale() {
    return angoloAttuale;
}

public void setAngoloAttuale(double angoloAttuale) {
    this.angoloAttuale = angoloAttuale;
}

public double getPuntataAffiliata() {
    return puntataAffiliata;
}

public double getX() {
    return x;
}

public void setX(double x) {
    this.x = x;
}

public double getY() {
    return y;
}

public void setY(double y) {
    this.y = y;
}

public int getDiametro() {
    return diametro;
}

public void Movimento() {
    ModificaAngolo();
    stabilizzaAngolo();
    double angoloInRadianti = Math.toRadians(angoloAttuale);
    double xTmp = (double) (Math.cos(angoloInRadianti) * velocita);
    double yTmp = (double) (Math.sin(angoloInRadianti) * velocita) + gravita;
    this.x += xTmp;
    this.y += yTmp;
}

// Funziona però dopo la collisione l'angolo non varia e quindi la pallina tende troppo a sinistra (non so perchè)
// ma provo a fare uno stabilizzatore di angolo che con il tempo riporta l'angolo verso i 90 se trovi la funzione serve a questo
public void ModificaAngolo() {
    // Controlla collisioni con ostacoli e modifica l'angolo di conseguenza
    if (!collisioneConOstacolo()) {
        return;
    } else {
        double deviazione = 20 + Math.random() * 25; // deviazione pseudocasuale tra 20 e 45 gradi (in caso modificabile)
        if (Math.random() < 0.5) {
            angoloAttuale = 90 + deviazione; // va a sinistra
        } else {
            angoloAttuale = 90 - deviazione; // va a destra
        }
        // questi due if servono per evitare che la pallina rimbalzi verso l'alto e si blocchi (mi è successo in un test ma raro)
        // if (angoloAttuale < 100) angoloAttuale = 100;
        if (angoloAttuale > 260) angoloAttuale = 260;
    }
}

private void stabilizzaAngolo() {
    double ritorno = 3; // velocità di ritorno verso 90 gradi

    if (angoloAttuale > 90) {
        for (int i = 0; i < 2; i++) {
            angoloAttuale -= ritorno;
        }
        if (angoloAttuale < 90) angoloAttuale = 90;
    } else if (angoloAttuale < 90) {
        for (int i = 0; i < 9; i++) {
            angoloAttuale += ritorno;
        }
        if (angoloAttuale > 90) angoloAttuale = 90; // FUNZIONE DI TEST perchè nel plinko serve che la pallina non tenda a sinistra
    }
}

public boolean collisioneConOstacolo() {
    for (int i = 0; i < MyPanel.ostacoli.length; i++) {
        Ostacolo o = MyPanel.ostacoli[i];
        double distX = Math.abs(this.x - o.getX());
        double distY = Math.abs(this.y - o.getY());
        double distanza = (double) Math.sqrt(distX * distX + distY * distY);
        if (distanza <= this.diametro) // non sono sicuro che funzioni per trovare gli ostacoli quando proveremo in caso cambio
        {
            return true;
        }
    }
    return false;
}

public boolean isFinish() {
    return false;
    /*
    if(this.y == y della riga dei moltiplicatori) // mi serve la y dove metteremo i moltiplicatori almeno so dove far finire il percorso
    {
        return true;
    }
    return false;
    */
}

@Override
public void run() {
    while (!isFinish()) {
        Movimento();
        f.repaint();
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
}