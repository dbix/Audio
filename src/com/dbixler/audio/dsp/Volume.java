package com.dbixler.audio.dsp;


import com.dbixler.audio.mathlib.DSPMath;

/**
 * Utility for altering the volume of
 * an audio buffer in different ways.
 * <p/>
 * Created by dbixler on 11/20/2014.
 */
public class Volume {

  /**
   * Returns a scaled input buffer with a maximum amplitude of 1
   *
   * @param input source audio
   * @return normalized version of source audio
   */
  public static float[][] normalize(float[][] input) {
    float scale = DSPMath.maxMultiplier(input);

    for (int i = 0; i < input[0].length; i++) {
      input[0][i] = input[0][i] * scale;
      input[1][i] = input[1][i] * scale;
    }

    return input;
  }

  /**
   * Returns a scaled input buffer with a maximum amplitude of 1
   *
   * @param scale source audio
   * @return normalized version of source audio
   */
  public static float[][] scale(float[][] input, float scale) {
    for (int i = 0; i < input[0].length; i++) {
      input[Channel.LEFT][i] = input[Channel.LEFT][i] * scale;
      input[Channel.RIGHT][i] = input[Channel.RIGHT][i] * scale;
    }

    return input;
  }
}
