package org.jacobo.adyd.domain.model.staticgamevalues;


import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class AlignmentModel extends BaseGameValue{

    private String name;
    private String description;
}
