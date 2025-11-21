package com.example.nth_min_service.service;

import java.util.List;
import java.util.Random;

public class Quickselect {
    private static final Random rnd = new Random();

    public static int select(List<Integer> a, int left, int right, int k) {
        while (true) {
            if (left == right) return a.get(left);

            int pivot = left + rnd.nextInt(right - left + 1);
            pivot = partition(a, left, right, pivot);

            if (k == pivot) return a.get(k);
            if (k < pivot) right = pivot - 1;
            else left = pivot + 1;
        }
    }

    private static int partition(List<Integer> a, int left, int right, int pivot) {
        int pivotValue = a.get(pivot);
        swap(a, pivot, right);
        int store = left;

        for (int i = left; i < right; i++) {
            if (a.get(i) < pivotValue) {
                swap(a, store, i);
                store++;
            }
        }

        swap(a, store, right);
        return store;
    }

    private static void swap(List<Integer> a, int i, int j) {
        int tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }
}