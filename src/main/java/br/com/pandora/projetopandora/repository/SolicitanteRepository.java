package br.com.pandora.projetopandora.repository;

import br.com.pandora.projetopandora.data.model.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Integer> {

    @Query(value = "SELECT TOP 1 * FROM Solicitantes WHERE email = :email", nativeQuery = true)
    Optional<Solicitante> findByEmail(String email);

    @Query(value = "SELECT TOP 1 * FROM Solicitantes WHERE cnpj = :cnpj", nativeQuery = true)
    Optional<Solicitante> findByCnpj(String cnpj);

    @Query(value = "SELECT TOP 1 * FROM Solicitantes WHERE cpf = :cpf", nativeQuery = true)
    Optional<Solicitante> findByCpf(String cpf);

    Optional<Solicitante> getById(Integer id);

    Optional<Solicitante> getByEmail(String email);

}
