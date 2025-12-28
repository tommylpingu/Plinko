public class Moltiplicatore {
    final int NUM_MOLT = 16;
    final int POS_Y = 730;
    final int OFFSET_X = 182;
    int DIM_BASE;
    int index;
    int posX;

    Moltiplicatore(int index, int DIM_BASE){
        this.index = index;
        this.DIM_BASE = DIM_BASE;
    }

    public int getPosX() {
        posX = OFFSET_X + DIM_BASE*index + (3*DIM_BASE)*index; //Posiziona in x contando l'offset dal bordo, il raggio degli ostacoli (così sta in mezzo), e la distanza tra gli ostacoli che è la larghezza del moltiplicatore
        return posX;
    } 

    public int getPosY(){
        return POS_Y;
    }


}
