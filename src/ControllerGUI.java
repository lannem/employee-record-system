import javax.swing.JFrame;
/**
 * Controller GUI class.
 * Runs the swing GUI.
 * Also adds main form class and sets its size.
 * @author Lucy MacGlashan 15107763
 * @version 1.0
 */
public class ControllerGUI {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			/**
			 * Creates new main form and sets its size.
			 */
			public void run() {
				MainForm form = new MainForm();
				form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				form.setSize(800, 500);
				form.setVisible(true);
			}
		});
	}

}
