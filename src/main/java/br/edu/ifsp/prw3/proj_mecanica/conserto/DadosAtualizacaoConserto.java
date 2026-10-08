package br.edu.ifsp.prw3.proj_mecanica.conserto;

import br.edu.ifsp.prw3.proj_mecanica.mecanico.DadosMecanico;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoConserto(
        @NotNull
        Long id,
        String dataSaida,
        DadosMecanico mecanico
) {}