package com.example.daterangedemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRangeRepository extends JpaRepository<DateEntity, TimeStampRange> {

//  @Query(value = "INSERT INTO #{#entityName}(dateinterval,id) VALUES(:range,:id)", nativeQuery = true)
//  void insertRange(
//      @Param("range")
//          String range,
//      @Param("id")
//          String id);
}
