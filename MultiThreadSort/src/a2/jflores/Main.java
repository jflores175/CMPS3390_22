package a2.jflores;

import a1.jflores.*;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        Scanner scan =  new Scanner(System.in);
        Random ran = new Random();

        System.out.print("Do you want a [S]ingle Sort or a [D]ual sort? ");
        char selection = scan.next().charAt(0);

        System.out.print("How many items do you want to sort? ");
        int count = scan.nextInt();

        Item[] items = new Item[count];

        for (int i = 0; i < count; i++) {
            int t = ran.nextInt(4);

            switch(t) {
                case 0 -> {
                    items[i] = a1.jflores.Main.genFood();
                    break;
                }
                case 1 -> {
                    items[i] = a1.jflores.Main.genTool();
                    break;
                }
                case 2 -> {
                    items[i] = a1.jflores.Main.genClothing();
                    break;
                }
                case 3 -> {
                    items[i] = a1.jflores.Main.genWeapon();
                    break;
                }
            }
        }

        switch(selection) {
            case 's':
            case 'S':
                SingleSort(items);
                break;
            case 'd':
            case 'D':
                DualSort(items);
        }



    }

    private static void DualSort(Item[] items) throws InterruptedException {
        int mid = Math.round(items.length / 2f);
        ThreadSort t1 = new ThreadSort(items, 0, mid);
        ThreadSort t2 = new ThreadSort(items, mid, items.length);

        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m1 = new MergeSort(t1.gettItems(), t2.gettItems());
        m1.start();
        m1.join();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        for (Item i : m1.getSortedItems()) {
            System.out.println(i);
        }
        System.out.println("Dual sort took: " + duration);
    }

    private static void SingleSort(Item[] items) {
        // Sort before print
        ThreadSort single = new ThreadSort(items, 0, items.length);
        long startTime = System.nanoTime();
        single.start();
        try {
            single.join();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            Item[] sortedItems = single.gettItems();
            for (Item i : sortedItems) {
                System.out.println(i);
            }
            System.out.println("Was sorted in: " + duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
