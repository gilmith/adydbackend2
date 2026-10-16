package org.jacobo.adyd.application.service;

import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.model.RaceModel;
import org.jacobo.adyd.domain.repository.RaceCharacteristicRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RaceCharacteristicServiceImplTest {
    @Mock
    private RaceCharacteristicRepository raceCharacteristicRepository;
    @InjectMocks
    private RaceCharacteristicServiceImpl raceCharacteristicService;

    private static RaceCharacteristicModel raceCharacteristicModel;

    @BeforeAll
    static void setUp() {
        RaceModel race = RaceModel.builder()
                .id(1L)
                .name("Human")
                .build();

        List<CharacteristicModel> characteristics = List.of(
                CharacteristicModel.builder()
                        .id(1L)
                        .name("Strength")
                        .value("10")
                        .build(),
                CharacteristicModel.builder()
                        .id(2L)
                        .name("Dexterity")
                        .value("12")
                        .build()
        );

        raceCharacteristicModel = RaceCharacteristicModel.builder()
                .race(race)
                .characteristic(CharacteristicModel.builder().id(3L).name("Constitution").build())
                .characteristicsList(characteristics)
                .build();
    }

    @Test
    void save_ok() {
        when(raceCharacteristicRepository.save(any(RaceCharacteristicModel.class)))
                .thenReturn(raceCharacteristicModel);

        RaceCharacteristicModel result = raceCharacteristicService.save(raceCharacteristicModel);

        assertNotNull(result);
        assertNotNull(result.getRace());
        assertEquals("Human", result.getRace().getName());
        assertFalse(result.getCharacteristicsList().isEmpty());
        verify(raceCharacteristicRepository).save(raceCharacteristicModel);
    }

    @Test
    void save_ko_null() {
        when(raceCharacteristicRepository.save(any(RaceCharacteristicModel.class)))
                .thenReturn(null);

        RaceCharacteristicModel result = raceCharacteristicService.save(raceCharacteristicModel);

        assertNull(result);
        verify(raceCharacteristicRepository).save(any(RaceCharacteristicModel.class));
    }

    @Test
    void getRaceById_ok() {
        when(raceCharacteristicRepository.findByRaceId(1L))
                .thenReturn(raceCharacteristicModel);

        RaceCharacteristicModel result = raceCharacteristicService.getRaceById(1L);

        assertNotNull(result);
        assertNotNull(result.getRace());
        assertEquals("Human", result.getRace().getName());
        assertTrue(result.getCharacteristicsList().size() > 0);
        verify(raceCharacteristicRepository).findByRaceId(1L);
    }

    @Test
    void getRaceById_notFound() {
        when(raceCharacteristicRepository.findByRaceId(999L))
                .thenReturn(null);

        RaceCharacteristicModel result = raceCharacteristicService.getRaceById(999L);

        assertNull(result);
        verify(raceCharacteristicRepository).findByRaceId(999L);
    }

    @Test
    void findRaceCharacteristicByRaceIdAndCharacteristicId_ok() {
        when(raceCharacteristicRepository.findByRaceIdAndCharacteristicId(1L, 1L))
                .thenReturn(raceCharacteristicModel);

        RaceCharacteristicModel result = raceCharacteristicService.findRaceCharacteristicByRaceIdAndCharacteristicId(1L, 1L);

        assertNotNull(result);
        assertNotNull(result.getRace());
        assertNotNull(result.getCharacteristicsList());
        verify(raceCharacteristicRepository).findByRaceIdAndCharacteristicId(1L, 1L);
    }

    @Test
    void findRaceCharacteristicByRaceIdAndCharacteristicId_notFound() {
        when(raceCharacteristicRepository.findByRaceIdAndCharacteristicId(1L, 999L))
                .thenReturn(null);

        RaceCharacteristicModel result = raceCharacteristicService.findRaceCharacteristicByRaceIdAndCharacteristicId(1L, 999L);

        assertNull(result);
        verify(raceCharacteristicRepository).findByRaceIdAndCharacteristicId(1L, 999L);
    }
}
