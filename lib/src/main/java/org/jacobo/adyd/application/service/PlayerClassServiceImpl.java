package org.jacobo.adyd.application.service;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.repository.PlayerClassRepository;
import org.jacobo.adyd.domain.service.PlayerClassService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerClassServiceImpl implements PlayerClassService {

    private final PlayerClassRepository playerClassRepository;

    @Override
    public List<PlayerClassModel> getAll() {
        return playerClassRepository.findAll();
    }

    @Override
    public PlayerClassModel getById(Long id) {
        return playerClassRepository.findById(id).orElseThrow(() -> new NotFoundRunTimeException("Player class not found"));
    }

    @Override
    public void deleteById(Long id) {
        playerClassRepository.deleteById(id);
    }

    @Override
    public PlayerClassModel createNewPlayerClass(PlayerClassModel playerClassModel) {
        return playerClassRepository.save(playerClassModel);
    }

    @Override
    public PlayerClassModel update(PlayerClassModel playerClassModel) {
        return null;
    }
}
