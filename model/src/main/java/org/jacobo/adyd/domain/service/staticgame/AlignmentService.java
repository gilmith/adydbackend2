package org.jacobo.adyd.domain.service.staticgame;

import org.jacobo.adyd.domain.model.staticgamevalues.AlignmentModel;
import org.jacobo.adyd.domain.validator.SortValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface AlignmentService {

    AlignmentModel getById(Long id);


    @SortValidator(AlignmentModel.class)
    Page<AlignmentModel> getAll(PageRequest pageRequest);

}
