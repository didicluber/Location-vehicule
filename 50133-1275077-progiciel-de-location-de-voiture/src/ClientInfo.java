public class ClientInfo {

	private String nom,prenom,securite_social,adresse,telephone,numero_permis;


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSecurite_social() {
		return securite_social;
	}
	public void setSecurite_social(String securite_social) {
		this.securite_social = securite_social;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNumero_permis() {
		return numero_permis;
	}
	public void setNumero_permis(String numero_permis) {
		this.numero_permis = numero_permis;
	}
	public ClientInfo(){}
	public ClientInfo(String nom, String prenom, String securite_social, String adresse,
						String telephone, String numero_permis){
		this.nom = nom;
		this.prenom = prenom;
		this.securite_social = securite_social;
		this.adresse = adresse;
		this.telephone = telephone;
		this.numero_permis = numero_permis;
	}
	
	
	

	public String toString(){
		String str;
		if(this.nom != null && this.prenom != null &&
				this.securite_social != null && this.adresse != null &&
				this.telephone != null &
				this.numero_permis != null){
			str = "Le client \n :";
			str += "Nom : " + this.nom + "\n";
			str += "Prenom : " + this.prenom + "\n";
			str += "Numéro Securité Social : " + this.securite_social + "\n";
			str += "Adresse  : " + this.adresse + "\n";
			str += "Téléphone : " + this.telephone + "\n";
			str += "Numéro du permis : " + this.numero_permis + "\n";
			
			
		}
		else{
			str = "Aucune information !";
		}
		return str;
	}
}
