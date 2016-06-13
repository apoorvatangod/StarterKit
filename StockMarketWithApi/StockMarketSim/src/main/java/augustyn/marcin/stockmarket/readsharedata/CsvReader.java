package augustyn.marcin.stockmarket.readsharedata;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@Component
public class CsvReader {
	//TODO czy mozliwe dodac do properties jesli zaczytujemy w postconstruct?
	private final static String FILE_PATH = "src/main/resources/dane-mixed.csv";
	
	List<ShareDataTo> readRecords() {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {
            return stream
            		.map(StreamToShareDataParser::parse)
            		.collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }  

}
