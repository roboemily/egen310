package egen310;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class RunPanel extends JPanel{
	private static RunPanel instance;

	public RunPanel() {
		GridLayout layout = new GridLayout(2, 1);
		setLayout(layout);
		add(GuiPanel.getInstance(), BorderLayout.NORTH);
		add(ButtonPanel.getInstance(), BorderLayout.SOUTH);
		
	    setVisible(true);
	}
	
	//returns a static instance of RunPanel
	public static JPanel getInstance() {
		if(instance == null) {
			instance = new RunPanel();
		}
		return instance;
	}
	
	//dispose of current panel and get next one
	public static void nextPanel() {
		CardLayout cl = (CardLayout) MainPanel.getInstance().getLayout();
		cl.next(MainPanel.getInstance());
	}
}
