import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class SwingPaintDemo1 {
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }
    
    private static void createAndShowGUI() {
        
        JFrame f = new JFrame("Pinklo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000,800);
        //f.setResizable(false); //Fa si che la window no possa essere cambiata di dimensioni, se no si sminchia tutto secondo me
        MyPanel p = new MyPanel();
        f.add(p);
        f.pack();
        f.setVisible(true);
    }
}