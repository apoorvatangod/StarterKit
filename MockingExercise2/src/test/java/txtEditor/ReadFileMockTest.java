package txtEditor;

import static org.junit.Assert.assertTrue;

import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import events.NoFileToReadException;
import events.ReadFile;

public class ReadFileMockTest {
	private static final Logger logger = LogManager.getLogger(ReadFileMockTest.class);
	private ReadFile readFile = new ReadFile();
	
	@Mock
    private ActionEvent actionEventMock;
	
	@Mock
    private JTextArea textAreaMock;
	
	@Mock
	private TextField textFieldMock;
    
    
    @Before 
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
    public void fileInAreaSuccessTest() {
		Mockito.when(textFieldMock.getText()).thenReturn("C:/Users/marciaug/Documents/file.txt");
		String path = textFieldMock.getText();
		try {
			readFile.fileInArea(textAreaMock, path);
			assertTrue(true);
		} catch (NoFileToReadException e) {
			logger.info("Error occured.");
			assertTrue(false);
		}
	}
	@Test(expected=NoFileToReadException.class)
    public void fileInAreaFailTest() throws NoFileToReadException {
		Mockito.when(textFieldMock.getText()).thenReturn("wrongPath");
		String path = textFieldMock.getText();
		readFile.fileInArea(textAreaMock, path);
		
		/*lub tak - wtedy bez throws
		 * try {
			rf.fileInArea(textAreaMock, path);
			logger.info("Error not occured.");
			assertTrue(false);
		} catch (NoFileToReadException e) {
			assertTrue(true);
		}*/
	}
	/**
	 * TODO 4: Przetestuj metode ReadFile.fileInArea(JTextArea, String). Sprawdz,
	 * czy zwraca ona wyjatek zaleznie od danych wejsciowych. Mozesz uzyc mockow lub danych przygotowanych.
	 */

	/*
	 * TODO 5: Przetestuj metode actionPerformed w klasie ReadFile.
	 * Chcemy sprawic, by PathPanel.sayFileOpened() nie zostalo wywolane.
	 * Utworz mock obiektu ActionEvent i wywolaj metode actionPerformed.
	 * Zamockuj tez odpowiednio TextField.getText(), tak by if w metodzie actionPerformed zwrocil true.
	 * Nie zapomnij o mocku dla metody areaInFile tak, by metoda zwrocila wyjatek.
	 */
}
