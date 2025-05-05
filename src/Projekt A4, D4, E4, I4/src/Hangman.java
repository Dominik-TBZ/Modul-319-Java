import java.util.*;

public class Hangman {
    static Scanner scanner = new Scanner(System.in);
    static String word;
    static Set<Character> guessedLetters;
    static int maxFails = 6;
    static int fails;

    public static void main(String[] args) {
        do {
            clearConsole();
            playGame();
        } while (askForRestart());

        scanner.close();
    }

    public static void playGame() {
        System.out.print("Gib ein Wort zum Raten ein.");
        word = scanner.nextLine().toUpperCase();
        clearConsole();

        guessedLetters = new HashSet<>();
        fails = 0;

        boolean gameOver = false;

        while (!gameOver) {
            clearConsole();
            displayGameStatus();

            if (isGameWon()) {
                System.out.println("\n==> Du hast gewonnen!");
                gameOver = true;
            } else if (isGameLost()) {
                System.out.println("\n==> Du hast verloren! Das Wort war: " + word);
                gameOver = true;
            } else {
                char input = getUserInput();
                updateGameState(input);
            }
        }
    }

    // === Eingabe ===
    public static char getUserInput() {
        while (true) {
            System.out.print("\nRate einen Zeichen: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Ungültige Eingabe. Bitte gib genau einen Zeichen ein.");
                continue;
            }

            char guessedChar = input.charAt(0);
            if (guessedLetters.contains(guessedChar)) {
                System.out.println("Diesen Zeichen hast du schon geraten.");
                continue;
            }

            return guessedChar;
        }
    }

    // === Verarbeitung ===
    public static void updateGameState(char guessedChar) {
        guessedLetters.add(guessedChar);
        if (!word.contains(String.valueOf(guessedChar))) {
            fails++;
        }
    }

    // === Ausgabe ===
    public static void displayGameStatus() {
        drawHangman(fails);
        System.out.println("Wort: " + getMaskedWord(word, guessedLetters));
        System.out.println("Geratene Zeichen: " + guessedLetters);
        System.out.println("Fehlversuche: " + fails + "/" + maxFails);
    }

    public static boolean isGameWon() {
        return getMaskedWord(word, guessedLetters).equals(word);
    }

    public static boolean isGameLost() {
        return fails >= maxFails;
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

    public static boolean askForRestart() {
        System.out.print("\nNeue Runde? (J/N): ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("J");
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}
