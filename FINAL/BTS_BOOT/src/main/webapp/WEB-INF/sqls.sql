CREATE SEQUENCE MEMBER_NO_SEQ;

CREATE TABLE BTS_MEMBER (
	MEMBER_NO			NUMBER				PRIMARY KEY, 
	MEMBER_ID			VARCHAR2(200)		UNIQUE NOT NULL,
	MEMBER_PW			VARCHAR2(200)		NOT NULL,
	MEMBER_ENABLE		NUMBER				NOT NULL,
	MEMBER_AUTHORITY	VARCHAR2(200)		NOT NULL,
	MEMBER_NICKNAME		VARCHAR2(200)		NOT NULL,
	MEMBER_EMAIL		VARCHAR2(200)		NOT NULL,
	MEMBER_IMAGE		VARCHAR2(200)		NULL,
	MEMBER_REGDATE		DATE				NOT NULL
);

SELECT * FROM BTS_MEMBER;

UPDATE BTS_MEMBER SET
MEMBER_AUTHORITY = 'ROLE_ADMIN'
WHERE MEMBER_ID = 'admin';


SELECT COUNT(*) FROM BTS_MEMBER WHERE TO_CHAR(MEMBER_REGDATE, 'yyyy-mm-dd') = TO_CHAR(SYSDATE, 'yyyy-mm-dd');
SELECT COUNT(*) FROM BTS_MEMBER WHERE TO_CHAR(MEMBER_REGDATE, 'yyyy-mm-dd') = TO_CHAR(SYSDATE-1, 'yyyy-mm-dd');
SELECT COUNT(*) FROM BTS_MEMBER WHERE TO_CHAR(MEMBER_REGDATE, 'yyyy-mm-dd') >= '2019-10-07'
AND TO_CHAR(MEMBER_REGDATE, 'yyyy-mm-dd') <= '2019-10-12';


SELECT COUNT(*) FROM BTS_MEMBER;






SELECT ROW_NUM, MEMBER_NO, MEMBER_ID, MEMBER_ENABLE, MEMBER_NICKNAME, MEMBER_EMAIL, MEMBER_REGDATE
FROM(SELECT ROWNUM AS ROW_NUM, MEMBER_NO, MEMBER_ID, MEMBER_ENABLE, MEMBER_NICKNAME, MEMBER_EMAIL, MEMBER_REGDATE
FROM(SELECT MEMBER_NO, MEMBER_ID, MEMBER_ENABLE, MEMBER_NICKNAME, MEMBER_EMAIL, MEMBER_REGDATE
FROM BTS_MEMBER ORDER BY MEMBER_NO DESC))
WHERE ROW_NUM BETWEEN 1 AND 5



--------------- 더미데이터 ---------------------

DELETE BTS_MEMBER WHERE MEMBER_ID LIKE '%dummy%'
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy01',
      'dummy01',
      1,
      'ROLE_MEMBER',
      'dummy01',
      'dummy01',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy02',
      'dummy02',
      1,
      'ROLE_MEMBER',
      'dummy02',
      'dummy02',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy03',
      'dummy03',
      1,
      'ROLE_MEMBER',
      'dummy03',
      'dummy03',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy04',
      'dummy04',
      1,
      'ROLE_MEMBER',
      'dummy04',
      'dummy04',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy05',
      'dummy05',
      1,
      'ROLE_MEMBER',
      'dummy05',
      'dummy05',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy06',
      'dummy06',
      1,
      'ROLE_MEMBER',
      'dummy06',
      'dummy06',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy07',
      'dummy07',
      1,
      'ROLE_MEMBER',
      'dummy07',
      'dummy07',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy08',
      'dummy08',
      1,
      'ROLE_MEMBER',
      'dummy08',
      'dummy08',
      NULL,
      SYSDATE - 7
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy09',
      'dummy09',
      1,
      'ROLE_MEMBER',
      'dummy09',
      'dummy09',
      NULL,
      SYSDATE - 7
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy10',
      'dummy10',
      1,
      'ROLE_MEMBER',
      'dummy10',
      'dummy10',
      NULL,
      SYSDATE - 7
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy11',
      'dummy11',
      1,
      'ROLE_MEMBER',
      'dummy11',
      'dummy11',
      NULL,
      SYSDATE - 7
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy12',
      'dummy12',
      1,
      'ROLE_MEMBER',
      'dummy12',
      'dummy12',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy13',
      'dummy13',
      1,
      'ROLE_MEMBER',
      'dummy13',
      'dummy13',
      NULL,
      SYSDATE - 1
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy14',
      'dummy14',
      1,
      'ROLE_MEMBER',
      'dummy14',
      'dummy14',
      NULL,
      SYSDATE
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy15',
      'dummy15',
      1,
      'ROLE_MEMBER',
      'dummy15',
      'dummy15',
      NULL,
      SYSDATE
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy16',
      'dummy16',
      1,
      'ROLE_MEMBER',
      'dummy16',
      'dummy16',
      NULL,
      SYSDATE
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy17',
      'dummy17',
      1,
      'ROLE_MEMBER',
      'dummy17',
      'dummy17',
      NULL,
      SYSDATE
   );
   
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy18',
      'dummy18',
      1,
      'ROLE_MEMBER',
      'dummy18',
      'dummy18',
      NULL,
      SYSDATE
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy19',
      'dummy19',
      1,
      'ROLE_MEMBER',
      'dummy19',
      'dummy19',
      NULL,
      SYSDATE
   );
   
   INSERT INTO BTS_MEMBER VALUES(
      MEMBER_NO_SEQ.NEXTVAL,
      'dummy20',
      'dummy20',
      1,
      'ROLE_MEMBER',
      'dummy20',
      'dummy20',
      NULL,
      SYSDATE
   );



------------------------------------------------

