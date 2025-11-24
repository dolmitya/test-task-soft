package com.example.nth_min_service.util;

import java.util.Arrays;

public final class IntHashSet {
    private static final int EMPTY = Integer.MIN_VALUE;

    private int[] table;
    private int size;
    private int threshold;

    public IntHashSet(int capacity) {
        int cap = 1;
        while (cap < capacity * 2) cap <<= 1;
        table = new int[cap];
        Arrays.fill(table, EMPTY);
        threshold = cap / 2;
    }

    public boolean add(int value) {
        int mask = table.length - 1;
        int idx = value & mask;

        while (true) {
            int v = table[idx];
            if (v == EMPTY) {
                table[idx] = value;
                if (++size > threshold) {
                    rehash();
                }
                return true;
            }
            if (v == value) return false; // уже есть
            idx = (idx + 1) & mask;
        }
    }

    private void rehash() {
        int[] old = table;
        table = new int[old.length * 2];
        Arrays.fill(table, EMPTY);

        size = 0;
        threshold = table.length / 2;
        int mask = table.length - 1;

        for (int v : old) {
            if (v != EMPTY) {
                int idx = v & mask;
                while (table[idx] != EMPTY) idx = (idx + 1) & mask;
                table[idx] = v;
                size++;
            }
        }
    }

    public int[] toArray() {
        int[] result = new int[size];
        int i = 0;
        for (int v : table)
            if (v != EMPTY) result[i++] = v;
        return result;
    }
}