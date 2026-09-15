package org.jacobo.adyd.infraestructure.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jacobo.adyd.api.CampaignControllerApi;
import org.jacobo.adyd.api.dto.CampaignDto;
import org.jacobo.adyd.api.dto.CampaignRaceDto;
import org.jacobo.adyd.domain.service.CampaignService;
import org.jacobo.adyd.infraestructure.mapper.CampaignDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerApi {

    private final CampaignService campaignService;
    private final CampaignDtoMapper campaignMapper;

    @Override
    public ResponseEntity<List<CampaignDto>> getAllCampaign(){
        return ResponseEntity.ok(campaignMapper.toDto(campaignService.findAll()));
    }

    @Override
    public ResponseEntity<CampaignDto> getCampaignById(final Long campaignId) {
        return ResponseEntity.ok(campaignMapper.toDto(campaignService.findById(campaignId)));
    }

    @Override
    public ResponseEntity<CampaignDto> createCampaign(final CampaignDto campaignDto) {
        return ResponseEntity.accepted().body(campaignMapper.toDto(campaignService.create(campaignMapper.toModel(campaignDto))));
    }

    @Override
    public ResponseEntity<CampaignDto> updateCampaign(final Long campaignId, final CampaignDto campaignDto) {
        return ResponseEntity.accepted().body(campaignMapper.toDto(campaignService.update(campaignId, campaignMapper.toModel(campaignDto))));
    }

    @Override
    public ResponseEntity<CampaignRaceDto> getRaceByCampaignId(final Long campaignId) {
        return ResponseEntity.ok(campaignMapper.toCampaignRaceDto(campaignService.findAllRacesByCampaignId(campaignId)));
    }

    @Override
    public ResponseEntity<Void> deleteRaceInCampaign(final Long campaignId, final Long raceId) {
        campaignService.deleteCampaignRace(campaignId, raceId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> addRaceInCampaign(
            final Long campaignId,
            final Long raceId
    ) {
        campaignService.addRaceInCampaign(campaignId, raceId);
        return ResponseEntity.accepted().build();
    }


}
