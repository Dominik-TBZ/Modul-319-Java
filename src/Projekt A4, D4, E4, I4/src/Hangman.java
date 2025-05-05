import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            clearConsole();
            playGame(scanner);
        } while (askForRestart(scanner));

        scanner.close();
        System.out.println("Danke fürs Spielen!");
    }

    public static void playGame(Scanner scanner) {
        System.out.print("Gib ein Wort zum Raten ein: ");
        String word = scanner.nextLine().toUpperCase();
        clearConsole();
        
        Set<Character> guessedLetters = new HashSet<>();
        int maxFails = 6;
        int fails = 0;
        boolean gameOver = false;
    
        while (!gameOver) {
            // Die Konsole nur dann löschen, wenn das Spiel noch läuft
            clearConsole();
            drawHangman(fails);
    
            String maskedWord = getMaskedWord(word, guessedLetters);
            System.out.println("Wort: " + maskedWord);
            System.out.println("Geratene Buchstaben: " + guessedLetters);
            System.out.println("Fehlversuche: " + fails + "/" + maxFails);
    
            if (maskedWord.equals(word)) {
                // Gewinnnachricht direkt ausgeben, bevor die Konsole gelöscht wird
                System.out.println("\n==> Du hast gewonnen!");
                gameOver = true;
                break;
            }
    
            if (fails >= maxFails) {
                // Verlustnachricht direkt ausgeben, bevor die Konsole gelöscht wird
                System.out.println("\n==> Du hast verloren! Das Wort war: " + word);
                gameOver = true;
                break;
            }
    
            // Rateeingabe des Spielers
            System.out.print("\nRate einen Buchstaben: ");
            String input = scanner.nextLine().toUpperCase();
    
            // Überprüfen, ob die Eingabe gültig ist (nur ein Buchstabe)
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Ungültige Eingabe. Bitte gib nur einen Buchstaben ein.");
                continue;
            }
    
            char guessedChar = input.charAt(0);
    
            // Überprüfen, ob der Buchstabe schon geraten wurde
            if (guessedLetters.contains(guessedChar)) {
                System.out.println("Diesen Buchstaben hast du schon geraten.");
                continue;
            }
    
            // Füge den geratenen Buchstaben zur Menge hinzu
            guessedLetters.add(guessedChar);
    
            // Überprüfen, ob der Buchstabe im Wort ist
            if (!word.contains(String.valueOf(guessedChar))) {
                fails++;
            }
        }
    }    
    
    public static String getMaskedWord(String word, Set<Character> guessed) {
        StringBuilder result = new StringBuilder();
        for (char c : word.toCharArray()) {
            result.append(guessed.contains(c) ? c : "_");
        }
        return result.toString();
    }

    public static void drawHangman(int fails) {
        System.out.println("--------");
        System.out.println("|      |");
        System.out.println("|      " + (fails >= 1 ? "O" : ""));
        System.out.println("|     " + (fails >= 3 ? "/" : " ") + (fails >= 2 ? "|" : "") + (fails >= 4 ? "\\" : ""));
        System.out.println("|     " + (fails >= 5 ? "/" : "") + " " + (fails >= 6 ? "\\" : ""));
        System.out.println("|");
        System.out.println("==========");
    }

    public static boolean askForRestart(Scanner scanner) {
        System.out.print("\nNeue Runde? (J/N): ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("J");
    }

    // Versucht Konsole zu "leeren"
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}