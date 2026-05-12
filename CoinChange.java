public class CoinChange {

    public static void main(String[] args) {
        int amount = 1988;

        int[] denominations = {5000, 1000, 500, 100, 50, 20, 10, 5, 2, 1};
        String[] denomNames = {
                "Rs. 5000 Note",
                "Rs. 1000 Note",
                "Rs. 500 Note",
                "Rs. 100 Note",
                "Rs. 50 Note",
                "Rs. 20 Note",
                "Rs. 10 Coin",
                "Rs. 5 Coin",
                "Rs. 2 Coin",
                "Rs. 1 Coin"
        };

        System.out.println("=============================================");
        System.out.println("  Coin Change Problem");
        System.out.println("  Amount: Rs. " + amount);
        System.out.println("=============================================");

        int remaining = amount;
        int totalPieces = 0;

        System.out.printf("\n%-22s %s%n", "Denomination", "Count");
        System.out.println("------------------------------");

        for (int i = 0; i < denominations.length; i++) {
            if (remaining <= 0) break;
            int count = remaining / denominations[i];
            if (count > 0) {
                System.out.printf("%-22s x %3d%n", denomNames[i], count);
                remaining -= count * denominations[i];
                totalPieces += count;
            }
        }

        System.out.println("------------------------------");
        System.out.println("Total pieces used: " + totalPieces);

        if (remaining == 0) {
            System.out.println("Change for Rs. " + amount + " dispensed successfully!");
        } else {
            System.out.println("Remaining: Rs. " + remaining);
        }
    }
}
