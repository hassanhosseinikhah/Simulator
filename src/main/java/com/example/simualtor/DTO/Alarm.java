package com.example.simualtor.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alarm implements Serializable {

    static final long serialVersionUID = 42L;


    private String source;
    private Date onTime;
    private Date offTime;
    private String siteName;
    private String cellName;
    private String status;
    private String occurrenceID;

}