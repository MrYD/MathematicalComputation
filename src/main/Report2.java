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
            File file = new File("C:\\Users\\wang\\Desktop\\Seiseki.dat");
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
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        quick_sort(Array);

        for (Seiseki itr:Array) {
            System.out.println(itr.Number +" " + itr.getScore());
        }
    }

    static void quick_sort(Seiseki[] d) {
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
                        if(d[l].Number > p.Number || l == c) {
                            break;
                        }
                    }
                    l++;
                }
                // 右端からピボットまで、ピボット以上の要素を探す
                while(d[r].getScore() <= p.getScore()) {
                    // ピボットと成績が同じ場合は受験番号をみる
                    if(d[r].getScore() == p.getScore()){
                        if(d[r].Number < p.Number || r == c){
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
    public int Number;
    public int Math2B;
    public int Math3C;
    public int English;
    public int Physics;
    public int Chemical;
    public int Biology;
    public int EarthScience;
    public int getScore(){
        return _score;
    }
    private int _score;
    private int _sience1;
    private int _sience2;

    public Seiseki(String str){
        Number = Integer.parseInt(str.substring(0,5).trim());
        Math2B = Integer.parseInt(str.substring(7,10).trim());
        Math3C = Integer.parseInt(str.substring(12,15).trim());
        English = Integer.parseInt(str.substring(17,20).trim());
        Physics = Integer.parseInt(str.substring(22,25).trim());
        Chemical = Integer.parseInt(str.substring(27,30).trim());
        Biology =Integer.parseInt(str.substring(32,35).trim());
        EarthScience = Integer.parseInt(str.substring(37,40).trim());

        _sience1 = Physics;
        _sience2 = Chemical;
        if(Biology > _sience1) _sience1 = Biology;
        else if(Biology > _sience2) _sience2 = Biology;
        if(EarthScience > _sience1) _sience1 = EarthScience;
        else if(EarthScience > _sience2) _sience2 = EarthScience;
        _score = Math2B+Math3C+English+_sience1+_sience2;
    }

    @Override
    public String toString() {
        return "" + Number + " " + Math2B + " " + Math3C + " " + English
                + " " + Physics + " " + Chemical +" "+ Biology +" "+ EarthScience;
    }
}
