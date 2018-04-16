package com.example.daterangedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateService {

  @Autowired
  private DateRangeRepository repository;

  void save(DateDto dto) {

    DateEntity entity = DateEntity.builder()
        .dateInterval(TimeStampRange.builder()
            .dateFrom(3133454567L)
            .dateTo(3133454569L)
            .build())
        .id(dto.getId())
        .build();
    repository.save(entity);
  }


}
