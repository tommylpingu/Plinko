public class Pallina extends Thread 
{
    private double x;
    private double y;
    private int diametro;
    private double puntataAffiliata;
    private double velocitaX = 0;  
    private double velocitaY = 0.5;  
    private double gravita = 0.18;
    private int cooldownCollisione = 0;
    private double ultimaDirezioneCollisione = 1;
    MyPanel f = null;

    public Pallina(int x, int y, double puntataAffiliata, MyPanel f, int diametro) { 
        this.x = x;
        this.y = y;
        this.puntataAffiliata = puntataAffiliata;
        this.f = f;
        this.diametro = diametro;
    }

    @Override
    public void run() {
        while (!isFinish()) {
            Movimento();
            f.repaint();
            try {
                Thread.sleep(16);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public void Movimento() {
        if (cooldownCollisione > 0) {
            cooldownCollisione--;
        }   
        
        if (cooldownCollisione == 0 && collisioneConOstacolo()) {
            double spintaLaterale = (Math.random() * 1.5 + 1.5);
            velocitaX = ultimaDirezioneCollisione * spintaLaterale;   
            velocitaY *= 0.4;    
            cooldownCollisione = 12;
        }
        
        velocitaY += gravita;
        if (velocitaY > 7) {
            velocitaY = 7;
        }   
        
        x += velocitaX;
        y += velocitaY;
        velocitaX *= 0.96;
    }

    public boolean collisioneConOstacolo() 
    {
        double rOstacolo = 10;
        double rPallina = diametro / 2.0;
        
        for (Ostacolo o : MyPanel.ostacoli)
        {
            double ox = o.getX();
            double oy = o.getY();
            double dx = x - ox;
            double dy = y - oy;
            double distanza = Math.sqrt(dx * dx + dy * dy);  
            
            if (distanza <= rOstacolo + rPallina) 
            {   
                double sovrapposizione = (rOstacolo + rPallina) - distanza;
                // tengo la lunghezza positiva
                double lunghezza = Math.max(distanza, 0.001);  
                double nx = dx / lunghezza;
                double ny = dy / lunghezza; 
                x += nx * (sovrapposizione + 2.5);
                y += ny * (sovrapposizione + 2.5);         
                // Determino la direzione del rimbalzo
                if (dx < 0) {
                    ultimaDirezioneCollisione = -1;
                } else {
                    ultimaDirezioneCollisione = 1;
                }      
                return true;
            }
        }
        return false;
    }

    public boolean isFinish() {
        return y > f.getHeight();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDiametro() {
        return diametro;
    }

    public double getPuntataAffiliata() {
        return puntataAffiliata;
    }
}