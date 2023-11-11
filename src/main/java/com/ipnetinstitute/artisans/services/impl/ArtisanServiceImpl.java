package com.ipnetinstitute.artisans.services.impl;

import com.ipnetinstitute.artisans.dao.ArtisanDao;
import com.ipnetinstitute.artisans.dto.ArtisanRequestDto;
import com.ipnetinstitute.artisans.dto.ArtisanResponseDto;
import com.ipnetinstitute.artisans.entities.Artisans;
import com.ipnetinstitute.artisans.mappers.ArtisanMapper;
import com.ipnetinstitute.artisans.services.ArtisanService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtisanServiceImpl implements ArtisanService {
    private  final ArtisanDao artisanDao ;
    private final ArtisanMapper artisanMapper ;

    public ArtisanServiceImpl(ArtisanDao artisanDao, ArtisanMapper artisanMapper) {
        this.artisanDao = artisanDao;
        this.artisanMapper = artisanMapper;
    }

    @Override
    public ArtisanResponseDto ajouterArtisan(ArtisanRequestDto artisanRequestDto) {
        Artisans artisan =  artisanMapper.toArtisan(artisanRequestDto);
        artisan.setCode(RandomStringUtils.randomAlphanumeric(8));
        Artisans artisanSaved = artisanDao.save(artisan) ;
        return artisanMapper.fromArtisan(artisanSaved);
    }

    @Override
    public Page<ArtisanResponseDto> listArtisans() {
        return null;
    }

    @Override
    public ArtisanResponseDto recuperArtisansParCode(String code) {
        Optional<Artisans> artisans = artisanDao.findByCode(code);
        return artisans.map(artisanMapper::fromArtisan).orElse(null);
    }
}
