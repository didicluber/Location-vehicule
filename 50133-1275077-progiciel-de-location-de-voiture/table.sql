
CREATE TABLE client
(

  nom varchar(64),
  prenom varchar(64),
  nss varchar(64),
  adresse varchar(64),
  tel varchar(64),
  permis varchar(64),
  etat varchar(64),
  CONSTRAINT client_pkey PRIMARY KEY (id)
)


CREATE TABLE facture
(

  nom varchar(64),
  prenom varchar(64),
  nss varchar(64),
  adresse varchar(64),
  tel varchar(64),
  permis varchar(64),
  etat varchar(64),
  marque varchar(64),
  modele varchar(64),
  numero_meneralogique varchar(64),
  kilometrage varchar(64),
  prix varchar(64),
  statut varchar(64),
  CONSTRAINT "Facture_pkey" PRIMARY KEY (id)
)


CREATE TABLE utilisateurs
(
 
  log varchar(64),
  passe varchar(64),
  ip varchar(64),
  statut varchar(64),
  CONSTRAINT "Utilisateurs_pkey" PRIMARY KEY (id)
)

CREATE TABLE vehicule
(

  marque varchar(64),
  modele varchar(64),
  numero_meneralogique varchar(64),
  kilometrage varchar(64),
  prix varchar(64),
  statut varchar(64),
  CONSTRAINT "Vehicule_pkey" PRIMARY KEY (id)
)