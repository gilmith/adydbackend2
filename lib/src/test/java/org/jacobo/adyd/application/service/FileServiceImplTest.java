package org.jacobo.adyd.application.service;

import org.jacobo.adyd.domain.exception.AdydException;
import org.jacobo.adyd.domain.model.FileStoreModel;
import org.jacobo.adyd.domain.repository.FileStoreRepository;
import org.jacobo.adyd.domain.service.FileStoreService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {
    @Mock
    private FileStoreRepository fileStoreRepository;
    @Mock
    private FileStoreService fileStoreService;
    @InjectMocks
    private FileServiceImpl fileService;

    private static FileStoreModel fileStoreModel;

    @BeforeAll
    static void setUp() {
        fileStoreModel = FileStoreModel.builder()
                .id(1L)
                .fileName("test.png")
                .url("https://test.com/test.png")
                .build();
    }

    @Test
    void existsFileInStorage_ok() {
        when(fileStoreService.exists("test.png", "campaigns")).thenReturn(true);

        Boolean result = fileService.existsFileInStorage("test.png", "campaigns");

        assertTrue(result);
        verify(fileStoreService).exists("test.png", "campaigns");
    }

    @Test
    void existsFileInStorage_notExists() {
        when(fileStoreService.exists("missing.png", "campaigns")).thenReturn(false);

        Boolean result = fileService.existsFileInStorage("missing.png", "campaigns");

        assertFalse(result);
        verify(fileStoreService).exists("missing.png", "campaigns");
    }

    @Test
    void uploadAndGenerateUrl_newFile() throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream("test-content".getBytes());
        when(fileStoreRepository.findByFileName("test.png")).thenReturn(Optional.empty());
        when(fileStoreService.uploadFile(eq("test.png"), eq("campaigns"), anyString(), any()))
                .thenReturn("https://s3.com/test.png");
        when(fileStoreRepository.save(any(FileStoreModel.class))).thenReturn(fileStoreModel);

        FileStoreModel result = fileService.uploadAndGenerateUrl("test.png", stream);

        assertNotNull(result);
        assertTrue(result.getFileName().contains("test"));
        verify(fileStoreService).uploadFile(eq("test.png"), eq("campaigns"), anyString(), any());
        verify(fileStoreRepository).save(any(FileStoreModel.class));
    }

    @Test
    void uploadAndGenerateUrl_existingFile() throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream("updated-content".getBytes());
        FileStoreModel existing = FileStoreModel.builder()
                .id(1L)
                .fileName("test.png")
                .url("https://old.com/test.png")
                .build();

        when(fileStoreRepository.findByFileName("test.png")).thenReturn(Optional.of(existing));
        when(fileStoreService.uploadFile(eq("test.png"), eq("campaigns"), anyString(), any()))
                .thenReturn("https://s3.com/test.png");
        when(fileStoreRepository.save(any(FileStoreModel.class))).thenReturn(existing);

        FileStoreModel result = fileService.uploadAndGenerateUrl("test.png", stream);

        assertNotNull(result);
        assertTrue(result.getUrl().contains("s3.com"));
        verify(fileStoreService).uploadFile(eq("test.png"), eq("campaigns"), anyString(), any());
        verify(fileStoreRepository).save(existing);
    }

    @Test
    void uploadAndGenerateUrl_uploadFails() {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream("test-content".getBytes());
            when(fileStoreRepository.findByFileName("test.png")).thenReturn(Optional.empty());
            when(fileStoreService.uploadFile(anyString(), anyString(), anyString(), any()))
                    .thenThrow(new IOException("Upload failed"));

            AdydException exception = assertThrows(AdydException.class,
                    () -> fileService.uploadAndGenerateUrl("test.png", stream));

            assertTrue(exception.getMessage().contains("Error uploading campaign image"));
            verify(fileStoreRepository, never()).save(any());
        } catch (IOException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void getFileByName_notFound() {
        FileStoreModel result = fileService.getFileByName("missing.png");

        assertNull(result);
    }
}
