package main;
import com.company.*;

import java.util.Random;

/**
 * Created by wang on 2016/06/20.
 */
public class Main0620 {
    public static void main(String[] args){
        Random rm = new Random();
        int n = 10;
        int x[] = new int[n];
        for (int i = 0; i < x.length; i++){
            x[i] = rm.nextInt() % 10;
            System.out.print(x[i] + " ");
        }
        Sort.QuickSort(x);

        for (int i = 0; i < x.length; i++){
            System.out.print(x[i] + " ");
        }
    }
}
