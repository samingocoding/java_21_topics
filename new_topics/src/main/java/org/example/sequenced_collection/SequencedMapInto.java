package org.example.sequenced_collection;

import java.util.LinkedHashMap;
import java.util.SequencedMap;

public class SequencedMapInto {
    public static void main(String[] args) {
        SequencedMap<Integer,String> stringSequencedMap = new LinkedHashMap<>();
        stringSequencedMap.put(1, "one");
        stringSequencedMap.putFirst(2, "two");
        stringSequencedMap.putLast(3, "three");
        stringSequencedMap.reversed(); //Immutable take note
        System.out.println(" This is the squenced map "+stringSequencedMap);
    }
}
