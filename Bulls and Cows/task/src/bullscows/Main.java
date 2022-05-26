package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        String secretNum;
        int turnCnt = 0;

        do {
            // 1
            System.out.println("Input the length of the secret code:");
            int secretNumLength;
            input = scanner.nextLine();
            if (isNotDigit(input)) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", input);
                return;
            } else {
                secretNumLength = Integer.parseInt(input);
            }
            if (secretNumLength < 1 || secretNumLength > 36) {
                System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough " +
                        "unique digits.\n", secretNumLength);
                return;
            }

            // 2
            System.out.println("Input the number of possible symbols in the code:");
            int secretNumSymbolCnt;
            input = scanner.nextLine();
            if (isNotDigit(input)) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", input);
                return;
            } else {
                secretNumSymbolCnt = Integer.parseInt(input);
            }
            if (secretNumSymbolCnt > 36) {
                System.out.printf("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).\n");
                return;
            }
            if (secretNumSymbolCnt < secretNumLength) {
                System.out.printf("Error: it's not possible to generate a code with a length of %s with %s unique symbols.\n",
                        secretNumLength,
                        secretNumSymbolCnt);
                return;
            }

            secretNum = generateRandom(secretNumLength, secretNumSymbolCnt);
        } while (secretNum == null || secretNum.isEmpty());
        System.out.println("Okay, let's start a game!");
        //scanner.nextLine();

        String enterNum;
        do {
            turnCnt++;
            System.out.printf("Turn %d:\n", turnCnt);
            enterNum = scanner.nextLine();
        } while (!checkNum(secretNum, enterNum));
    }

    static boolean isNotDigit(String str) {
        if (str.length() == 0) {
            return true;
        }
        try {
            int num = Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    static String generateRandom(int secretNumLength, int secretNumSymbolCnt) {
        String[] array = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                                      "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                                      "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                                      "u", "v", "w", "x", "y", "z"};
        if (secretNumLength < 1 || secretNumLength > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough " +
                    "unique digits.\n", secretNumLength);
            return null;
        }

        //return (int) (Math.random() * (max - min + 1) + min);
        String generateNumber = "";
        for (int i = 0; i < secretNumLength; i++) {
            boolean isContinue = true;
            while (isContinue) {
                String generateDigit = array[(int) (Math.random() * secretNumSymbolCnt)];
                if (i == 0 || !generateNumber.contains(generateDigit)) {
                    generateNumber = generateNumber.concat(generateDigit);
                    isContinue = false;
                }
            }
        }
        System.out.printf("The secret is prepared: %s (%s%s)\n",
                String.format("%" + secretNumLength + "s", "").replace(' ', '*'),
                "0-" + (secretNumSymbolCnt >= 10 ? "9" : array[secretNumSymbolCnt - 1]),
                secretNumSymbolCnt <= 10 ? "" :
                        (secretNumSymbolCnt == 11 ? ", a" : ", a-" + array[secretNumSymbolCnt - 1]));
        return generateNumber;
    }

    static boolean checkNum(String origNum, String enterNum) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < enterNum.length(); i++) {
            if (enterNum.charAt(i) == origNum.charAt(i)) {
                bulls++;
            } else if (origNum.contains(String.valueOf(enterNum.charAt(i)))) {
                cows++;
            }
        }
        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: 0 bull(s) or cow(s)");
        } else {
            System.out.printf("Grade: %s%s%s%s%s%s\n",
                    bulls == 0 ? "" : String.valueOf(bulls),
                    bulls == 0 ? "" : " bull(s)",
                    bulls != 0 && cows != 0 ? " and " : "",
                    cows == 0 ? "" : String.valueOf(cows),
                    cows == 0 ? "" : " cow(s)",
                    origNum);
        }

        if (bulls == origNum.length()) {
            System.out.println("Congratulations! You guessed the secret code.");
            return true;
        } else {
            return false;
        }
    }
}
