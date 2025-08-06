package org.example.examen.service;

import org.example.examen.model.Immeuble;
import org.example.examen.repository.ImmeubleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImmeubleServiceTest {

    @Mock
    private ImmeubleRepository repository;

    @InjectMocks
    private ImmeubleService service;

    @Test
    void findAllReturnsRepositoryData() {
        Immeuble immeuble = new Immeuble();
        when(repository.findAll()).thenReturn(List.of(immeuble));

        List<Immeuble> result = service.findAll();

        assertThat(result).hasSize(1).contains(immeuble);
        verify(repository).findAll();
    }

    @Test
    void findByIdDelegatesToRepository() {
        Immeuble immeuble = new Immeuble();
        when(repository.findById(1L)).thenReturn(Optional.of(immeuble));

        Optional<Immeuble> result = service.findById(1L);

        assertThat(result).contains(immeuble);
        verify(repository).findById(1L);
    }

    @Test
    void savePersistsEntity() {
        Immeuble immeuble = new Immeuble();
        when(repository.save(immeuble)).thenReturn(immeuble);

        Immeuble saved = service.save(immeuble);

        assertThat(saved).isEqualTo(immeuble);
        verify(repository).save(immeuble);
    }

    @Test
    void deleteRemovesEntity() {
        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}
