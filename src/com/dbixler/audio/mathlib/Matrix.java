package com.dbixler.audio.mathlib;

import com.dbixler.audio.exception.MatrixDimensionsException;

/**
 * Matrix math
 * <p/>
 * Created by dbixler on 11/23/14.
 */
public class Matrix {

  public static double[][] add(double[][] a, double[][] b) throws MatrixDimensionsException {
    int m = a.length;
    int n = a[0].length;
    int q = b.length;
    int r = b[0].length;

    if (m != q || n != r) {
      throw new MatrixDimensionsException();
    } else {
      double[][] c = new double[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          c[i][j] = a[i][j] + b[i][j];
        }
      }
      return c;
    }
  }

  public static double[][] subtract(double[][] a, double[][] b) throws MatrixDimensionsException {
    int m = a.length;
    int n = a[0].length;
    int q = b.length;
    int r = b[0].length;

    if (m != q || n != r) {
      throw new MatrixDimensionsException();
    } else {
      double[][] c = new double[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          c[i][j] = a[i][j] - b[i][j];
        }
      }
      return c;
    }
  }

  public static double[][] multiply(double[][] a, double[][] b) throws MatrixDimensionsException {
    int m = a.length;
    int r = b[0].length;

    if (m != r) {
      throw new MatrixDimensionsException();
    } else {
      // TODO:
      double[][] c = new double[m][r];
      return c;
    }
  }

  public static void print(double[][] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        System.out.print(a[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String args[]) {

    double[][] a = {
        {1, 2, 3, 4},
        {1, 2, 3, 4}
    };

    double[][] b = {
        {1, 2, 3, 4},
        {1, 2, 3, 4}
    };

    try {
      Matrix.print(a);
      Matrix.print(b);
      double[][] c = Matrix.add(a, b);
      Matrix.print(c);
      c = Matrix.subtract(a, b);
      Matrix.print(c);
    } catch (MatrixDimensionsException e) {
      e.printStackTrace();
    }
  }

}
