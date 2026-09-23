--------------------------------------------------------
--  Fichier créé - mardi-septembre-22-2026   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PROJET
--------------------------------------------------------

  CREATE TABLE PROJET 
   (	NUPROJ NUMBER(3,0), 
	NOMPROJ CHAR(20 BYTE), 
	RESP NUMBER(4,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS ;
REM INSERTING into PROJET
SET DEFINE OFF;
Insert into PROJET (NUPROJ,NOMPROJ,RESP) values ('103','cobra               ','30');
Insert into PROJET (NUPROJ,NOMPROJ,RESP) values ('237','zorro               ','30');
Insert into PROJET (NUPROJ,NOMPROJ,RESP) values ('370','erasmus             ','57');
Insert into PROJET (NUPROJ,NOMPROJ,RESP) values ('492','commet              ','20');
Insert into PROJET (NUPROJ,NOMPROJ,RESP) values ('135','eureka              ','57');
Insert into PROJET (NUPROJ,NOMPROJ,RESP) values ('160','esprit              ','30');
--------------------------------------------------------
--  DDL for Index PK_PROJET
--------------------------------------------------------

  CREATE UNIQUE INDEX PK_PROJET ON PROJET (NUPROJ) 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS ;
--------------------------------------------------------
--  Constraints for Table PROJET
--------------------------------------------------------

  ALTER TABLE PROJET ADD CONSTRAINT PK_PROJET PRIMARY KEY (NUPROJ)
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS  ENABLE;
  ALTER TABLE PROJET MODIFY (RESP NOT NULL ENABLE);
  ALTER TABLE PROJET MODIFY (NOMPROJ NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table PROJET
--------------------------------------------------------

  ALTER TABLE PROJET ADD CONSTRAINT FK_PROJET_EMPLOI FOREIGN KEY (RESP)
	  REFERENCES EMPLOYE (NUEMPL) ENABLE;
