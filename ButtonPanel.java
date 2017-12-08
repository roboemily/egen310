package egen310;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener{
	private static ButtonPanel instance;
	public static long startTime = gui.startTime;
	public static long endTime;
	
	public ButtonPanel() {
		JButton stop = new JButton("Stop");
		JButton start = new JButton("Start");
		stop.addActionListener(this);
		start.addActionListener(this);
		add(start);
		add(stop);

		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 100);
	}
	
	//returns a static instance of ButtonPanel
	public static JPanel getInstance() {
		if(instance == null) {
			instance = new ButtonPanel();
		}
		return instance;
	}
	
	@Override
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        switch (command) {
            case "Start": 
            break;
            
            case "Stop": endTime = System.currentTimeMillis() - startTime;
            RunPanel.nextPanel();
            //InfoPanel.printInfo();
            break;
        }
    }
}
