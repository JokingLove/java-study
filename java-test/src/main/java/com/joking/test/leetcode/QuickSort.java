package com.joking.test.leetcode;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        // 输出排序后的数组
        System.out.println("排序后的数组:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // 分区操作
            int pivotIndex = partition(arr, start, end);
            // 对左半部分进行快速排序
            quickSort(arr, start, pivotIndex - 1);
            // 对右半部分进行快速排序
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arr[j] < pivot) {
                i++;
                // 交换元素
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 交换基准元素和i+1位置的元素
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;
        return i + 1;
    }
}