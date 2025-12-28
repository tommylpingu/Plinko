 public class Pallina extends Thread 
{
    private double x;
    private double y;
    private int diametro;
    private double puntataAffiliata;
    //private double angoloAttuale = 90; al momento inutilizzata // verso il basso 270 gradi a destra 0 gradi verso l'alto 90 gradi e a sinistra 180
    private double velocitaX = 0;  
    private double velocitaY = 0.5;  
    private double gravita = 0.18; // Accelerazione gravitazionale (per il valore sono andato a tentativi)
    private int cooldownCollisione = 0; // Evita collisioni multiple consecutive
    private double ultimaDirezioneCollisione = 1; // 1 = destra, -1 = sinistra
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
                Thread.sleep(16); // vicino ai 60 fps per compensare la imprecicisione con la velocità della pallina
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public void Movimento() {
        // Diminuisco il cooldown
        if (cooldownCollisione > 0) {
            cooldownCollisione--;
        }   
        if (cooldownCollisione == 0 && collisioneConOstacolo()) {
            // se la pallina è nella parte sinistra dell'ostacolo rimbalza a sinistra, altrimenti a destra
            double spintaLaterale = (Math.random() * 1.5 + 1.5); // da 1,5 a 3
            velocitaX = ultimaDirezioneCollisione * spintaLaterale;   
            // diminuisco la velocità per la collisione 
            velocitaY *= 0.4;    
            cooldownCollisione = 12;
        }
        velocitaY += gravita;
        if (velocitaY > 7) {
            velocitaY = 7; // vel massima
        }   
        x += velocitaX;
        y += velocitaY;
        velocitaX *= 0.96;// attrito per provare a renderlo più realistico (per non aumentare troppo velocemente la velocità)
    }

    public boolean collisioneConOstacolo() 
    {
        double rOstacolo = 10;  // raggio fisso ostacolo
        double rPallina = diametro / 2.0;
        // centro pallina
        double cx = x + rPallina;
        double cy = y + rPallina;

        for (Ostacolo o : MyPanel.ostacoli) // simile al foreach di python perché non mi servono gli indici
        {
            double ox = o.getX();
            double oy = o.getY();
            double dx = cx - ox;
            double dy = cy - oy;
            double distanza = Math.sqrt(dx * dx + dy * dy);  
            if (distanza <= rOstacolo + rPallina) 
            {
                // Determino la direzione del rimbalzo (sinistra o destra)
                // in base a dove si trova la pallina rispetto all'ostacolo
                if (dx < 0) {
                    ultimaDirezioneCollisione = -1; // pallina a sinistra
                } else {
                    ultimaDirezioneCollisione = 1; // pallina a destra
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