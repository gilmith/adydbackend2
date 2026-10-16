package org.jacobo.adyd.application.service;

import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.repository.PlayerClassCharacteristicRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerClassCharacteristicServiceImplTest {
    @Mock
    private PlayerClassCharacteristicRepository playerClassCharacteristicRepository;
    @InjectMocks
    private PlayerClassCharacteristicServiceImpl playerClassCharacteristicService;

    private static PlayerClassCharacteristicModel playerClassCharacteristicModel;

    @BeforeAll
    static void setUp() {
        PlayerClassModel playerClass = PlayerClassModel.builder()
                .id(1L)
                .name("Fighter")
                .build();

        List<CharacteristicModel> characteristics = List.of(
                CharacteristicModel.builder()
                        .id(1L)
                        .name("Strength")
                        .value("15")
                        .build(),
                CharacteristicModel.builder()
                        .id(2L)
                        .name("Dexterity")
                        .value("12")
                        .build()
        );

        playerClassCharacteristicModel = PlayerClassCharacteristicModel.builder()
                .playerClass(playerClass)
                .characteristic(CharacteristicModel.builder().id(3L).name("Constitution").build())
                .characteristics(characteristics)
                .build();
    }

    @Test
    void save_ok() {
        when(playerClassCharacteristicRepository.save(any(PlayerClassCharacteristicModel.class)))
                .thenReturn(playerClassCharacteristicModel);

        PlayerClassCharacteristicModel result = playerClassCharacteristicService.save(playerClassCharacteristicModel);

        assertNotNull(result);
        assertNotNull(result.getCharacteristics());
        assertFalse(result.getCharacteristics().isEmpty());
        verify(playerClassCharacteristicRepository).save(playerClassCharacteristicModel);
    }

    @Test
    void save_ko_null() {
        when(playerClassCharacteristicRepository.save(any(PlayerClassCharacteristicModel.class)))
                .thenReturn(null);

        PlayerClassCharacteristicModel result = playerClassCharacteristicService.save(playerClassCharacteristicModel);

        assertNull(result);
        verify(playerClassCharacteristicRepository).save(any(PlayerClassCharacteristicModel.class));
    }

    @Test
    void getPlayerClassCharacteristics_ok() {
        when(playerClassCharacteristicRepository.findByPlayerClassId(1L))
                .thenReturn(playerClassCharacteristicModel);

        PlayerClassCharacteristicModel result = playerClassCharacteristicService.getPlayerClassCharacteristics(1L);

        assertNotNull(result);
        assertNotNull(result.getPlayerClass());
        assertEquals("Fighter", result.getPlayerClass().getName());
        assertTrue(result.getCharacteristics().size() > 0);
        verify(playerClassCharacteristicRepository).findByPlayerClassId(1L);
    }

    @Test
    void getPlayerClassCharacteristics_empty() {
        when(playerClassCharacteristicRepository.findByPlayerClassId(999L))
                .thenReturn(null);

        PlayerClassCharacteristicModel result = playerClassCharacteristicService.getPlayerClassCharacteristics(999L);

        assertNull(result);
        verify(playerClassCharacteristicRepository).findByPlayerClassId(999L);
    }
}
