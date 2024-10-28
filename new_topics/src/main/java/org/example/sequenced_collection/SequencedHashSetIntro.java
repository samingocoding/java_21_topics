package org.example.sequenced_collection;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

public class SequencedHashSetIntro {
    public static void main(String[] args) {
        SequencedSet<String> sequencedSet = new LinkedHashSet<>();
        SequencedSet<String> sequencedSetSecond = new LinkedHashSet<>();
        sequencedSet.add("A");
        sequencedSet.addFirst("B");
        sequencedSet.addLast("C");
        sequencedSetSecond.addFirst("D");
        sequencedSetSecond.addLast("E");
        sequencedSet.addAll(sequencedSetSecond);
        System.out.println(" This is the sequenced collection : " + sequencedSet);
        sequencedSet.reversed(); //NB this is immutable hence no change to original
        System.out.println("This is the reversed sequenced set : " + sequencedSet);
    }
}
