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
    static double dt = 0.001;
    static double dx = 0.01;
    static double kappa = 0.0117; // 金の熱伝達係数 m^2/s //
    static double lambda = kappa * dt / dx / dx;
    static int height = 61; // 格子点の数 (長さ60cm)
    static int width = 41; // 格子点の数 (幅40cm)
    static int m = 50000; // 時間格子の数

    /* データを貯めておく配列 */
    static double[][][] TTT = new double[(int)(m*dt)+1][height][width];;

    public static void main(String[] args) throws Exception {
        // 変数初期化
        int i, j;
        double[][] T = new double[height][width];
        double[][] TT = new double[height][width];

        // 初期条件の設定
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                if ((i - 30) * (i - 30) + (j - 20) * (j - 20) <= 10 * 10) {
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
                    if ((i - 30) * (i - 30) + (j - 20) * (j - 20) <= 10 * 10) {
                        T[i][j] = 773.0;
                    }
                }
            }
            for (i = 0; i < height; i++) {// 境界はノイマン条件とする 左右
                T[i][0] = T[i][1];
                T[i][width - 1] = T[i][width - 2];
            }
            for (j = 0; j < width; j++) {// 境界はノイマン条件とする 上下
                T[0][j] = T[1][j];
                T[height - 1][j] = T[height - 2][j];
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
    double w = width * 10;
    double h = height * 10;

    /* 秒数のカウンター */
    int count = 0;

    /* Windowの設定 */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene s = new Scene(root, w + x1 * 2, y1 + h + 100, Color.WHITE);
        final Canvas canvas = new Canvas(w + x1 * 2, y1 + h + 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // 図の描画
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
        root.getChildren().add(canvas);
        root.getChildren().add(plusButton);
        root.getChildren().add(minusButton);
        primaryStage.setScene(s);
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
                gc.strokeRect(x1 + w1 * k,y1 + h1 * j,w1, h1);
            }
        }
        gc.strokeRect(x1,y1,w,h);

        // 温度バーの描画
        for (int j = 0; j <= w; j++) {
            double x = (double)j/w;
            Color color = GetColor(x);
            gc.setFill(color);
            gc.fillRect(x1 + j,y1 + h + 30,1,10);
            if((x*500) % 50 == 0){
                gc.setFill(Color.BLACK);
                gc.fillText("" + (int)(x*500),x1 + j - 5,(int)(y1 + h) + 60);
            }
        }
        gc.strokeRect(x1,y1 + h + 30,w,10);

        // 秒数
        gc.setFill(Color.BLACK);
        gc.fillText(count+" 秒後",x1 + 40,y1+h+20);
    }


    /* 0-1の値から色を返す */
    Color GetColor(double x){
        double r = 0;
        double g = 0;
        double b = 0;

        if(0 <= x && x < 0.1){
            r = 255;
            g = 255;
            b = 245;
        }
        else if(0.1 <= x && x < 0.2){
            r = 255;
            g = 255;
            b = 200;
        }
        else if(0.2 <= x && x < 0.3){
            r = 255;
            g = 254;
            b = 173;
        }
        else if(0.3 <= x && x < 0.4){
            r = 255;
            g = 244;
            b = 133;
        }
        else if(0.4 <= x && x < 0.5){
            r = 254;
            g = 237;
            b = 100;
        }
        else if(0.5 <= x && x < 0.6){
            r = 254;
            g = 217;
            b = 53;
        }
        else if(0.6 <= x && x < 0.7){
            r = 253;
            g = 193;
            b = 40;
        }
        else if(0.7 <= x && x < 0.8){
            r = 253;
            g = 163;
            b = 38;
        }
        else if(0.8 <= x && x < 0.9){
            r = 253;
            g = 116;
            b = 34;
        }
        else if(0.9 <= x && x <= 1){
            r = 252;
            g = 13;
            b = 27;
        }
        return new Color(r/255,g/255,b/255,1);
    }
}