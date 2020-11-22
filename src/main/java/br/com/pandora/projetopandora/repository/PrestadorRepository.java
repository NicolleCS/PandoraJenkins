package br.com.pandora.projetopandora.repository;

import br.com.pandora.projetopandora.data.model.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

    @Query(value = "SELECT TOP 1 * FROM Prestadores WHERE email = :email", nativeQuery = true)
    Optional<Prestador> findByEmail(String email);

    @Query(value = "SELECT TOP 1 * FROM Prestadores WHERE cnpj = :cnpj", nativeQuery = true)
    Optional<Prestador> findByCnpj(String cnpj);

    @Query(value = "SELECT TOP 1 * FROM Prestadores WHERE cpf = :cpf", nativeQuery = true)
    Optional<Prestador> findByCpf(String cpf);

    Optional<Prestador> getById(Integer id);

    Optional<Prestador> getByEmail(String email);

    @Query("SELECT a from Solicitacao s JOIN s.fkAvaliacao a where s.fkPrestador.id = ?1")
    List findAllAvaliacoes(Integer id);

}
