package main;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;


/**
 * Created by wang on 2016/06/26.
 */
public class Report2 {
    public static void main(String[] args) {
        int n = 2184;
        Seiseki[] Array = new Seiseki[n];

        try {
            File file = new File("out/production/ConsoleApp1/main/Seiseki.dat");
            FileReader filereader = new FileReader(file);
            int count = 0;
            int ch;
            String str = "";
            while ((ch = filereader.read()) != -1) {
                if (((char) ch) == '\n') {
                    Array[count] = new Seiseki(str);
                    if (count == n - 1) break;
                    count++;
                    str = "";
                } else
                    str += (char) ch;
            }
            filereader.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        quick_sort(Array);

        for (Seiseki itr:Array) {
            System.out.println(itr);
        }
    }

    private static void quick_sort(Seiseki[] d) {
        // 再帰関数だとスタックオーバーフローになるのでwhileで書く
        // スタックにクイックソートの引数を格納し、引数がなくなるまでループ
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0,d.length - 1});

        while (!stack.isEmpty()){
            int[] s = stack.pop();
            int left = s[0];
            int right = s[1];
            if (left >= right) {
                continue;
            }
            //　ピボットを中心に取る
            int c =  (left+right)/2;
            Seiseki p = d[c];
            int l = left, r = right;
            while(l <= r) {
                // 左端からピボットまで、ピボット以下の要素を探す
                while(d[l].getScore() >= p.getScore()) {
                    // ピボット成績が同じ場合は受験番号をみる
                    if(d[l].getScore() == p.getScore()){
                        if(d[l].getNumber() > p.getNumber() || l == c) {
                            break;
                        }
                    }
                    l++;
                }
                // 右端からピボットまで、ピボット以上の要素を探す
                while(d[r].getScore() <= p.getScore()) {
                    // ピボットと成績が同じ場合は受験番号をみる
                    if(d[r].getScore() == p.getScore()){
                        if(d[r].getNumber() < p.getNumber() || r == c){
                            break;
                        }
                    }
                    r--;
                }
                // 入れ替え
                if (l <= r) {
                    Seiseki tmp = d[l];
                    d[l] = d[r];
                    d[r] = tmp;
                    l++;
                    r--;
                }
            }
            // 再帰
            stack.push(new int[]{left,r});
            stack.push(new int[]{l,right});
        }
    }
}
class Seiseki
{
    String Number;
    String Math2B;
    String Math3C;
    String English;
    String Physics;
    String Chemical;
    String Biology;
    String EarthScience;
    int getScore(){
        return _score;
    }
    int getNumber(){
        return _number;
    }
    private int _score;
    private int _number;

    Seiseki(String str){
      init(str);
    }

    void init(String str){
        Number = str.substring(0,5);
        Math2B = str.substring(7,10);
        Math3C = str.substring(12,15);
        English = str.substring(17,20);
        Physics = str.substring(22,25);
        Chemical = str.substring(27,30);
        Biology = str.substring(32,35);
        EarthScience = str.substring(36,40);

        int math2B = Integer.parseInt(Math2B.trim());
        int math3C = Integer.parseInt(Math3C.trim());
        int english = Integer.parseInt(English.trim());
        int physics = Integer.parseInt(Physics.trim());
        int chemical = Integer.parseInt(Chemical.trim());
        int biology =Integer.parseInt(Biology.trim());
        int earthScience = Integer.parseInt(EarthScience.trim());

        int sience1 = physics;
        int sience2 = chemical;
        if(biology > sience1) sience1 = biology;
        else if(biology > sience2) sience2 = biology;
        if(earthScience > sience1) sience1 = earthScience;
        else if(earthScience > sience2) sience2 = earthScience;

        _number = Integer.parseInt(str.substring(0,5).trim());
        _score = math2B + math3C +english + sience1 + sience2;
    }

    @Override
    public String toString() {
        return Number+"  "+Math2B+"  "+Math3C+"  "+English
                +"  "+Physics+"  "+Chemical+"  "+Biology+"  "+EarthScience;
    }
}
