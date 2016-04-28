package events;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import main.MainFrame;
import panels.PathPanel;

public class ReadFile implements ActionListener {

	private JTextArea area;

	public void actionPerformed(ActionEvent arg0) {

		area = MainFrame.mainPanel.textAreaPanel.textArea;
		String path = MainFrame.mainPanel.namePanel.textField.getText();

		if (!path.equals("") && new File(path).isFile()) {
			try {
				fileInArea(area, path);
			} catch (NoFileToReadException e) {
				JOptionPane.showMessageDialog(new Frame(), "Error during reading file.");
			}
			PathPanel.sayFileOpened();
		} else {
			JFileChooser fileopen = new JFileChooser();
			int ret = fileopen.showDialog(null, "Open file");
			if (ret == JFileChooser.APPROVE_OPTION) {

				path = fileopen.getSelectedFile().getAbsolutePath();
				try {
					fileInArea(area, path);
				} catch (NoFileToReadException e) {
					JOptionPane.showMessageDialog(new Frame(), "Error during reading file.");
				}

				MainFrame.mainPanel.namePanel.textField.setText(path);
				PathPanel.sayFileOpened();
			}
		}
	}

	public void fileInArea(JTextArea area, String path) throws NoFileToReadException {
		/*
		 * TODO 3: Obsluz wyjatek tak, by go zlapac, przekazac wlasny wyjatek NoFileToReadException wyzej
		 * i obsluzyc go w metodzie nadrzednej.
		 * Podobnie jak w TODO 1, mozesz obsluzyc go w dowolny sposob.
		 */
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			area.read(br, null);
		} catch (IOException e) {
			throw new NoFileToReadException(e.getMessage());
		}
		try {
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new Frame(), "Error during closing file.");
		}
	}
}
