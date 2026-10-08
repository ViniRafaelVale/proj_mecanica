package br.edu.ifsp.prw3.proj_mecanica.conserto;

import br.edu.ifsp.prw3.proj_mecanica.mecanico.Mecanico;
import br.edu.ifsp.prw3.proj_mecanica.veiculo.Veiculo;

public record DadosDetalhamentoConserto(
        Long id,
        String dataEntrada,
        String dataSaida,
        Mecanico mecanico,
        Veiculo veiculo,
        Boolean ativo
) {
    public DadosDetalhamentoConserto(Conserto conserto) {
        this(
                conserto.getId(),
                conserto.getDataEntrada(),
                conserto.getDataSaida(),
                conserto.getMecanico(),
                conserto.getVeiculo(),
                conserto.getAtivo()
        );
    }
}