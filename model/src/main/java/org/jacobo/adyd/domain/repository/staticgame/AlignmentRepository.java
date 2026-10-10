package org.jacobo.adyd.domain.repository.staticgame;

import org.jacobo.adyd.domain.model.staticgamevalues.AlignmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface AlignmentRepository {

    AlignmentModel getById(Long id);

    Page<AlignmentModel> getAll(PageRequest pageRequest);


}
