package com.ivango.quiosque;

import com.ivango.quiosque.entity.Quiosque;
import com.ivango.quiosque.repository.QuiosqueRepository;
import com.ivango.quiosque.service.QuiosqueService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class QuiosqueServiceTest {

    @Mock
    private QuiosqueRepository quiosqueRepository;

    @InjectMocks
    private QuiosqueService quiosqueService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvarQuiosque() {
        // Create a sample Quiosque object to save
        Quiosque quiosque = new Quiosque();
        quiosque.setNome("Quiosque A");

        // Create a mock Quiosque object to return when the save method is called
        Quiosque savedQuiosque = new Quiosque();
        savedQuiosque.setId(1L);
        savedQuiosque.setNome("Quiosque A");

        // Set up the mock repository object to return the mock Quiosque object when save is called
        Mockito.when(quiosqueRepository.save(Mockito.any())).thenReturn(savedQuiosque);

        // Call the service method and assert that the returned Quiosque object matches the mock Quiosque object
        Quiosque returnedQuiosque = quiosqueService.salvarQuiosque(quiosque);
        assertEquals(savedQuiosque, returnedQuiosque);

        // Verify that the save method was called on the mock repository object with the correct Quiosque object
        Mockito.verify(quiosqueRepository).save(Mockito.eq(quiosque));
    }

    @Test
    public void testBuscarQuiosques() {
        // Arrange
        String nome = "Test";
        List<Quiosque> expectedQuiosques = new ArrayList<>();
        expectedQuiosques.add(new Quiosque());
        expectedQuiosques.add(new Quiosque());

        when(quiosqueRepository.findByNomeLike(anyString())).thenReturn(expectedQuiosques);

        // Act
        List<Quiosque> actualQuiosques = quiosqueService.buscarQuiosques(nome);

        // Assert
        assertEquals(expectedQuiosques, actualQuiosques);
        Mockito.verify(quiosqueRepository, times(1)).findByNomeLike("%" + nome + "%");
    }

    @Test
    public void testBuscarQuiosque() {
        // Create a mock Quiosque object to return when the findById method is called
        Quiosque quiosque = new Quiosque();
        quiosque.setId(1L);
        quiosque.setNome("Quiosque A");

        // Set up the mock repository object to return the mock Quiosque object when findById is called
        when(quiosqueRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(quiosque));

        // Call the service method and assert that the returned Quiosque object matches the mock Quiosque object
        Optional<Quiosque> returnedQuiosque = quiosqueService.buscarQuiosque(1L);
        Assert.assertTrue(returnedQuiosque.isPresent());
        assertEquals(quiosque, returnedQuiosque.get());

        // Verify that the findById method was called on the mock repository object with the correct id
        Mockito.verify(quiosqueRepository).findById(Mockito.eq(1L));
    }

    @Test
    public void testBuscarQuiosqueNaoExistente() {
        // Set up the mock repository object to return an empty Optional object when findById is called
        when(quiosqueRepository.findById(Mockito.eq(2L))).thenReturn(Optional.empty());

        // Call the service method and assert that the returned Optional object is empty
        Optional<Quiosque> returnedQuiosque = quiosqueService.buscarQuiosque(2L);
        Assert.assertFalse(returnedQuiosque.isPresent());

        // Verify that the findById method was called on the mock repository object with the correct id
        Mockito.verify(quiosqueRepository).findById(Mockito.eq(2L));
    }

    @Test
    public void testAtualizarQuiosque() {
        // Given
        Quiosque quiosque = new Quiosque();
        quiosque.setId(1L);
        quiosque.setNome("Quiosque A");
        quiosque.setEndereco("Rua A");
        quiosque.setCidade("São Paulo");

        Quiosque existeQuiosque = new Quiosque();
        existeQuiosque.setId(1L);
        existeQuiosque.setNome("Quiosque B");
        existeQuiosque.setEndereco("Rua B");
        existeQuiosque.setCidade("Rio de Janeiro");

        when(quiosqueRepository.findById(quiosque.getId())).thenReturn(Optional.of(existeQuiosque));
        when(quiosqueRepository.save(existeQuiosque)).thenReturn(existeQuiosque);

        // When
        Quiosque result = quiosqueService.atualizarQuiosque(quiosque);

        // Then
        assertEquals(quiosque.getId(), result.getId());
        assertEquals(quiosque.getNome(), result.getNome());
        assertEquals(quiosque.getEndereco(), result.getEndereco());
        assertEquals(quiosque.getCidade(), result.getCidade());
    }

    @Test
    public void testRemoverQuiosque() {
        // Call the service method with a mock Quiosque id and assert that the correct success message is returned
        Long mockQuiosqueId = 1L;
        String expectedSuccessMessage = "Quiosque removido com sucesso! -> " + mockQuiosqueId;
        assertEquals(expectedSuccessMessage, quiosqueService.removerQuiosque(mockQuiosqueId));

        // Verify that the deleteById method was called on the mock repository object with the correct id
        Mockito.verify(quiosqueRepository).deleteById(mockQuiosqueId);
    }
}
