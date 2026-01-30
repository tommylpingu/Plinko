public class AutoBet extends Thread{
    MyPanel p;

    public AutoBet(MyPanel p){
        this.p = p;
    }

    @Override
    public void run() {
        while(p.auto == true){
            try {
                p.generaPallina();
                sleep(200);
            } catch (Exception e) {}
        }
        super.run();
    }
}
