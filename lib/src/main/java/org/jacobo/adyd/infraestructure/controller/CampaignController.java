package org.jacobo.adyd.infraestructure.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jacobo.adyd.api.CampaignControllerApi;
import org.jacobo.adyd.api.dto.CampaignDto;
import org.jacobo.adyd.domain.service.CampaignService;
import org.jacobo.adyd.infraestructure.mapper.CampaignDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<CampaignDto> getCampaignById(Long campaignId) {
        return ResponseEntity.ok(campaignMapper.toDto(campaignService.findById(campaignId)));
    }

    @Override
    public ResponseEntity<CampaignDto> createCampaign(CampaignDto campaignDto) {
        return ResponseEntity.accepted().body(campaignMapper.toDto(campaignService.create(campaignMapper.toModel(campaignDto))));
    }

    @Override
    public ResponseEntity<CampaignDto> updateCampaign(Long campaignId, CampaignDto campaignDto) {
        return ResponseEntity.accepted().body(campaignMapper.toDto(campaignService.update(campaignId, campaignMapper.toModel(campaignDto))));
    }


}
