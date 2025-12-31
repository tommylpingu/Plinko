public class Moltiplicatore {
    final int NUM_MOLT = 16;
    final int POS_Y = 730;
    final int OFFSET_X = 182;
    int DIM_BASE;
    int index;
    int posX = 0;
    public double valore;

    Moltiplicatore(int index, int DIM_BASE){
        this.index = index;
        this.DIM_BASE = DIM_BASE;
        setValore();    //da il valore ai moltiplicatori
    }

    public int getPosX() {
        posX = OFFSET_X + DIM_BASE*index + (3*DIM_BASE)*index; //Posiziona in x contando l'offset dal bordo, il raggio degli ostacoli (così sta in mezzo), e la distanza tra gli ostacoli che è la larghezza del moltiplicatore
        return posX;
    } 

    public int getPosY(){
        return POS_Y;
    }

    public void setValore(){                //usato per settare il valore del moltiplicatore corrente in base all'indice, SE DOBBIAMO MODIFICARE I MOLTIPLICATORI QUESTO E' IL PUNTO
        switch (index) {
            case 0:  valore = 88;  break;
            case 1:  valore = 18;  break;
            case 2:  valore = 11;  break;
            case 3:  valore = 5;   break;
            case 4:  valore = 3;   break;
            case 5:  valore = 1.3; break;
            case 6:  valore = 0.5; break;
            case 7:  valore = 0.3; break;
            case 8:  valore = 0.3; break;
            case 9:  valore = 0.5; break;
            case 10: valore = 1.3; break;
            case 11: valore = 3;   break;
            case 12: valore = 5;   break;
            case 13: valore = 11;  break;
            case 14: valore = 18;  break;
            case 15: valore = 88;  break;
            default: valore = 0;   break;
        }
    }

    public boolean isMoltGiusto(double pallinaX){           //controlla in base al valore di x se è parte di questo moltiplicatore
        if(pallinaX >= posX && pallinaX <= posX+(3*DIM_BASE)){      //controlla la zona in x che va dall'inzio del rettangolo alla fine (lunghezza = 3*DIM_BASE)
            return true;
        }
        return false;
    }


}
