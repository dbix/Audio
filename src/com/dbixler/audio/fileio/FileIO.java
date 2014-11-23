package com.dbixler.audio.fileio;

import java.io.File;

/**
 * A file input/output class specifically
 * for audio files.
 *
 * Created by dbixler on 11/19/2014.
 */
public class FileIO {

  /*
   * TODO:
   * addWavHeader(File f)
   * checkWavHeader(File f)
   * readPcm(String file_path)
   * readWav(String file_path)
   * stripWavHeader(File f)
   */

  public static void addWavHeader(File f)
  {

  }

  public static void checkWavHeader(File f)
  {

  }

  public static float[][] readPcm(String file_path)
  {
    float[][] pcm = null;
    return pcm;
  }

  public static float[][] readWav(String file_path)
  {
    float[][] wav = null;
    return wav;
  }

  public static void stripWavHeader(File f)
  {

  }

  public static boolean writePcm(float[][] stereoPCM)
  {
    return false;
  }

  public static boolean writeWav(float[][] stereoPCM)
  {
    return false;
  }

}
