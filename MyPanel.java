import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel; 

class MyPanel extends JPanel {
 
    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        MyKeyAdapter keyboard = new MyKeyAdapter(this);
        addKeyListener(keyboard);     
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

    }  

    
}
