import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font; 

public class SwingPaintDemo1 {
    
    private static JButton button;
    private static JComboBox<Integer> comboBox;
    private static JTextField textField;
    private static JCheckBox checkBox;
    static int nCombo = 10000; 

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
        checkBox = creaCheckBox(p);
        
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
        checkBox.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        //aggiunge componenti con un po' di spazio tra di loro
        westPanel.add(button);
        westPanel.add(javax.swing.Box.createVerticalStrut(5));
        
        westPanel.add(comboBox);
        westPanel.add(javax.swing.Box.createVerticalStrut(5));

        westPanel.add(textField);
        westPanel.add(javax.swing.Box.createVerticalStrut(5));

        westPanel.add(checkBox);
        westPanel.add(javax.swing.Box.createVerticalStrut(5));
        
        JButton ButtonStatistiche = new JButton("Valori");
        ButtonStatistiche.setFocusable(false);
        ButtonStatistiche.setMaximumSize(new Dimension(80, 40)); // Per farlo uguale agli altri
        ButtonStatistiche.addActionListener(e -> mostraStatistiche(p)); 
        westPanel.add(ButtonStatistiche);
        ButtonStatistiche.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        //finisce di settare
        f.add(westPanel, BorderLayout.WEST);
        p.setTextField(textField);
        p.setCheckBox(checkBox);
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
        Integer[] numeri = new Integer[nCombo];
        for (int i = 0; i < nCombo; i++) 
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

 
    private static void mostraStatistiche(MyPanel p) {
    JFrame statsFrame = new JFrame("Statistiche Moltiplicatori");
    statsFrame.setSize(300, 450);
    statsFrame.setLocationRelativeTo(null); // Centra la finestra
    JPanel panel = new JPanel();
    panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
    panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JLabel titolo = new JLabel("PALLINE PER MOLTIPLICATORE:");
    titolo.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(titolo);
    panel.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
    double[] valoriUnici = {58, 14.5, 5.6, 3.5, 1.8, 1, 0.5, 0.3};
    for (double v : valoriUnici) {
        int conteggio = p.getTotalePerValore(v);//metodo nel panel
        JLabel label = new JLabel("Valore " + v + "x: " + conteggio + " palline");
        label.setFont(new Font("Arial", Font.PLAIN, 13));//senza font mi usciva male prima 
        panel.add(label);
        panel.add(javax.swing.Box.createRigidArea(new Dimension(0, 5)));
    }
    statsFrame.add(panel);
    statsFrame.setVisible(true);
}
 
    private static JCheckBox creaCheckBox(MyPanel p){
        checkBox = new JCheckBox("Auto bet");
        return checkBox;
    }
 
}
