public class Pallina extends Thread {
    
    private double x;
    private double y;
    private int diametro=10;
    private double puntataAffiliata;
    private double angoloAttuale=90; //verso il basso 270 gradi a destra 0 gradi verso l'alto 90 gradi e a sinistra 180 
    private int velocita=5; 
    private int gravita=1; //per aumentare un po'la velocità quando va verso il basso
    MyPanel f = null;
    
    

    public Pallina(int x, int y,double puntataAffiliata, MyPanel f) {
        this.x = x;
        this.y = y;
        this.puntataAffiliata = puntataAffiliata;
        this.f = f;
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

    public void Movimento()
    {
       ModificaAngolo();
       double angoloInRadianti = Math.toRadians(angoloAttuale);
       double xTmp = (double)(Math.cos(angoloInRadianti) *velocita);
       double yTmp = (double)(Math.sin(angoloInRadianti) *velocita) + gravita;       
       this.x += xTmp;
       this.y += yTmp;
         
    }
    public void ModificaAngolo()
    {
        //Controlla collisioni con ostacoli e modifica l'angolo di conseguenza
        if(!collisioneConOstacolo())
        {
            return;
        }
        else
        {
             angoloAttuale = 240 + Math.random() * 60; // per fare un rimbalzo un po' casuale
        }

    }
    public boolean collisioneConOstacolo()
    {
        for(int i=0; i< MyPanel.ostacoli.length; i++)
        {
            Ostacolo o = MyPanel.ostacoli[i];
            double distX = Math.abs(this.x - o.getX());
            double distY = Math.abs(this.y - o.getY());
            double distanza = (double)Math.sqrt(distX*distX + distY*distY);
            if(distanza <= this.diametro) //non sono sicuro che funzioni per trovare gli ostacoli quando proveremo in caso cambio
            {
                return true;
            }
        }
        return false;
    }
    public boolean isFinish()
    {
        return false;
        /* 
        if(this.y == y della riga dei moltiplicatori) // mi serve la y dove metteremo i moltiplicatori almeno so dove far finire il percorso
        {
            return true;
        }
        return false;*/
    }

    @Override
    public void run() {
        
        while( !isFinish())
        {
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


 