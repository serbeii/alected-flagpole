package org.alected.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    LapRepository lapRepository;
    
    @PostMapping("/insert")
    public void admin(@RequestBody LapData lapData) {
        System.out.println(lapData.getName());
        lapRepository.save(lapData);
    }
}
