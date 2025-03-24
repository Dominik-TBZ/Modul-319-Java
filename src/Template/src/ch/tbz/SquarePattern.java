// Source code is decompiled from a .class file using FernFlower decompiler.
package ch.tbz;

import ch.tbz.lib.Input;

public class SquarePattern {
    public static void main(String[] args) {
        int iZahl = Input.inputInt("Seitenlänge eingeben:");
        
        System.out.println("\nMuster a) Linie abwechselnd:");
        for (int iVert = 1; iVert <= iZahl; iVert++) {
            String zeile = (iVert % 2 == 0) ? "x".repeat(iZahl) : "*".repeat(iZahl);
            System.out.println(zeile);
        }
        
        System.out.println("\nMuster b) Spalte alternierend:");
        for (int iVert = 1; iVert <= iZahl; iVert++) {
            StringBuilder sZeile = new StringBuilder();
            for (int iHor = 1; iHor <= iZahl; iHor++) {
                sZeile.append((iHor % 2 == 0) ? "x" : "*");
            }
            System.out.println(sZeile);
        }
        
        System.out.println("\nMuster c) Werte multiplizieren:");
        for (int iVert = 1; iVert <= iZahl; iVert++) {
            for (int iHor = 1; iHor <= iZahl; iHor++) {
                System.out.print((iVert * iHor) + " ");
            }
            System.out.println();
        }
    }
}