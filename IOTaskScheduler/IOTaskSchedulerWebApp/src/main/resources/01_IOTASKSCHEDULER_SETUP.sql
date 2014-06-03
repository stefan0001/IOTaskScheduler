;             
CREATE SCHEMA IF NOT EXISTS TASKSCHEDULER AUTHORIZATION H2; 

CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_F3C3C7BC_CFC0_4D9E_9BDD_360E58457324 START WITH 1 BELONGS_TO_TABLE;             
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_DC05AD94_11B3_4FC1_A6EF_80166D16DD9B START WITH 1 BELONGS_TO_TABLE;             
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_8AA79971_012A_4DDB_8A92_A901C91BE2C3 START WITH 1 BELONGS_TO_TABLE;             
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_3BA99A5E_1B7C_4AFF_A086_6C0FB136B2F1 START WITH 1 BELONGS_TO_TABLE;             
CREATE SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_B00D3F7B_13BA_4C34_A1FD_2BE691CDD37C START WITH 1 BELONGS_TO_TABLE;             
CREATE CACHED TABLE TASKSCHEDULER.EVENT(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_DC05AD94_11B3_4FC1_A6EF_80166D16DD9B) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_DC05AD94_11B3_4FC1_A6EF_80166D16DD9B,
    NAME VARCHAR(100) NOT NULL
);
ALTER TABLE TASKSCHEDULER.EVENT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_3 PRIMARY KEY(ID);    
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.EVENT;                 
CREATE CACHED TABLE TASKSCHEDULER.EVENTTASK(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_8AA79971_012A_4DDB_8A92_A901C91BE2C3) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_8AA79971_012A_4DDB_8A92_A901C91BE2C3,
    NAME VARCHAR(100) NOT NULL,
    EVENT_EVENTID INTEGER NOT NULL
);       
ALTER TABLE TASKSCHEDULER.EVENTTASK ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_A PRIMARY KEY(ID);
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.EVENTTASK;        
CREATE CACHED TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT(
    EVENTTASKS_ID INTEGER NOT NULL,
    ISSUEDRAFTS_ID INTEGER NOT NULL
);         
ALTER TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_37 PRIMARY KEY(EVENTTASKS_ID, ISSUEDRAFTS_ID);         
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.EVENTTASK_ISSUEDRAFT;             
CREATE CACHED TABLE TASKSCHEDULER.ISSUEDRAFT(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_F3C3C7BC_CFC0_4D9E_9BDD_360E58457324) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_F3C3C7BC_CFC0_4D9E_9BDD_360E58457324,
    ISSUEDESCRIPTION VARCHAR(500) NOT NULL,
    ISSUENAME VARCHAR(100) NOT NULL,
    ISSUETYPE VARCHAR(255) NOT NULL
);   
ALTER TABLE TASKSCHEDULER.ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_7 PRIMARY KEY(ID);               
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.ISSUEDRAFT;       
CREATE CACHED TABLE TASKSCHEDULER.ISSUEENTITY(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_B00D3F7B_13BA_4C34_A1FD_2BE691CDD37C) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_B00D3F7B_13BA_4C34_A1FD_2BE691CDD37C,
    ARCHIVED BOOLEAN NOT NULL,
    ISSUERESOLUTION VARCHAR(255) NOT NULL,
    ISSUESTATUS VARCHAR(255) NOT NULL,
    ISSUEDRAFT_ISSUEDRAFTID INTEGER NOT NULL
);        
ALTER TABLE TASKSCHEDULER.ISSUEENTITY ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_F PRIMARY KEY(ID);              
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.ISSUEENTITY;             
CREATE CACHED TABLE TASKSCHEDULER.TIMETASK(
    ID INTEGER DEFAULT (NEXT VALUE FOR TASKSCHEDULER.SYSTEM_SEQUENCE_3BA99A5E_1B7C_4AFF_A086_6C0FB136B2F1) NOT NULL NULL_TO_DEFAULT SEQUENCE TASKSCHEDULER.SYSTEM_SEQUENCE_3BA99A5E_1B7C_4AFF_A086_6C0FB136B2F1,
    ACTIVATED BOOLEAN NOT NULL,
    FIRECOUNT INTEGER NOT NULL,
    FIRSTFIRETIME TIMESTAMP NOT NULL,
    INTERVALL INTEGER NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    NEXTFIRETIME TIMESTAMP NOT NULL
);             
ALTER TABLE TASKSCHEDULER.TIMETASK ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_B PRIMARY KEY(ID); 
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.TIMETASK;         
CREATE CACHED TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT(
    TIMETASKS_ID INTEGER NOT NULL,
    ISSUEDRAFTS_ID INTEGER NOT NULL
);           
ALTER TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.CONSTRAINT_B7 PRIMARY KEY(TIMETASKS_ID, ISSUEDRAFTS_ID);           
-- 1 +/- SELECT COUNT(*) FROM TASKSCHEDULER.TIMETASK_ISSUEDRAFT;                
ALTER TABLE TASKSCHEDULER.EVENTTASK ADD CONSTRAINT TASKSCHEDULER.FK79515F1FB219EBFC FOREIGN KEY(EVENT_EVENTID) REFERENCES TASKSCHEDULER.EVENT(ID) NOCHECK;    
ALTER TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FK212AF988BBEF8F85 FOREIGN KEY(ISSUEDRAFTS_ID) REFERENCES TASKSCHEDULER.ISSUEDRAFT(ID) NOCHECK;   
ALTER TABLE TASKSCHEDULER.ISSUEENTITY ADD CONSTRAINT TASKSCHEDULER.FKA1B459FC785FC0B0 FOREIGN KEY(ISSUEDRAFT_ISSUEDRAFTID) REFERENCES TASKSCHEDULER.ISSUEDRAFT(ID) NOCHECK;   
ALTER TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FKEBFCE0B5474AC519 FOREIGN KEY(TIMETASKS_ID) REFERENCES TASKSCHEDULER.TIMETASK(ID) NOCHECK;        
ALTER TABLE TASKSCHEDULER.TIMETASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FKEBFCE0B5BBEF8F85 FOREIGN KEY(ISSUEDRAFTS_ID) REFERENCES TASKSCHEDULER.ISSUEDRAFT(ID) NOCHECK;    
ALTER TABLE TASKSCHEDULER.EVENTTASK_ISSUEDRAFT ADD CONSTRAINT TASKSCHEDULER.FK212AF9886EDBB17 FOREIGN KEY(EVENTTASKS_ID) REFERENCES TASKSCHEDULER.EVENTTASK(ID) NOCHECK;    
