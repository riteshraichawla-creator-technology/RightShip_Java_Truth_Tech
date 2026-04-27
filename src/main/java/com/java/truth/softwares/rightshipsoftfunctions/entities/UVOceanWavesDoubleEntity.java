package com.java.truth.softwares.rightshipsoftfunctions.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.java.truth.softwares.rightshipsoftfunctions.entities.UVOceanWavesBasicCommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UVOceanWavesDoubleEntity extends UVOceanWavesBasicCommonEntity {

    private Double doubleData;

    private Set<Double> doubleDataSet;

    private List<Double> doubleDataList;

    private Map<String, Double> doubleDataMap;
}
