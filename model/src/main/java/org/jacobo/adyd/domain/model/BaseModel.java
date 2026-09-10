package org.jacobo.adyd.domain.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseModel {

    private Long id;
    private LocalDateTime create_date;
    private String createUser;
    private LocalDateTime update_date;
    private String updateUser;
}
