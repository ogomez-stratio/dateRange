package com.example.daterangedemo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeStampRange implements Serializable {
    LocalDateTime dateFrom;
    LocalDateTime dateTo;
}
