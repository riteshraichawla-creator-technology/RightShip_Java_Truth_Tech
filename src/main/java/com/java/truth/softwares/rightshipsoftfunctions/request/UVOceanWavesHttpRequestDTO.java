package com.java.truth.softwares.rightshipsoftfunctions.request;

import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVOceanWavesGeneralUniversalDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class UVOceanWavesHttpRequestDTO extends UVOceanWavesCommonBaseRequest {

    private String requestDataString;

    private Class<?> requestEntityClazz;

    private  UVOceanWavesGeneralUniversalDTO requestDto =
            new UVOceanWavesGeneralUniversalDTO();

    public UVOceanWavesHttpRequestDTO() {
        super();
        requestDto = new UVOceanWavesGeneralUniversalDTO();
        this.initializeRequest(requestDto);
    }

    public void initializeRequest(UVOceanWavesGeneralUniversalDTO httpRequestDto) {
        requestDto.initializeDateDTO(new Date(), false, false, false);
        requestDto.initializeStringDTO(StringUtils.EMPTY, false, false, false);
        requestDto.initializeObjectDTO(StringUtils.EMPTY, false, false, false);
        requestDto.initializeLongDTO(0L, false, false, false);
        requestDto.initializeDoubleDTO(0.0d, false, false, false);
    }


}
