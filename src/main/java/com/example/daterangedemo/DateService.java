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
            .dateFrom(0L)
            .dateTo(1L)
            .build())
        .id("id")
        .build();
    repository.save(entity);
  }


}
