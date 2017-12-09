package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import main.DataModel;

import com.google.gson.*;

public class SpecificPanel extends JPanel {

	private static final long serialVersionUID = -2694967280433167581L;
	private JTextArea dataArea;
	private DataModel dataModel;

	public SpecificPanel() {
		
		DataModel dataModel = new DataModel();
		this.dataModel = dataModel;

		//create main jpanel
		JPanel mainPanel = new JPanel();
		//set layout
		mainPanel.setLayout(new ColumnLayout(1, 15, 3, ColumnLayout.WIDTH));

		//create the content panel
		JPanel contentPanel = new JPanel();
		//set layout
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		//add the content panel to the mainpanel
		mainPanel.add(contentPanel);

		//add the main panel to the Caesar jframe
		add(mainPanel);

		JPanel searchPanel = new JPanel();
		TitledBorder searchBorder;
		searchBorder = BorderFactory.createTitledBorder("Search");
		searchPanel.setBorder(searchBorder);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		final JTextField searchBox = new JTextField("GOOG", 4);
		searchPanel.add(new JLabel("Symbol:  "));
		JButton searchButton = new JButton("Search");
		searchButton.setMnemonic(KeyEvent.VK_S);
		searchButton.setToolTipText("Search for a specific stock by symbol.");
		searchPanel.add(searchBox);
		searchPanel.add(searchButton);
		contentPanel.add(searchPanel);

		JPanel dataPanel = new JPanel();
		TitledBorder plainBorder;
		plainBorder = BorderFactory.createTitledBorder("Stock Data");
		dataPanel.setBorder(plainBorder);
		JTextArea dataArea = new JTextArea(10, 30);
		this.dataArea = dataArea;
		dataArea.setEditable(true);
		dataArea.setLineWrap(false);
		dataPanel.add(new JScrollPane(dataArea));

		contentPanel.add(dataPanel);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//do the thing here
				
				StringBuilder sb = new StringBuilder();
				String symb = searchBox.getText();
				// add pattern matching to not grunk up the search here
		
				String jsonstring = dataModel.getSingleIntraday(symb);
	
				
				JsonElement jelem = new JsonParser().parse(jsonstring);
				JsonObject jobj = jelem.getAsJsonObject();
				JsonObject jobjmeta = jobj.getAsJsonObject("Meta Data");
				String jsymb = jobjmeta.get("2. Symbol").toString();
				String jrefreshed = jobjmeta.get("3. Last Refreshed").toString();
				JsonObject timeSeries = jobj.getAsJsonObject("Time Series (1min)");
				String timeSeriesJobj = timeSeries.get("2017-12-08 16:00:00").toString();
				dataArea.setText(timeSeriesJobj);
				//String tsstr = timeSeries.get(jrefreshed).toString();
				//dataArea.setText(tsstr);
//				String jopen = timeSeriesJobj.get("1. open").toString();
//				dataArea.setText(jopen);
//				String jclose = timeSeriesJobj.get("3. open").toString();
//				String jvol = timeSeriesJobj.get("5. open").toString();
//				dataArea.setText(jsymb + "\n" + jrefreshed + "\n" + jopen + "\n" + jclose + "\n" + jvol);
		
				
			}
		});
	}
}
