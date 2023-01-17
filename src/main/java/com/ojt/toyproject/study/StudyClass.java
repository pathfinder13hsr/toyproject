package com.ojt.toyproject.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudyClass {

    public static StudyDto entityToDto (StudyEntity studyEntity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        PropertyMap<StudyEntity, StudyDto> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setCreateDateTime(source.getJoinDateTime());
                map().setDeleteDateTime(source.getSignOutDateTime());
            }
        };
        modelMapper.addMappings(propertyMap);

     return  modelMapper.map(studyEntity, StudyDto.class);
    }

    public static List<StudyDto> entityListToDtoList (List<StudyEntity> studyEntityList) {
        return studyEntityList.stream().map(entity -> new ModelMapper().map(entity, StudyDto.class)).collect(Collectors.toList());
        //리스트로 넘기고 받을 때 스트림으로 처리할 수 있음, 다만 addMappings 하기가 곤란함, ModelMapper는 필드명이 거의 동일해 개별 지정을 많이 해주지 않아도 될 때 효용이 있는듯
    }


    public static void main(String[] args) {
        StudyEntity studyEntity = new StudyEntity();
        studyEntity.setId("test");
        studyEntity.setName("테스트");
        studyEntity.setSeq(1L);
        studyEntity.setJoinDateTime(LocalDateTime.now().minusDays(4L));
        studyEntity.setSignOutDateTime(LocalDateTime.now());

        StudyEntity studyEntity2 = new StudyEntity(2L, "test2", "테스트2", null, null);
        StudyEntity studyEntity3 = new StudyEntity(3L, "test3", "테스트3", null, null);
        StudyEntity studyEntity4 = new StudyEntity(4L, "test4", "테스트4", null, null);

        List<StudyEntity> studyEntityList = new ArrayList<>();
        studyEntityList.add(studyEntity);
        studyEntityList.add(studyEntity2);
        studyEntityList.add(studyEntity3);
        studyEntityList.add(studyEntity4);

        StudyDto studyDto = entityToDto(studyEntity);
        List<StudyDto> studyDtoList = entityListToDtoList(studyEntityList);

        System.out.println(studyDto);
        System.out.println(studyDtoList);
    }







}
