package org.jacobo.adyd.infraestructure.controller.staticgame;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.api.AbilitiesControllerApi;
import org.jacobo.adyd.api.dto.AbilityDto;
import org.jacobo.adyd.domain.service.staticgame.AbilitiesService;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;
import org.jacobo.adyd.infraestructure.mapper.gamestatic.AbilitiesDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AbilitiesController implements AbilitiesControllerApi {

    private final AbilitiesService abilitiesService;
    private final AbilitiesDtoMapper abilitiesDtoMapper;

    @Override
    public ResponseEntity<AbilityDto> getAbilityByid(
            String ability,
            Long id
    ) {
        return ResponseEntity.ok(abilitiesDtoMapper
                .toDto(abilitiesService
                        .getAbilityById(id, StaticAbilitiesEnum.valueOf(ability.toUpperCase()))));

    }


}
