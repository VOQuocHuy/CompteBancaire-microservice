package fr.dauphine.miageif.msa.projet;


import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class ServiceController {

    @Autowired
    private CompteBancaireRepository repository;

    @GetMapping("/compte")
    public List<CompteBancaire> consultercompte() {
    	return repository.findAll();
    }
    
    @GetMapping("/comptes/{id}")
    public Optional<CompteBancaire> cherchercompte(@PathVariable Long id) throws CompteBancaireNotFoundException {
    	Optional<CompteBancaire> cb = repository.findById(id);
    	 if(!cb.isPresent()) throw new CompteBancaireNotFoundException("Le compte bancaire correspondant à l'id " + id + " n'existe pas");
    	return cb;
    }
    
    @DeleteMapping("/comptes/delete/{id}")
    public void deleteMethod(@PathVariable long id) {
    	repository.deleteById(id);
    }   
    
    @PostMapping("/comptes/new")
    public ResponseEntity<CompteBancaire> creercompte(@RequestBody CompteBancaire compte) throws CompteBancaireNotFoundException{
    	 CompteBancaire nouvelcompte = repository.save(compte);  

         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                 .buildAndExpand(nouvelcompte.getId()).toUri();

         return ResponseEntity.created(location).build();
    	
    	
    }
    
    @PutMapping("/comptes/update/{id}")
    public ResponseEntity<CompteBancaire> modifiercompte(@RequestBody CompteBancaire compte, @PathVariable Long id) {

        Optional<CompteBancaire> cb = repository.findById(id);

        if (!cb.isPresent())
            return ResponseEntity.notFound().build();

        compte.setId(id);

        repository.save(compte);

        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value="/view",method = {RequestMethod.GET})
    public ModelAndView getAccountForDemo() {

        ModelAndView mav = new ModelAndView("compte/afficherCompte");

        List<CompteBancaire> compte =  this.repository.findAll();
        mav.addObject("compte", compte);

        System.out.println(compte.toString());
        return mav;
    }

    @PutMapping("/virement/{id1}/{id2}")
    public ResponseEntity<CompteBancaire> faireUnVirement (@PathVariable long id1,
                                                   @PathVariable long id2,
                                                @RequestParam(value = "montant", required = false) Double montant){
            		
    	CompteBancaire compte1 = repository.findById(id1);
    	CompteBancaire compte2 = repository.findById(id2);
        compte1.setSolde(compte1.getSolde()-montant);
        compte2.setSolde(compte2.getSolde()+montant);

        repository.save(compte1);
        repository.save(compte2);

        return ResponseEntity.noContent().build();
    }
}
