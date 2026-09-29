//CREATION TRIGGERS

//************Triggers de type For each row et utilisation de « :NEW » et « :OLD »*********
//A- Ecrire un trigger de type for each row qui interdit la diminution du salaire d'un employé. Ce
//trigger se déclenche après la modification du salaire

//CREATE OR REPLACE TRIGGER TrigSalaireDim AFTER UPDATE OF SALAIRE ON EMPLOYE FOR EACH ROW 
//BEGIN IF
// :NEW.SALAIRE < :OLD.SALAIRE 
// THEN RAISE_APPLICATION_ERROR(-12500,'VIOL DE REGLE : SALAIRE NE PEUT PAS ETRE DIMINUE');
// END IF ;
//END;
 
 SELECT * FROM EMPLOYE WHERE NUEMPL =37;
 
UPDATE EMPLOYE SET SALAIRE = 10 WHERE NUEMPL = 37;
 
// Rapport d'erreur -
//Erreur SQL : ORA-20001: VIOL DE REGLE : SALAIRE NE PEUT PAS ETRE DIMINUE
//ORA-06512: à "S3A02A.TRIGSALAIREDIM", ligne 4
//ORA-04088: erreur lors d'exécution du déclencheur 'S3A02A.TRIGSALAIREDIM'

 
//B- Il y a une autre contrainte qui n'est pas spécifiée "la durée hebdomadaire d'un employé ne peut

//pas augmenter", elle n'est pas descriptible. Vous écrivez le trigger nécessaire à la vérification de

//cette contrainte

//CREATE OR REPLACE TRIGGER TrigHebdoAugmen AFTER UPDATE OF HEBDO ON EMPLOYE FOR EACH ROW 
//BEGIN IF :NEW.HEBDO > :OLD.HEBDO THEN 
//RAISE_APPLICATION_ERROR(-20002 ,'VIOL DE REGLE : HEBDO NE PEUT PAS AUGMNTE');
//END IF;
//END ;

 SELECT * FROM EMPLOYE WHERE NUEMPL =37;
 UPDATE EMPLOYE SET HEBDO = 99 WHERE NUEMPL = 37;
 
//Erreur commençant à la ligne: 37 de la commande -
//UPDATE EMPLOYE SET HEBDO = 99 WHERE NUEMPL = 37
//Erreur à la ligne de commande: 37 Colonne: 8
//Rapport d'erreur -
//Erreur SQL : ORA-20002: VIOL DE REGLE : HEBDO NE PEUT PAS AUGMNTE
//ORA-06512: à "S3A02A.TRIGHEBDOAUGMEN", ligne 2
//ORA-04088: erreur lors d'exécution du déclencheur 'S3A02A.TRIGHEBDOAUGMEN'

//***************************Trigger de type : Delete**********************************

//A –La spécification de l'opération supprimer_employe impose que 
//  la suppression d'unemployé soit accompagnée de la suppression 
//  des lignes de travail correspondantes. Mettezen place un trigger 
//  table qui le fait. (pas de problème si on a déclaré "deferred" 
//  la contrainteFK_employe de la table travail vers la table employe 
//  "les employés de travailexistent").

/*
CREATE OR REPLACE TRIGGER trigSupprimer_employe
BEFORE DELETE ON employe
FOR EACH ROW
BEGIN
	DELETE FROM travail
	WHERE travail.nuempl = :OLD.nuempl;
END;
*/
/*
B – La spécification de l'opération supprimer_projet impose que la suppression d'un projet
soit accompagnée de la suppression des lignes de travail et de la table concerne
correspondantes. Mettez en place un trigger table qui effectue cela. (pas de problème si on a
déclaré "deferred" la contrainte FK_nuproj de la table travail et de la table concerne vers la
table projet).
*/

/*
create or replace TRIGGER trigSupprimer_TRAVAIL
BEFORE DELETE ON projet
FOR EACH ROW
BEGIN
	DELETE FROM travail
	WHERE travail.nuproj = :OLD.nuproj;

    DELETE FROM CONCERNE
    WHERE concerne.nuproj = :OLD.NUPROJ;
end;

*/

//Exercice 3

/*
A – Il y a une contrainte qui n'est pas spécifiée "la somme des durées de travail d'un
employé ne doit pas excéder son temps de travail hebdomadaire", elle n'est pas descriptible.
Vous écrivez le (ou les) trigger nécessaire à la vérification de cette contrainte.
*/

//La contrainte à mettre en place SUM(duree)<=hebdo.

/*
- Quels sont les différentes opérations(update, insert,…) sur les tables employe ou travail
qui vous amènent à ne pas respecter cette contrainte.
*/
// c'est les insertions et les mises à jour sur la table travail ou sur la table employe qui peuvent ne pas respecter cette contrainte.

/*
- Combien de trigger allez vous mettre en place ? Indiquez dans quel cas les trigger se
déclenchent.
*/
 
// je vais mettre en place 2 triggers, un sur la table travail et un sur la table employe.
// Le trigger sur la table travail se déclenche lors d'une insertion ou d'une mise à jour de la durée de travail, 
//et le trigger sur la table employe se déclenche lors d'une mise à jour du temps de travail hebdomadaire.

/*
CREATE OR REPLACE TRIGGER TRG_VERIF_TRAVAIL
AFTER INSERT OR UPDATE OF DUREE, NUEMPL
ON TRAVAIL
DECLARE v_employe EMPLOYE%ROWTYPE;
BEGIN
    SELECT * INTO v_employe FROM EMPLOYE e WHERE (
        SELECT SUM(duree) FROM TRAVAIL t WHERE e.nuempl = t.nuempl) 
        > e.hebdo;
    RAISE_APPLICATION_ERROR(-20010,
    'ERREUR : la somme des durées de travail dépasse la durée hebdomadaire'
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
END;
*/
//PUIS SUR CELUI DE LA TABLE EMPLOYE
/*
CREATE OR REPLACE TRIGGER TRG_VERIF_HEBDO
AFTER UPDATE OF HEBDO ON EMPLOYE
DECLARE v_employe EMPLOYE%ROWTYPE;

BEGIN
SELECT *INTO v_employe FROM EMPLOYE e WHERE (SELECT SUM(duree)
        FROM TRAVAIL t WHERE e.nuempl = t.nuempl) 
        > e.hebdo;

    RAISE_APPLICATION_ERROR(-20011,
        'ERREUR : la durée hebdomadaire devient inférieure à la somme des durées de travail'
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;

END;
*/

// insertion valide
INSERT INTO TRAVAIL VALUES (20, 103, 10);
UPDATE TRAVAIL SET DUREE = 12 WHERE NUEMPL = 52 AND NUPROJ = 492;
// insertion invalide
INSERT INTO TRAVAIL VALUES (20, 103, 30);
UPDATE TRAVAIL SET DUREE = 20 WHERE NUEMPL = 52 AND NUPROJ = 492;

/*
B - Ecrire un trigger qui vérifie la contrainte suivante: « un employé est responable au plus
sur 3 projets ».
Idem que la question précédente, vous utilisez la requête suivante pour construire votre
trigger : 
*/

// le trigger se déclenche quand on fait une  INSERT sur un nouveau projet avec un responsable qui en possède déjà 3 ou si on fait un 
//UPDATE de RESP dans PROJET,sur à un employé déjà responsable de 3 projets.


// il peux fait d'une seul triggers

/*
CREATE OR REPLACE TRIGGER TRG_MAX_3_PROJETS
AFTER INSERT OR UPDATE OF RESP ON PROJET
DECLARE v_employe EMPLOYE%ROWTYPE;
BEGIN
 SELECT * INTO v_employe FROM EMPLOYE e
    WHERE ( SELECT COUNT(*) FROM PROJET p WHERE e.nuempl = p.resp) 
    > 3;
    RAISE_APPLICATION_ERROR(-20020,
        'ERREUR : employe ne peut pas etre responsable de plus de 3 projets'
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;

END;

*/
// insertion interdit

INSERT INTO PROJET 
VALUES (999, 'TEST', 30);

//C- Ecrire un trigger qui vérifie la contrainte suivante : « un service ne peut être concerné par
//plus de 3 projets ». 

/*
CREATE OR REPLACE TRIGGER TRG_MAX_3_PROJETS_SERVICE
AFTER INSERT OR UPDATE OF NUSERV ON CONCERNE
DECLARE v_service SERVICE%ROWTYPE;
BEGIN
 SELECT * INTO v_service FROM SERVICE s
    WHERE ( SELECT COUNT(*) FROM CONCERNE c WHERE c.NUSERV = s.NUSERV)
    > 3;

    RAISE_APPLICATION_ERROR(-20030,
        'ERREUR : un service ne peut pas être concerné par plus de 3 projets'
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;
END;
*/
//insertion interdit
INSERT INTO CONCERNE (NUSERV, NUPROJ) VALUES (5, 103);

//update interdit 
UPDATE CONCERNE SET NUSERV = 5 WHERE NUSERV = 3 AND NUPROJ = 237;
 
//D- Ecrire un trigger qui vérifie la contrainte suivante : « un chef de service gagne plus que
//les employés de son service. 


CREATE OR REPLACE TRIGGER TRG_SALAIRE_CHEF_EMPLOYE
AFTER INSERT OR UPDATE OF SALAIRE, AFFECT
ON EMPLOYE
DECLARE v_service SERVICE%ROWTYPE;
BEGIN
 SELECT * INTO v_service FROM SERVICE s
    WHERE EXISTS ( SELECT 1 FROM EMPLOYE e JOIN EMPLOYE chef ON chef.NUEMPL = s.CHEF
        WHERE e.AFFECT = s.NUSERV
          AND e.NUEMPL != s.CHEF
          AND e.SALAIRE !=  chef.SALAIRE
    );

    RAISE_APPLICATION_ERROR(-20040,
        'ERREUR : le chef de service doit gagner plus que les employés de son service'
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;

END;

// update autorisée
UPDATE EMPLOYE SET SALAIRE = 2000 WHERE NUEMPL = 39;
// update interdit
UPDATE EMPLOYE SET SALAIRE = 100 WHERE NUEMPL = 41;


//E- Ecrire un trigger qui vérifie la contrainte suivante : « un chef de service gagne plus que
//les employés responsables de projets. 
select * from employe;
