package com.ipnetinstitute.artisans.services;

import com.ipnetinstitute.artisans.dto.ArtisanRequestDto;
import com.ipnetinstitute.artisans.dto.ArtisanResponseDto;
import org.springframework.data.domain.Page;


public interface ArtisanService {
    ArtisanResponseDto ajouterArtisan(ArtisanRequestDto artisanRequestDto);
    Page<ArtisanResponseDto> listArtisans();
    ArtisanResponseDto recuperArtisansParCode(String code);
}
