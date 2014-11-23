package com.dbixler.audio.mathlib;

import java.util.Random;

/**
 * A math library that is similar to java.lang.Math but with a few extra twists.
 * <p>
 * Created by dbixler on 11/20/2014.
 */
public class DSPMath {

  /**
   * Euler's constant
   */
  public static final double E = 2.7182818284590452354;

  /**
   * Pi = circumference/diameter =
   */
  public static final double PI = 3.14159265358979323846;

  /**
   * Float constants
   */
  public static final float FLT_POSITIVE_INFINITY = java.lang.Float.POSITIVE_INFINITY;
  public static final float FLT_NEGATIVE_INFINITY = java.lang.Float.NEGATIVE_INFINITY;
  public static final float FLT_NaN = java.lang.Float.NaN;
  public static final float FLT_MAX_VALUE = java.lang.Float.MAX_VALUE;
  public static final float FLT_MIN_VALUE = java.lang.Float.MIN_VALUE;
  /**
   * A constant holding the smallest positive normal value of type
   * <code>float</code>, 2<sup>-126</sup>. It is equal to the value returned by
   * <code>Float.intBitsToFloat(0x00800000)</code>.
   */
  public static final float FLT_MIN_NORMAL = 1.17549435E-38f;
  /**
   * The number of logical bits in the significand of a <code>float</code>
   * number, including the implicit bit.
   */
  public static final int FLT_SIGNIFICAND_WIDTH = 24;
  /**
   * Maximum exponent a finite <code>float</code> number may have. It is equal
   * to the value returned by <code>Math.ilogb(Float.MAX_VALUE)</code>.
   */
  public static final int FLT_MAX_EXPONENT = 127;
  /**
   * Minimum exponent a normalized <code>float</code> number may have. It is
   * equal to the value returned by <code>Math.ilogb(Float.MIN_NORMAL)</code>.
   */
  public static final int FLT_MIN_EXPONENT = -126;
  /**
   * The exponent the smallest positive <code>float</code> subnormal value would
   * have if it could be normalized. It is equal to the value returned by
   * <code>FpUtils.ilogb(Float.MIN_VALUE)</code>.
   */
  public static final int FLT_MIN_SUB_EXPONENT = FLT_MIN_EXPONENT
      - (FLT_SIGNIFICAND_WIDTH - 1);
  /**
   * Bias used in representing a <code>float</code> exponent.
   */
  public static final int FLT_EXP_BIAS = 127;
  /**
   * Bit mask to isolate the sign bit of a <code>float</code>.
   */
  public static final int FLT_SIGN_BIT_MASK = 0x80000000;
  /**
   * Bit mask to isolate the exponent field of a <code>float</code>.
   */
  public static final int FLT_EXP_BIT_MASK = 0x7F800000;
  /**
   * Bit mask to isolate the significand field of a <code>float</code>.
   */
  public static final int FLT_SIGNIF_BIT_MASK = 0x007FFFFF;

  static
  {
    // verify bit masks cover all bit positions and that the bit
    // masks are non-overlapping
    assert (((FLT_SIGN_BIT_MASK | FLT_EXP_BIT_MASK | FLT_SIGNIF_BIT_MASK) == ~0) && (((FLT_SIGN_BIT_MASK & FLT_EXP_BIT_MASK) == 0)
        && ((FLT_SIGN_BIT_MASK & FLT_SIGNIF_BIT_MASK) == 0) && ((FLT_EXP_BIT_MASK & FLT_SIGNIF_BIT_MASK) == 0)));
  }

  /**
   * Double constants
   */
  public static final double DBL_POSITIVE_INFINITY = java.lang.Double.POSITIVE_INFINITY;
  public static final double DBL_NEGATIVE_INFINITY = java.lang.Double.NEGATIVE_INFINITY;
  public static final double DBL_NaN = java.lang.Double.NaN;
  public static final double DBL_MAX_VALUE = java.lang.Double.MAX_VALUE;
  public static final double DBL_MIN_VALUE = java.lang.Double.MIN_VALUE;
  /**
   * A constant holding the smallest positive normal value of type
   * <code>double</code>, 2<sup>-1022</sup>. It is equal to the value returned
   * by <code>Double.longBitsToDouble(0x0010000000000000L)</code>.
   */
  public static final double DBL_MIN_NORMAL = 2.2250738585072014E-308;
  /**
   * The number of logical bits in the significand of a <code>double</code>
   * number, including the implicit bit.
   */
  public static final int DBL_SIGNIFICAND_WIDTH = 53;
  /**
   * Maximum exponent a finite <code>double</code> number may have. It is equal
   * to the value returned by <code>Math.ilogb(Double.MAX_VALUE)</code>.
   */
  public static final int DBL_MAX_EXPONENT = 1023;
  /**
   * Minimum exponent a normalized <code>double</code> number may have. It is
   * equal to the value returned by <code>Math.ilogb(Double.MIN_NORMAL)</code>.
   */
  public static final int DBL_MIN_EXPONENT = -1022;
  /**
   * The exponent the smallest positive <code>double</code> subnormal value
   * would have if it could be normalized. It is equal to the value returned by
   * <code>FpUtils.ilogb(Double.MIN_VALUE)</code>.
   */
  public static final int DBL_MIN_SUB_EXPONENT = DBL_MIN_EXPONENT
      - (DBL_SIGNIFICAND_WIDTH - 1);
  /**
   * Bias used in representing a <code>double</code> exponent.
   */
  public static final int DBL_EXP_BIAS = 1023;
  /**
   * Bit mask to isolate the sign bit of a <code>double</code>.
   */
  public static final long DBL_SIGN_BIT_MASK = 0x8000000000000000L;
  /**
   * Bit mask to isolate the exponent field of a <code>double</code>.
   */
  public static final long DBL_EXP_BIT_MASK = 0x7FF0000000000000L;
  /**
   * Bit mask to isolate the significand field of a <code>double</code>.
   */
  public static final long DBL_SIGNIF_BIT_MASK = 0x000FFFFFFFFFFFFFL;

  static
  {
    // verify bit masks cover all bit positions and that the bit
    // masks are non-overlapping
    assert (((DBL_SIGN_BIT_MASK | DBL_EXP_BIT_MASK | DBL_SIGNIF_BIT_MASK) == ~0L) && (((DBL_SIGN_BIT_MASK & DBL_EXP_BIT_MASK) == 0L)
        && ((DBL_SIGN_BIT_MASK & DBL_SIGNIF_BIT_MASK) == 0L) && ((DBL_EXP_BIT_MASK & DBL_SIGNIF_BIT_MASK) == 0L)));
  }

  /**
   * Negative zero for max and min methods
   */
  public static final long FLT_NEG_ZERO_BITS = Float.floatToIntBits(-0.0f);
  /**
   * Negative zero for min and max methods
   */
  public static final long DBL_NEG_ZERO_BITS = Double.doubleToLongBits(-0.0d);
  /**
   * Random number generator for random() method
   */
  private static Random randomNumberGenerator;

  /**
   * @return whether f is equal to NaN
   */
  public static boolean isNan(float f) {
    // NaN is never equal to NaN in Java
    return (f != f);
  }

  /**
   * @return double d's
   */
  public static boolean isNan(double d) {
    // NaN is never equal to NaN in Java
    return (d != d);
  }

  /**
   * Computes the remainder operation on two arguments as prescribed by the IEEE
   * 754 standard.
   *
   * @param f1 the dividend.
   * @param f2 the divisor.
   * @return the remainder when {@code f1} is divided by {@code f2}.
   */
  public static double IEEEremainder(double f1, double f2)
  {
    return StrictMath.IEEEremainder(f1, f2); // delegate to StrictMath
  }

  /**
   * Calculates the absolute value of the input
   *
   * @param a the argument whose absolute value is to be determined
   * @return the absolute value of the argument
   */
  public static long abs(long a)
  {
    return (a < 0) ? -a : a;
  }

  /**
   * Calculates the absolute value of the input
   *
   * @param a the argument whose absolute value is to be determined
   * @return the absolute value of the argument
   */
  public static float abs(float a)
  {
    return (a < 0) ? -a : a;
  }

  /**
   * Calculates the absolute value of the input
   *
   * @param a the argument whose absolute value is to be determined
   * @return the absolute value of the argument
   */
  public static double abs(double a)
  {
    return (a < 0) ? -a : a;
  }

  /**
   * Calculates the absolute value of the input.
   *
   * @param a the argument whose absolute value is to be determined
   * @return the absolute value of the argument
   */
  public static int abs(int a)
  {
    return (a < 0) ? -a : a;
  }

  /**
   * Returns the arc cosine of the input value.
   *
   * @param a the value whose arc cosine is to be returned.
   * @return the arc cosine of the argument.
   */
  public static double acos(double a)
  {
    return StrictMath.acos(a); // default impl. delegates to StrictMath
  }

  /**
   * Returns the arc sine of the input value.
   *
   * @param a the value whose arc sine is to be returned.
   * @return the arc sine of the argument.
   */
  public static double asin(double a)
  {
    return StrictMath.asin(a); // default impl. delegates to StrictMath
  }

  /**
   * Returns the arc tangent of the input value.
   *
   * @param a the value whose arc tangent is to be returned.
   * @return the arc tangent of the argument.
   */
  public static double atan(double a)
  {
    return StrictMath.atan(a); // default impl. delegates to StrictMath
  }

  /**
   * Calculates the cubed root of the input.
   *
   * @param a a value.
   * @return the cube root of {@code a}.
   */
  public static double cbrt(double a)
  {
    return StrictMath.cbrt(a);
  }

  /**
   * Calculates the ceiling function using the input value.
   *
   * @param a a value.
   * @return the smallest (closest to negative infinity) floating-point value
   * that is greater than or equal to the argument and is equal to a
   * mathematical integer.
   */
  public static double ceil(double a)
  {
    return StrictMath.ceil(a); // default impl. delegates to StrictMath
  }

  /**
   * Copy the sign of the second argument to the first argument.
   *
   * @param magnitude absolute value of the desired output.
   * @param sign      the sign of the desired output.
   * @return value with the magnitude of {@code magnitude} and the sign of
   * {@code sign}.
   */
  public static float copySign(float magnitude, float sign)
  {
    return StrictMath.copySign(magnitude, sign);
  }

  /**
   * Copy the sign of the second argument to the first argument.
   *
   * @param magnitude absolute value of the desired output.
   * @param sign      the sign of the desired output.
   * @return value with the magnitude of {@code magnitude} and the sign of
   * {@code sign}.
   */
  public static double copySign(double magnitude, double sign)
  {
    return StrictMath.copySign(magnitude, sign);
  }

  /**
   * Calculate the sine function of the input.
   *
   * @param a an angle, in radians.
   * @return the cosine of the argument.
   */
  public static double cos(double a)
  {
    return StrictMath.cos(a); // default impl. delegates to StrictMath
  }

  /**
   * Calculate the hyperbolic cosine function of the input.
   *
   * @param x The number whose hyperbolic cosine is to be returned.
   * @return The hyperbolic cosine of {@code x}.
   */
  public static double cosh(double x)
  {
    return StrictMath.cosh(x);
  }

  /**
   * Calculate e raised to the power of the input value.
   *
   * @param a power to raise e by.
   * @return e^a
   */
  public static double exp(double a)
  {
    return StrictMath.exp(a); // default impl. delegates to StrictMath
  }

  /**
   * Calculates the floor function using the input, a.
   *
   * @param a a value.
   * @return the largest (closest to positive infinity) floating-point value
   * that less than or equal to the argument and is equal to a
   * mathematical integer.
   */
  public static double floor(double a)
  {
    return StrictMath.floor(a); // default impl. delegates to StrictMath
  }

  /**
   * Calculate the exponent bias in IEE Standard 754 form.
   *
   * @param f floating point value
   * @return the unbiased exponent in IEE 754 format
   */
  public static int getExponent(float f)
  {
    return ((Float.floatToRawIntBits(f) & FLT_EXP_BIT_MASK) >> (FLT_SIGNIFICAND_WIDTH - 1)) - FLT_EXP_BIAS;
  }

  /**
   * Calculate the exponent bias in IEE Standard 754 form.
   *
   * @param d a double value
   * @return the unbiased exponent in IEE 754 format
   */
  public static int getExponent(double d)
  {
    return (int) (((Double.doubleToRawLongBits(d) & DBL_EXP_BIT_MASK) >> (DBL_SIGNIFICAND_WIDTH - 1)) - DBL_EXP_BIAS);
  }

  /**
   * Calculate the square root of (x^2 + y^2).
   *
   * @return sqrt(x*x + y*y)
   */
  public static double hypot(double x, double y)
  {
    return StrictMath.hypot(x, y);
  }

  /**
   * Checks and returns true if input value is not infinity
   *
   * @param d the {@code double} value to be tested
   * @return {@code true} if the argument is a finite floating-point value,
   * {@code false} otherwise.
   */
  public static boolean isFinite(double d)
  {
    return Math.abs(d) <= DBL_MAX_VALUE;
  }

  /**
   * Checks and returns true if input value is infinity.
   *
   * @param f the {@code float} value to be tested
   * @return {@code true} if the argument is a finite floating-point value,
   * {@code false} otherwise.
   */
  public static boolean isFinite(float f)
  {
    return Math.abs(f) <= FLT_MAX_VALUE;
  }

  /**
   * Calculate the natural logarithm (base e) of the input value.
   *
   * @param a number to take natural log of.
   * @return natural log of input.
   */
  public static double log(double a)
  {
    return StrictMath.log(a); // default impl. delegates to StrictMath
  }

  /**
   * Calculate the larger of the two input values.
   *
   * @param a any integer value
   * @param b any integer value
   * @return the max value between a or b
   */
  public static int max(int a, int b)
  {
    return (a > b ? a : b);
  }

  /**
   * Calculate the larger of the two input values.
   *
   * @param a any long value
   * @param b any long value
   * @return the max value between a or b
   */
  public static long max(long a, long b)
  {
    return (a > b ? a : b);
  }

  /**
   * Calculate the larger of the two input values.
   *
   * @param a any float value
   * @param b any float value
   * @return the max value between a or b
   */
  public static float max(float a, float b)
  {
    if (a != a)
      return a; // a is NaN
    if ((a == 0.0f) && (b == 0.0f)
        && (Float.floatToIntBits(a) == FLT_NEG_ZERO_BITS))
    {
      return b;
    }
    return (a >= b) ? a : b;
  }

  /**
   * Calculate the larger of the two input values.
   *
   * @param a any double value
   * @param b any double value
   * @return the max value between a or b
   */
  public static double max(double a, double b)
  {
    if (a != a)
      return a; // a is NaN
    if ((a == 0.0d) && (b == 0.0d)
        && (Double.doubleToLongBits(a) == DBL_NEG_ZERO_BITS))
    {
      return b;
    }
    return (a >= b) ? a : b;
  }

  /**
   * Calculate the max value between a, b, and c
   *
   * @param a any integer value
   * @param b any integer value
   * @param c any integer value
   * @return the max value between a, b, and c
   */
  public static int max3(int a, int b, int c)
  {
    return max(max(a, b), c);
  }

  /**
   * Calculate the max value between a, b, and c
   *
   * @param a any long value
   * @param b any long value
   * @param c any long value
   * @return the max value between a, b, and c
   */
  public static long max3(long a, long b, long c)
  {
    return max(max(a, b), c);
  }

  /**
   * Calculate the max value between a, b, and c
   *
   * @param a any float value
   * @param b any float value
   * @param c any float value
   * @return the max value between a, b, and c
   */
  public static float max3(float a, float b, float c)
  {
    return max(max(a, b), c);
  }

  /**
   * Calculate the max value between a, b, and c
   *
   * @param a any double value
   * @param b any double value
   * @param c any double value
   * @return the max value between a, b, and c
   */
  public static double max3(double a, double b, double c)
  {
    return max(max(a, b), c);
  }

  /**
   * Get the max amplitude from an audio buffer
   *
   * @param input an audio buffer
   * @return value of max amplitude
   */
  public static float maxAmplitude(float[][] input)
  {
    float max_amplitude = 0.0f;
    float amplitude_left;
    float amplitude_right;

    for (int i = 0; i < input[0].length; i++)
    {
      amplitude_left = abs(input[0][i]);
      amplitude_right = abs(input[1][i]);
      max_amplitude = max3(amplitude_left, amplitude_right, max_amplitude);
    }

    return max_amplitude;
  }

  /**
   * Returns the most you can multiply a sample by without exceeding 1.0, which
   * is the max value of vst audio data
   *
   * @param input an audio buffer
   * @return the value of the maximum multiplier
   */
  public static float maxMultiplier(float[][] input)
  {
    float max_amplitude = maxAmplitude(input);
    return 1 / max_amplitude;
  }

  /**
   * Calculate the smaller of the two input values.
   *
   * @param a any integer value.
   * @param b any integer value.
   * @return the smaller of {@code a} and {@code b}.
   */
  public static int min(int a, int b)
  {
    return (a <= b) ? a : b;
  }

  /**
   * Calculate the smaller of the two input values.
   *
   * @param a any long value.
   * @param b any long value.
   * @return the smaller of {@code a} and {@code b}.
   */
  public static long min(long a, long b)
  {
    return (a <= b) ? a : b;
  }

  /**
   * Calculate the smaller of the two input values.
   *
   * @param a an argument.
   * @param b another argument.
   * @return the smaller of {@code a} and {@code b}.
   */
  public static float min(float a, float b)
  {
    if (a != a)
      return a; // a is NaN
    if ((a == 0.0f) && (b == 0.0f)
        && (Float.floatToIntBits(b) == FLT_NEG_ZERO_BITS))
    {
      return b;
    }
    return (a <= b) ? a : b;
  }

  /**
   * Calculate the smaller of the two input values.
   *
   * @param a an argument.
   * @param b another argument.
   * @return the smaller of {@code a} and {@code b}.
   */
  public static double min(double a, double b)
  {
    if (a != a)
      return a; // a is NaN
    if ((a == 0.0d) && (b == 0.0d)
        && (Double.doubleToLongBits(b) == DBL_NEG_ZERO_BITS))
    {
      return b;
    }
    return (a <= b) ? a : b;
  }

  /**
   * Print the values in a stereo audio buffer to console
   *
   * @param input an audio buffer
   */
  public static void printStereoBuffer(float[][] input)
  {
    for (int i = 0; i < input[0].length; i++)
    {
      String l, r;
      if (input[0][i] < 0) l = "" + input[0][i];
      else l = " " + input[0][i];

      if (input[1][i] < 0) r = "" + input[1][i];
      else r = " " + input[1][i];
      System.out.println(l + "\t\t" + r);

    }
  }

  /**
   * Print the values in a stereo audio buffer to console
   * along with a nice little message.
   *
   * @param message user message before print
   * @param input   an audio buffer
   */
  public static void printStereoBuffer(String message, float[][] input)
  {
    System.out.println(message);

    for (int i = 0; i < input[0].length; i++)
    {
      String l, r;
      if (input[0][i] < 0) l = "" + input[0][i];
      else l = " " + input[0][i];

      if (input[1][i] < 0) r = "" + input[1][i];
      else r = " " + input[1][i];
      System.out.println(l + "\t\t" + r);

    }
  }

  /**
   * Generates a pseudorandom number with uniform distribution.
   *
   * @return random value x such that 0.0 <= x < 1.0
   */
  public static double random()
  {
    if (randomNumberGenerator == null)
      randomNumberGenerator = new Random();
    return randomNumberGenerator.nextDouble();
  }

  /**
   * Calculates the closest integer to the input value.
   *
   * @param a a floating-point value to be rounded to an integer.
   * @return the value of the argument rounded to the nearest {@code int} value.
   * @see Integer#MAX_VALUE
   * @see Integer#MIN_VALUE
   */
  public static int round(float a)
  {
    return (int) floor(a + 0.5f);
  }

  /**
   * Calculates the closest integer to the input value.
   *
   * @param a a floating-point value to be rounded to an integer.
   * @return the value of the argument rounded to the nearest {@code int} value.
   * @see Integer#MAX_VALUE
   * @see Integer#MIN_VALUE
   */
  public static int round(double a)
  {
    return (int) floor(a + 0.5f);
  }

  /**
   * Calculate the sine function of the input.
   *
   * @param a an angle, in radians.
   * @return the sine of the argument.
   */
  public static double sin(double a)
  {
    return StrictMath.sin(a); // default impl. delegates to StrictMath
  }

  /**
   * Calculate the hyperbolic sine function of the input.
   *
   * @param x The number whose hyperbolic cosine is to be returned.
   * @return The hyperbolic cosine of {@code x}.
   */
  public static double sinh(double x)
  {
    return StrictMath.sinh(x);
  }

  /**
   * Calculates the square root of the input.
   *
   * @param a a value.
   * @return the positive square root of a. If the argument is NaN or less than
   * zero, the result is NaN.
   */
  public static double sqrt(double a)
  {
    return StrictMath.sqrt(a);
  }

  /**
   * Calculate the tangent of the input value
   *
   * @param a an angle, in radians.
   * @return the tangent of the argument.
   */
  public static double tan(double a)
  {
    return StrictMath.tan(a); // default impl. delegates to StrictMath
  }

  /**
   * Calculate the hyperbolic tangent function of the input.
   *
   * @param x The number whose hyperbolic cosine is to be returned.
   * @return The hyperbolic cosine of {@code x}.
   */
  public static double tanh(double x)
  {
    return StrictMath.tanh(x);
  }

  /**
   * Converts an angle measured in radians to an approximately equivalent angle
   * measured in degrees. The conversion from radians to degrees is generally
   * inexact; users should not expect {@code cos(toRadians(90.0))} to exactly
   * equal 0.0.
   *
   * @param angrad an angle, in radians
   * @return the measurement of the angle {@code angrad} in degrees.
   */
  public static double toDegrees(double angrad)
  {
    return angrad * 180.0 / PI;
  }

  /**
   * Converts an angle measured in degrees to an approximately equivalent angle
   * measured in radians. The conversion from degrees to radians is generally
   * inexact.
   *
   * @param angdeg an angle, in degrees
   * @return the measurement of the angle {@code angdeg} in radians.
   */
  public static double toRadians(double angdeg)
  {
    return angdeg / 180.0 * PI;
  }

}
