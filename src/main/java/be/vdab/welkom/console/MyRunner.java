package be.vdab.welkom.console;

import be.vdab.welkom.exceptions.RepositoryException;
import be.vdab.welkom.repositories.LandRepository;
import be.vdab.welkom.repositories.TaalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final LandRepository landRepository;
    private final TaalRepository taalRepository;

    public MyRunner(LandRepository landRepository,@Qualifier("XML") TaalRepository taalRepository) {
        this.landRepository = landRepository;
        this.taalRepository = taalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            landRepository.findAll().forEach(land -> System.out.println(land.getCode() + ":" +
                    land.getNaam() + " " + land.getOppervlakte()));

            System.out.println("");

            taalRepository.findAll().forEach(taal -> System.out.println(taal.getCode() + " " +
                    taal.getNaam()));
        } catch (RepositoryException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
