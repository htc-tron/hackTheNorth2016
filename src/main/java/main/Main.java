package main;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.github.sarxos.webcam.Webcam;

import arduino.ArduinoSerial;
import imageSearching.DetectImage;

public class Main {

	public static void main(String[] args) {
		
		
		Webcam webcam = Webcam.getDefault();
		ArduinoSerial main = new ArduinoSerial();
		webcam.open();
		
		main.initialize();
		
	}
}
