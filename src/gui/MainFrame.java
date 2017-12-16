package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager.*;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	
	public static final String[] top = {"GOOG", "AMZN", "MSFT", "TSLA"};

	/**
	 * Auto-generated serialVersionUID
	 */
	private static final long serialVersionUID = -8424734531682214227L;

//	private final JPanel contentPane;

	private static void initLookAndFeel() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				initLookAndFeel();
				try {
					new MainFrame();
					
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

private JTabbedPane tabbedPane;

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainFrame() throws IOException {
		this.setLocation(20, 20);
		this.setPreferredSize(new Dimension(600,700));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		
		
		final JTabbedPane tabbedPane = new JTabbedPane();
		this.tabbedPane = tabbedPane;
		
		
		HomePanel home = new HomePanel();
		SpecificPanel specific = new SpecificPanel();
		AnalysisPanel anPanel = new AnalysisPanel();
		
		tabbedPane.addTab("Home", home);
		tabbedPane.addTab("Single Stock Intraday", specific);
		tabbedPane.addTab("Run Analysis", anPanel);

		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int iCoordX = (objDimension.width - this.getWidth()) / 2;
		int iCoordY = (objDimension.height - this.getHeight()) / 2;
		this.setLocation(iCoordX, iCoordY);

		add(tabbedPane, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}

}