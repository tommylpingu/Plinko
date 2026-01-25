import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension; 

public class SwingPaintDemo1 {
    
    private static JButton button;
    private static JComboBox<Integer> comboBox;
    private static JTextField textField;

    public static void main(String[] args) {
         // Aggiungi questo SUBITO all'inizio del main
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                PassaggioDati.stampaRiepilogo();
                System.out.println("Riepilogo creato");
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        //crea
        JFrame f = new JFrame("Pinklo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1400,825);
        f.setResizable(false);

        //MyPanel
        MyPanel p = new MyPanel();
        f.setLayout(new BorderLayout());
        f.add(p, BorderLayout.CENTER);

        //Componenti westPanel
        button = creaButton(p);
        comboBox = creaComboBox(p);
        textField = creaTextField(p);
        
        //crea westPanel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new javax.swing.BoxLayout(westPanel, javax.swing.BoxLayout.Y_AXIS));
        westPanel.setPreferredSize(new Dimension(90, 825));

        //aggiunge box a westPanel
        westPanel.add(javax.swing.Box.createVerticalStrut(300));    //Box che fa centrare tutto

        //mette apposto i componenti se no erano tutti storti
        button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        comboBox.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        textField.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        //aggiunge componenti con un po' di spazio tra di loro
        westPanel.add(button);
        westPanel.add(javax.swing.Box.createVerticalStrut(5));
        
        westPanel.add(comboBox);
        westPanel.add(javax.swing.Box.createVerticalStrut(5));

        westPanel.add(textField);
        westPanel.add(javax.swing.Box.createVerticalStrut(5));
        
        //finisce di settare
        f.add(westPanel, BorderLayout.WEST);
        p.setTextField(textField);
        f.setVisible(true);
    }

    private static JButton creaButton(MyPanel p){           //funzioni pulsante 
        JButton button = new JButton("Gioca!");
        button.setFocusable(false); //Leva il rettangolo che puoi selezionare con il tab
        button.setPreferredSize(new Dimension(80, 30));
        button.addActionListener(e -> p.generaPallina());      //richiama la genera pallina
        button.setMaximumSize(new Dimension(80, 40)); //Fa si che non si allarghi a caso
        return button;
    }

    private static JComboBox<Integer> creaComboBox(MyPanel p) {    
        Integer[] numeri = new Integer[32];
        for (int i = 0; i < 32; i++) 
        {
            numeri[i] = i + 1;  
        }
        JComboBox<Integer> comboBox = new JComboBox<>(numeri);
        comboBox.setSelectedIndex(0); //in modo che ci sia qualcosa di selezionato 
        comboBox.setFocusable(false);
        comboBox.setPreferredSize(new Dimension(80, 30)); 
        comboBox.addActionListener(e -> {
            int nPalline = (int) comboBox.getSelectedItem();
            for (int i = 0; i < nPalline; i++) {
                p.generaPallina();
            }      
        });
        comboBox.setMaximumSize(new Dimension(80, 40)); //Fa si che non si allarghi a caso
        return comboBox;
    }

    private static JTextField creaTextField(MyPanel p){
        textField = new JTextField(3);
        textField.setText("10");
        textField.setFont(textField.getFont().deriveFont(20f));
        textField.setMaximumSize(new Dimension(80, 40)); //Fa si che non si allarghi a caso
        return textField;
    }

}
