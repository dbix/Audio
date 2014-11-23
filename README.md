Audio
=====

&lt;html xmlns="http://www.w3.org/1999/xhtml">
&lt;head>
&lt;title>Audio-and-Signal-Processing-Library&lt;/title>
&lt;/head>
&lt;body>
&lt;pre>
My DSP library that I've built so far after working with jVstwRapper. There are currently three classes: DSP.Math, DSP.Filter, and DSP.Volume. 

The DSP.Filter class contains the following filters:
  DSP.Filter.lowpassFilter,
  DSP.Filter.highpassFilter,
  DSP.Filter.resonatorFilter, and
  DSP.Filter.bandpassFilter.

There are a few additions in DSP.Math but it is mostly just a wrapper for java.lang.StrictMath:
  DSP.Math.Math
  DSP.Math.abs
  DSP.Math.acos
  DSP.Math.asin
  DSP.Math.atan
  DSP.Math.cbrt
  DSP.Math.ceil
  DSP.Math.copySign
  DSP.Math.cos
  DSP.Math.cosh
  DSP.Math.exp
  DSP.Math.floor
  DSP.Math.getExponent
  DSP.Math.hypot
  DSP.Math.IEEEremainder
  DSP.Math.log
  DSP.Math.max
  DSP.Math.max3
  DSP.Math.maxAmplitude
  DSP.Math.maxMultiplier
  DSP.Math.min
  DSP.Math.printStereoBuffer
  DSP.Math.random
  DSP.Math.round
  DSP.Math.sin
  DSP.Math.sinh
  DSP.Math.sqrt
  DSP.Math.tan
  DSP.Math.tanh
  DSP.Math.toDegrees
  DSP.Math.toRadians
  DSP.Math.E
  DSP.Math.negativeZeroDoubleBits
  DSP.Math.negativeZeroFloatBits
  DSP.Math.PI
  DSP.Math.randomNumberGenerator.

The DSP.Volume class currently contains the following methods:
  DSP.Volume.normalize
  DSP.Volume.scale
  
Last edited 11/20/2014.
&lt;/pre>
&lt;/body>
