package com.example.daterangedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateService {

  @Autowired
  private DateRangeRepository repository;

  void save(DateDto dto) {

    DateEntity entity = DateEntity.builder()
        .dateInterval(TimeStampRange.builder()
            .dateFrom(LocalDateTime.of(2018,12,8,9,54,34,0001))
            .dateTo(LocalDateTime.of(2018,12,8,14,54,34,0001))
            .build())
        .id(dto.getId())
        .build();
   DateEntity res =  repository.saveAndFlush(entity);

   System.out.println();
  }


}
