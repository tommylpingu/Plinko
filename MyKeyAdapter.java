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
        /*
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: pannelloSuCuiLavorare.muovi(0, -1); break;
            case KeyEvent.VK_S: pannelloSuCuiLavorare.muovi(0, 1); break;
            case KeyEvent.VK_A: pannelloSuCuiLavorare.muovi(-1, 0); break;
            case KeyEvent.VK_D: pannelloSuCuiLavorare.muovi(1, 0); break;
            case KeyEvent.VK_R: pannelloSuCuiLavorare.generaMappa(); break; // R per rigiocare
        }
        */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilizzato
    }
}
