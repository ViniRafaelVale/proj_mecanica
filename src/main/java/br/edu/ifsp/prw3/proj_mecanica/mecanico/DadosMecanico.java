package br.edu.ifsp.prw3.proj_mecanica.mecanico;

import jakarta.validation.constraints.NotBlank;

public record DadosMecanico(
        @NotBlank
        String nome,

        String anosExperiencia
) {}
