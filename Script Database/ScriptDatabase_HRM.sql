-- Start of DDL Script for Table TELSOFT01.ROLE_TELSOFT
-- Generated 02-Jul-2015 15:45:45 from TELSOFT01@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)))(CONNECT_DATA =(SERVICE_NAME = mountain)))

CREATE TABLE role_telsoft
    (role_id                        NUMBER(3,0) ,
    role_name                      VARCHAR2(300 CHAR),
    note                           VARCHAR2(4000 CHAR))
  SEGMENT CREATION IMMEDIATE
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  STORAGE   (
    INITIAL     65536
    NEXT        1048576
    MINEXTENTS  1
    MAXEXTENTS  2147483645
  )
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- Constraints for ROLE_TELSOFT

ALTER TABLE role_telsoft
ADD CONSTRAINT pk_role_roleid PRIMARY KEY (role_id)
USING INDEX
  PCTFREE     10
  INITRANS    2
  MAXTRANS    255
  TABLESPACE  users
  STORAGE   (
    INITIAL     65536
    NEXT        1048576
    MINEXTENTS  1
    MAXEXTENTS  2147483645
  )
/


-- End of DDL Script for Table TELSOFT01.ROLE_TELSOFT

-- Start of DDL Script for Table TELSOFT01.USER_TELSOFT
-- Generated 02-Jul-2015 15:45:59 from TELSOFT01@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)))(CONNECT_DATA =(SERVICE_NAME = mountain)))

CREATE TABLE user_telsoft
    (user_id                        NUMBER(4,0) ,
    user_name                      VARCHAR2(300 CHAR),
    full_name                      VARCHAR2(300 CHAR),
    birthday                       DATE,
    email                          VARCHAR2(300 CHAR),
    salary                         NUMBER(10,2),
    status                         NUMBER(1,0),
    role_id                        NUMBER(3,0),
    password                       VARCHAR2(300 CHAR))
  SEGMENT CREATION IMMEDIATE
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  STORAGE   (
    INITIAL     65536
    NEXT        1048576
    MINEXTENTS  1
    MAXEXTENTS  2147483645
  )
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- Constraints for USER_TELSOFT


ALTER TABLE user_telsoft
ADD CONSTRAINT pk_user_user_id PRIMARY KEY (user_id)
USING INDEX
  PCTFREE     10
  INITRANS    2
  MAXTRANS    255
  TABLESPACE  users
  STORAGE   (
    INITIAL     65536
    NEXT        1048576
    MINEXTENTS  1
    MAXEXTENTS  2147483645
  )
/


-- Triggers for USER_TELSOFT

CREATE OR REPLACE TRIGGER tg_user
 BEFORE
  INSERT
 ON user_telsoft
REFERENCING NEW AS NEW OLD AS OLD
 FOR EACH ROW
BEGIN
  SELECT seq_user.NEXTVAL
  INTO   :new.user_id
  FROM   dual;
END;
/


-- End of DDL Script for Table TELSOFT01.USER_TELSOFT

-- Foreign Key
ALTER TABLE user_telsoft
ADD CONSTRAINT fk_user_role_roleid FOREIGN KEY (role_id)
REFERENCES role_telsoft (role_id)
/
-- End of DDL script for Foreign Key(s)






-- Start of DDL Script for Sequence TELSOFT01.SEQ_ROLE_TELSOFT
-- Generated 02-Jul-2015 15:46:10 from TELSOFT01@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)))(CONNECT_DATA =(SERVICE_NAME = mountain)))

CREATE SEQUENCE seq_role_telsoft
  INCREMENT BY 1
  START WITH 101
  MINVALUE 1
  MAXVALUE 9999999999999999999999999999
  NOCYCLE
  NOORDER
  CACHE 20
/


-- End of DDL Script for Sequence TELSOFT01.SEQ_ROLE_TELSOFT

-- Start of DDL Script for Sequence TELSOFT01.SEQ_USER
-- Generated 02-Jul-2015 15:46:17 from TELSOFT01@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)))(CONNECT_DATA =(SERVICE_NAME = mountain)))

CREATE SEQUENCE seq_user
  INCREMENT BY 1
  START WITH 61
  MINVALUE 1
  MAXVALUE 9999999999999999999999999999
  NOCYCLE
  NOORDER
  CACHE 20
/


-- End of DDL Script for Sequence TELSOFT01.SEQ_USER




