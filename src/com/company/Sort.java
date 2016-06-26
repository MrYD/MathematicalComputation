package com.company;

import sun.awt.image.ImageWatched;

import java.awt.*;
import java.awt.List;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by wang on 2016/06/20.
 */
public class Sort {
    public static void GnormSort2(int[] array) {
        int i = 1;
        int j = 1;
        while (i < array.length) {
            if (array[i - 1] <= array[i]) {
                i++;
                if (j < i) {
                    j = i;
                } else {
                    i = j;
                }
            } else {
                int a = array[i];
                array[i - 1] = array[i];
                array[i] = a;
                i--;
            }
            if (i == 0) {
                i += 2;
            }
        }
    }
    public static void GnormSort(int[] array){
        int i = 1;
        int j = 1;
        while (i < array.length){
            if(array[i-1]<=array[i]){
                i++;
            }else {
                int a = array[i];
                array[i-1] = array[i];
                array[i]=a;
                i--;
                if(i==0){
                    i += 2;
                }
            }
        }
    }

    public static void BubbleSort(double[] array){
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = array.length - 1; i > j; i--) {
                if(array[i] < array[i - 1]){
                    double a = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = a;
                }
            }
        }
    }

    public static void BubbleSort(int[] array){
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = array.length - 1; i > j; i--) {
                if(array[i] < array[i - 1]){
                    int a = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = a;
                }
            }
        }
    }

    public static void SorcerSort(int[] data){
        int top_index = 0;
        int bot_index = data.length -1;

        while (true)
        {
            int last_swap_index;

    /* 順方向のスキャン */
            last_swap_index = top_index;

            for (int i = top_index; i < bot_index; i++)
            {
                if (data[i] > data[i+1])
                {
                    int a = data[i];
                    data[i] = data[i+1];
                    data[i+1] = a;
                    last_swap_index = i;
                }
            }
            bot_index = last_swap_index; /* 後方のスキャン範囲を狭める */

            if (top_index == bot_index)
                break;

    /* 逆方向のスキャン */
            last_swap_index = bot_index;

            for (int i = bot_index; i > top_index; i--)
            {
                if (data[i] < data[i-1])
                {
                    int a = data[i];
                    data[i] = data[i-1];
                    data[i-1] = a;
                    last_swap_index = i;
                }
            }
            top_index = last_swap_index; /* 前方のスキャン範囲を狭める */

            if (top_index == bot_index)
                break;
        }

    }
    public static void combSort(int[] data) {
        int h = data.length * 10 / 13;

        while (true) {
            int swaps = 0;
            for (int i = 0; i + h < data.length; ++i) {
                if (data[i] > data[i + h]) {
                    swap(data, i, i + h);
                    ++swaps;
                }
            }
            if (h == 1) {
                if (swaps == 0) {
                    break;
                }
            } else {
                h = h * 10 / 13;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int[] QuickSort(int[] data){
        if(data.length == 2){
            if(data[0] > data[1]){
                swap(data,0,1);
            }
            return data;
        }
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        for (int i = 0; i < data.length - 1; i++) {
            if(data[i] < data[data.length - 1]){
                list1.add(data[i]);
            }else {
                list2.add(data[i]);
            }
        }
        int[] array1 = QuickSort(toArray(list1));
        int[] array2 = QuickSort(toArray(list2));
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        list3.addAll(toList(array1));
        list3.add(data[data.length - 1]);
        list3.addAll(toList(array2));
        return toArray(list3);
    }

    public static int[] toArray(ArrayList<Integer> list){
        int l = list.size();
        int[] res = new int[l];
        int n=0;
        for (int i : list) {
            res[n] = i;
            n++;
        }
        return res;
    }
    public static ArrayList<Integer> toList(int[] arr){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int t : arr) list.add(t);
        return list;
    }


}
