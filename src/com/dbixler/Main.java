package com.dbixler;

import com.dbixler.audio.dsp.Filter;
import com.dbixler.audio.dsp.Volume;
import com.dbixler.audio.mathlib.DSPMath;

public class Main {

  public static void main(String[] args) {
    float[][] audio_buffer;
    float max_amplitude;
    float max_multiplier;

    // Test max amplitude
    System.out.println("Testing dsp.Math.maxAmplitude");
    audio_buffer = new float[][]{{1.0f, -1.0f, 1.0f, -1.1f}, {1.0f, -1.0f, 1.0f, -1.0f}};
    max_amplitude = DSPMath.maxAmplitude(audio_buffer);
    System.out.println("This value should be 1.1:\t\t" + max_amplitude);
    audio_buffer = new float[][]{{-0.5f, -0.5f, -0.51f, -0.5f}, {-0.5f, -0.5f, -0.5f, -0.5f}};
    max_amplitude = DSPMath.maxAmplitude(audio_buffer);
    System.out.println("This value should be 0.51:\t" + max_amplitude);
    System.out.println();

    // Test max multiplier
    System.out.println("Testing dsp.Math.maxMultiplier");
    audio_buffer = new float[][]{{1.0f, -1.0f, 1.0f, -1.0f}, {1.0f, -1.0f, 1.0f, -1.0f}};
    max_multiplier = DSPMath.maxMultiplier(audio_buffer);
    System.out.println("This value should be 1: " + max_multiplier);
    audio_buffer = new float[][]{{-0.5f, -0.5f, -0.5f, -0.5f}, {-0.5f, -0.5f, -0.5f, -0.5f}};
    max_multiplier = DSPMath.maxMultiplier(audio_buffer);
    System.out.println("This value should be 2: " + max_multiplier);
    System.out.println();

    DSPMath.tan(5.0);

    // Test normalization
    System.out.println("Testing dsp.Volume.normalize");
    audio_buffer = new float[][]{{1.0f, 2.0f, 3.0f, 4.0f}, {1.0f, 2.0f, 3.0f, 4.0f}};
    System.out.println("Pre-normalization");
    DSPMath.printStereoBuffer(audio_buffer);
    audio_buffer = Volume.normalize(audio_buffer);     // Normalize
    System.out.println("Post-normalization");
    DSPMath.printStereoBuffer(audio_buffer);
    audio_buffer = new float[][]{{0.1f, -0.1f, -0.5f, 0.1f}, {0.2f, -0.2f, -0.7f, 0.1f}};
    System.out.println("Pre-normalization");
    DSPMath.printStereoBuffer(audio_buffer);
    audio_buffer = Volume.normalize(audio_buffer);     // Normalize
    System.out.println("Post-normalization");
    DSPMath.printStereoBuffer(audio_buffer);
    System.out.println();

    // Test lowpass filter
    System.out.println("Testing dsp.Filter.lowpassFilter");
    audio_buffer = new float[][]{{0.1f, -0.1f, -0.5f, 0.1f}, {0.2f, -0.2f, -0.7f, 0.1f}};
    System.out.println("Pre-lowpass");
    DSPMath.printStereoBuffer(audio_buffer);
    audio_buffer = Filter.lowpassFilter(audio_buffer, 1000, new float[]{1, 1}, 44100);
    System.out.println("Post-lowpass");
    DSPMath.printStereoBuffer(audio_buffer);
    System.out.println();


    // Test highpass filter
    System.out.println("Testing dsp.Filter.highpassFilter");
    audio_buffer = new float[][]{{0.1f, -0.1f, -0.5f, 0.1f}, {0.2f, -0.2f, -0.7f, 0.1f}};
    System.out.println("Pre-highpass");
    DSPMath.printStereoBuffer(audio_buffer);
    audio_buffer = Filter.highpassFilter(audio_buffer, 1000, new float[]{1, 1}, 44100);
    System.out.println("Post-highpass");
    DSPMath.printStereoBuffer(audio_buffer);
    System.out.println();

    // Test resonator filter
    System.out.println("Testing dsp.Filter.resonatorFilter");
    audio_buffer = new float[][]{{1f, -1f, 1f, -1f}, {1f, -1f, 1f, -1f}};
    System.out.println("Pre-resonatorFilter");
    DSPMath.printStereoBuffer(audio_buffer);
    audio_buffer = Filter.resonatorFilter(audio_buffer, 1000, new float[][]{{1, 1}, {1, 1}}, 50, 44100);
    audio_buffer = Volume.normalize(audio_buffer);
    System.out.println("Post-resonatorFilter");
    DSPMath.printStereoBuffer(audio_buffer);
    System.out.println();

    // Test bandpass filter
    System.out.println("Testing dsp.Filter.resonatorFilter");
    audio_buffer = new float[][]{{1f, -1f, 1f, -1f}, {1f, -1f, 1f, -1f}};
    System.out.println("Pre-bandpassFilter");
    DSPMath.printStereoBuffer(audio_buffer);
    audio_buffer = Filter.bandpassFilter(audio_buffer, 1000, new float[][]{{1, 1}, {1, 1}}, 50, 44100);
    audio_buffer = Volume.normalize(audio_buffer);
    System.out.println("Post-bandpassFilter");
    DSPMath.printStereoBuffer(audio_buffer);
    System.out.println();
  }

}

