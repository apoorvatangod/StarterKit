package txtEditor;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;  

import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import events.WriteFile;
import main.MainFrame;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest(MainFrame.class)
public class WriteFileMockTest {
	private WriteFile writeFile = new WriteFile();
	@Mock
    private ActionEvent actionEventMock;

    @Mock
    private TextField textFieldMock;
    
    @Mock
    private static MainFrame mainFrameMock;
    
    
    @Before 
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
    public void sayFileSavedSuccessTest() {
		///PowerMockito.mockStatic(MainFrame.class);
		/*actionEventMock = mock(ActionEvent.class);
		textFieldMock = mock(TextField.class);
		mainFrameMock = mock(MainFrame.class);
		WriteFile spy = Mockito.spy(writeFile);
		
		when(textFieldMock.getText()).thenReturn("C:/Documents/file.txt");
		//when(MainFrame.mainPanel.namePanel.textField.getText()).thenReturn("C:/Documents/lol.txt");
		
		Mockito.doNothing().when(spy).areaInFile(any(JTextArea.class), Mockito.anyString());
		
		writeFile.actionPerformed(actionEventMock);
		verify(writeFile, times(1)).actionPerformed(actionEventMock);
		assertTrue(true);*/
	}
    


	/**
	 * TODO 2: Przetestuj metode actionPerformed w klasie WriteFile.
	 * Chcemy sprawdzic, czy PathPanel.sayFileSaved() zostalo wywolane raz.
	 * Utworz mock obiektu ActionEvent i wywolaj metode actionPerformed.
	 * Zamockuj tez odpowiednio TextField.getText(), tak by if w metodzie actionPerformed zwrocil true.
	 * Nie zapomnij o mocku dla metody areaInFile tak, by metoda nie probowala otwierac rzeczywistego pliku.
	 */
}