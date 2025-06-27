package org.example.Tads;

import java.util.LinkedHashSet;
import java.util.Set;

public class SortedMap<k extends Comparable<k>,v>{

    public NodoSet<k,v> root;
    public int size=0;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void put(k key, v value){
        root = put(root,key,value);
    }
    private NodoSet<k,v> put(NodoSet<k,v> n, k key,v value){
        if (n == null){
            size++;
            return new NodoSet<>(key,value);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0){
            n.left = put(n.left,key,value);
        } else if (cmp >0) {
            n.right = put(n.right,key,value);
        }else {
            n.value = value;
        }
        return n;
    }

    public v get(k key){
        NodoSet<k,v> n = root;
        while (n !=null){
            int cmp = key.compareTo(n.key);
            if (cmp == 0){
                return n.value;
            }else if (cmp < 0){
                n = n.left;
            }else {
                n = n.right;
            }
        }
        return null;
    }

    public boolean containsKey(k key){
        return get(key) != null;
    }


    public void removeKey(k key){
        root = remove(root,key);
    }
    private NodoSet<k,v> remove(NodoSet<k,v> n, k key){
        if (n == null){
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0){
            n.left = remove(n.left,key);
        }else if (cmp >0 ){
            n.right = remove(n.right,key);
        }else {
            size--;
            if (n.left == null) return n.right;
            if (n.right== null) return n.left;
            NodoSet<k,v> min = getMin(n.right);
            n.key=min.key;
            n.value=min.value;
            n.right = remove(n.right,min.key);
        }
        return n;
    }

public k min(){
        if (root == null){
            return null;
        }
        return getMin(root).key;
}
    private NodoSet<k,v> getMin(NodoSet<k,v> n){
        while (n.left != null){
            n = n.left;
        }
        return n;
    }

    public void removeMin(){
        if (root != null){
            root = removeMin(root);
        }
    }

    private NodoSet<k,v> removeMin(NodoSet<k,v> n){
        if (n.left == null){
            size--;
            return n.right;
        }
        n.left = removeMin(n.left);
        return n;
    }

    public Set<k> keySet(){
        Set<k> keys = new LinkedHashSet<>();
        inorder(root,keys);
        return keys;
    }

    private void inorder(NodoSet<k,v> n, Set<k> set){
        if(n!=null){
            inorder(n.left,set);
            set.add(n.key);
            inorder(n.right,set);
        }
    }
}
