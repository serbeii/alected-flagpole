package org.alected.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173")
public class MainController {
    @Autowired
    private LapRepository lapRepository;

    @GetMapping("main")
    public ResponseEntity<?> mainPage() {
        List<LapData> rankings = lapRepository.findAll();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }
    
}
