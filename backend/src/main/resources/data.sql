INSERT INTO CALENDAR (ID, BUSINESS_NAME) VALUES (1, 'Business 1');
INSERT INTO EMPLOYEES (ID, CALENDAR_ID, FIRST_NAME, LAST_NAME) VALUES (1, 1, 'John1', 'Doe');
INSERT INTO EMPLOYEES (ID, CALENDAR_ID, FIRST_NAME, LAST_NAME) VALUES (2, 1, 'Jane2', 'Smith');
INSERT INTO EMPLOYEES (ID, CALENDAR_ID, FIRST_NAME, LAST_NAME) VALUES (3, 1, 'Bob3', 'Johnson');
INSERT INTO TIME_BLOCK(START_TIME, END_TIME, SHIFTS_REQUIRED, WEEK_DAY_ENUM, ID, CALENDAR_ID) VALUES(1.0,3.0,2,0,1,1);
INSERT INTO TIME_BLOCK(
    START_TIME,
    END_TIME, 
    SHIFTS_REQUIRED,  
    WEEK_DAY_ENUM,
    ID, 
    CALENDAR_ID 
    ) VALUES(
    4.0,
    6.0,
    1,
    0,
    2,
    1);
INSERT INTO SHIFT(
    ID,
    CALENDAR_ID,
    TIME_BLOCK_ID,
    FIRST_DATE
    ) VALUES(
        1,
        1,
        1,
        01012025
    );
INSERT INTO SHIFT(
    ID,
    CALENDAR_ID,
    TIME_BLOCK_ID,
    FIRST_DATE
    ) VALUES(
        2,
        1,
        2,
        01012025
    );
INSERT INTO EMPLOYEE_SHIFT(
    EMPLOYEE_ID,
    SHIFT_ID
) VALUES(
    2,
    1
);
INSERT INTO EMPLOYEE_SHIFT(
    EMPLOYEE_ID,
    SHIFT_ID
) VALUES(
    1,
    1
);
INSERT INTO EMPLOYEE_SHIFT(
    EMPLOYEE_ID,
    SHIFT_ID
) VALUES(
    1,
    2
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
    1,
    1,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0
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
    5.0,
    1.0,
    5.0,
    1.0,
    5.0,
    1.0,
    5.0,
    1.0,
    5.0,
    1.0,
    5.0,
    1.0,
    5.0
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
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0,
    1.0,
    10.0
);
INSERT INTO TIME_OFF_REQUEST(
    ID,
    EMPLOYEE_ID, 
    FIRST_DATE, 
    WEEK_DAY_ENUM
    ) VALUES(
    1,
    1,
    01082025,
    0);

