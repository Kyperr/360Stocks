package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	/**
	 * Auto-generated serialVersionUID
	 */
	private static final long serialVersionUID = -8424734531682214227L;

//	private final JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
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
	 */
	public MainFrame() {
		this.setLocation(20, 20);
		this.setPreferredSize(new Dimension(600,600));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		
		final JTabbedPane tabbedPane = new JTabbedPane();
		this.tabbedPane = tabbedPane;
		
		
		HomePanel home = new HomePanel();
		SpecificPanel specific = new SpecificPanel();
		
		tabbedPane.addTab("Home", home);
		tabbedPane.addTab("Single Stock Intraday", specific);

		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int iCoordX = (objDimension.width - this.getWidth()) / 2;
		int iCoordY = (objDimension.height - this.getHeight()) / 2;
		this.setLocation(iCoordX, iCoordY);

		add(tabbedPane, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}

}