package com.ipnetinstitute.artisans.mappers;

import com.ipnetinstitute.artisans.dto.ArtisanRequestDto;
import com.ipnetinstitute.artisans.dto.ArtisanResponseDto;
import com.ipnetinstitute.artisans.entities.Artisans;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtisanMapper {

    @Mapping(target = "artisanId", ignore = true)
    @Mapping(target ="code" , ignore = true)
    Artisans  toArtisan(ArtisanRequestDto artisanRequestDto);

    ArtisanResponseDto fromArtisan(Artisans artisans);
}
