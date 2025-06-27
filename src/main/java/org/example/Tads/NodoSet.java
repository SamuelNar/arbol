package org.example.Tads;

public class NodoSet<k,v> {
    public k key;
    public v value;
    NodoSet<k,v> left,right;

    public NodoSet(k key,v value){
        this.key=key;
        this.value=value;
    }

}
