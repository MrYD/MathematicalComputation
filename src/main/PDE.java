package main;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.application.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class PDE extends Application{

    /* 初期条件の設定 */
    static int n = 5;
    static double dt = 0.001;
    static double dx = 0.01 / n;
    static double kappa = 0.000023473150395; // 熱伝達係数 m^2/s //
    static double lambda = kappa * dt / dx / dx;
    static int height = 61 * n; // 格子点の数 (長さ60cm)
    static int width = 41 * n; // 格子点の数 (幅40cm)
    static int m = 50000; // 時間格子の数

    /* データを貯めておく配列 */
    static double[][][] TTT = new double[(int)(m*dt)+1][height][width];;

    public static void main(String[] args) throws Exception {
        // 変数初期化
        int i, j;
        double[][] T = new double[height][width];
        double[][] TT = new double[height][width];
        int d = 9;

        // 初期条件の設定
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                double rr = ((i - height/2) * (i - height/2) + (j - width/2) * (j - width/2)) / n / n;
                if (10 * 10  - d <= rr && rr <= 10 * 10 + d) {
                    T[i][j] = 773.0;
                } else {
                    T[i][j] = 300.0;
                }
            }
        }

        // 初期値の格納
        int nn = 0;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
               TTT[nn][i][j] = T[i][j] - 273;
            }
        }
        // 内部温度の計算
        while (nn <= m) {
            nn++;
            for (i = 1; i < (height - 1); i++) {
                for (j = 1; j < (width - 1); j++) {
                    TT[i][j] = lambda * (T[i - 1][j] + T[i][j - 1] + T[i + 1][j] + T[i][j + 1])
                            + (1 - 2 * lambda - 2 * lambda) * T[i][j];
                }
            }
            for (i = 1; i < (height - 1); i++) {
                for (j = 1; j < (width - 1); j++) {
                    T[i][j] = TT[i][j];
                }
            }
            for (i = 0; i < height; i++) {// 火源はディリクレ条件とする
                for (j = 0; j < width; j++) {
                    double rr = ((i - height/2) * (i - height/2) + (j - width/2) * (j - width/2)) / n / n;
                    if (10 * 10  - d <= rr && rr <= 10 * 10 + d) {
                        T[i][j] = 773.0;
                    }
                }
            }
            for (i = 0; i < height; i++) {// 境界はノイマン条件とする 左右
                T[i][0] = T[i][1];
                T[i][width - 1] = T[i][width - 2];
                if(T[i][width - 1] > 773)throw new Exception();
            }
            for (j = 0; j < width; j++) {// 境界はノイマン条件とする 上下
                T[0][j] = T[1][j];
                T[height - 1][j] = T[height - 2][j];
                if(T[height - 1][j] > 773)throw new Exception();
            }

            // 計算結果をTTTに代入
            if ((nn) % 1000 == 0) {
                for (i = 0; i < height; i++) {
                    for (j = 0; j < width; j++) {
                        TTT[(nn) / 1000][i][j] = T[i][j] - 273;
                    }
                }
            }
        }

        // Windowの起動
        launch(args);
    }

    /* 図のサイズ設定 */
    double x1 = 50;
    double y1 = 20;
    double w = width * 10 / n;
    double h = height * 10 / n;

    /* 秒数のカウンター */
    int count = 0;

    /* Windowの設定 */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 図の描画
        final Canvas canvas = new Canvas(w + x1 * 2, y1 + h + 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.GRAY);
        drawGraph(gc,count);

        // ->ボタン
        Button plusButton = new Button();
        plusButton.setText("→");
        plusButton.setLayoutX(x1+w-30);
        plusButton.setLayoutY(y1+h);
        plusButton.setOnAction(event->{
            if(count < TTT.length - 1)
                drawGraph(gc,++count);
        });

        // <-ボタン
        Button minusButton = new Button();
        minusButton.setText("←");
        minusButton.setLayoutX(x1);
        minusButton.setLayoutY(y1+h);
        minusButton.setOnAction(event -> {
            if(count > 0)
                drawGraph(gc,--count);
        });

        // UI要素追加
        Group root = new Group();
        Scene s = new Scene(root, w + x1 * 2, y1 + h + 100, Color.WHITE);

        root.getChildren().add(canvas);
        root.getChildren().add(plusButton);
        root.getChildren().add(minusButton);

        primaryStage.setScene(s);
        primaryStage.setTitle("２次元熱伝導方程式");
        primaryStage.show();
    }

    /* 図の描画 */
    void drawGraph(GraphicsContext gc,int i){
        // 図のクリア
        gc.clearRect(0,0,w + x1 * 2,y1 + h + 100);

        // 等高線の描画
        for (int j = 0; j < TTT[0].length; j++) {
            for (int k = 0; k < TTT[0][0].length; k++) {
                Color c = GetColor(TTT[i][j][k]/500);
                double h1 = h/TTT[0].length;
                double w1 = w/TTT[0][0].length;
                gc.setFill(c);
                gc.fillRect(x1 + w1 * k,y1 + h1 * j,w1, h1);
                //gc.strokeRect(x1 + w1 * k,y1 + h1 * j,w1, h1);
            }
        }
        gc.strokeRect(x1,y1,w,h);
        gc.setFill(Color.BLACK);
        gc.fillText(Integer.toString(height),x1-20,y1+10);
        gc.fillText("0", x1-20,y1+h);
        gc.fillText(Integer.toString(width), x1+w+10,y1+h);

        // 温度バーの描画
        for (int j = 0; j <= w; j++) {
            double x = (double)j/w;
            Color color = GetColor(x);
            gc.setFill(color);
            gc.fillRect(x1 + j,y1 + h + 30,1,10);
            if((x*500) % 50 == 0){
                gc.setFill(Color.BLACK);
                gc.fillText("" + (int)(x*500),x1 + j - 10,(int)(y1 + h) + 60);
            }
        }
        gc.fillText("(℃)",x1 + w + 20,(int)(y1 + h) + 60);
        gc.strokeRect(x1,y1 + h + 30,w,10);

        // 秒数
        gc.setFill(Color.BLACK);
        gc.fillText(count+" 秒後",x1 + 40,y1+h+20);
    }


    /* 0-1の値から色を返す */
    Color GetColor(double x){
        double r = 240;
        if(0 <= x && x < 0.1){
           return Color.hsb(0.9*r,1,1);
        }
        else if(0.1 <= x && x < 0.2){
            return Color.hsb(0.8*r,1,1);
        }
        else if(0.2 <= x && x < 0.3){
            return Color.hsb(0.7*r,1,1);
        }
        else if(0.3 <= x && x < 0.4){
            return Color.hsb(0.6*r,1,1);
        }
        else if(0.4 <= x && x < 0.5){
            return Color.hsb(0.5*r,1,1);
        }
        else if(0.5 <= x && x < 0.6){
            return Color.hsb(0.4*r,1,1);
        }
        else if(0.6 <= x && x < 0.7){
            return Color.hsb(0.3*r,1,1);
        }
        else if(0.7 <= x && x < 0.8){
            return Color.hsb(0.2*r,1,1);
        }
        else if(0.8 <= x && x < 0.9){
            return Color.hsb(0.1*r,1,1);
        }
        else if(0.9 <= x && x <= 1){
            return Color.hsb(0 ,1,1);
        }
        return Color.BLACK;
    }
}