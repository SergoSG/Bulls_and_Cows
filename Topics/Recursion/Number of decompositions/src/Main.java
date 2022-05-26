import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int enter = Integer.parseInt(scanner.nextLine());

        //ArrayList<Integer> array = new ArrayList<>(Arrays.asList(enter));
        //decompose1(array, enter);
        decompose(enter);
        System.out.print("\n" + enter);
    }

    public static void decompose (int enter) {
        if (enter == 1) {
            System.out.print(enter + " ");
            return;
        } else {
            int num1 = enter - 1;
            int num2 = 1;
            int cnt = 1;
            while (num1 >= num2) {
                decompose(num1);
                decompose(num2);
                if (num1 > 1) {
                    System.out.print("\n" + num1 + " " + num2);
                }
                cnt++;
                num1 = enter - cnt;
                num2 = enter - num1;
            }
        }
    }



    public static void decompose1 (ArrayList<Integer> array, int enter) {
        if (array.size() == enter) {
            return;
        }

        if (array.get(array.size() - 1) > 1) {
            ArrayList<Integer> tmpArray = new ArrayList<>();
            if (array.size() > 1) {
                tmpArray.addAll(0, array.subList(0, array.size() - 2));
            }
            tmpArray.add(array.get(array.size() - 1) - 1);
            tmpArray.add(1);

            for (int i = 0; i < tmpArray.size(); i++) {
                System.out.print(tmpArray.get(i) + " ");
            }
            System.out.println();
        } else {

        }
/*
        if (array.size() == 1) {
            System.out.println(array.get(0));
        } else {
            for (int i = 0; i < array.size(); i++) {
                System.out.print(array.get(i) + " ");
            }
            System.out.println();

            for (int i = 0; i < array.size() / 2; i++) {
                ArrayList<Integer> tmpArray = new ArrayList<>();
                tmpArray.add(i, array.get(i * 2) + array.get(i * 2 + 1));
                tmpArray.addAll(i + 1, array.subList(i * 2 + 1, array.size() - 1));
                //decompose(tmpArray);
                for (int j = 0; j < tmpArray.size(); j++) {
                    System.out.print(tmpArray.get(j) + " ");
                }
                System.out.println();
            }
        }

 */
    }
}