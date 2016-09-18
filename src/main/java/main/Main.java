package main;



import com.github.sarxos.webcam.Webcam;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import arduino.ArduinoSerial;
import javazoom.jl.decoder.JavaLayerException;

public class Main {

	public static void main(String[] args) throws JavaLayerException {
		
		
		Webcam webcam = Webcam.getDefault();
		ArduinoSerial main = new ArduinoSerial();
		webcam.open();
		
		main.initialize();
		 VoiceManager vm = VoiceManager.getInstance();
	        Voice voice = vm.getVoice("kevin16");
	 
	        voice.allocate();
	 
		
	}
}
