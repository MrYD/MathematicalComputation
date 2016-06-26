package com.company;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Software {
    public Software() {
    }

    public static int inputMatrix(String var0, double[][] var1) {
        String var2 = "";
        int var5 = 1;

        try {
            String var3;
            BufferedReader var6;
            for(var6 = new BufferedReader(new InputStreamReader(new FileInputStream(var0))); (var3 = var6.readLine()) != null; ++var5) {
                StringTokenizer var7 = new StringTokenizer(var3, " ");
                int var4 = var7.countTokens();

                for(int var8 = 1; var8 <= var4; ++var8) {
                    try {
                        var2 = var7.nextToken();
                        var1[var5][var8] = Double.valueOf(var2).doubleValue();
                    } catch (NoSuchElementException var10) {
                        System.out.println("No Token" + var3);
                    }
                }
            }

            var6.close();
        } catch (FileNotFoundException var11) {
            System.out.println("File Not Found ---> " + var0);
        } catch (IOException var12) {
            System.out.println(var12.getMessage());
        }

        return var5 - 1;
    }

    public static int inputVector(String var0, double[] var1) {
        String var2 = "";
        int var5 = 1;

        try {
            BufferedReader var6 = new BufferedReader(new InputStreamReader(new FileInputStream(var0)));

            String var3;
            while((var3 = var6.readLine()) != null) {
                StringTokenizer var7 = new StringTokenizer(var3, " ");
                int var4 = var7.countTokens();

                for(int var8 = 1; var8 <= var4; ++var8) {
                    try {
                        var2 = var7.nextToken();
                        var1[var5] = Double.valueOf(var2).doubleValue();
                        ++var5;
                    } catch (NoSuchElementException var10) {
                        System.out.println("No Token" + var3);
                    }
                }
            }

            var6.close();
        } catch (FileNotFoundException var11) {
            System.out.println("File Not Found ---> " + var0);
        } catch (IOException var12) {
            System.out.println(var12.getMessage());
        }

        return var5 - 1;
    }

    static void matrixDisplay(int var0, double[][] var1, String var2) {
        for(int var3 = 1; var3 <= var0; ++var3) {
            for(int var4 = 1; var4 <= var0; ++var4) {
                System.out.printf("%" + var2 + "f", new Object[]{Double.valueOf(var1[var3][var4])});
            }

            System.out.println("");
        }

    }

    static void vectorDisplay(int var0, double[] var1, String var2) {
        for(int var3 = 1; var3 <= var0; ++var3) {
            System.out.printf("%" + var2 + "f", new Object[]{Double.valueOf(var1[var3])});
        }

        System.out.println("");
    }

    public static String inputKeybordString(String var0) {
        Scanner var1 = new Scanner(System.in);
        System.out.print(var0);
        String var2 = var1.next();
        return var2;
    }

    public static int inputKeybordInteger(String var0) {
        String[] var1 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Scanner var2 = new Scanner(System.in);
        System.out.print(var0);
        String var3 = var2.next();
        int var4 = var3.length();

        int var5;
        for(var5 = 0; var5 < var4; ++var5) {
            boolean var6 = false;

            for(int var7 = 0; var7 < 10; ++var7) {
                if(var3.substring(var5, var5 + 1).equals(var1[var7])) {
                    var6 = true;
                    break;
                }
            }

            if(!var6) {
                System.out.println("  " + var3 + " is not integer value");
                System.exit(1);
            }
        }

        var5 = Integer.valueOf(var3).intValue();
        return var5;
    }
}
