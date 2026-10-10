package org.jacobo.adyd.infraestructure.adapter.staticgame;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.staticgamevalues.AlignmentModel;
import org.jacobo.adyd.domain.repository.staticgame.AlignmentRepository;
import org.jacobo.adyd.infraestructure.mapper.staticgame.AlignmentMapper;
import org.jacobo.adyd.infraestructure.persistence.staticgame.AlignmentJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AlignmentRepositoryAdapter implements AlignmentRepository {

    private final AlignmentJpaRepository alignmentJpaRepository;
    private final AlignmentMapper alignmentMapper;


    @Override
    @Transactional(readOnly = true)
    public AlignmentModel getById(Long id) {
        return alignmentJpaRepository.findById(id).map(alignmentMapper::toModel).orElseThrow(() -> new RuntimeException("Alignment not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlignmentModel> getAll(PageRequest pageRequest) {
        return  alignmentMapper.toModel(alignmentJpaRepository.findAll(pageRequest));
    }
}
