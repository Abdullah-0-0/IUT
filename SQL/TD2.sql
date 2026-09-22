//CREATION TRIGGERS

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



// ALTER TABLE TRAVAIL DROP CONSTRAINT NOM_CONSTRAINT
// REÉCRIRE AVEC INITILLY DEFERRED A LA FIN 