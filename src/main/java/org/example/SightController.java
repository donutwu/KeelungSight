package org.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SightController {
    @Autowired
    private  SightRepository sightRepository;

    @CrossOrigin(origins = "*")

    @GetMapping("/printSights")
    public void printSights() {
        List<Sight> sights = sightRepository.findAll();
        for (Sight sight : sights) {
            System.out.println(sight.getSightName());
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/SightAPI")
    public List<Sight> getSightsByZone(@RequestParam String zone) {
        System.out.println(sightRepository.findAllByZone(zone));
        return sightRepository.findAllByZone(zone+"區");
    }
}
