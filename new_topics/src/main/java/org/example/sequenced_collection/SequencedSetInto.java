package org.example.sequenced_collection;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

public class SequencedSetInto {
    public static void main(String[] args) {
        SequencedSet<String> sequencedSet = new LinkedHashSet<>();
        sequencedSet.add("A");
        sequencedSet.addFirst("B");
        sequencedSet.addLast("C");

        System.out.println(sequencedSet); // Output: [B, A, C]
        System.out.println(sequencedSet.getFirst()); // Output: B
        System.out.println(sequencedSet.getLast()); // Output: C
    }
}
