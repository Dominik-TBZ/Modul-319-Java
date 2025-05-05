package ch.tbz;

import java.util.ArrayList;
import java.util.Scanner;

public class NotenRechner {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean weiter = true;

        while (weiter) {
            EingabeNoten(); // Aufruf
            ArrayList<Double> noten = NotenEingabeSchleife(); // Eingabe der Noten
            double durchschnitt = BerechneDurchschnitt(noten); // Errechnung des Durchschnitts
            String schulnote = ErmittleNote(durchschnitt); // Errechnung der Schulnote
            AusgabeErgebnis(noten, durchschnitt, schulnote); // Ausgabe des Durchschnitts und der Schulnote
            weiter = SollFortfahren(); // Frage zum Fortfahren
        }
    }

    public static void EingabeNoten() { // Aufruf
        System.out.println("Gib deine Noten ein (mit Punkt z.B. 5.3). Drücke die Leertaste und Enter zum Beenden:");
    }

    public static ArrayList<Double> NotenEingabeSchleife() { // Eingabe der Noten
        ArrayList<Double> noten = new ArrayList<>();
        
        while (true) {
            System.out.print("Note: ");
            String eingabe = scanner.nextLine().trim();
            
            if (eingabe.isEmpty()) {
                break;
            }
            
            try {
                double note = Double.parseDouble(eingabe);
                noten.add(note);
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe! Bitte eine Zahl eingeben.");
            }
        }
        
        return noten;
    }
    
    public static double BerechneDurchschnitt(ArrayList<Double> noten) { // Errechnung des Durchschnitts
        double summe = 0;
        for (double note : noten) {
            summe += note;
        }
        return noten.size() > 0 ? summe / noten.size() : 0; // Durchschnitt berechnen
    }
    //Es wird entschieden was für eine Note man hat
    public static String ErmittleNote(double durchschnitt) { // Errechnung der Schulnote
        if (durchschnitt >= 5.5) 
            return "Sehr gut";
        else if (durchschnitt >= 4.5) 
            return "Gut";
        else if (durchschnitt >= 4.0) 
            return "Genügend";
        else 
            return "Ungenügend";
    }
    //Ausgabe wird gegeben
    public static void AusgabeErgebnis(ArrayList<Double> noten, double durchschnitt, String schulnote) { // Ausgabe des Durchschnitts und der Schulnote
        System.out.println("============ Ergebnis ============\n");
        System.out.println("Eingegebene Noten: " + noten);
        System.out.printf("Durchschnitt: %.2f\n", durchschnitt); 
        System.out.println("Schulnote: " + schulnote);
    }

    public static boolean SollFortfahren() { // Exception bei ungültiger Eingabe
        System.out.println("\n========== Weitermachen ==========");
        System.out.print("\nMöchtest du weitere Noten eingeben? (ja/nein): ");
        String antwort = scanner.nextLine().trim().toLowerCase();

        if (antwort.equals("nein")) {
            System.out.println("Programm beendet.");
            return false;
        }

        return true;
    }
}
