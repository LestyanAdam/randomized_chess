package randomized_chess;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PreviousGameWindow extends JFrame{

	private static final long serialVersionUID = 1L; //I just did this to get rid of the warning, it has no real use for us
	
	private JPanel panel;
	
	private XMLHandler handler;
	
	public PreviousGameWindow() {
		super("Previous Game");
		
		handler = new XMLHandler();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setResizable(false);
		
		panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		String[] logs = null;
		try {
			logs = handler.loadLogsIntoStringArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(logs[0] == "") {
			JLabel noLogs = new JLabel("There is no previous game.");
			panel.add(noLogs);
			this.setMinimumSize(new Dimension(300,100));
		}else {
			for(int i = 0; i < logs.length; i++) {
				JLabel log = new JLabel("o  " + logs[i]);
				panel.add(log);
			}
			this.setPreferredSize(new Dimension(300,500));
		}
		
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        JScrollPane scrPane = new JScrollPane(panel);
		
		this.add(scrPane);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
        
	}
}