package com.ipnetinstitute.artisans.restcontrollers;

import com.ipnetinstitute.artisans.dto.ArtisanRequestDto;
import com.ipnetinstitute.artisans.dto.ArtisanResponseDto;
import com.ipnetinstitute.artisans.services.ArtisanService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/v1/artisans")
public class ArtisanRestController {
    private final ArtisanService artisanService ;

    public ArtisanRestController(ArtisanService artisanService) {
        this.artisanService = artisanService;
    }


    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<ArtisanResponseDto>> listArtisans(@PathVariable("page") int page , @PathVariable("size") int size){
        return new ResponseEntity<>( artisanService.listArtisans(page, size), HttpStatus.OK);
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
