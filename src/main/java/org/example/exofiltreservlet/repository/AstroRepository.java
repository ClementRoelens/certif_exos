package org.example.exofiltreservlet.repository;

import org.example.exofiltreservlet.model.CelestObject;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class AstroRepository {
    private List<CelestObject> celestObjects;

    private AstroRepository() {
        celestObjects = new ArrayList<>();
        celestObjects.add(new CelestObject("Venus",
                LocalDateTime.of(2024,1,12,22,45),
                "Une étoile mais en beaucoup plus brillant",
                50.64,
                3.12)
        );
        celestObjects.add(new CelestObject("Super nova",
                LocalDateTime.of(1054,7,4,12,0),
                "Une nouvelle étoile visible en plein jour pendant des années",
                39.54,
                116.23)
        );
        celestObjects.add(new CelestObject("Perséides",
                LocalDateTime.of(2024,8,11,23,30),
                "Quelques étoiles filantes, bof cette année",
                50.64,
                3.12)
        );
    }

    public List<CelestObject> getCelestObjects() {
        return celestObjects;
    }

    public void addCelestObject(CelestObject celestObject) {
        try {
            celestObjects.add(celestObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CelestObject> findCelestObjects(String name){
        return celestObjects.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

}
