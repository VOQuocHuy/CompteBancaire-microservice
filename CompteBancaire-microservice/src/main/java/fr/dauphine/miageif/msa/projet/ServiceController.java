package fr.dauphine.miageif.msa.projet;


import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class ServiceController {

    @Autowired
    private CompteBancaireRepository repository;

    @GetMapping("/comptes")
    public List<CompteBancaire> consultercompte() {
    	return repository.findAll();
    }
    
    @GetMapping("/comptes/id/{id}")
    public CompteBancaire chercherid(@PathVariable long id) {
    	CompteBancaire cb = repository.findById(id);
    	if(cb == null) throw new RuntimeException("Le compte bancaire correspondant à l'id " + id + " n'existe pas");
    	return cb;
    }
   
    
    @GetMapping("/comptes/iban/{iban}")
    public CompteBancaire chercheriban(@PathVariable String iban) {
    	CompteBancaire cb = repository.findByIban(iban);
    	if(cb == null) throw new RuntimeException("Le compte bancaire correspondant à l'iban " + iban + " n'existe pas");
    	return cb;
    }    

    @DeleteMapping("/comptes/delete/id/{id}")
    public void deleteMethod(@PathVariable long id) {
    	repository.deleteById(id);
    }  
    
    @PostMapping("/comptes/new")
    public ResponseEntity<CompteBancaire> creercompte(@RequestBody CompteBancaire compte) {
    	 CompteBancaire nouvelcompte = repository.save(compte);  

         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                 .buildAndExpand(nouvelcompte.getId()).toUri();

         return ResponseEntity.created(location).build();
    	
    	
    }
    
    @PutMapping("/comptes/update/{id}")
    public ResponseEntity<CompteBancaire> modifiercompte(@RequestBody CompteBancaire compte, @PathVariable long id) {

        CompteBancaire cb = repository.findById(id);

        if(cb == null) throw new RuntimeException("Le compte bancaire correspondant à l'id " + id + " n'existe pas");

        compte.setId(id);

        repository.save(compte);

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/comptes/deposerouretirer/{id}")
    public ResponseEntity<Object> retirerArgent(@PathVariable long id,
    		                                    @RequestParam(value = "typeoperation") String type,
                                                @RequestParam(value = "montant", required = false) Double montant){
        
    	CompteBancaire cb = repository.findById(id);
    	
    	if(type.equals("deposer")){
    		 cb.setSolde(cb.getSolde() + montant);
    		 repository.save(cb);
		}
		else if(type.equals("retirer")){
			if(cb.getSolde() < montant)
				throw new RuntimeException("Solde insuffisant");
			cb.setSolde(cb.getSolde() - montant);
   		    repository.save(cb);
		}

       
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/comptes/virement/{idsour}/{iddest}")
    public ResponseEntity<CompteBancaire> faireUnVirement (@PathVariable long idsour,
                                                   @PathVariable long iddest,
                                                @RequestParam(value = "montant", required = false) Double montant){
 		
    	CompteBancaire comptesour = repository.findById(idsour);
    	CompteBancaire comptedest = repository.findById(iddest);

    	if(comptesour.equals(comptedest)){
			throw new RuntimeException("Impossible de faire un virement sur le même compte");
		}  
    	if(comptesour.getSolde() < montant)
			throw new RuntimeException("Solde insuffisant");
    	
    	comptesour.setSolde(comptesour.getSolde()-montant);
    	comptedest.setSolde(comptedest.getSolde()+montant);

        repository.save(comptesour);
        repository.save(comptedest);

        return ResponseEntity.noContent().build();
    }
}
