package org.example;

import org.example.Entidades.AVLs;
import org.example.Entidades.BSTSearch;
import org.example.Tads.SortedMap;

public class Main {
    public static void main(String[] args) {
       AVLs<Integer> avl = new AVLs<>();
       avl.add(10);
       avl.add(5);
       System.out.println(avl);

    }
}