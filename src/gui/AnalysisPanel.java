package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import main.DataModel;
import query_functions.AbstractQueryFunctionData;
import query_functions.Interval;
import query_functions.QueryFunctionData;
import query_functions.RelativeStrengthIndex;
import query_functions.SeriesType;
import query_functions.SimpleMovingAveragesData;

import com.google.gson.*;

public class AnalysisPanel extends JPanel {

	private static final long serialVersionUID = -2694967280433167581L;
	private JTextArea dataArea;
	private DataModel dataModel;
	private String filePath = System.getProperty("user.dir")+ "/top5.txt";
	private Map<String, AbstractQueryFunctionData> analytics = new HashMap<>();
	{//Empty initializer
		String sma_60_high = "SMA(60)_High";
		analytics.put(sma_60_high, new SimpleMovingAveragesData(Interval.Daily, 60, SeriesType.HIGH));
		String sma_60_low = "SMA(60)_Low";
		analytics.put(sma_60_low, new SimpleMovingAveragesData(Interval.Daily, 60, SeriesType.LOW));
		String rsi_60_high = "RSI(60)_High";
		analytics.put(rsi_60_high, new RelativeStrengthIndex(Interval.Daily, 60, SeriesType.HIGH));
		String rsi_60_low = "RSI(60)_Low";
		analytics.put(rsi_60_low, new RelativeStrengthIndex(Interval.Daily, 60, SeriesType.LOW));
	}

	public AnalysisPanel() throws IOException {
		
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

		mainPanel.add(contentPanel);

		add(mainPanel);

		BufferedReader input = new BufferedReader(new FileReader(filePath));
		List<String> strings = new ArrayList<String>();
		try {
		  String line = null;
		  while (( line = input.readLine()) != null){
		    strings.add(line);
		  }
		}
		catch (FileNotFoundException e) {
		    System.err.println("Error, file " + filePath + " didn't exist.");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
		    input.close();
		}

		String[] lineArray = strings.toArray(new String[]{});

		JComboBox searchSymbols = new JComboBox(lineArray);
		JComboBox searchAnalytics = new JComboBox(analytics.keySet().toArray());
		
		JPanel searchPanel = new JPanel();
		TitledBorder searchBorder;
		searchBorder = BorderFactory.createTitledBorder("Search");
		searchPanel.setBorder(searchBorder);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		//final JTextField searchBox = new JTextField("GOOG", 4);
		searchPanel.add(new JLabel("Symbol:  "));
		JButton searchButton = new JButton("Search");
		searchButton.setMnemonic(KeyEvent.VK_S);
		searchButton.setToolTipText("Search for a specific stock by symbol.");
		searchPanel.add(searchSymbols);
		searchPanel.add(searchAnalytics);
		searchPanel.add(searchButton);
		contentPanel.add(searchPanel);

		JPanel dataPanel = new JPanel();
		TitledBorder plainBorder;
		plainBorder = BorderFactory.createTitledBorder("Stock Data");
		dataPanel.setBorder(plainBorder);


		contentPanel.add(dataPanel);
		String[] columns = {"Date/Time", "Value"};
		String[][] data = {};
		
		JTable table = new JTable(new DefaultTableModel(data, columns));
		dataPanel.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		TableColumn column = null;
		for (int i = 0; i < 2; i++ ) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(100);
			}
		}

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rows = model.getRowCount();
				for (int i = rows -1; i >= 0; i--) {
					model.removeRow(i);
				}
				
				//do the thing here
				String symb = searchSymbols.getSelectedItem().toString().toUpperCase();
				// add pattern matching to not grunk up the search here
				QueryFunctionData data = analytics.get(searchAnalytics.getSelectedItem().toString());
				String jsonstring = dataModel.getResponse(symb, data);//dataModel.getSingleIntraday(symb);

				JsonElement jelem = new JsonParser().parse(jsonstring);
				JsonObject jobj = jelem.getAsJsonObject();
				JsonObject timeSeries = jobj.getAsJsonObject(data.getJSONObjectHeader());
				Set<String> entries = timeSeries.keySet();
				
				for (String entry : entries) {
					JsonObject smaObj = timeSeries.getAsJsonObject(entry);
					Set<String> fields = smaObj.keySet();
					for(String field : fields) {
						String fieldValue = smaObj.get(field).getAsString();
						model.addRow(new String[] {entry, fieldValue});
					}

				}
			}
		});
	}
}
