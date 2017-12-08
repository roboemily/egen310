package egen310;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class GuiPanel extends JPanel{
	
	public static GuiPanel instance;


	public GuiPanel() {
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 100);
		this.setVisible(true);
	}
	
	//returns a static instance of GuiPanel
	public static GuiPanel getInstance()
    {
        if (instance == null)
        {
            instance = new GuiPanel();
        }
        return instance;
    }
	
	public void updateInfo() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	//paint method
	@Override
	public void paint(Graphics g) {
		Graphics2D graph = (Graphics2D) g;
	    graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    graph.setColor(Color.blue);
	    graph.setFont(new Font("Sans-Serif", Font.BOLD, 18));
	    graph.fillRect( 10, 20, 340, 50);
	    graph.fillRect( 465, 20, 300, 50);
	    graph.fillRect( 890, 20, 470, 50);
	    //graph.fillArc(350, 350, 300, 300, 0, 180);
	    graph.setColor(Color.white);
	    graph.drawString("Acceleration: ", 20, 50);
	    graph.drawString("Tilt:", 475, 50);
	    graph.drawString("Distance Traveled:", 900, 50);
	    /*graph.drawString("0", 360, 490);
	    graph.drawString("1", 370, 450);
	    graph.drawString("2", 395, 415);
	    graph.drawString("3", 420, 390);
	    graph.drawString("4", 455, 375);
	    graph.drawString("5", 495, 368);
	    graph.drawString("6", 535, 375);
	    graph.drawString("7", 570, 390);
	    graph.drawString("8", 600, 415);
	    graph.drawString("9", 622, 450);
	    graph.drawString("10", 625, 490);*/
	    graph.fillRect(145, 25, 195, 40);
	    graph.fillRect(515, 25, 240, 40);
	    graph.fillRect(1080, 25, 270, 40);
	    graph.setColor(Color.red);
	    graph.drawString(gui.accel + " cm/s", 160, 50);
	    graph.drawString(gui.tilt + " degrees", 530, 50);
	    graph.drawString(gui.distance + " cm", 1095, 50);
	}

}
