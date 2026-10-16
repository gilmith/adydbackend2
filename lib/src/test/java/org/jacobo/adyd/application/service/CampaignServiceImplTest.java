package org.jacobo.adyd.application.service;

import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.*;
import org.jacobo.adyd.domain.repository.CampaignRepository;
import org.jacobo.adyd.domain.repository.PlayerClassRepository;
import org.jacobo.adyd.domain.repository.RaceRepository;
import org.jacobo.adyd.domain.service.FileService;
import org.jacobo.adyd.infraestructure.mapper.CampaignDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceImplTest {
    @Mock
    private CampaignRepository campaignRepository;
    @Spy
    private CampaignDtoMapper campaignMapper = Mappers.getMapper(CampaignDtoMapper.class);
    @Mock
    private RaceRepository raceRepository;
    @Mock
    private PlayerClassRepository playerClassRepository;
    @Mock
    private FileService fileService;
    @InjectMocks
    private CampaignServiceImpl campaignService;

    @Test
    void findAll_empty() {
        PageRequest page = PageRequest.of(0, 10);
        when(campaignRepository.findAll(page)).thenReturn(Page.empty());
        assertFalse(campaignService.findAll(page).hasContent());
    }

    @Test
    void findAll_withData() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        CampaignModel campaign = CampaignModel.builder()
                .name("Test Campaign")
                .races(Collections.emptySet())
                .playerClasses(Collections.emptySet())
                .build();
        when(campaignRepository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(Collections.singletonList(campaign)));

        Page<CampaignModel> result = campaignService.findAll(pageRequest);

        assertFalse(result.isEmpty());
        assertTrue(result.getTotalElements() > 0);
        assertNotNull(result.getContent().getFirst().getName());
    }

    @Test
    void findById_ok() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(CampaignModel.builder()
                .name("Test Campaign")
                .races(Collections.emptySet())
                .playerClasses(Collections.emptySet())
                .build()));
        CampaignModel result = campaignService.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getName());
    }

    @Test
    void findById_notFound() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.findById(1L));
    }

    @Test
    void create_ok() {
        CampaignModel campaign = CampaignModel.builder()
                .name("New Campaign")
                .races(Collections.emptySet())
                .playerClasses(Collections.emptySet())
                .build();
        when(campaignRepository.save(campaign)).thenReturn(campaign);
        CampaignModel result = campaignService.create(campaign);
        assertNotNull(result);
        assertNotNull(result.getName());
        verify(campaignRepository).save(campaign);
    }

    @Test
    void update_ok_withoutImage() throws IOException {
        CampaignModel existing = CampaignModel.builder()
                .id(1L)
                .name("Old Name")
                .races(Collections.emptySet())
                .playerClasses(Collections.emptySet())
                .build();
        CampaignModel updated = CampaignModel.builder()
                .id(1L)
                .name("New Name")
                .races(Collections.emptySet())
                .playerClasses(Collections.emptySet())
                .build();

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(campaignMapper.getModifiedCampaignModel(any(CampaignModel.class), any(CampaignModel.class))).thenReturn(updated);
        when(campaignRepository.save(any(CampaignModel.class))).thenReturn(updated);

        CampaignModel result = campaignService.update(updated);
        assertNotNull(result);
        verify(campaignRepository).findById(1L);
        verify(campaignMapper).getModifiedCampaignModel(any(), any());
        verify(campaignRepository).save(any());
    }

    @Test
    void update_ok_withImage() throws IOException {
        CampaignModel existing = CampaignModel.builder()
                .id(1L)
                .name("Campaign")
                .races(Collections.emptySet())
                .playerClasses(Collections.emptySet())
                .build();
        CampaignModel updated = CampaignModel.builder()
                .id(1L)
                .name("Updated Campaign")
                .races(Collections.emptySet())
                .playerClasses(Collections.emptySet())
                .build();
        
        ByteArrayResource imageResource = new ByteArrayResource("test-content".getBytes()) {
            @Override
            public String getFilename() {
                return "test.png";
            }
        };
        updated.setCampaignImage(imageResource);
        
        FileStoreModel fileStore = FileStoreModel.builder()
                .url("https://test.com/image.png")
                .fileName("test.png")
                .build();
        
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(fileService.uploadAndGenerateUrl(anyString(), any(InputStream.class))).thenReturn(fileStore);
        when(campaignMapper.getModifiedCampaignModel(any(), any())).thenReturn(updated);
        when(campaignRepository.save(any())).thenReturn(updated);

        CampaignModel result = campaignService.update(updated);
        assertNotNull(result);
        verify(fileService).uploadAndGenerateUrl(eq("test.png"), any(InputStream.class));
    }

    @Test
    void update_notFound() {
        CampaignModel updated = CampaignModel.builder()
                .id(999L)
                .name("Updated")
                .build();
        when(campaignRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.update(updated));
    }

    @Test
    void findAllRacesByCampaignId_ok() {
        CampaignRaceModel campaignRace = CampaignRaceModel.builder()
                .campaignName("Test Campaign")
                .races(new ArrayList<>())
                .build();
        when(campaignRepository.findAllRacesByCampaignId(1L)).thenReturn(Optional.of(campaignRace));

        CampaignRaceModel result = campaignService.findAllRacesByCampaignId(1L);
        assertNotNull(result);
        assertNotNull(result.getCampaignName());
        verify(campaignRepository).findAllRacesByCampaignId(1L);
    }

    @Test
    void findAllRacesByCampaignId_notFound() {
        when(campaignRepository.findAllRacesByCampaignId(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.findAllRacesByCampaignId(1L));
    }

    @Test
    void deleteCampaign_ok() {
        doNothing().when(campaignRepository).deleteById(1L);
        campaignService.deleteCampaign(1L);
        verify(campaignRepository).deleteById(1L);
    }

    @Test
    void deleteCampaignRace_ok() {
        CampaignModel campaign = CampaignModel.builder()
                .id(1L)
                .races(new HashSet<>(List.of(
                        RaceModel.builder().id(1L).name("Test Race").build()
                )))
                .build();
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any(CampaignModel.class))).thenReturn(campaign);

        campaignService.deleteCampaignRace(1L, 1L);
        verify(campaignRepository).findById(1L);
        verify(campaignRepository).save(campaign);
        assertTrue(campaign.getRaces().isEmpty());
    }

    @Test
    void deleteCampaignRace_campaignNotFound() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.deleteCampaignRace(1L, 1L));
    }

    @Test
    void addRaceInCampaign_ok() {
        CampaignModel campaign = CampaignModel.builder()
                .id(1L)
                .races(new HashSet<>())
                .build();
        RaceModel race = RaceModel.builder().id(2L).name("New Race").build();

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(raceRepository.findById(2L)).thenReturn(Optional.of(race));
        when(campaignRepository.save(any(CampaignModel.class))).thenReturn(campaign);

        campaignService.addRaceInCampaign(1L, 2L);
        assertTrue(campaign.getRaces().contains(race));
        verify(campaignRepository).save(campaign);
    }

    @Test
    void addRaceInCampaign_campaignNotFound() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.addRaceInCampaign(1L, 2L));
    }

    @Test
    void addRaceInCampaign_raceNotFound() {
        CampaignModel campaign = CampaignModel.builder()
                .id(1L)
                .races(new HashSet<>())
                .build();
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(raceRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRunTimeException.class, () -> campaignService.addRaceInCampaign(1L, 99L));
    }

    @Test
    void addPlayerClassInCampaign_ok() {
        CampaignModel campaign = CampaignModel.builder()
                .id(1L)
                .playerClasses(new HashSet<>())
                .build();
        PlayerClassModel playerClass = PlayerClassModel.builder().id(2L).name("Fighter").hitDice("d10").build();

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(playerClassRepository.findById(2L)).thenReturn(Optional.of(playerClass));
        when(campaignRepository.save(any(CampaignModel.class))).thenReturn(campaign);

        campaignService.addPlayerClassInCampaign(1L, 2L);
        assertTrue(campaign.getPlayerClasses().contains(playerClass));
        verify(campaignRepository).save(campaign);
    }

    @Test
    void addPlayerClassInCampaign_campaignNotFound() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.addPlayerClassInCampaign(1L, 2L));
    }

    @Test
    void addPlayerClassInCampaign_playerClassNotFound() {
        CampaignModel campaign = CampaignModel.builder()
                .id(1L)
                .playerClasses(new HashSet<>())
                .build();
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(playerClassRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRunTimeException.class, () -> campaignService.addPlayerClassInCampaign(1L, 99L));
    }

    @Test
    void getPlayerClassForCampaign_ok() {
        CampaignPlayerClassModel playerClass = CampaignPlayerClassModel.builder()
                .campaign("Test Campaign")
                .playerClass(new ArrayList<>())
                .build();
        when(campaignRepository.findPlayerClassForCampaign(1L)).thenReturn(Optional.of(playerClass));

        CampaignPlayerClassModel result = campaignService.getPlayerClassForCampaign(1L);
        assertNotNull(result);
        assertNotNull(result.getCampaign());
        verify(campaignRepository).findPlayerClassForCampaign(1L);
    }

    @Test
    void getPlayerClassForCampaign_notFound() {
        when(campaignRepository.findPlayerClassForCampaign(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.getPlayerClassForCampaign(1L));
    }

    @Test
    void getRaceByCampaignId_ok() {
        CampaignRaceModel campaignRace = CampaignRaceModel.builder()
                .campaignName("Test Campaign")
                .races(new ArrayList<>())
                .build();
        when(campaignRepository.findAllRacesByCampaignId(1L)).thenReturn(Optional.of(campaignRace));

        CampaignRaceModel result = campaignService.getRaceByCampaignId(1L);
        assertNotNull(result);
        assertNotNull(result.getCampaignName());
        verify(campaignRepository).findAllRacesByCampaignId(1L);
    }

    @Test
    void getRaceByCampaignId_notFound() {
        when(campaignRepository.findAllRacesByCampaignId(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundRunTimeException.class, () -> campaignService.getRaceByCampaignId(1L));
    }
}
