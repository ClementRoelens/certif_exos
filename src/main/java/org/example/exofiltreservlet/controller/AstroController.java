package org.example.exofiltreservlet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Before;
import org.example.exofiltreservlet.annotation.ExceptionAnnotation;
import org.example.exofiltreservlet.annotation.JournalizationAnnotation;
import org.example.exofiltreservlet.dto.CelestObjectDTO;
import org.example.exofiltreservlet.model.CelestObject;
import org.example.exofiltreservlet.service.AstroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("api")
public class AstroController {

    @Autowired
    private AstroService astroService;

    @GetMapping("")
    @JournalizationAnnotation
    public List<CelestObject> getObjects(){
        return astroService.findCelestObjects();
    }


    @JournalizationAnnotation
    @PostMapping("")
    public void addCelestObject(@RequestBody CelestObjectDTO celestObjectDTO){
        astroService.addCelestObject(celestObjectDTO.toDTO());
    }

    @GetMapping("search")
    @JournalizationAnnotation
    public List<CelestObject> findCelestObjects(@RequestParam String name){
        return astroService.findCelestObjects(name);
    }

    @GetMapping("exception")
    @ExceptionAnnotation
    public void exception(){
        astroService.exception();
    }


}
