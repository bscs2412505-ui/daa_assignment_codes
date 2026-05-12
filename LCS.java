public class LCS {

    static int[][] buildDPTable(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp;
    }

    static String backtrackLCS(int[][] dp, String s1, String s2) {
        StringBuilder lcs = new StringBuilder();
        int i = s1.length(), j = s2.length();

        while (i > 0 && j > 0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                lcs.append(s1.charAt(i-1));
                i--; j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString();
    }

    static void printDPTable(int[][] dp, String s1, String s2) {
        System.out.println("\nDP Table:");
        System.out.print("     ");
        for (char c : s2.toCharArray()) System.out.printf("  %c", c);
        System.out.println();

        for (int i = 0; i <= s1.length(); i++) {
            String label = (i == 0) ? " " : String.valueOf(s1.charAt(i-1));
            System.out.printf("  %s  ", label);
            for (int j = 0; j <= s2.length(); j++) {
                System.out.printf(" %d ", dp[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String name     = "Muskan Lohana";
        String string2  = "ETAOINSHR";


        String s1 = name.replace(" ", "").toUpperCase();
        String s2 = string2.toUpperCase();

        System.out.println("==================================================");
        System.out.println("  Longest Common Subsequence (LCS)");
        System.out.println("==================================================");
        System.out.println("\nString 1 (Your Name) : " + name);
        System.out.println("String 2             : " + string2);
        System.out.println("\n(Spaces removed for computation)");
        System.out.println("S1 cleaned: " + s1);
        System.out.println("S2 cleaned: " + s2);

        int[][] dp = buildDPTable(s1, s2);
        printDPTable(dp, s1, s2);

        String lcsResult = backtrackLCS(dp, s1, s2);
        int lcsLength = dp[s1.length()][s2.length()];

        System.out.println("\nLCS Length : " + lcsLength);
        System.out.println("LCS String : " + lcsResult);
        System.out.println("\nCharacters from '" + name + "' that appear in '" + string2 + "': " + lcsResult);
    }
}
