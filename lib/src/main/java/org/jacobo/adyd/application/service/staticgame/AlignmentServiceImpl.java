package org.jacobo.adyd.application.service.staticgame;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.staticgamevalues.AlignmentModel;
import org.jacobo.adyd.domain.repository.staticgame.AlignmentRepository;
import org.jacobo.adyd.domain.service.staticgame.AlignmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlignmentServiceImpl implements AlignmentService {

    private final AlignmentRepository alignmentRepository;


    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public AlignmentModel getById(Long id) {
        return alignmentRepository.getById(id);
    }

    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public Page<AlignmentModel> getAll(PageRequest pageRequest) {
        return alignmentRepository.getAll(pageRequest);
    }
}
