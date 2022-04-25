package be.vdab.welkom.repositories;

import be.vdab.welkom.domain.Taal;
import be.vdab.welkom.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@Qualifier("CSV")
public class CsvTaalRepository implements TaalRepository {

    private final String pad;

    public CsvTaalRepository(@Value("${talenCsvPad}") String pad) {
        this.pad = pad;
    }

    @Override
    public List<Taal> findAll() {
        try{
            return Files.lines(Path.of(pad))
                    .map(regel -> regel.split(","))
                    .map(regelOnderdelen -> new Taal(regelOnderdelen[0], regelOnderdelen[1]))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new RepositoryException(ex);
        }
    }
}
