package dto;

import lombok.*;

import javax.management.openmbean.ArrayType;
import java.lang.reflect.Array;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString

public class NewCar {

    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private int seats;
    private String carClass;
    private Double pricePerDay;
    private String about;
    private String city;
    private int lat;
    private int lng;
    private String image;
    private String owner;
    private ArrayType<BookedPeriods> bookedPeriods;

}

