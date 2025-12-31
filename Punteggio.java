public class Punteggio {
    double saldoTot;

    Punteggio(double saldoTot){
        this.saldoTot = saldoTot;
    }

    public void cambiaSaldo(double val){
        saldoTot += val;
    }

    public double getSaldoTot() {
        return saldoTot;
    }
}
