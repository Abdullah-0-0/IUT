//DOCUMENTATION FAITES 

//CREATION DES TABLES

CREATE table EMPLOYE as SELECT * from basetd.employe;

CREATE table SERVICE as SELECT * from basetd.Service;

CREATE table PROJET as SELECT * from basetd.PROJET;

CREATE table TRAVAIL as SELECT * from basetd.TRAVAIL;

CREATE table CONCERNE as SELECT * from basetd.CONCERNE;


// SUPPRESION TABLE 
DROP TABLE EMPLOYE;

DROP TABLE SERVICE;

DROP TABLE PROJET;

DROP TABLE TRAVAIL;

DROP TABLE CONCERNE;

//declaration les clés primaire 

ALTER TABLE EMPLOYE add CONSTRAINT PK_empl PRIMARY key (nuempl);
// precise que le couple nuempl et affect sont unique (A FAIRE APRES LA CREATION DE MA CLÉ ETRANGERE).
ALTER TABLE EMPLOYE add CONSTRAINT U_employe UNIQUE(nuempl,affect);

ALTER TABLE EMPLOYE DROP CONSTRAINT U_EMPLOYE;

ALTER TABLE Service add CONSTRAINT PK_Service PRIMARY KEY (nuserv);

ALTER TABLE EMPLOYE ADD CONSTRAINT FK_ServiceEmplo UNIQUE (nuempl , affect) INITIALLY DEFERRED;

ALTER TABLE PROJET ADD CONSTRAINT PK_PROJET PRIMARY KEY (nuproj) ;

ALTER TABLE TRAVAIL ADD CONSTRAINT PK_travail PRIMARY KEY (nuempl,nuproj) ;

ALTER TABLE CONCERNE ADD CONSTRAINT PK_concerne PRIMARY KEY (nuserv,nuproj);


// clées etrangeres


ALTER  TABLE employe ADD CONSTRAINT FK_affect FOREIGN KEY (affect) REFERENCES service (nuserv);

ALTER TABLE PROJET ADD CONSTRAINT FK_Projet_emploi FOREIGN KEY (RESP) REFERENCES EMPLOYE(NUEMPL);

ALTER TABLE TRAVAIL ADD CONSTRAINT FK_travailEmploye FOREIGN KEY (NUEMPL) REFERENCES EMPLOYE (NUEMPL);
ALTER TABLE TRAVAIL ADD CONSTRAINT FK_TravailProjet FOREIGN KEY(NUPROJ) REFERENCES PROJET(NUPROJ);

ALTER TABLE CONCERNE ADD CONSTRAINT FK_ConcerneServ FOREIGN KEY (NUSERV) REFERENCES SERVICE(NUSERV);
ALTER TABLE CONCERNE ADD CONSTRAINT FK_ConcerneProjet FOREIGN KEY (NUPROJ) REFERENCES PROJET (NUPROJ);

//SELECTION 
SELECT * FROM employe;
SELECT * FROM SERVICE;

//Vérifiez que tout cela fonctionne bien en faisant des insertions/suppressions/modifications interdites
//et/ou autorisées

//INSERTON INTERDIE AVEC UN SERVICE INNEXISTANT
INSERT INTO EMPLOYE(nuempl,nomempl,hebdo,affect)
VALUES (09,'ABDULLAH',0,6);

// INSERTION AVEC UN SERVICE EXISTANT 
INSERT INTO EMPLOYE(NUEMPL,NOMEMPL,HEBDO,AFFECT)
VALUES ( 20 , 'ABDULLAH' , 0 , 5);
        
// SUPPRESSION INTERDIT D'UN SERVICE (violation de contrainte (S3A02A.FK_AFFECT) d'intégrité)
DELETE EMPLOYE WHERE NUEMPL  = 99;

// SUPPRESSION INTERDIT D'UN SERVICE
DELETE SERVICE WHERE NUSERV = 1;

// –insertion d'un employé qui existe déjà
INSERT INTO SERVICE VALUES ( 1 ,' ', 2);

// INSERTION INTERDIT NOM TROP LONG 
INSERT INTO EMPLOYE VALUES ( 03 , 'DFHHHHHHHHHHHHHHHHJHHHJ',20,3);

// INSERTION INTERDIT ID TROP GRAND 
INSERT INTO EMPLOYE VALUES (10000 , 'ABDULLAH',20,3);

// COMMIT ERREUR 
INSERT INTO SERVICE VALUES (10 ,' ',99);
INSERT INTO EMPLOYE VALUES ( 99 , ' ',0,1);



// mise a jour 

alter table employe add salaire number;

//tous les employe leur salaire a 2000
update  employe set salaire = 2000 ;

// Les chefs de service gagnent 3500 euros.
update employe set employe.salaire =3500 where employe.nuempl in (select chef from service) ;

//Les responsables de projet
update employe set employe.salaire =2500 where nuempl in (select distinct resp from projet);


// La somme des durées d'un employé (de la table travail) doit être inférieur à la durée
//hebdomadaire (Sum(duree) <= hebdo).

//select distinct nuempl from travail order by nuempl;
//select distinct nuempl , (select SUM(t2.duree) from travail t2 where t1.nuempl = t2.nuempl  )as heure from travail t1  ;

//SELECT nuempl from employe e where nuempl in (select t2.nuempl from travail t2 where employe.hebdo <= sum(t2.duree));

select * from employe e where e.hebdo < (select sum(t.duree) from travail t where t.nuempl = e.nuempl );


DELETE SERVICE WHERE NUSERV = 10;
commit ;

// RETOUR EN ARRIERE
rollback ;
