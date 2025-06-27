package org.example.Entidades;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    public T key;
    public Node hi, hd;
    public Node(T key) {
        this.key = key;
    }

    public List<T> inOrder() {
        List<T> result = new LinkedList<T>();
        if (hd != null) {
            result.addAll(hd.inOrder());
        }
        result.add(key);
        if (hi != null) {
            result.addAll(hi.inOrder());
        }
        return result;
    }
}
