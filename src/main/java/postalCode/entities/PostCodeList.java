package postalCode.entities;

import com.sun.tools.internal.ws.processor.model.Request;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostCodeList {
    private Result[] result;
    private Integer status;

}
