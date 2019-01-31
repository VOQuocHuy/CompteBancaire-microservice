# CompteBancaire-microservice

## Classes

| Object  | Type | Description |
| ------------- | ------------- | ------------- |
| Id  | Long  | Id du compte bancaire  |
| Iban  | String | IBAN du compte bancaire  |
| interet  | String | Interet du compte bancaire|
| Typecompte  | String | Type de compte bancaire  |
| fraistenue  | Double | Frais de tenue du compte bancaire  |
| solde  | Double | Solde du compte bancaire  |


## Méthodes

| Method  | Syntax | Description |
| ------------- | ------------- | ------------- |
| get()  |     public Long getId() | Renvoie de la valeur Id du compte   |
| set()  |     public void setId() | Définition de la valeur Id du compte   |
| get()  |     public String getIban() | Retour de la valeur Iban du compte   |
| set()  |     public void setIban() | Définition de la valeur Iban  du compte  |
| get()  |     public String gettypecompte() | Retour de la valeur type de compte |
| set()  |     public void settypecompte() | Définition de la valeur type de compte   |
| get()  |     public Double getInteret() | Retour de la valeur interet du compte   |
| set()  |     public void setInteret() | Définition de la valeur interet du compte   |
| get()  |     public Double getFrais() | Retour de la valeur frais de tenue du compte   |
| set()  |     public void setFrais() | Définition de la valeur frais de tenue du compte    |
| get()  |     public Double getSolde() | Retour de la valeur solde du compte |
| set()  |     public void setSolde() | Définition de la valeur solde du compte  |


## Repository
| Interface | Description |
| ------------- | ------------- | 
| CompteBancaireRepository |     Création de la couche d'accès aux données| 

## Rest API

| URL  | Method | Format |
| ------------- | ------------- | ------------- |
|/comptes  |    **GET** | XML, JSON  |
|/comptes/id     |   **GET**| XML, JSON  |
|/comptes/iban    |  **GET**| XML, JSON  |
|/comptes/delete/ |   **DELETE** | XML, JSON  |
|/comptes/new  |    **POST** | XML, JSON  |
|/comptes/update  |    **PUT** | XML, JSON  |
|/comptes/deposerouretirer|    **PUT** | XML, JSON  |
|/comptes/virement/{idsour}/{iddest}|    **PUT** | XML, JSON  |
