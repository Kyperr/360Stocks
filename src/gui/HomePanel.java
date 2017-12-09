package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class HomePanel extends JPanel {
	
	private JTextArea dataArea;

	public HomePanel() {
		
		//create main jpanel
		JPanel mainPanel = new JPanel();
		//set layout
		mainPanel.setLayout(new ColumnLayout(1, 15, 3, ColumnLayout.WIDTH));

		//create the content panel
		JPanel contentPanel = new JPanel();
		//set layout
		contentPanel.setLayout(new ColumnLayout(2, 15, 3, ColumnLayout.WIDTH));

		//add the content panel to the mainpanel
		mainPanel.add(contentPanel);

		//add the main panel to the Caesar jframe
		add(mainPanel);

		JPanel dataPanel = new JPanel();
		TitledBorder plainBorder;
		plainBorder = BorderFactory.createTitledBorder("Stock Data");
		dataPanel.setBorder(plainBorder);
		final JTextArea dataArea = new JTextArea(10, 30);
		this.dataArea = dataArea;
		dataArea.setEditable(false);
		dataArea.setLineWrap(false);
		dataPanel.add(new JScrollPane(dataArea));
		
		contentPanel.add(dataPanel);
		
	}

}
