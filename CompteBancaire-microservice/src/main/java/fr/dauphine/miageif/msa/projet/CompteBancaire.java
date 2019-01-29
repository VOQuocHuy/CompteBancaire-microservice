package fr.dauphine.miageif.msa.projet;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

@Entity
@Service
@Table(name = "compte_bancaire")
public class CompteBancaire {

    @Id 
    @GeneratedValue
    private Long id;
    @Column(name="IBAN")
    private String iban;    
    @Column(name="TypeCompte")
    private String typecompte;
    @Column(name="interet")
    private Double interet;
    @Column(name="fraistenue")
    private Double fraistenue;    
    @Column(name="solde")
    private Double solde; 
    
    public CompteBancaire() {}

    public CompteBancaire(String iban, String typecompte,Double interet,Double fraistenue, Double solde) {
        this.iban = iban;       
    	this.typecompte = typecompte;
        this.interet = interet;
        this.fraistenue = fraistenue;
        this.solde = solde;
    }
    
    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id=id;
    }
        
    
    public String getIban() {
    	return iban;
    }
    
    public void setIban(String iban) {
    	this.iban=iban;
    }
    
    public String gettypecompte() {
    	return typecompte;
    }
    public void settypecompte(String typecompte) {
    	this.typecompte=typecompte;
    }
   
    public Double getInteret() {
    	return interet;
    }
    public void setInteret(Double interet) {
    	this.interet=interet;
    }
    
    public Double getFrais() {
    	return fraistenue;
    }
    public void setFrais(Double fraistenue) {
    	this.fraistenue=fraistenue;
    }  
    
    public Double getSolde() {
    	return solde;
    }
    public void setSolde(Double solde) {
    	this.solde=solde;
    }     
  

}