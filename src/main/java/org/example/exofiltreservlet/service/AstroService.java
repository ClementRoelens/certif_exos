package org.example.exofiltreservlet.service;

import org.example.exofiltreservlet.model.CelestObject;
import org.example.exofiltreservlet.repository.AstroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AstroService {
    @Autowired
    private AstroRepository astroRepository;


    public List<CelestObject> findCelestObjects() {
        return astroRepository.getCelestObjects();
    }

    public void addCelestObject(CelestObject celestObject) {
        astroRepository.addCelestObject(celestObject);
    }

    public List<CelestObject> findCelestObjects(String name) {
        return astroRepository.findCelestObjects(name);
    }

    public void exception() {
        int[] emptyArray = new int[0];
        System.out.println(emptyArray[1]);
    }
}
