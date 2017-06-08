package org.sing;

import java.util.Arrays;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class TestSensor {
	public static void main(String[] args) {
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
		colorSensor.setFloodlight(false);
		
		
		float[] samples = new float[3];
		SensorMode sensorMode = colorSensor.getRGBMode();
		
		sensorMode.fetchSample(samples, 0);
		
		int colorID = colorSensor.getColorID();
		MatchboxColor colorString;
		
		switch (colorID) {
		case 7:
			colorString = MatchboxColor.BLACK;
			break;
		case 2:
			colorString = MatchboxColor.BLUE;
			break;
		case 1:
			colorString = MatchboxColor.GREEN;
			break;
		case 3:
			colorString = MatchboxColor.YELLOW;
			break;
		case 0:
			colorString = MatchboxColor.RED;
			break;
		case 6:
			colorString = MatchboxColor.WHITE;
			break;
		case 13:
			colorString = MatchboxColor.BROWN;
			break;
		default:
			colorString = MatchboxColor.NO;
			break;
		}
		
		System.out.println(colorString.toString());
		for(int i = 0; i < 3; i++) {
			System.out.printf("%.5f \n", samples[i]);
		}
		
		Delay.msDelay(5000);
		colorSensor.close();
	}
}
