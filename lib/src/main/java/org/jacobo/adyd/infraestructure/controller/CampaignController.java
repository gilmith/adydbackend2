package org.jacobo.adyd.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jacobo.adyd.api.CampaignControllerApi;
import org.jacobo.adyd.api.dto.*;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.service.CampaignService;
import org.jacobo.adyd.infraestructure.mapper.CampaignDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.PageMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class CampaignController implements CampaignControllerApi {

    private final CampaignService campaignService;
    private final CampaignDtoMapper campaignMapper;

    @Override
    public ResponseEntity<PaginatedDto> getAllCampaign(String xAPIVersion, Integer page, Integer size, String sort){
        val pagination = PageMapper.of(page, size, sort);
        return ResponseEntity.ok(campaignMapper.toDto(campaignService.findAll(pagination), xAPIVersion));
    }

    @Override
    public ResponseEntity<CampaignDto> getCampaignById(Long campaignId, String xAPIVersion) {
        return ResponseEntity.ok(campaignMapper.toDto(campaignService.findById(campaignId), xAPIVersion));
    }

    @Override
    public ResponseEntity<CampaignDto> createCampaign(String xAPIVersion, CreateCampaignDto campaignDto) {
        return ResponseEntity.accepted().body(campaignMapper.toDto(campaignService.create(campaignMapper.fromCreateModel(campaignDto)), xAPIVersion));
    }

    @Override
    public ResponseEntity<CampaignDto> updateCampaign(Long campaignId, String xAPIVersion, String name, String description, MultipartFile campaignImage) {
        try {
            val model = CampaignModel.builder()
                    .id(campaignId)
                    .name(name)
                    .campaignDescription(description)
                    .campaignImage(campaignImage == null ? null : campaignImage.getResource())
                    .build();
            return ResponseEntity.accepted().body(campaignMapper.toDto(campaignService.update(model), xAPIVersion));
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<CampaignPlayerClassDto> getPlayerClassForCampaign(Long campaignId, String xAPIVersion) {
        return ResponseEntity.ok(campaignMapper.toCampaignPlayerClassDto(campaignService.getPlayerClassForCampaign(campaignId)));
    }

    @Override
    public ResponseEntity<CampaignPlayerClassDto> addPlayerClassInCampaign(Long campaignId, Long playerClassId, String xAPIVersion){
        campaignService.addPlayerClassInCampaign(campaignId, playerClassId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<CampaignRaceDto> getRaceByCampaignId(Long campaignId, String xAPIVersion){
        return ResponseEntity.ok(campaignMapper.toCampaignRaceDto(campaignService.getRaceByCampaignId(campaignId)));
    }

    @Override
    public ResponseEntity<Void> addRaceInCampaign(Long campaignId, Long raceId, String xAPIVersion){
        campaignService.addRaceInCampaign(campaignId, raceId);
        return ResponseEntity.accepted().build();
    }
    @Override
    public ResponseEntity<Void> deleteCampaignId(Long campaignId, String xAPIVersion){
        campaignService.deleteCampaign(campaignId);
        return ResponseEntity.accepted().build();
    }

}
