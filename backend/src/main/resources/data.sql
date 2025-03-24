INSERT INTO CALENDAR (ID,
     BUSINESS_NAME
    ) VALUES (
      1,
        'Business 1');
 INSERT INTO CALENDAR (
     ID,
     BUSINESS_NAME
     ) VALUES (
         2,
         'Business 2');

INSERT INTO TIME_BLOCK(
    ID,
    CALENDAR_ID,
    START_TIME,
    END_TIME,
    SHIFTS_REQUIRED,
    WEEK_DAY_ENUM
) VALUES (
    1,
    1,
    1.0,
    12.0,
    2,
    0
),(
    2,
    1,
    13.0,
    24.0,
    2,
    0
 ),(
    3,
    1,
    1.0,
    12.0,
    1,
    1
 ),(
    4,
    1,
    13.0,
    24.0,
    1,
    1
 ),(
    5,
    1,
    1.0,
    12.0,
    1,
    2
 ),(
    6,
    1,
    13.0,
    24.0,
    1,
    2
 ),(
    7,
    1,
    1.0,
    12.0,
    1,
    3
),(
    8,
    1,
    13.0,
    24.0,
    1,
    3
),(
    9,
    1,
    1.0,
    12.0,
    1,
    4
),(
    10,
    1,
    13.0,
    24.0,
    1,
    4
),(
    11,
    1,
    1.0,
    12.0,
    1,
    5
),(
    12,
    1,
    13.0,
    24.0,
    1,
    5
), (
    13,
    1,
    1.0,
    12.0,
    1,
    6
),(
    14,
    1,
    13.0,
    24.0,
    1,
    6
);

 INSERT INTO EMPLOYEES (
     ID,
     CALENDAR_ID,
     FIRST_NAME,
     LAST_NAME,
     IS_MANAGER,
     EMAIL,
     PASSWORD
     ) VALUES 
         (1,
         1, 
         'John1', 
         'Doe',
         True,
         'email@email.com',         '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq');

 INSERT INTO EMPLOYEES (
     ID, 
     CALENDAR_ID, 
     FIRST_NAME, 
     LAST_NAME,
     IS_MANAGER,
     EMAIL,
     PASSWORD
     ) VALUES (
         2, 
         1, 
         'Jane2', 
         'Smith',
         False,
         'email2@email.com',
         '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq'
         );

 INSERT INTO EMPLOYEES (
     ID, 
     CALENDAR_ID, 
     FIRST_NAME, 
     LAST_NAME,
     IS_MANAGER,
     EMAIL,
     PASSWORD
     ) VALUES (
         3, 
         1, 
         'Bob3', 
         'Johnson',
         False,
         'email3@email.com',
         '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq');


 INSERT INTO AVAILABILITY(
     ID,
     EMPLOYEE_ID,
     MON_START,
     MON_END,
     TUE_START,
     TUE_END,
     WED_START,
     WED_END,
     THU_START,
     THU_END,
     FRI_START,
     FRI_END,
     SAT_START,
     SAT_END,
     SUN_START,
     SUN_END
 ) VALUES(
     1,
     1,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0
 );
 INSERT INTO AVAILABILITY(
     ID,
     EMPLOYEE_ID,
     MON_START,
     MON_END,
     TUE_START,
     TUE_END,
     WED_START,
     WED_END,
     THU_START,
     THU_END,
     FRI_START,
     FRI_END,
     SAT_START,
     SAT_END,
     SUN_START,
     SUN_END
 ) VALUES(
     2,
     2,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0
 );
 INSERT INTO AVAILABILITY(
     ID,
     EMPLOYEE_ID,
     MON_START,
     MON_END,
     TUE_START,
     TUE_END,
     WED_START,
     WED_END,
     THU_START,
     THU_END,
     FRI_START,
     FRI_END,
     SAT_START,
     SAT_END,
     SUN_START,
     SUN_END
 ) VALUES(
     3,
     3,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0,
     1.0,
     24.0
 );
--  INSERT INTO TIME_OFF_REQUEST(
--      ID,
--      EMPLOYEE_ID, 
--      FIRST_DATE, 
--      WEEK_DAY_ENUM
--      ) VALUES(
--      1,
--      1,
--      '01-08-2025',
--      0);

