package tMilionerzy;

import java.awt.Color;

import javax.swing.JFrame;

public class BarChartFrame extends JFrame
{
	private JFrame frame;
	public BarChartFrame(double[] valuesF,int qNr)
	{
		this.setLayout(null);
		
	    frame = new JFrame("Publiczność");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(350, 300);
	 
	    String title = "Wynik głosowania do pytania "+qNr;
	    double[] values = valuesF;
	    String[] labels = new String[]{"A","B","C","D"};
	    Color[] colors = new Color[]{
	        Color.red,
	        Color.orange,
	        Color.yellow,
	        Color.green,

	    };
	    BarChart bc = new BarChart(values, labels, colors, title);
	 
	    frame.add(bc);
	    frame.setVisible(true);
	}
	public void Close()
	{
		frame.setVisible(false);
		frame.dispose();
	}
}
