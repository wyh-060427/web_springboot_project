package com.wyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpQueryParam {
    private Integer page=1;
    private Integer pageSize=10;
    private String name; Integer gender;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate start;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate end;
}
