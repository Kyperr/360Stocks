package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import main.DataModel;


public class HomePanel extends JPanel {
	
	private JTextArea dataArea;
	private Object dataModel;

	public HomePanel() {
		
		DataModel dataModel = new DataModel();
		this.dataModel = dataModel;
		
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
		contentPanel.add(dataPanel);
		String[] columns = {"Symbol", "Date/Time", "Open", "Close", "Volume"};
		String[][] data = {};
		
		JTable table = new JTable(new DefaultTableModel(data, columns));
		dataPanel.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		TableColumn column = null;
		for (int i = 0; i < 4; i++ ) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(25);
			} else if (i == 1) {
				column.setPreferredWidth(100);
			}
		}
		
		contentPanel.add(dataPanel);
		
		
		try (BufferedReader br = new BufferedReader(new FileReader("top5.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String jsonstring = dataModel.getSingleIntraday(line);

				JsonElement jelem = new JsonParser().parse(jsonstring);
				JsonObject jobj = jelem.getAsJsonObject();
				JsonObject jobjmeta = jobj.getAsJsonObject("Meta Data");
				String jsymb = jobjmeta.get("2. Symbol").getAsString();
				String jrefreshed = jobjmeta.get("3. Last Refreshed").getAsString();
				JsonObject timeSeries = jobj.getAsJsonObject("Time Series (1min)");
				JsonObject mostrecentseries = timeSeries.getAsJsonObject(jrefreshed);
				String jopen = mostrecentseries.get("1. open").getAsString();
				String jclose = mostrecentseries.get("4. close").getAsString();
				String jvol = mostrecentseries.get("5. volume").getAsString();
				
				model.addRow(new String[] {line, jrefreshed, jopen, jclose, jvol});
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
