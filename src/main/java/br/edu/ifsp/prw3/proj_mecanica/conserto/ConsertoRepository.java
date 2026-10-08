package br.edu.ifsp.prw3.proj_mecanica.conserto;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {
    List<Conserto> findAllByAtivoTrue();
}