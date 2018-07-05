INSERT INTO CLIENT (typeClient,nom,prenom,dateNaissance) VALUES ('PP','Fabre','Arthur','1995-08-11');
INSERT INTO CLIENT (typeClient,nom,prenom,dateNaissance) VALUES ('PP','Camelot','Yann','1993-05-13');
INSERT INTO CLIENT (typeClient,nom,prenom,dateNaissance) VALUES ('PP','Borveto','Fanni','1993-12-14');
INSERT INTO CLIENT (typeClient,nom,prenom,dateNaissance) VALUES ('PP','Sainte Rose','Alan','1995-01-01');
INSERT INTO CLIENT (typeClient,nom,prenom,dateNaissance) VALUES ('PP','Le Philippe','Aubin','1995-09-06');
INSERT INTO CLIENT (typeClient,nom, siren) VALUES ('PM','Capelec','485452151');
INSERT INTO CLIENT (typeClient,nom, siren) VALUES ('PM','Sopra Steria','541214584');
INSERT INTO CLIENT (typeClient,nom, siren) VALUES ('PM','Inaxel','696969696');

INSERT INTO ASSURANCE (typeAssurance,dateSouscription,dateAnniversaire,datePrelevement,adresseAssuree,valeurCouverture,idAssure)
VALUES('AH','2017-08-12','2018-09-12','2018-08-12','7 boulevard st jean 34150 ANIANE',400,(SELECT idClient FROM CLIENT WHERE nom='Fabre'));
INSERT INTO ASSURANCE (typeAssurance,dateSouscription,dateAnniversaire,datePrelevement,immatriculation, bonusMalus)
VALUES('AA','2012-09-15','2018-07-01','2018-08-21','DK074FH',-55);

INSERT INTO CONTACT(client, cType, valeur) 
VALUES ((SELECT idClient FROM CLIENT WHERE nom='Fabre'), 'PORTABLE', '0645488778');
INSERT INTO CONTACT(client, cType, valeur) 
VALUES ((SELECT idClient FROM CLIENT WHERE nom='Fabre'), 'PORTABLE', '0645488778');
INSERT INTO CONTACT(client, cType, valeur) 
VALUES ((SELECT idClient FROM CLIENT WHERE nom='Camelot'), 'PORTABLE', '0642922057');
INSERT INTO CONTACT(client, cType, valeur) 
VALUES ((SELECT idClient FROM CLIENT WHERE nom='Camelot'), 'MAIL', 'y.camelot@gmail.com');

INSERT INTO COMPTEBANCAIRE(iban, proprietaire, bic) 
VALUES ('lecomptelol',(SELECT idClient FROM CLIENT WHERE nom='Camelot'),'JDHDHHH7G45');
INSERT INTO COMPTEBANCAIRE(iban, proprietaire, bic) 
VALUES ('ouiouiceciest1iban',(SELECT idClient FROM CLIENT WHERE nom='Fabre'),'PAPKJXHC45');
INSERT INTO COMPTEBANCAIRE(iban, proprietaire, bic) 
VALUES ('lecomptedesesclavagistes',(SELECT idClient FROM CLIENT WHERE nom='Sopra Steria'),'PR0L034');
INSERT INTO COMPTEBANCAIRE(iban, proprietaire, bic) 
VALUES ('lesesclavagistesontdessous',(SELECT idClient FROM CLIENT WHERE nom='Sopra Steria'),'PR0L078');
INSERT INTO COMPTEBANCAIRE(iban, proprietaire, bic) 
VALUES ('w1nd3vc7r0c001',(SELECT idClient FROM CLIENT WHERE nom='Inaxel'),'CAMPING');

UPDATE CLIENT SET ibanComptePrincipal='lecomptelol' WHERE nom='Fabre';
UPDATE CLIENT SET ibanComptePrincipal='ouiouiceciest1iban' WHERE nom='Camelot';
UPDATE CLIENT SET ibanComptePrincipal='lecomptedesesclavagistes' WHERE nom='Sopra Steria';

INSERT INTO CLIENT_ASSURANCE(idClient,idAssurance) VALUES
((SELECT idClient FROM CLIENT WHERE nom='Borveto'),
(SELECT idAssurance FROM ASSURANCE WHERE adresseAssuree='7 boulevard st jean 34150 ANIANE'));
INSERT INTO CLIENT_ASSURANCE(idClient,idAssurance) VALUES
((SELECT idClient FROM CLIENT WHERE nom='Camelot'),
(SELECT idAssurance FROM ASSURANCE WHERE immatriculation='DK074FH'));
INSERT INTO CLIENT_ASSURANCE(idClient,idAssurance) VALUES
((SELECT idClient FROM CLIENT WHERE nom='Fabre'),
(SELECT idAssurance FROM ASSURANCE WHERE immatriculation='DK074FH'));

INSERT INTO ECHEANCES(assuranceLiee,prix,dateEmission) VALUES
((SELECT idAssurance FROM ASSURANCE WHERE adresseAssuree='7 boulevard st jean 34150 ANIANE'),200,'2018-06-29');
INSERT INTO ECHEANCES(assuranceLiee,prix,dateEmission) VALUES
((SELECT idAssurance FROM ASSURANCE WHERE adresseAssuree='7 boulevard st jean 34150 ANIANE'),200,'2018-06-30');
INSERT INTO ECHEANCES(assuranceLiee,prix,dateEmission) VALUES
((SELECT idAssurance FROM ASSURANCE WHERE idAssurance=2),200,'2018-06-30');


INSERT INTO ASSURANCE (typeAssurance,dateSouscription,dateAnniversaire,datePrelevement,adresseAssuree,valeurCouverture,idAssure)
VALUES('AA','2017-07-05','2018-07-05','2018-07-05','EPSI',200,(SELECT idClient FROM CLIENT WHERE nom='Fabre'));
INSERT INTO ECHEANCES(assuranceLiee,prix,dateEmission) VALUES
(3,200,'2018-06-30');