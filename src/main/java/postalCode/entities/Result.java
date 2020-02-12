package postalCode.entities;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result {
    private String postcode;
    private Integer quality;
    private Integer eastings;
    private Integer northings;
    private String country;
    private Float longitude;
    private Float latitude;
    private String region;
    private String lsoa;
    private String msoa;
    private String incode;
    private String outcode;
    private String parish;
    private String ced;
    private String ccg;
    private String nuts;
    private Codes codes;

}
