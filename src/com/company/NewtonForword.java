package com.company;

        import java.util.ArrayList;

/**
 * Created by Main on 2016/10/10.
 */
public class NewtonForword {
    public Polynomial P;
    public ArrayList<ArrayList<Double>> ForwardDifferences;
    public double Xzero;
    public double H;
    public ArrayList<Double> YList;
    public NewtonForword(double xzero,double yzero,double h){
        YList = new ArrayList<>();
        this.Xzero = xzero;
        this.H = h;
        YList.add(yzero);
        P = new Polynomial(new double[]{yzero});
        ForwardDifferences = new ArrayList<>();
        ForwardDifferences.add(new ArrayList<>());
        ForwardDifferences.get(0).add(yzero);
    }

    public void Add(double y){
        int n = YList.size();
        YList.add(y);
        ForwardDifferences.add(new ArrayList<>());
        ForwardDifferences.get(0).add(y);
        for (int i = 1; i < ForwardDifferences.size(); i++) {
            double las = ForwardDifferences.get(i-1).get(ForwardDifferences.get(i-1).size()-1);
            double sec = ForwardDifferences.get(i-1).get(ForwardDifferences.get(i-1).size()-2);
            ForwardDifferences.get(i).add(las - sec);
        }
        Polynomial np = new Polynomial(YList.size());
        np.vector[0] = 1;
        Polynomial alpha = new Polynomial(new double[]{-Xzero/H,1/H});
        for (int i = 0; i < n; i++) {
             np = np.Multi(alpha.Sub(i));
        }
        np = np.Multi(1/(double)factorial(n));
        np = np.Multi(ForwardDifferences.get(ForwardDifferences.size()-1).get(0));
        P = P.Add(np);
    }

    private int factorial(int i){
        if(i == 0)return 1;
        return i*factorial(i-1);
    }
}
