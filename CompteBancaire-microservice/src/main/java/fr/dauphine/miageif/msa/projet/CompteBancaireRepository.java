package fr.dauphine.miageif.msa.projet;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
	
  CompteBancaire findById(long id);

}

