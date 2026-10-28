/*create or replace package MAJ is 
PROCEDURE EMPLOYE (LE_NUEMPL IN NUMBER, LE_NOMEMPL IN VARCHAR2, LE_HEBDO IN NUMBER, LE_AFFECT IN NUMBER,LE_SALAIRE IN NUMBER);
END;
*/
/*
create or replace package  BODY MAJ is 
PROCEDURE EMPLOYE (LE_NUEMPL IN NUMBER, LE_NOMEMPL IN VARCHAR2, LE_HEBDO IN NUMBER, LE_AFFECT IN NUMBER,LE_SALAIRE IN NUMBER) is
BEGIN
SET TRANSACTION READ WRITE;
INSERT INTO employe VALUES(LE_NUEMPL, LE_NOMEMPL, LE_HEBDO, LE_AFFECT,LE_SALAIRE);
COMMIT;
EXCEPTION WHEN OTHERS THEN ROLLBACK;
IF SQLCODE=-00001 THEN ROLLBACK;
            RAISE_APPLICATION_ERROR (-20401, 'Un employe avec le meme numero existe deja');
ELSE  RAISE_APPLICATION_ERROR (-20999,'Erreur inconnue'||SQLcode);
END IF; 
END;
END;
*/

/*
a) Compléter la procédure « Creer_employe » qui se trouve sur madoc, dans le fichier
Procedure_Package_MAJ.txt. Cette procédure est dans un package. Vous devriez compléter la
procédure Creer_employe qui se trouve dans le package BODY. Vous compilez la package
spécifications(signature des procédures) avant le package body.
b) Reporter ces erreurs dans le tableau(remplacer les XX).
*/

/*
CREATE OR REPLACE PACKAGE BODY MAJ IS 
PROCEDURE EMPLOYE(NUEMPL IN NUMBER, NOMEMPL IN VARCHAR2, LEHEBDO IN NUMBER ,AFFECT IN NUMBER, SALAIRE IN NUMBER) IS
BEGIN
SET TRANSACTION READ WRITE;
INSERT INTO EMPLOYE VALUES(
      NUEMPL, NOMEMPL, LEHEBDO, AFFECT, SALAIRE);
COMMIT;
EXCEPTION WHEN OTHERS THEN ROLLBACK;
IF SQLCODE =-00001 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20001,'Un employe avec le meme numero existe deja');
ELSIF SQLCODE =-2291 THEN ROLLBBACK;
      RAISE_APPLICATION_ERROR(-20002,'Le service auquel il est affecté n’existe pas');
ELSIF SQLCODE =-20011 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20003,'la durée hebdomadaire d’un employé doit être inférieure ou égale à 35h');
ELSIF SQLCODE =-1438 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20004,'Une valeur (nombre) dépasse le nombre de caractères autorisés');
ELSIF SQLCODE =-12899 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20005,'Une valeur (chaine de caractère) dépasse le nombre de caractères autorisés');
ELSIF SQLCODE =-20040 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20006,'Le salaire de cet employé dépasse celui de son chef de service');
      
ELSE  RAISE_APPLICATION_ERROR(-20099,'Erreur inconnue'|| SQLCODE);
END IF;
END;
END;

*/

create or replace PACKAGE BODY MAJ IS 
PROCEDURE CREE_EMPLOYE(LE_NUEMPL IN NUMBER, LE_NOMEMPL IN VARCHAR2, LE_HEBDO IN NUMBER ,LE_AFFECT IN NUMBER, LE_SALAIRE IN NUMBER) IS
BEGIN
SET TRANSACTION READ WRITE;
INSERT INTO EMPLOYE VALUES(
      LE_NUEMPL, LE_NOMEMPL, LE_HEBDO, LE_AFFECT, LE_SALAIRE);
COMMIT;
EXCEPTION WHEN OTHERS THEN ROLLBACK;
IF SQLCODE =-00001 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20001,'Un employe avec le meme numero existe deja');
ELSIF SQLCODE =-2291 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20002,'Le service auquel il est affecté n’existe pas');
ELSIF SQLCODE =-20011 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20003,'la durée hebdomadaire d’un employé doit être inférieure ou égale à 35h');
ELSIF SQLCODE =-1438 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20004,'Une valeur (nombre) dépasse le nombre de caractères autorisés');
ELSIF SQLCODE =-12899 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20005,'Une valeur (chaine de caractère) dépasse le nombre de caractères autorisés');
ELSIF SQLCODE =-20040 THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20006,'Le salaire de cet employé dépasse celui de son chef de service');

ELSE  RAISE_APPLICATION_ERROR(-20099,'Erreur inconnue'|| SQLCODE);
END IF;
END CREE_EMPLOYE;

PROCEDURE MODIF_EMPLOYE(LE_NUEMPL IN NUMBER, LE_HEBDO IN NUMBER ) IS
PAS_EMPLOYE EXCEPTION;
BEGIN 
UPDATE EMPLOYE SET HEBDO = LE_HEBDO WHERE NUEMPL = LE_NUEMPL;
IF SQL%ROWCOUNT = 0 THEN RAISE PAS_EMPLOYE;
END IF;
EXCEPTION WHEN PAS_EMPLOYE THEN ROLLBACK;
      RAISE_APPLICATION_ERROR(-20007,'Aucun employé avec ce numéro n’existe');
END MODIF_EMPLOYE;

END MAJ;
/

// creation d'un employé avec un id déjà utilisé , creation impossible
MAJ.CREE_EMPLOYE(20,'test',35,3,2000);

/*
ERROR à la ligne 1:
ORA-20001: Un employe avec le meme numero existe deja
ORA-06512: à "S3A02A.MAJ", ligne 10
ORA-06512: à ligne 2
*/