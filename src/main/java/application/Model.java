package application;

import arduino.ArduinoSerial;

public class Model {
	private static ArduinoSerial arduino;
	private static boolean hasStart;
	private static Thread t;
	
	public static void initialize(){
		arduino=new ArduinoSerial();
		hasStart=false;
		
	}
	
	public static void start(){
		if(!hasStart){
			
			hasStart=true;
			t=new Thread() {
				public void run() {
					arduino.initialize();
					while(hasStart){
						
					}
					//the following line will keep this app alive for 1000 seconds,
					//waiting for events to occur and responding to them (printing incoming messages to console).
//					try {Thread.sleep(10000);} catch (InterruptedException ie) {}
				}
			};
			t.start();
			System.out.println("Started");
		}
	}
	
	public static void stop(){
		hasStart=false;
		arduino.close();
	}

}
