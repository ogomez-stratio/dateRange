package com.example.daterangedemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity(name = "datetable")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TypeDef(typeClass = TsRangeType.class, name = "tsRange")
public class DateEntity {


  @Column(name = "dateinterval")
  @Type(type = "tsRange")
  private TimeStampRange dateInterval;
  @Id
  private String id;

}
