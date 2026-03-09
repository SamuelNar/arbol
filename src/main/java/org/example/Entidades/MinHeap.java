package org.example.Entidades;

import org.example.PriorityQueue;
import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<? super T>> implements PriorityQueue<T> {

    public T[] queue;
    private int size;
    private static final int CAPACITY = 8;

    public MinHeap() {
        queue = (T[]) new Comparable[CAPACITY + 1]; // 1-indexed
        size = 0;
    }

    public boolean repOk() {
        for (int i = 1; i <= size; i++) {
            if (queue[i] == null) return false;
        }
        for (int i = size + 1; i < queue.length; i++) {
            if (queue[i] != null) return false;
        }
        if (queue[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int i) {
        if (i > size) return true;

        int left = 2 * i;
        int right = 2 * i + 1;

        // En min-heap: parent <= children
        if (left <= size && less(queue[left], queue[i])) return false;
        if (right <= size && less(queue[right], queue[i])) return false;

        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    private boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private void exch(int i, int j) {
        T t = queue[i];
        queue[i] = queue[j];
        queue[j] = t;
    }

    // Subir: si el hijo es menor que el padre, intercambiar
    private void swim(int i) {
        while (i > 1 && less(queue[i], queue[i / 2])) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    // Bajar: intercambiar con el hijo menor si el padre es mayor
    private void sink(int i) {
        while (2 * i <= size) {
            int j = 2 * i; // hijo izquierdo

            // elegir el hijo MENOR
            if (j < size && less(queue[j + 1], queue[j])) {
                j++;
            }

            // si parent <= child menor, está ok
            if (!less(queue[j], queue[i])) {
                break;
            }

            exch(i, j);
            i = j;
        }
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Comparable[capacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    @Override
    public void insert(T x) {
        if (size == queue.length - 1) {
            resize(2 * queue.length);
        }
        queue[++size] = x;
        swim(size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    // --- API de MinHeap ---
    public T min() {
        if (isEmpty()) throw new NoSuchElementException();
        return queue[1];
    }

    public T removeMin() {
        if (isEmpty()) throw new NoSuchElementException();
        T min = queue[1];
        exch(1, size--);
        queue[size + 1] = null;
        sink(1);
        return min;
    }

    // Si tu interfaz PriorityQueue exige max/removeMax, hay dos opciones:
    // 1) cambiás la interfaz para MinHeap
    // 2) o dejás estos métodos como "aliases" (pero dijiste que no querés nada de max)
    @Override
    public T max() {
        throw new UnsupportedOperationException("MinHeap no soporta max()");
    }

    @Override
    public T removeMax() {
        throw new UnsupportedOperationException("MinHeap no soporta removeMax()");
    }
}
