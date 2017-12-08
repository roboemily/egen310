package egen310;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private static MainPanel instance;
	
	public MainPanel() {
		//set panels to appear in order
		setLayout(new CardLayout());
		add(RunPanel.getInstance());
		add(InfoPanel.getInstance());
		
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.first(this);
		
	}
	
	//returns a static instance of MainPanel
	public static JPanel getInstance() {
		if(instance == null) {
			instance = new MainPanel();
		}
		return instance;
	}
}
