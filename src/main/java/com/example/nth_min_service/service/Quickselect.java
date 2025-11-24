package com.example.nth_min_service.service;

public final class Quickselect {

    private static final int INSERTION_THRESHOLD = 12;

    public static int select(int[] a, int n) {
        return quickselect(a, 0, a.length - 1, n);
    }

    private static int quickselect(int[] a, int left, int right, int k) {
        while (true) {
            // небольшой диапазон - выгоднее вставками
            if (right - left < INSERTION_THRESHOLD) {
                insertionSort(a, left, right);
                return a[k];
            }

            int pivotIndex = medianOfThree(a, left, right);
            int pivotPos = partition(a, left, right, pivotIndex);

            if (k == pivotPos) return a[k];
            if (k < pivotPos) right = pivotPos - 1;
            else left = pivotPos + 1;
        }
    }

    // median-of-three: выбираем опорный элемент из (left, mid, right)
    // куда стабильнее результаты, чем случайный pivot
    private static int medianOfThree(int[] a, int left, int right) {
        int mid = (left + right) >>> 1;

        int x = a[left];
        int y = a[mid];
        int z = a[right];

        if (x > y) {
            int t = x;
            x = y;
            y = t;
        }
        if (y > z) {
            int t = y;
            y = z;
            z = t;
        }
        if (x > y) {
            int t = x;
            x = y;
            y = t;
        }

        if (y == a[left]) return left;
        if (y == a[mid]) return mid;
        return right;
    }

    private static int partition(int[] a, int left, int right, int pivotIndex) {
        int pivotValue = a[pivotIndex];

        swap(a, pivotIndex, right);

        int store = left;
        for (int i = left; i < right; i++) {
            if (a[i] < pivotValue) {
                swap(a, store, i);
                store++;
            }
        }

        swap(a, store, right);
        return store;
    }

    // insertion sort на коротких участках: работает быстрее quickselect при малых n.
    private static void insertionSort(int[] a, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int value = a[i];
            int j = i - 1;

            while (j >= left && a[j] > value) {
                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = value;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}