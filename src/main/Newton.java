package main;
import com.company.*;
/**
 * Created by Main on 2016/10/10.
 */
public class Newton {
    public static void main(String[] args){
        NewtonForword nf = new NewtonForword(0,-5, 1);

        nf.Add(1);

        nf.Add(9);

        nf.Add(25);

        System.out.println(nf.P);

    }

}

