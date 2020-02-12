package postalCode.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostCodeFullEntity {
    private Result result;
    private int status; //Http status
}
