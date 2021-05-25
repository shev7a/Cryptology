package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag;
        int a, b, choice;
        System.out.println("Выберите тип задачи (1 или 2):");
        choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Введите два числа:");
            System.out.print("A = ");
            a = scanner.nextInt();
            System.out.print("B = ");
            b = scanner.nextInt();
            System.out.format("Задача: решить уравнение %dx + %dy = 1\n", a, b);
            System.out.println("Решение:");
            flag = true;
        } else {
            System.out.println("Введите два числа:");
            System.out.format("%c(n) = ", 0x3D5);
            a = scanner.nextInt();
            System.out.print("e = ");
            b = scanner.nextInt();
            System.out.format("Задача: найти обратный элемент для e = %d по составному модулю %c(n) = %d\n", a, 0x3D5, b);
            System.out.println("Решение:");
            System.out.format("взяв А = %c(n) = %d и B = e = %d, получим:\n", 0x3D5, a, b);
            flag = false;
        }
        ArrayList[] result = Euclid(a, b);
        int length = result[0].size();
        System.out.println("+-----+-----+-----+-----+-----+-----+");
        System.out.println("|  A  |  B  | A%B | A/B |  x  |  y  |");
        System.out.println("+-----+-----+-----+-----+-----+-----+");
        for (int i = 0; i < length; i ++) {
            for (ArrayList arrayList : result) {
                System.out.format("| %3s ", arrayList.get(i));
            }
            System.out.print("|\n");
            System.out.println("+-----+-----+-----+-----+-----+-----+");
        }
        if (flag) {
            System.out.format("Ответ: (x, y) = (%d, %d)", result[4].get(0), result[5].get(0));
        } else {
            System.out.format("y = %d - искомое значение обратного элемента\n", result[5].get(0));
            System.out.format("d = y mod %c(n) = %d mod %d = %d\n", 0x3D5, result[5].get(0), a, mod((Integer)result[5].get(0),a));
            System.out.format("Ответ: %d", result[5].get(0));
        }
    }

    public static ArrayList[] Euclid(int a, int b) {
        int c = a % b;
        ArrayList[] resultTable = new ArrayList[6];
        for (int i = 0; i < resultTable.length; i++) {
            resultTable[i] = new ArrayList(1);
        }
        while (c != 0) {
            c = a % b;
            resultTable[0].add(a);
            resultTable[1].add(b);
            resultTable[2].add(c);
            resultTable[3].add(a / b);
            resultTable[4].add(0);
            resultTable[5].add(0);
            a = b;
            b = c;
        }
        int deep = resultTable[0].size();
        resultTable[4].set(--deep, 0);
        resultTable[5].set(deep--, 1);
        for (int i = deep; i >= 0; i--) {
            resultTable[4].set(i, resultTable[5].get(i + 1));
            resultTable[5].set(i, (Integer)resultTable[4].get(i + 1) - ((Integer)resultTable[5].get(i + 1)) * ((Integer)resultTable[3].get(i)));
        }
        return resultTable;
    }

    public static int mod(int a, int b) {
        return (int) (( (long) (a % b) + b) % b );
    }
}
