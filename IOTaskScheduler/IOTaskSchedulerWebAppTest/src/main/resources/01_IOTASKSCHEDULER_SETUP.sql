CREATE SCHEMA IF NOT EXISTS TASKSCHEDULER;
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_4A0E8AD2_48B7_4F8A_9A82_9B5EBAC27E99 START WITH 2 BELONGS_TO_TABLE;            
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_CF963BB4_4648_45BC_8692_4A35495A02C1 START WITH 2 BELONGS_TO_TABLE;            
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_1C4E58EE_6F36_448F_8443_1D7E2B2941F4 START WITH 2 BELONGS_TO_TABLE;            
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_5DCBEA10_03B6_4F60_AB29_FA0A99544A5D START WITH 2 BELONGS_TO_TABLE;            
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_0688215A_4D1D_4898_9281_245EBEC5109A START WITH 2 BELONGS_TO_TABLE;            
CREATE CACHED TABLE TASKSCHEDULER.EVENT(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_5DCBEA10_03B6_4F60_AB29_FA0A99544A5D) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_5DCBEA10_03B6_4F60_AB29_FA0A99544A5D,
    NAME VARCHAR(100) NOT NULL
);
ALTER TABLE TASKSCHEDULER.EVENT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_3 PRIMARY KEY(ID);    
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.EVENT;            
INSERT INTO TASKSCHEDULER.EVENT(ID, NAME) VALUES
(1, 'TESTEVENT');           
CREATE CACHED TABLE TASKSCHEDULER.EVENTTASK(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_4A0E8AD2_48B7_4F8A_9A82_9B5EBAC27E99) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_4A0E8AD2_48B7_4F8A_9A82_9B5EBAC27E99,
    NAME VARCHAR(100) NOT NULL,
    EVENT_EVENTID INTEGER NOT NULL
);       
ALTER TABLE TASKSCHEDULER.EVENTTASK ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_A PRIMARY KEY(ID);
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.EVENTTASK;        
INSERT INTO TASKSCHEDULER.EVENTTASK(ID, NAME, EVENT_EVENTID) VALUES
(1, 'TESTEVENTTASK', 1); 
CREATE CACHED TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT(
    EVENTTASKS_ID INTEGER NOT NULL,
    ISSUEDRAFTS_ID INTEGER NOT NULL
);         
ALTER TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_37 PRIMARY KEY(EVENTTASKS_ID, ISSUEDRAFTS_ID);         
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.EVENTTASK_ISSUEDRAFT;             
INSERT INTO TASKSCHEDULER.EVENTTASK_ISSUEDRAFT(EVENTTASKS_ID, ISSUEDRAFTS_ID) VALUES
(1, 1); 
CREATE CACHED TABLE TASKSCHEDULER.ISSUEDRAFT(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_CF963BB4_4648_45BC_8692_4A35495A02C1) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_CF963BB4_4648_45BC_8692_4A35495A02C1,
    ISSUEDESCRIPTION VARCHAR(500) NOT NULL,
    ISSUENAME VARCHAR(100) NOT NULL,
    ISSUETYPE VARCHAR(255) NOT NULL
);   
ALTER TABLE TASKSCHEDULER.ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_7 PRIMARY KEY(ID);               
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.ISSUEDRAFT;       
INSERT INTO TASKSCHEDULER.ISSUEDRAFT(ID, ISSUEDESCRIPTION, ISSUENAME, ISSUETYPE) VALUES
(1, 'TESTISSUE', 'TESTISSUE', 'BUG');
CREATE CACHED TABLE TASKSCHEDULER.ISSUEENTITY(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_1C4E58EE_6F36_448F_8443_1D7E2B2941F4) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_1C4E58EE_6F36_448F_8443_1D7E2B2941F4,
    ARCHIVED BOOLEAN NOT NULL,
    ISSUERESOLUTION VARCHAR(255) NOT NULL,
    ISSUESTATUS VARCHAR(255) NOT NULL,
    ISSUEDRAFT_ISSUEDRAFTID INTEGER NOT NULL
);        
ALTER TABLE TASKSCHEDULER.ISSUEENTITY ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_F PRIMARY KEY(ID);              
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.ISSUEENTITY;      
INSERT INTO TASKSCHEDULER.ISSUEENTITY(ID, ARCHIVED, ISSUERESOLUTION, ISSUESTATUS, ISSUEDRAFT_ISSUEDRAFTID) VALUES
(1, FALSE, 'UNRESOLVED', 'NEW', 1);        
CREATE CACHED TABLE TASKSCHEDULER.TIMETASK(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_0688215A_4D1D_4898_9281_245EBEC5109A) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_0688215A_4D1D_4898_9281_245EBEC5109A,
    ACTIVATED BOOLEAN NOT NULL,
    FIRECOUNT INTEGER NOT NULL,
    FIRSTFIRETIME TIMESTAMP NOT NULL,
    INTERVALL INTEGER NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    NEXTFIRETIME TIMESTAMP NOT NULL
);             
ALTER TABLE TASKSCHEDULER.TIMETASK ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_B PRIMARY KEY(ID); 
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.TIMETASK;         
INSERT INTO TASKSCHEDULER.TIMETASK(ID, ACTIVATED, FIRECOUNT, FIRSTFIRETIME, INTERVALL, NAME, NEXTFIRETIME) VALUES
(1, TRUE, 0, TIMESTAMP '2014-01-01 00:00:00.0', 3600, 'TESTTIMETASK', TIMESTAMP '2014-01-01 00:00:00.0');  
CREATE CACHED TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT(
    TIMETASKS_ID INTEGER NOT NULL,
    ISSUEDRAFTS_ID INTEGER NOT NULL
);           
ALTER TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_B7 PRIMARY KEY(TIMETASKS_ID, ISSUEDRAFTS_ID);           
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.TIMETASK_ISSUEDRAFT;              
INSERT INTO TASKSCHEDULER.TIMETASK_ISSUEDRAFT(TIMETASKS_ID, ISSUEDRAFTS_ID) VALUES
(1, 1);   
ALTER TABLE TASKSCHEDULER.EVENTTASK ADD CONSTRAINT TASKSCHEDULER.FK79515F1FB219EBFC FOREIGN KEY(EVENT_EVENTID) REFERENCES TASKSCHEDULER.EVENT(ID) NOCHECK;    
ALTER TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FK212AF988BBEF8F85 FOREIGN KEY(ISSUEDRAFTS_ID) REFERENCES TASKSCHEDULER.ISSUEDRAFT(ID) NOCHECK;   
ALTER TABLE TASKSCHEDULER.ISSUEENTITY ADD CONSTRAINT TASKSCHEDULER.FKA1B459FC785FC0B0 FOREIGN KEY(ISSUEDRAFT_ISSUEDRAFTID) REFERENCES TASKSCHEDULER.ISSUEDRAFT(ID) NOCHECK;   
ALTER TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FKEBFCE0B5474AC519 FOREIGN KEY(TIMETASKS_ID) REFERENCES TASKSCHEDULER.TIMETASK(ID) NOCHECK;        
ALTER TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FKEBFCE0B5BBEF8F85 FOREIGN KEY(ISSUEDRAFTS_ID) REFERENCES TASKSCHEDULER.ISSUEDRAFT(ID) NOCHECK;    
ALTER TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FK212AF9886EDBB17 FOREIGN KEY(EVENTTASKS_ID) REFERENCES TASKSCHEDULER.EVENTTASK(ID) NOCHECK;      
