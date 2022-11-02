package com.example.hashset;


import java.util.HashSet;
import java.util.Iterator;
/**
 * Class for HashSet
 **/
public class HashSetInstance {
    private HashSet<Integer> hashSet = new HashSet<>();

    public boolean add(Integer value){
        return hashSet.add(value);
    }

    public boolean remove(Integer value){
        return hashSet.remove(value);
    }

    public void clear (){
        hashSet.clear();
    }

    public boolean contains(Integer value){
        return hashSet.contains(value);
    }

    public int size(){
        return hashSet.size();
    }

    public HashSet<Integer> getHashSet() {
        return hashSet;
    }

    public void setHashSet(HashSet<Integer> hashSet) {
        this.hashSet = hashSet;
    }

    public void printHashSet(){
        Iterator<Integer> i = hashSet.iterator();
        while (i.hasNext())
            System.out.println(i.next());
    }
}
