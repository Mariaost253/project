package postalCode.entities;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Codes {
    private String adminDistrict;
    private String adminCounty;
    private String adminWard;
    private String parish;
    private String parliamentaryConstituency;
    private String ccg;
    private String ced;
    private String nuts;
}
