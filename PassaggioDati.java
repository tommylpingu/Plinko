import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PassaggioDati { 
    public static void passaggio(double saldoPrima, double saldoDopo, double puntata, double moltiplicatore) {
        try {//senza try catch da errore
            PrintWriter writer = new PrintWriter(new FileWriter("plinko_dati.csv", true));//il true fa in modo che non cancella l'output di prima      
            if (new java.io.File("plinko_dati.csv").length() == 0) //fa solo la prima volta
            {
                writer.println("SaldoPrima,SaldoDopo,Puntata,Moltiplicatore,Profitto");
            }     
            double vincita = puntata * moltiplicatore;
            double profitto = vincita - puntata;
            writer.println(saldoPrima + "," + saldoDopo + "," + puntata + "," + moltiplicatore + "," + profitto); 
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


       public static void stampaRiepilogo() {
        try {
            File file = new File("plinko_dati.csv");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            double profittoTotale = 0;
            int numCadute = 0;
            double saldoIniziale = 0;
            double saldoFinale = 0;
            reader.readLine(); // Salta l'header
            while ((line = reader.readLine()) != null) {
                String[] dati = line.split(",");
                if (numCadute == 0) {
                    saldoIniziale = Double.parseDouble(dati[0]);
                }
                double profitto = Double.parseDouble(dati[5]);
                profittoTotale += profitto;
                saldoFinale = Double.parseDouble(dati[1]);
                numCadute++;
            } 
            reader.close();
            
            // Scrivi il riepilogo su file
            FileWriter fw = new FileWriter("riepilogo.txt");
            PrintWriter writer = new PrintWriter(fw); 
            writer.println("------ RIEPILOGO SIMULAZIONE ------");
            writer.println("Saldo iniziale: " + String.format("%.2f", saldoIniziale) + "€");
            writer.println("Saldo finale: " + String.format("%.2f", saldoFinale) + "€");
            writer.println("Numero cadute: " + numCadute);
            writer.println("Profitto totale: " + String.format("%.2f", profittoTotale) + "€");
            writer.println("----------------------------"); 
            writer.flush(); // non ho capito benissimo a cosa serve ma ho seguito un video inteoria forza la scrittura immediata 
            writer.close();
            fw.close();      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}