package org.jacobo.adyd.infraestructure.controller.staticgame;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.api.AbilitiesControllerApi;
import org.jacobo.adyd.domain.service.staticgame.AbilitiesService;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AbilitiesController implements AbilitiesControllerApi {

    private final AbilitiesService abilitiesService;

    @Override
    public ResponseEntity<String> getAbilityByBasePoint(
            String ability,
            String basePoint
    ) {
        val result = abilitiesService.getAbilityByBasePointId(basePoint, StaticAbilitiesEnum.valueOf(ability.toUpperCase()));
        return null;
    }


}
