package com.example.daterangedemo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataRangeController {

  private final  DateService dateService;

  public DataRangeController(DateService dateService) {
    this.dateService = dateService;
  }

  @PostMapping(value = "/range")

  public DateDto post(
      @RequestBody
      DateDto dto){
    dateService.save(dto);
    return null;
  }

}
