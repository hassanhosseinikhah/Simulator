package com.example.simualtor.DTO;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlarmDto implements Serializable {

    private String location;
    private Date onTime;
    private Date offTime;
    private String severity;
    private String serialNumber;
    private Date clarityTime;

    public AlarmDto(String location, Date onTime, Date offTime, String severity, String serialNumber) {
        this.location = location;
        this.onTime = onTime;
        this.offTime = offTime;
        this.severity = severity;
        this.serialNumber = serialNumber;
    }
}
