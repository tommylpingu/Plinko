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
        JFrame statsFrame = new JFrame("Statistiche");
        statsFrame.setSize(400, 550); 
        statsFrame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel titolo = new JLabel("ANALISI RENDIMENTO:");
        titolo.setFont(new Font("Arial", Font.BOLD, 16));
        titolo.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        panel.add(titolo);
        panel.add(javax.swing.Box.createRigidArea(new Dimension(0, 15)));
        double[] valoriUnici = {58.0, 14.5, 5.6, 3.5, 1.8, 1.0, 0.5, 0.3};
        int totalePalline = 0;
        double sommaVincite = 0;
        for (double v : valoriUnici) {
            totalePalline += p.getTotalePerValore(v);
        }
        for (double v : valoriUnici)
      {
            int conteggio = p.getTotalePerValore(v);
            double percentuale;
            if (totalePalline > 0) {// Evita divisione per zero
                percentuale = (conteggio * 100.0) / totalePalline;
            } else {
                percentuale = 0;
            } 
            sommaVincite += (conteggio * v);
            // Formattazione della stringa e anche il font è apposito per l'allineamento
            String riga = String.format("Valore %5.1fx: %d palline (%5.2f%%)", v, conteggio, percentuale);
            JLabel label = new JLabel(riga);
            label.setFont(new Font("Monospaced", Font.PLAIN, 13));  
            panel.add(label);
            panel.add(javax.swing.Box.createRigidArea(new Dimension(0, 5)));
        }
        panel.add(javax.swing.Box.createVerticalGlue());  
        panel.add(new javax.swing.JSeparator());  //crea la linea
        panel.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
        // Statistiche finali
        double rtp;
        if (totalePalline > 0)
      {        
            rtp = (sommaVincite / totalePalline) * 100; //Rtp è il ritorno al giocatore
        } else {
            rtp = 0;
        }  
        JLabel lblTotale = new JLabel("Totale Lanci: " + totalePalline);
        lblTotale.setFont(new Font("Arial", Font.ITALIC, 13));
        panel.add(lblTotale);
        JLabel lblRTP = new JLabel(String.format("RTP Reale: %.2f%%", rtp));//sempre la formattazione per estetica
        lblRTP.setFont(new Font("Arial", Font.BOLD, 15));
        // Colora in base al rendimento
        if (rtp > 100) 
        {
        lblRTP.setForeground(new java.awt.Color(0, 150, 0)); //colore verde se si è in positivo(praticamente impossibile)
        } else {
        lblRTP.setForeground(new java.awt.Color(200, 0, 0));// colore rosso se si è in negativo
        }
        panel.add(lblRTP);
        JLabel lblBordo = new JLabel(String.format("Margine Banco: %.2f%%", 100 - rtp));
        lblBordo.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(lblBordo);

        statsFrame.add(panel);
        statsFrame.setVisible(true);
    }
     
 
    private static JCheckBox creaCheckBox(MyPanel p){
        checkBox = new JCheckBox("Auto bet");
        return checkBox;
    }
 
}
