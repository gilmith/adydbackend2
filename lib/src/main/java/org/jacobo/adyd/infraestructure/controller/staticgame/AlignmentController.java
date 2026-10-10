package org.jacobo.adyd.infraestructure.controller.staticgame;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.api.AlignmentControllerApi;
import org.jacobo.adyd.api.dto.PaginatedDto;
import org.jacobo.adyd.domain.service.staticgame.AlignmentService;
import org.jacobo.adyd.infraestructure.mapper.PageMapper;
import org.jacobo.adyd.infraestructure.mapper.gamestatic.AlignmentDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlignmentController implements AlignmentControllerApi {

    private final AlignmentService alignmentService;
    private final AlignmentDtoMapper alignmentDtoMapper;

    @Override
    public ResponseEntity<PaginatedDto> getAllAlignment(Integer page, Integer size, String sort) {
        val pagination = PageMapper.of(page, size, sort);
        return  ResponseEntity.ok(alignmentDtoMapper
                .toDto(alignmentService.getAll(pagination)));
    }

}
