package org.jacobo.adyd.application.service;

import org.jacobo.adyd.application.service.helpers.PlayerClassCharacteristicHelper;
import org.jacobo.adyd.application.service.helpers.RaceCharacteristicHelper;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.repository.CharacteristicRepository;
import org.jacobo.adyd.domain.service.RaceCharacteristicService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacteristicServiceImplTest {
    @Mock
    private CharacteristicRepository characteristicRepository;
    @Mock
    private RaceCharacteristicService raceCharacteristicService;
    @Mock
    private CharacteristicMapper characteristicMapper;
    @Mock
    private RaceCharacteristicHelper raceCharacteristicHelper;
    @Mock
    private PlayerClassCharacteristicHelper playerClassCharacteristicHelper;
    @InjectMocks
    private CharacteristicServiceImpl characteristicService;

    private static CharacteristicModel characteristicModel;
    private static CharacteristicModel updatedModel;

    @BeforeAll
    static void setUp() {
        characteristicModel = CharacteristicModel.builder()
                .id(1L)
                .code("code")
                .name("Test Characteristic")
                .value("test-value")
                .build();

        updatedModel = CharacteristicModel.builder()
                .id(1L)
                .code("updated-code")
                .name("Updated Characteristic")
                .value("updated-value")
                .build();
    }

    @Test
    void createNewProperty_ok() {
        when(raceCharacteristicHelper.createNewProperty(anyLong(), any(), anyString())).thenReturn(characteristicModel);

        CharacteristicModel result = characteristicService.createNewProperty(1L, new CharacteristicModel(), "test");

        assertNotNull(result);
        assertEquals("code", result.getCode());
        verify(raceCharacteristicHelper).createNewProperty(eq(1L), any(), eq("test"));
    }

    @Test
    void createNewProperty_ko() {
        when(raceCharacteristicHelper.createNewProperty(anyLong(), any(), anyString())).thenThrow(new RuntimeException("Helper error"));

        assertThrows(RuntimeException.class, () -> characteristicService.createNewProperty(1L, new CharacteristicModel(), "test"));
        verify(raceCharacteristicHelper).createNewProperty(anyLong(), any(), anyString());
    }

    @Test
    void createNewPropertyPlayer_ok() {
        when(playerClassCharacteristicHelper.createNewProperty(anyLong(), any(), anyString())).thenReturn(characteristicModel);

        CharacteristicModel result = characteristicService.createNewPropertyPlayer(1L, new CharacteristicModel(), "test");

        assertNotNull(result);
        assertEquals("code", result.getCode());
        verify(playerClassCharacteristicHelper).createNewProperty(eq(1L), any(), eq("test"));
    }

    @Test
    void createNewPropertyPlayer_ko() {
        when(playerClassCharacteristicHelper.createNewProperty(anyLong(), any(), anyString())).thenThrow(new RuntimeException("PlayerClass helper error"));

        assertThrows(RuntimeException.class, () -> characteristicService.createNewPropertyPlayer(1L, new CharacteristicModel(), "test"));
        verify(playerClassCharacteristicHelper).createNewProperty(anyLong(), any(), anyString());
    }

    @Test
    void findByCode_ok() {
        when(characteristicRepository.findByCode("test-code")).thenReturn(Optional.of(characteristicModel));

        Optional<CharacteristicModel> result = characteristicService.findByCode("test-code");

        assertTrue(result.isPresent());
        assertEquals("code", result.get().getCode());
        verify(characteristicRepository).findByCode("test-code");
    }

    @Test
    void findByCode_notFound() {
        when(characteristicRepository.findByCode("nonexistent")).thenReturn(Optional.empty());

        Optional<CharacteristicModel> result = characteristicService.findByCode("nonexistent");

        assertTrue(result.isEmpty());
        verify(characteristicRepository).findByCode("nonexistent");
    }

    @Test
    void getBuiltInCharacteristics_ok() {
        List<String> result = characteristicService.getBuiltInCharacteristics();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    void updateCharacteristic_ok() {
        CharacteristicModel savedModel = CharacteristicModel.builder()
                .id(1L)
                .code("updated-code")
                .name("Updated")
                .value("updated-value")
                .build();

        RaceCharacteristicModel raceCharacteristic = RaceCharacteristicModel.builder()
                .race(org.jacobo.adyd.domain.model.RaceModel.builder().id(1L).name("Test Race").build())
                .characteristic(characteristicModel)
                .build();

        when(characteristicRepository.findById(1L)).thenReturn(Optional.of(characteristicModel));
        when(characteristicMapper.copyValues(characteristicModel, updatedModel)).thenReturn(savedModel);
        when(raceCharacteristicService.findRaceCharacteristicByRaceIdAndCharacteristicId(1L, 1L)).thenReturn(raceCharacteristic);
        when(raceCharacteristicService.save(any(RaceCharacteristicModel.class))).thenReturn(raceCharacteristic);
        when(characteristicRepository.save(any(CharacteristicModel.class))).thenReturn(savedModel);

        CharacteristicModel result = characteristicService.updateCharacteristic(1L, 1L, updatedModel);

        assertNotNull(result);
        assertEquals("updated-code", result.getCode());
        verify(characteristicRepository).findById(1L);
        verify(characteristicMapper).copyValues(characteristicModel, updatedModel);
        verify(raceCharacteristicService).findRaceCharacteristicByRaceIdAndCharacteristicId(1L, 1L);
        verify(raceCharacteristicService).save(any(RaceCharacteristicModel.class));
        verify(characteristicRepository).save(any(CharacteristicModel.class));
    }

    @Test
    void updateCharacteristic_notFound() {
        when(characteristicRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRunTimeException.class, () -> characteristicService.updateCharacteristic(1L, 999L, updatedModel));
        verify(characteristicRepository).findById(999L);
        verify(characteristicMapper, never()).copyValues(any(), any());
        verify(raceCharacteristicService, never()).save(any());
    }
}
