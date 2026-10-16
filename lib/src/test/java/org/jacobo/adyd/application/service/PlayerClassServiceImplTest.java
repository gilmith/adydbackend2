package org.jacobo.adyd.application.service;

import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.repository.PlayerClassRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerClassServiceImplTest {
    @Mock
    private PlayerClassRepository playerClassRepository;
    @InjectMocks
    private PlayerClassServiceImpl playerClassService;

    private static PlayerClassModel playerClassModel;

    @BeforeAll
    static void setUp() {
        playerClassModel = PlayerClassModel.builder()
                .id(1L)
                .name("Fighter")
                .hitDice("d10")
                .build();
    }

    @Test
    void getAll_ok() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(playerClassRepository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(List.of(playerClassModel)));

        Page<PlayerClassModel> result = playerClassService.getAll(pageRequest);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        verify(playerClassRepository).findAll(pageRequest);
    }

    @Test
    void getAll_empty() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(playerClassRepository.findAll(pageRequest)).thenReturn(Page.empty());

        Page<PlayerClassModel> result = playerClassService.getAll(pageRequest);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(playerClassRepository).findAll(pageRequest);
    }

    @Test
    void getById_ok() {
        when(playerClassRepository.findById(1L)).thenReturn(Optional.of(playerClassModel));

        PlayerClassModel result = playerClassService.getById(1L);

        assertNotNull(result);
        assertEquals("Fighter", result.getName());
        verify(playerClassRepository).findById(1L);
    }

    @Test
    void getById_notFound() {
        when(playerClassRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRunTimeException.class, () -> playerClassService.getById(999L));
        verify(playerClassRepository).findById(999L);
    }

    @Test
    void deleteById_ok() {
        doNothing().when(playerClassRepository).deleteById(1L);

        playerClassService.deleteById(1L);

        verify(playerClassRepository).deleteById(1L);
    }

    @Test
    void createNewPlayerClass_ok() {
        PlayerClassModel newPlayerClass = PlayerClassModel.builder()
                .name("Wizard")
                .hitDice("d6")
                .build();
        when(playerClassRepository.save(any(PlayerClassModel.class))).thenReturn(newPlayerClass);

        PlayerClassModel result = playerClassService.createNewPlayerClass(newPlayerClass);

        assertNotNull(result);
        assertEquals("Wizard", result.getName());
        verify(playerClassRepository).save(newPlayerClass);
    }

    @Test
    void update_notImplemented() {
        PlayerClassModel playerClassModel = PlayerClassModel.builder()
                .id(1L)
                .name("Fighter")
                .build();

        PlayerClassModel result = playerClassService.update(playerClassModel);

        assertNull(result);
    }

    @Test
    void getPlayerClassById_ok() {
        when(playerClassRepository.findById(1L)).thenReturn(Optional.of(playerClassModel));

        PlayerClassModel result = playerClassService.getPlayerClassById(1L);

        assertNotNull(result);
        assertEquals("Fighter", result.getName());
        verify(playerClassRepository).findById(1L);
    }

    @Test
    void getPlayerClassById_notFound() {
        when(playerClassRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRunTimeException.class, () -> playerClassService.getPlayerClassById(999L));
        verify(playerClassRepository).findById(999L);
    }
}
