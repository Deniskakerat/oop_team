package com.example.hashset;


import java.util.HashSet;
import java.util.Iterator;

public class HashSetInstance {
    private HashSet<String> hashSet = new HashSet<>();

    public boolean add(String value){
        return hashSet.add(value);
    }

    public boolean remove(String value){
        return hashSet.remove(value);
    }

    public void clear (){
        hashSet.clear();
    }

    public boolean contains(String value){
        return hashSet.contains(value);
    }

    public int size(){
        return hashSet.size();
    }

    public HashSet<String> getHashSet() {
        return hashSet;
    }

    public void setHashSet(HashSet<String> hashSet) {
        this.hashSet = hashSet;
    }

    public void printHashSet(){
        Iterator<String> i = hashSet.iterator();
        while (i.hasNext())
            System.out.println(i.next());
    }
}
