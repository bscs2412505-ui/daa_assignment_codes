import java.util.*;

public class Boyermoore {

    static final int ALPHABET_SIZE = 256;

    static int[] buildBadCharTable(String pattern) {
        int[] badChar = new int[ALPHABET_SIZE];
        Arrays.fill(badChar, -1);
        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i;
        }
        return badChar;
    }

    static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int[] badChar = buildBadCharTable(pattern);

        int comparisons = 0;
        int shiftOfText = 0;

        while (shiftOfText <= (n - m)) {
            int j = m - 1;

            while (j >= 0) {
                comparisons++;
                if (pattern.charAt(j) != text.charAt(shiftOfText + j)) break;
                j--;
            }

            if (j < 0) {
                matches.add(shiftOfText);
                System.out.println("Match found at index " + shiftOfText +
                        "  -> \"" + text.substring(shiftOfText, shiftOfText + m) + "\"");

                int nextChar = (shiftOfText + m < n) ? text.charAt(shiftOfText + m) : -1;
                shiftOfText += (shiftOfText + m < n) ? Math.max(1, m - badChar[nextChar]) : 1;
            } else {
                int badCharShift = j - badChar[text.charAt(shiftOfText + j)];
                shiftOfText += Math.max(1, badCharShift);
            }
        }
        System.out.println("\nTotal character comparisons performed: " + comparisons);
        return matches;
    }

    public static void main(String[] args) {
        String text = "Insertion sort typically has a smaller constant factor than merge sort";
        String pattern = "sort";

        System.out.println("=== Boyer-Moore String Matching Algorithm ===\n");
        System.out.println("Text    : " + text);
        System.out.println("Pattern : " + pattern + "\n");

        List<Integer> matches = search(text, pattern);

        System.out.println("\nPattern \"" + pattern + "\" found at indices: " + matches);
        System.out.println("Number of occurrences: " + matches.size());
    }
}