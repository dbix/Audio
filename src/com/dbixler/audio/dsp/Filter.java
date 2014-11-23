package com.dbixler.audio.dsp;

import com.dbixler.audio.mathlib.DSPMath;

/**
 * A filterbank with the following:
 * lowpass filter    (first-order recursive)
 * highpass filter   (first-order recursive)
 * bandpass filter   (second-order recursive)
 * resonator filter  (second-order recursive)
 * <p/>
 * Created by dbixler on 11/20/2014.
 */

public class Filter {

  /**
   * Resonator filter that uses one-sample and two-sample feedback delays.
   * It is based on the equation
   * y(n) = x(n) - b1*y(n-1) - b2*y(n-2),
   * R = 1 - pi*(BW/sr),
   * b1 = -[4*R^2 / (1 + R^2)] * cos(2*PI*f / sr), and
   * b2 = R^2.
   * With sharp resonances it is possible that the filter will cause clipping.
   * A scaling factor is applied to the input signal to avoid any clipping.
   *
   * @param input       the audio to be processed.
   * @param frequency   the center frequency in hertz.
   * @param delay       a 2x2 array that holds delay values
   *                    for the left and right channels.
   * @param bandwidth   the width of the filter in hertz.
   * @param sample_rate sample rate in hertz.
   * @return filtered version of input at specified
   * frequency using the specified feedback delay.
   */
  public static float[][] bandpassFilter(float[][] input, float frequency,
                                         float[][] delay, float bandwidth,
                                         float sample_rate) {
    double r;
    double rsq;
    double rr;
    double cos_theta;
    double scale;
    double w[] = new double[2];

    rr = 2 * (r = 1.0d - DSPMath.PI * (bandwidth / sample_rate));
    rsq = r * r;
    cos_theta = (rr / (1.0d + rsq)) * DSPMath.cos(2 * DSPMath.PI * frequency / sample_rate);
    scale = 1 - r;

    for (int i = 0; i < input[0].length; i++) {
      w[Channel.LEFT] = scale * input[Channel.LEFT][i] + rr * cos_theta * delay[Channel.LEFT][0] - rsq * delay[Channel.LEFT][1];
      delay[Channel.LEFT][1] = delay[Channel.LEFT][0];
      delay[Channel.LEFT][0] = (float) w[Channel.LEFT];
      input[Channel.LEFT][i] = (float) (w[Channel.LEFT] - r * delay[Channel.LEFT][1]);

      w[Channel.RIGHT] = scale * input[Channel.RIGHT][i] + rr * cos_theta * delay[Channel.RIGHT][0] - rsq * delay[Channel.RIGHT][1];
      input[Channel.RIGHT][i] = (float) (w[Channel.RIGHT] - r * delay[Channel.RIGHT][1]);
      delay[Channel.RIGHT][1] = delay[Channel.RIGHT][0];
      delay[Channel.RIGHT][0] = (float) w[Channel.RIGHT];
    }

    return input;
  }

  /**
   * First-order recursive (or feedback) highpass filter.
   * First-order means that the samples are delayed by one frame.
   * This algorithm is based the the following equation:
   * y(n) = a*x(n) - b*y*(n-1), where
   * a = 1 + b,
   * b = sqrt([2 - cos(2*PI*f / sr)]^2 - 1) - 2 + cos(2*PI*f / sr),
   * f = filter cutoff frequency, and
   * sr = sample rate.
   *
   * @param input       the audio to be processed.
   * @param frequency   cutoff frequency in hertz.
   * @param delay       an array of size 2 that holds delay values.
   *                    for the left and right channels.
   * @param sample_rate sample rate in hertz.
   * @return filtered version of input at specified
   * frequency using the specified feedback delay.
   */
  public static float[][] highpassFilter(float[][] input, float frequency, float[] delay, float sample_rate) {
    double cos_theta;
    double coefficient;

    cos_theta = 2.0 - DSPMath.cos(2 * DSPMath.PI * frequency / sample_rate);
    coefficient = cos_theta - DSPMath.sqrt(cos_theta * cos_theta - 1.0);

    for (int i = 0; i < input[0].length; i++) {
      input[Channel.LEFT][i] = (float) (input[Channel.LEFT][i] * (1 - coefficient) - delay[Channel.LEFT] * coefficient);
      delay[Channel.LEFT] = input[Channel.LEFT][i];

      input[Channel.RIGHT][i] = (float) (input[Channel.RIGHT][i] * (1 - coefficient) - delay[Channel.RIGHT] * coefficient);
      delay[Channel.RIGHT] = input[Channel.RIGHT][i];
    }

    return input;
  }

  /**
   * First-order recursive (or feedback) lowpass filter.
   * First-order means that the samples are delayed by one frame.
   * This algorithm is based the the following equation:
   * y(n) = a*x(n) - b*y*(n-1), where
   * a=1+b,
   * b=sqrt([2 - cos(2*PI*f / sr)]^2 - 1) - 2 + cos(2pif / sr),
   * f = filter cutoff frequency, and
   * sr = sample rate.
   *
   * @param input       the audio to be processed.
   * @param frequency   cutoff frequency in hertz.
   * @param delay       an array of size 2 that holds delay values for the left and right channels.
   * @param sample_rate sample rate in hertz.
   * @return filtered version of input at specified frequency.
   */
  public static float[][] lowpassFilter(float[][] input, float frequency, float[] delay, float sample_rate) {
    double cos_theta;
    double coefficient;

    cos_theta = 2.0 - DSPMath.cos(2 * DSPMath.PI * frequency / sample_rate);
    coefficient = DSPMath.sqrt(cos_theta * cos_theta - 1.0) - cos_theta;

    for (int i = 0; i < input[0].length; i++) {
      input[Channel.LEFT][i] = (float) (input[Channel.LEFT][i] * (1 + coefficient) - delay[Channel.LEFT] * coefficient);
      delay[Channel.LEFT] = input[Channel.LEFT][i];

      input[Channel.RIGHT][i] = (float) (input[Channel.RIGHT][i] * (1 + coefficient) - delay[Channel.RIGHT] * coefficient);
      delay[Channel.RIGHT] = input[Channel.RIGHT][i];
    }

    return input;
  }

  /**
   * Resonator filter that uses one-sample and two-sample feedback delays.
   * It is based on the equation
   * y(n) = x(n) - b1*y(n-1) - b2*y(n-2),
   * R = 1 - pi*(BW/sr),
   * b1 = -[4*R^2 / (1 + R^2)] * cos(2*PI*f / sr), and
   * b2 = R^2.
   * With sharp resonances it is possible that the filter will cause clipping.
   * A scaling factor is applied to the input signal to avoid any clipping.
   *
   * @param input       the audio to be processed.
   * @param frequency   the center frequency in hertz.
   * @param delay       a 2x2 array that holds delay values
   *                    for the left and right channels.
   * @param bandwidth   the width of the filter in hertz.
   * @param sample_rate sample rate in hertz.
   * @return filtered version of input at specified
   * frequency using the specified feedback delay.
   */
  public static float[][] resonatorFilter(float[][] input, float frequency,
                                          float[][] delay, float bandwidth,
                                          float sample_rate) {

    double r;
    double rsq;
    double rr;
    double cos_theta;
    double scale;

    rr = 2 * (r = 1.0d - DSPMath.PI * (bandwidth / sample_rate));
    rsq = r * r;
    cos_theta = (rr / (1.0d + rsq)) * DSPMath.cos(2 * DSPMath.PI * frequency / sample_rate);
    scale = (1 - rsq) * DSPMath.sin(DSPMath.acos(cos_theta));

    for (int i = 0; i < input[0].length; i++) {
      input[Channel.LEFT][i] = (float) (input[Channel.LEFT][i] * scale + rr * cos_theta * delay[Channel.LEFT][0] - rsq * delay[Channel.LEFT][1]);
      delay[Channel.LEFT][1] = delay[Channel.LEFT][0];
      delay[Channel.LEFT][0] = input[Channel.LEFT][i];

      input[Channel.RIGHT][i] = (float) (input[Channel.RIGHT][i] * scale + rr * cos_theta * delay[Channel.RIGHT][0] - rsq * delay[Channel.RIGHT][1]);
      delay[Channel.RIGHT][1] = delay[Channel.RIGHT][0];
      delay[Channel.RIGHT][0] = input[Channel.RIGHT][i];
    }

    return input;
  }
}
