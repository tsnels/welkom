package be.vdab.welkom.repositories;

import be.vdab.welkom.domain.Land;
import be.vdab.welkom.exceptions.RepositoryException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class LandRepository {

    public List<Land> findAll() {
        try {
            return Files.lines(Path.of("/data/landen.csv"))
                    .map(regel -> regel.split(","))
                    .map(regelOnderdelen -> new Land(regelOnderdelen[0], regelOnderdelen[1], Integer.valueOf(regelOnderdelen[2])))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException  | NumberFormatException ex) {
            throw new RepositoryException(ex);
        }
    }
}
