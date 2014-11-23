package com.dbixler.audio.mathlib;

/**
 * This is almost pointslees to make but there's already
 * enough going on in IEEE80. No javadocs for this one,
 * it's self explanatory and they look fugly.
 * <p/>
 * Created by dbixler on 11/20/2014.
 */
public class Unsigned {

  public static byte Byte(byte b) {
    return ((b >> 7) & 1) == 1 ? (byte) -b : b;
  }

  public static short Short(short s) {
    return ((s >> 15) & 1) == 1 ? (short) -s : s;
  }

  public static int Integer(int i) {
    return ((i >> 31) & 1) == 1 ? -i : i;
  }

  public static long Long(long l) {
    return ((l >> 63) & 1) == 1 ? -l : l;
  }

  public static float Float(float f) {
    int i = Float.floatToRawIntBits(f);
    return ((i >> 31) & 1) == 1 ? -f : f;
  }

  public static double Double(double d) {
    long l = Double.doubleToRawLongBits(d);
    return ((l >> 63) & 1) == 1 ? -d : d;
  }

}
