package egen310;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPanel extends JPanel implements ActionListener{

	private static InfoPanel instance;
	
	public InfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton info = new JButton("Get Info");
		info.addActionListener(this);
		add(info);
	}
	
	//return static instance of InfoPanel
	public static JPanel getInstance() {
		if(instance == null) {
			instance = new InfoPanel();
		}
		return instance;
	}
	
	public static void printInfo() {
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 30; j++) {
				System.out.print(gui.tilts[i][j] + " ");
			}
			System.out.println();
		}

	}
	
	@Override
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        switch (command) {
            case "Get Info": 
            int seconds = (int)ButtonPanel.endTime / 1000;
            int minutes = seconds / 60;
            Integer[] startMinuteOptions = new Integer[12];
            Integer[] startSecondOptions = new Integer[30];
            for(int i = 0; i <= minutes; i++) {
            		startMinuteOptions[i] = i;
            	 }
            Integer k = 0;
            for(int j = 0; j< startSecondOptions.length; j++) {
            		startSecondOptions[j] = k;
            		k+=2;
            }
            JComboBox<Integer> startminutes= new JComboBox<>(startMinuteOptions);
            JComboBox<Integer> startseconds = new JComboBox<>(startSecondOptions);
            JComboBox<Integer> endminutes = new JComboBox<>(startMinuteOptions);
            JComboBox<Integer> endseconds = new JComboBox<>(startSecondOptions);
            
            JOptionPane.showInputDialog(null, startminutes, "Select Start Time between 0 and " + minutes + " minutes " +  seconds%60 + " seconds");
            JOptionPane.showInputDialog(null, startseconds, "Select Start time between 0 and " + minutes + " minutes " +  seconds%60 + " seconds");
            JOptionPane.showInputDialog(null, endminutes, "Select End Time between " + startminutes.getSelectedItem() + " minutes " + startseconds.getSelectedItem() + " seconds and " + minutes + " minutes " +  seconds%60 + " seconds");
            JOptionPane.showInputDialog(null, endseconds, "Select End Time between " + startminutes.getSelectedItem() + " minutes " + startseconds.getSelectedItem() + " seconds and " + minutes + " minutes " +  seconds%60 + " seconds");
            
            //System.out.println(ButtonPanel.endTime);
            System.out.println("Tilt Ave: " + getTiltAve(startminutes.getSelectedIndex(), startseconds.getSelectedIndex(), endminutes.getSelectedIndex(), endseconds.getSelectedIndex()));
            System.out.println("Accel Ave: " + getAccelAve(startminutes.getSelectedIndex(), startseconds.getSelectedIndex(), endminutes.getSelectedIndex(), endseconds.getSelectedIndex()));
            //System.out.println("Distance Ave: " + getDistanceAve(startminutes.getSelectedIndex(), startseconds.getSelectedIndex(), endminutes.getSelectedIndex(), endseconds.getSelectedIndex()));
            System.out.println("Max tilt: " + getMaxTilt(endminutes.getSelectedIndex(), endseconds.getSelectedIndex()));
            System.out.println("Min tilt: " + getMinTilt(endminutes.getSelectedIndex(), endseconds.getSelectedIndex()));
            System.out.println("Time Elapsed: " + minutes + " minutes " + seconds%60 + " seconds");
            System.out.println("Distance Traveled: " + getDistanceTraveled());
            break;
        }
        
        
    }
	
	//method to find the average tilt during a period of time
	//takes in 4 ints as parameters
	//returns a float
	public float getTiltAve(int startm, int starts, int endm, int ends) {
		float tiltave = 0;
		int num = 0;
		for(int i = startm; i<=endm; i++) {
			for(int j = starts; j <= ends; j++) {
				tiltave += gui.tilts[i][j];
				num++;
			}
		}
		return tiltave/num;
	}
	
	//method to find the average acceleration during a period of time
	//takes in 4 ints as parameters
	//returns a float
	public float getAccelAve(int startm, int starts, int endm, int ends) {
		float accelave = 0;
		int num = 0;
		for(int i = startm; i<=endm; i++) {
			for(int j = starts; j <= ends; j++) {
				accelave += gui.accels[i][j];
				num++;
			}
		}
		return accelave/num;
	}
	
	//method to find the average distance traveled during a period of time
	//takes in 4 ints as parameters
	//returns a double
	public double getDistanceAve(int startm, int starts, int endm, int ends) {
		double distave = 0;
		int num = 0;
		for(int i = startm; i<=endm; i++) {
			for(int j = starts; j <= ends; j++) {
				distave += gui.distances[i][j];
				num++;
			}
		}
		return distave/num;
	}
	
	//method to find the maximum tilt
	//takes in 2 ints as parameters
	//returns a float
	public static float getMaxTilt(int endm, int ends) {
		float max = 0;
		for(int i = 0; i <=endm; i++) {
			for(int j = 0; j<=ends; j++) {
				if(gui.tilts[i][j] > max) {
					max = gui.tilts[i][j];
				}
			}
		}
		return max;
	}
  
	//method to find the minimum tilt
	//takes in 2 ints as parameters
	//returns a float
	public static float getMinTilt(int endm, int ends) {
		float min = 180;
		for(int i = 0; i <=endm; i++) {
			for(int j = 0; j<=ends; j++) {
				if(gui.tilts[i][j] < min) {
					min = gui.tilts[i][j];
				}
			}
		}
		return min;
	}
	
	//method to find the total distance traveled
	//returns a double
	public static double getDistanceTraveled() {
		double max = 0;
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j< 30; j++) {
				if(gui.distances[i][j] > max) {
					max = gui.distances[i][j];
				}
			}
		}
		return max;
	}
	
}
