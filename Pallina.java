public class Pallina extends Thread {
    
    private int x;
    private int y;
    private int diametro=10;
    private double puntataAffiliata;
    private double angoloAttuale=0; //verso il basso 0 gradi verso totale destra 90 verso totale sinistra -90
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
    public int getDiametro() {
        return diametro;
    }    

    @Override
    public void run() {
        
        while(true)
        {
                
          try {
            Thread.sleep(50);
          } catch (Exception e) {
            // TODO: handle exception
          }
        }
        
    }
    

}


 