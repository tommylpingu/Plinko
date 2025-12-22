import javax.swing.SwingUtilities;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class SwingPaintDemo1 {
    
    private static JButton button;
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
        f.setSize(1400,900);
        f.setResizable(false); //Fa si che la window no possa essere cambiata di dimensioni, se no si sminchia tutto secondo me
        MyPanel p = new MyPanel();
        f.setLayout(new BorderLayout());
        f.add(p, BorderLayout.CENTER);

        button = creaButton(p);         //Crea il bottone a sinistra 
        f.add(button, BorderLayout.WEST);

        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));   //lo mette in un èanel a sinistra dove metteremo tutte le interazioni dell'utente
        westPanel.add(button);

        f.add(westPanel, BorderLayout.WEST);

        f.setVisible(true);
    }

    private static JButton creaButton(MyPanel p){           //funzioni pulsante 
        JButton button = new JButton("Gioca!");
        button.setFocusable(false); //Leva il rettangolo che puoi selezionare con il tab
        button.setPreferredSize(new Dimension(80, 30));
        button.addActionListener(e -> p.generaPallina());      //richiama la genera pallina
        return button;
    }
}