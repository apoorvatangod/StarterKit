package events;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import main.MainFrame;
import panels.PathPanel;

public class WriteFile implements ActionListener {

	private JTextArea area;

	public void actionPerformed(ActionEvent e) {

		area = MainFrame.mainPanel.textAreaPanel.textArea;
		String path = MainFrame.mainPanel.namePanel.textField.getText();

		if (!path.equals("") && new File(path).isFile()) {
			areaInFile(area, path);
			PathPanel.sayFileSaved();
		} else {
			JFileChooser fileopen = new JFileChooser();
			int ret = fileopen.showDialog(null, "Save in file");
			if (ret == JFileChooser.APPROVE_OPTION) {

				path = fileopen.getSelectedFile().getAbsolutePath();
				areaInFile(area, path);

				MainFrame.mainPanel.namePanel.textField.setText(path);
				PathPanel.sayFileSaved();
			}
		}
	}

	public void areaInFile(JTextArea area, String path) {
		/*
		 * TODO 1: Obsluz pojawiajacy sie tutaj wyjatek. Wyswietl blad: 
		 * a)wersja trudniejsza: utworz popup/wypisz blad jako label w oknie 
		 * b)wersja latwiejsza: wyswietl blad w konsoli
		 */
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(path));
			area.write(bw);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new Frame(), "Error during writing to file.");
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new Frame(), "Error during closing file.");
		}
		

		/*alternative version if we dont care about distinguishing between closing and writing error
		 * try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			area.write(bw);
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new Frame(), "Error during writing to file.");
		}*/
	}
}
