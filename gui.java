package egen310;

import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.*;
import com.fazecast.jSerialComm.*;

public class gui extends JFrame {
	
	  static float tilt = 0;
	  static float accel = 0;
	  static long startTime;
	  static double distance = 0;
	  static boolean initialized;
	  //arrays to hold 11 minutes worth of data
	  static float[][] tilts = new float[12][30];
	  static float[][] accels = new float[12][30];
	  static double[][] distances = new double[12][30];
	  
	public static void main(String[] args) {		
		//find computers serial ports
		SerialPort ports[] = SerialPort.getCommPorts();
		int k = 1;
		System.out.println("Select a port");
		for(SerialPort p : ports) {
			System.out.println(k++ + ". " + p.getSystemPortName());
		}
		Scanner s = new Scanner(System.in);
		int userport = s.nextInt();
		//setup port and connect
		SerialPort port = ports[userport -1];
		if(port.openPort()) {
			System.out.println("connected to port " + port.getSystemPortName());
		}
		else {
			System.out.println("did not connect");
		}
		//never timeout
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		//get input from arduino
		Scanner arduino = new Scanner(port.getInputStream());
		//set start time
		startTime = System.currentTimeMillis();
		gui g = new gui();

		int i = 0;
		int j = 0;
		while(arduino.hasNextLine()) {
			float t = Float.parseFloat(arduino.nextLine());
			tilt = (float) Math.sqrt(t);
			float a = Float.parseFloat(arduino.nextLine());
			accel = (float) Math.sqrt(a);
			distance += gui.getDistance();
			//System.out.println("Tilt: " + tilt + "  Accel: " + accel + "di" + distance);
			//System.out.println(tilts[i][j]);
			tilts[i][j] = tilt;
			accels[i][j] = accel;
			distances[i][j] = distance;
			if(j == 29) {
				j = 0;
				i++;
			}
			else if(j < 29) {
				j++;
			}
			if(i == 12) {
				break;
			}
			//repaint jframe with updated info
			g.repaint();
		}
	}
	
	public gui() {
	    setTitle("EGEN 310 Vehicle");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container container = getContentPane();
	    container.add(MainPanel.getInstance());
	    setSize(Toolkit.getDefaultToolkit().getScreenSize());
	    setVisible(true);
	  }
	  
	//get distance traveled
	//returns a double
	  public static double getDistance() {
		  long time = 2;
		  double dist = (.5) * accel * time * time;
		  return dist;
	  }
	  
	  public void paint(Graphics g){
		  GuiPanel.getInstance().updateInfo();
	  }

}

