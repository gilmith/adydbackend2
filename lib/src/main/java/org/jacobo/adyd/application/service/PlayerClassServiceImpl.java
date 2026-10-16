package org.jacobo.adyd.application.service;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.repository.PlayerClassRepository;
import org.jacobo.adyd.domain.service.PlayerClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
public class PlayerClassServiceImpl implements PlayerClassService {

    private final PlayerClassRepository playerClassRepository;

    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public Page<PlayerClassModel> getAll(PageRequest pagination) {
        return playerClassRepository.findAll(pagination);
    }

    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public PlayerClassModel getById(Long id) {
        return playerClassRepository.findById(id).orElseThrow(() -> new NotFoundRunTimeException("Player class not found"));
    }

    @Override
    @PreAuthorize("hasAnyRole('admin')")
    public void deleteById(Long id) {
        playerClassRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAnyRole('admin')")
    public PlayerClassModel createNewPlayerClass(PlayerClassModel playerClassModel) {
        return playerClassRepository.save(playerClassModel);
    }

    @Override
    @PreAuthorize("hasAnyRole('admin')")
    public PlayerClassModel update(PlayerClassModel playerClassModel) {
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public PlayerClassModel getPlayerClassById(Long playerClassId) {
        return playerClassRepository.findById(playerClassId).orElseThrow(() -> new NotFoundRunTimeException("Player class not found"));
    }
}
