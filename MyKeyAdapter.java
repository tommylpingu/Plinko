import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyAdapter implements KeyListener {
    MyPanel pannelloSuCuiLavorare;
    
    public MyKeyAdapter(MyPanel p) {
        this.pannelloSuCuiLavorare = p;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilizzato
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilizzato
    }
}
