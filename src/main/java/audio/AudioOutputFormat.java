package audio;
/// <summary>
/// Voice output formats.
/// </summary>
public class AudioOutputFormat {

      /// <summary>
      /// raw-8khz-8bit-mono-mulaw request output audio format type.
      /// </summary>
      public static final String Raw8Khz8BitMonoMULaw = "raw-8khz-8bit-mono-mulaw";
      /// <summary>
      /// raw-16khz-16bit-mono-pcm request output audio format type.
      /// </summary>
      public static final String Raw16Khz16BitMonoPcm = "raw-16khz-16bit-mono-pcm";
      /// <summary>
      /// riff-8khz-8bit-mono-mulaw request output audio format type.
      /// </summary>
      public static final String Riff8Khz8BitMonoMULaw = "riff-16khz-16bit-mono-pcm";
      /// <summary>
      /// riff-16khz-16bit-mono-pcm request output audio format type.
      /// </summary>
      public static final String Riff16Khz16BitMonoPcm = "riff-16khz-16bit-mono-pcm";
      /// <summary>
      /// ssml-16khz-16bit-mono-silk request output audio format type.
      /// It is a SSML with audio segment, with audio compressed by SILK codec
      /// </summary>
      public static final String Ssml16Khz16BitMonoSilk = "ssml-16khz-16bit-mono-silk";
      /// <summary>
      /// ssml-16khz-16bit-mono-tts request output audio format type.
      /// It is a SSML with audio segment, and it needs tts engine to play out
      /// </summary>
      public static final String Ssml16Khz16BitMonoTts = "ssml-16khz-16bit-mono-tts";
}