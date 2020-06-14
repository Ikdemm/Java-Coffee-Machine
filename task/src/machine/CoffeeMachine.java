package machine;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

public class CoffeeMachine {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] state = {400, 540, 120, 9, 550};

        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String command = scanner.next();

            switch (command) {
                case "buy":
                    state = buy(state);
                    break;
                case "fill":
                    fill(state);
                    break;
                case "take":
                    take(state);
                    break;
                case "remaining":
                    printState(state);
                    break;
                case "exit":
                    exit(0);
            }

        } while (true);

        /*int cups = getCupsAmount();
        int possibleCups = possibleCups(supplies);
        printMessage(possibleCups, cups);*/

    }

    private static boolean sufficient(int[] supplies, int[] amounts) {
        for (int i = 0; i < 4; i++) {
            if (supplies[i] < amounts[i]) {
                switch (i) {
                    case 0:
                        System.out.println("Sorry, not enough water!");
                        return false;
                    case 1:
                        System.out.println("Sorry, not enough milk!");
                        return false;
                    case 2:
                        System.out.println("Sorry, not enough coffee!");
                        return false;
                    case 3:
                        System.out.println("Sorry, not enough cups!");
                        return false;
                }
            }
        }
        return true;
    }

    private static int[] buyCoffee(int[] state, int coffee) {

        int[] ingredients = new int[5];

        switch (coffee) {
            case 1:
                ingredients = new int[]{250, 0, 16, 1, 4};
                if (sufficient(state, ingredients)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    state = new int[]{
                        state[0] - 250,
                        state[1],
                        state[2] - 16,
                        state[3] - 1,
                        state[4] + 4
                    };
                    return state;
                }
                return state;
            case 2:
                ingredients = new int[]{350, 75, 20, 1, 7};
                if (sufficient(state, ingredients)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    state = new int[]{
                            state[0] - 350,
                            state[1] - 75,
                            state[2] - 20,
                            state[3] - 1,
                            state[4] + 7
                    };
                }
                return state;
            case 3:
                ingredients = new int[]{200, 100, 12, 1, 6};
                if (sufficient(state, ingredients)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    state = new int[]{
                            state[0] - 200,
                            state[1] - 100,
                            state[2] - 12,
                            state[3] - 1,
                            state[4] + 6
                    };
                    return state;
                }
                return state;
        }
        return state;
    }

    private static int[] buy(int[] state) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");

        if (scanner.hasNextInt()) {
            return buyCoffee(state, scanner.nextInt());
        }
        return state;
    }

    private static void take(int[] state) {
        System.out.println("I gave you $" + state[4]);
        state[4] = 0;
    }

    private static void printState(int[] state) {
        System.out.println(
            "The coffee machine has:\n"+
            state[0] + " of water\n" +
            state[1] + " of milk\n" +
            state[2] + " of coffee beans\n" +
            state[3] + " of disposable cups\n"+
            state[4] + " of money"
        );
    }

    private static String getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        return (scanner.nextLine());
    }

    /*private static void printMessage(int possibleCups, int cups) {
        int excessCups = possibleCups - cups;
        if (excessCups < 0) {
            System.out.println("No, I can make only " + possibleCups + " cup(s) of coffee");
            return;
        } else if (excessCups == 0) {
            System.out.println("Yes, I can make that amount of coffee");
            return;
        } else {
            System.out.println("Yes, I can make that amount of coffee (and even " + excessCups + " more than that");
            return;
        }
    }*/

    private static int possibleCups(int[] supplies) {
        supplies[0] /= 200;
        supplies[1] /= 50;
        supplies[2] /= 15;
        Arrays.sort(supplies);
        return supplies[0];
    }

    private static void fill(int[] state) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        state[0] += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        state[1] += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        state[2] += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        state[3] += scanner.nextInt();
    }

    /*private static void makeCoffee() {
        System.out.println(
            "Starting to make a coffee\n" +
            "Grinding coffee beans\n" +
            "Boiling water\n" +
            "Mixing boiled water with crushed coffee beans\n" +
            "Pouring coffee into the cup\n" +
            "Pouring some milk into the cup\n" +
            "Coffee is ready!"
        );
    }*/

    private static int getCupsAmount() {
        System.out.println("Write how many cups of coffee you will need:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /*private static void printIngredients(int cups) {
        System.out.println(
            "For " + cups + " cups of coffee you will need:\n" +
            200 * cups + " ml of water\n" +
            50 * cups + " ml of milk\n" +
            15 * cups + " g of coffee beans"
        );
    }*/
}
