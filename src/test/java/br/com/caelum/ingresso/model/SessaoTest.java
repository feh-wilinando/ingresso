package br.com.caelum.ingresso.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by nando on 07/03/17.
 */
public class SessaoTest {

    @Test
    public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {

        Sala sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));

        BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());

        Sessao sessao = new Sessao(LocalTime.now(), filme, sala);

        assertEquals( somaDosPrecosDaSalaEFilme, sessao.getPreco() );

    }

    @Test
    public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2EA3Disponiveis(){

        Lugar a1 = new Lugar("A", 1);
        Lugar a2 = new Lugar("A", 2);
        Lugar a3 = new Lugar("A", 3);

        Filme rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI_FI", new BigDecimal("12.0"));

        Sala eldorado7 = new Sala("Eldorado 7", new BigDecimal("8.5"));

        Sessao sessao = new Sessao(LocalTime.now(), rogueOne, eldorado7);

        Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);

        Set<Ingresso> ingressos = Stream.of(ingresso).collect(toSet());

        sessao.setIngressos(ingressos);

        assertFalse(sessao.isDisponivel(a1));
        assertTrue(sessao.isDisponivel(a2));
        assertTrue(sessao.isDisponivel(a3));
    }

}