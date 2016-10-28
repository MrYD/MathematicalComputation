package main;
import java.io.File;
import java.io.FileReader;
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

        int count = 1;
        for(int i = 0; count < 300;i++){
            System.out.print(count+" ");
            System.out.print(Array[i].getScore()+" ");
            System.out.print(Array[i] + " ");
            System.out.print(Array[i].getSience1() + " ");
            System.out.println(Array[i].getSience2());
            if(Array[i].getScore() != Array[i + 1].getScore()){
                count = i + 2;
            }
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


class Seiseki {
    int[] data = new int[8];

    int getScore() {
        return _score;
    }

    int getNumber() {
        return data[0];
    }

    String getSience1() {
        return getSience(_sience1);
    }

    String getSience2() {
        return getSience(_sience2);
    }

    private int _score;
    private byte _sience1;
    private byte _sience2;

    private String getSience(byte n) {
        switch (n) {
            case 4:
                return "物理B";
            case 5:
                return "化学B";
            case 6:
                return "生物B";
            case 7:
                return "地学B";
            default:
                return "";
        }
    }

    Seiseki(String str) {
        init(str);
    }

    void init(String str) {
        String Number = str.substring(0, 5);
        String Math2B = str.substring(7, 10);
        String Math3C = str.substring(12, 15);
        String English = str.substring(17, 20);
        String Physics = str.substring(22, 25);
        String Chemical = str.substring(27, 30);
        String Biology = str.substring(32, 35);
        String EarthScience = str.substring(36, 40);

        data[1] = Integer.parseInt(Math2B.trim());
        data[2] = Integer.parseInt(Math3C.trim());
        data[3] = Integer.parseInt(English.trim());
        data[4] = Integer.parseInt(Physics.trim());
        data[5] = Integer.parseInt(Chemical.trim());
        data[6] = Integer.parseInt(Biology.trim());
        data[7] = Integer.parseInt(EarthScience.trim());

        if (data[4] > data[5]) {
            _sience1 = 4;
            _sience2 = 5;
        } else{
            _sience1 = 5;
            _sience2 = 4;
        }
        if(data[6] > data[_sience2]){
            if(data[6] > data[_sience1]){
                _sience2 = _sience1;
                _sience1 = 6;
            }
            else {
                _sience2 = 6;
            }
        }
        if(data[7] > data[_sience2]){
            _sience2 = 7;
        }

        data[0] = Integer.parseInt(Number.trim());
        _score = data[1] + data[2] +data[3] + data[_sience1] + data[_sience2];
    }

    @Override
    public String toString() {
        return data[0]+" "+data[1]+" "+data[2]+" "+data[3]
                +" "+data[4]+" "+data[5]+" "+data[6]+" "+data[7];
    }
}
