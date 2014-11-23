package com.dbixler.audio.exception;

/**
 * Exception that is thrown when illegal operations are applied
 * to matrices from com.dbixler.audio.mathlib.Matrix
 * <p/>
 * Created by dbixler on 11/23/14.
 */
public class MatrixDimensionsException extends Exception {
  public MatrixDimensionsException() {
    super();
  }

  public MatrixDimensionsException(String message) {
    super(message);
  }

  public MatrixDimensionsException(String message, Throwable cause) {
    super(message, cause);
  }

  public MatrixDimensionsException(Throwable cause) {
    super(cause);
  }

  protected MatrixDimensionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
