package com.dbixler.audio.fileio;

/**
 * JNI for portsf
 * <p/>
 * Created by dbixler on 11/21/14.
 */
final class Portsf {

  static {
    System.loadLibrary("Portsf");
  }

  /**
   * enum
   */
  public static final int PSF_DITHER_OFF = 0;
  public static final int PSF_DITHER_ON = 1;
  /**
   * error codes
   */
  public static final int PSF_E_NOERROR = 0;
  public static final int PSF_E_CANT_OPEN = -1;
  public static final int PSF_E_CANT_CLOSE = -2;
  public static final int PSF_E_CANT_WRITE = -3;
  public static final int PSF_E_CANT_READ = -4;
  public static final int PSF_E_NOT_WAVE = -5;
  public static final int PSF_E_BAD_TYPE = -6;
  public static final int PSF_E_BAD_FORMAT = -7;
  public static final int PSF_E_UNSUPPORTED = -8;
  public static final int PSF_E_NOMEM = -9;
  public static final int PSF_E_BADARG = -10;
  public static final int PSF_E_CANT_SEEK = -11;
  public static final int PSF_E_TOOMANYFILES = -12;
  public static final int PSF_E_FILE_READONLY = -13;
  public static final int PSF_E_SEEK_BEYOND_EOF = -14;
  /**
   * speaker configurations
   */
  public static final int NUM_SPEAKER_POSITIONS = 18;
  public static final int SPEAKER_FRONT_LEFT = 0x1;
  public static final int SPEAKER_FRONT_RIGHT = 0x2;
  public static final int SPEAKER_FRONT_CENTER = 0x4;
  public static final int SPEAKER_LOW_FREQUENCY = 0x8;
  public static final int SPEAKER_BACK_LEFT = 0x10;
  public static final int SPEAKER_BACK_RIGHT = 0x20;
  public static final int SPEAKER_FRONT_LEFT_OF_CENTER = 0x40;
  public static final int SPEAKER_FRONT_RIGHT_OF_CENTER = 0x80;
  public static final int SPEAKER_BACK_CENTER = 0x100;
  public static final int SPEAKER_SIDE_LEFT = 0x200;
  public static final int SPEAKER_SIDE_RIGHT = 0x400;
  public static final int SPEAKER_TOP_CENTER = 0x800;
  public static final int SPEAKER_TOP_FRONT_LEFT = 0x1000;
  public static final int SPEAKER_TOP_FRONT_CENTER = 0x2000;
  public static final int SPEAKER_TOP_FRONT_RIGHT = 0x4000;
  public static final int SPEAKER_TOP_BACK_LEFT = 0x8000;
  public static final int SPEAKER_TOP_BACK_CENTER = 0x10000;
  public static final int SPEAKER_TOP_BACK_RIGHT = 0x20000;
  public static final int SPEAKER_RESERVED = 0x80000000;
  public static final int SPEAKERS_UNASSIGNED = 0x0;
  public static final int SPEAKERS_MONO = 0x00000040;
  public static final int SPEAKERS_STEREO = 0x00000003;
  public static final int SPEAKERS_GENERIC_QUAD = 0x00000033;
  public static final int SPEAKERS_SURROUND_LCRS = 0x00000107;
  public static final int SPEAKERS_SURR_5_0 = 0x00000037;
  public static final int SPEAKERS_DOLBY5_1 = 0x0000003f;
  public static final int SPEAKERS_SURR_7_1 = 0x0000007f;
  public static final int SPEAKERS_ACCEPT_ALL = 0xfffffff;

  public static native int psf_init();

  public static native int psf_finish();

  public static native int psf_sndCreate(String path, psf_props props, int clip_floats, int minheader, int mode);

  public static native int psf_sndOpen(String path, psf_props props, int rescale);

  public static native int psf_sndClose(int sfd);

  public static native int psf_sndSize(int sfd);

  public static native int psf_sndWriteFloatFrames(int sfd, float[] buf, DWORD nFrames);

  public static native int psf_sndWriteDoubleFrames(int sfd, double[] buf, DWORD nFrames);

  public static native int psf_sndWriteShortFrames(int sfd, short[] buf, DWORD nFrames);

  public static native int psf_sndTell(int sfd);

  public static native int psf_sndSeek(int sfd, int offset, int seekmode);

  public static native int psf_sndReadFloatFrames(int sfd, float[] buf, DWORD nFrames);

  public static native int psf_sndReadDoubleFrames(int sfd, double[] buf, DWORD nFrames);

  public static native int psf_sndReadPeaks(int sfd, psf_chpeak peakdata[], long peaktime);

  public static native psf_format psf_getFormatExt(String path);

  public static native int psf_sndSetDither(int sfd, int dtype);

  public static native int psf_sndGetDither(int sfd);

  public static native int psf_speakermask(int sfd);

  public static native psf_channelformat get_speakerlayout(DWORD chmask, DWORD chans);

  public static void main(String args[]) {
    System.loadLibrary("Portsf");
  }

  static final class DWORD {
    public static long value;
  }

  static final class WORD {
    public static short value;
  }

  /**
   * typedef enum psf_format
   */
  static final class psf_format {
    public static final int PSF_FMT_UNKNOWN = 0;
    public static final int PSF_STDWAVE = 1;
    public static final int PSF_WAVE_EX = 2;
    public static final int PSF_AIFF = 3;
    public static final int PSF_AIFC = 4;
  }

  /**
   * typedef enum psf_stype
   */
  static final class psf_stype {
    public static final int PSF_SAMP_UNKNOWN = 0;
    public static final int PSF_SAMP_8 = 1;
    public static final int PSF_SAMP_16 = 2;
    public static final int PSF_SAMP_24 = 3;
    public static final int PSF_SAMP_32 = 4;
    public static final int PSF_SAMP_IEEE_FLOAT = 5;
  }

  /**
   * typedef struct psf_chpeak
   */
  static final class psf_chpeak {
    public float val;
    public long pos;
  }

  /**
   * typedef enum psf_create_mode
   */
  static final class psf_create_mode {
    public static final int PSF_CREATE_RDWR = 0;
    public static final int PSF_CREATE_TEMPORARY = 1;
    public static final int PSF_CREATE_WRONGLY = 2;
  }

  /**
   * typedef enum psf_channelformat
   */
  static final class psf_channelformat {
    public static final int STDWAVE = 0;
    public static final int MC_STD = 1;
    public static final int MC_MONO = 2;
    public static final int MC_STEREO = 3;
    public static final int MC_QUAD = 4;
    public static final int MC_LCRS = 5;
    public static final int MC_BFMT = 6;
    public static final int MC_DOLBY_5_1 = 7;
    public static final int MC_SURR_5_0 = 8;
    public static final int MC_SURR_7_1 = 9;
    public static final int MC_WAVE_EX = 10;
  }

  /**
   * typedef struct psf_props
   */
  static final class psf_props {
    public int srate;
    public int chans;
    public psf_stype samptype;
    public psf_format format;
    public psf_channelformat chformat;
  }

}
