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
        double saldoFinale = 0;
        reader.readLine(); // Salta l'header
        while ((line = reader.readLine()) != null) 
            {
            String[] dati = line.split(",");
            double profitto = Double.parseDouble(dati[4]);
            profittoTotale += profitto;
            saldoFinale = Double.parseDouble(dati[1]);
            numCadute++;
            } 
        reader.close();
        
        // Scrive sul file
        PrintWriter writer = new PrintWriter(new FileWriter("riepilogo.csv"));
        writer.println("--- RIEPILOGO SIMULAZIONE ---");
        writer.println("Saldo iniziale: " + 1000 + "€");
        writer.println("Saldo finale: " + String.format("%.2f", saldoFinale) + "€");
        writer.println("Numero cadute: " + numCadute);
        writer.println("Profitto totale: " + String.format("%.2f", profittoTotale) + "€");
        writer.println("------------------------------"); 
        writer.flush();
        writer.close();   
    } catch (Exception e) {
        e.printStackTrace();
    }
}

 

 

 public static void cancellaDati()//elimina il file quando inizia il programma
  {
    try {
        File file = new File("plinko_dati.csv");
        if (file.exists()) {
            file.delete();
        }
         File file2 = new File("riepilogo.csv");
        if (file2.exists()) {
            file2.delete();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}