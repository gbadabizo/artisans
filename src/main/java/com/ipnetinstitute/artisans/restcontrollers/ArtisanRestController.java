package com.ipnetinstitute.artisans.restcontrollers;

import com.ipnetinstitute.artisans.dto.ArtisanRequestDto;
import com.ipnetinstitute.artisans.dto.ArtisanResponseDto;
import com.ipnetinstitute.artisans.services.ArtisanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/v1/artisans")
public class ArtisanRestController {
    private final ArtisanService artisanService ;

    public ArtisanRestController(ArtisanService artisanService) {
        this.artisanService = artisanService;
    }

    @PostMapping
    public ResponseEntity<ArtisanResponseDto> ajouterArtisan(@RequestBody ArtisanRequestDto artisanRequestDto){
        return new ResponseEntity<>(artisanService.ajouterArtisan(artisanRequestDto), HttpStatus.CREATED);
    }
    @GetMapping("/{code}")
    public ResponseEntity<ArtisanResponseDto> recupererArtisanParCode(@PathVariable("code") String code){
        return new ResponseEntity<>(artisanService.recuperArtisansParCode(code), HttpStatus.OK);
    }
}
