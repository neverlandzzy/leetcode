package l15_Swing;

import javax.swing.SwingUtilities;

public class App {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame("SwingWorker Demo");
			}
		});
	}

}
