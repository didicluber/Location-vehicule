public class LocationInfo {

	private String marque,modele,type,numeromeneralogique, kilometrage,prix;


	public LocationInfo(){}
	public LocationInfo(String numeromeneralogique, String marque, String modele,
						String prix, String kilometrage){
		this.numeromeneralogique = numeromeneralogique;
		this.marque = marque;
		this.modele = modele;
		this.prix = prix;
		this.kilometrage = kilometrage;
	}
	
	
	

	
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumeromeneralogique() {
		return numeromeneralogique;
	}
	public void setNumeromeneralogique(String numeromeneralogique) {
		this.numeromeneralogique = numeromeneralogique;
	}
	public String getKilometrage() {
		return kilometrage;
	}
	public void setKilometrage(String kilometrage) {
		this.kilometrage = kilometrage;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String toString(){
		String str;
		if(this.marque != null && this.modele != null &&
				this.prix != null && this.numeromeneralogique != null &&
				this.kilometrage != null){
			str = "Votre vehicule est :";
			str += "marque : " + this.marque + "\n";
			str += "Modele : " + this.modele + "\n";
			str += "numero meneralogique : " + this.numeromeneralogique + "\n";
			str += "Kilometrage : " + this.kilometrage + "\n";
			str += "prix : " + this.prix + "\n";
			
			
		}
		else{
			str = "Aucune information !";
		}
		return str;
	}
}
