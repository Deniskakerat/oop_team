package com.example.hashset;

import java.util.HashSet;
import java.util.Objects;

/** Class for HashSet Data Structure **/
public class HashSetInstance {
    private HashSet<Integer> hashSet = new HashSet<>();

    /** add value to hashSet */
    public boolean add(Integer value){
        return hashSet.add(value);
    }

    /** remove value from hashSet */
    public boolean remove(Integer value){
        return hashSet.remove(value);
    }

    /** clear the hashSet */
    public void clear (){
        hashSet.clear();
    }

    /**check if hashSet contains value*/
    public boolean contains(Integer value){
        return hashSet.contains(value);
    }

    public HashSet<Integer> getHashSet() {
        return hashSet;
    }

    public void setHashSet(HashSet<Integer> hashSet) {
        Objects.requireNonNull(hashSet);
        this.hashSet = hashSet;
    }

    /** Method that prints HashSet **/
    public void printHashSet(){
        System.out.println("HashSet ---------->");
        for (Integer value:hashSet) {
            System.out.println(value);
        }
    }

}
