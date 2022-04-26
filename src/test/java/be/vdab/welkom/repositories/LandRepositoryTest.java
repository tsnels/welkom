package be.vdab.welkom.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@PropertySource("application.properties")
@Import(LandRepository.class)

class LandRepositoryTest {

    private final LandRepository landRepository;
    private final String pad;

    public LandRepositoryTest(LandRepository landRepository, @Value("${landenCsvPad}") String pad) {
        this.landRepository = landRepository;
        this.pad = pad;
    }

    @Test
    void deRepositoryGeeftEvenveelLandenAlsHetAantalRegels() throws IOException {
        assertThat(landRepository.findAll().size()).isEqualTo(Files.lines(Path.of(pad)).count());
    }

    @Test
    void hetEersteLandBevatDeDataUitDeEersteRegel() throws IOException {
        var eersteLand = landRepository.findAll().get(0);
        var eersteRegel = Files.lines(Path.of(pad)).findFirst().get();
        assertThat(eersteLand.getCode() + "," + eersteLand.getNaam() + "," + eersteLand.getOppervlakte())
                .isEqualTo(eersteRegel);
    }
}