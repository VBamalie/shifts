INSERT INTO CALENDAR( ID, BUSINESS_NAME) VALUES (1, 'My Coffee Shop');
INSERT INTO TIME_BLOCK(ID, CALENDAR_ID, START_TIME, END_TIME, SHIFTS_REQUIRED, WEEK_DAY_ENUM) 
VALUES (1, 1, 8.0, 11.0, 1, 0),
(2,1,12.0,16.0,2,0),
(3,1,17.0,20.0,1,0),
(4,1,8.0,11.0,1,1),
(5,1,12.0,16.0,2,1),
(6,1,17.0,20.0,1,1),
(7,1,8.0,11.0,1,2),
(8,1,12.0,16.0,2,2),
(9,1,17.0,20.0,1,2),
(10,1,8.0,11.0,1,3),
(11,1,12.0,16.0,2,3),
(12,1,17.0,20.0,1,3),
(13,1,8.0,11.0,1,4),
(14,1,12.0,16.0,2,4),
(15,1,17.0,20.0,1,4),
(16,1,8.0,11.0,2,5),
(17,1,12.0,16.0,3,5),
(18,1,17.0,20.0,1,5),
(19,1,8.0,11.0,3,6),
(20,1,12.0,16.0,3,6),
(21,1,17.0,20.0,1,6);
INSERT INTO EMPLOYEES(ID, CALENDAR_ID, FIRST_NAME, LAST_NAME, IS_MANAGER, EMAIL, PASSWORD)
VALUES (1, 1, 'John', 'Doe', true, 'email@email.com', '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq'),
(2, 1, 'Jane', 'Smith', false, 'janesmith@example.com', '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq'),
(3, 1, 'Mike', 'Johnson', false, 'mikejohnson@example.com', '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq'),
(4, 1, 'Emily', 'Davis', false, 'emilydavis@example.com', '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq'),
(5, 1, 'David', 'Wilson', false, 'davidwilson@example.com', '$2a$12$czxz3NL0rPwCcBaVn7ZUFuEcC6NnvlxVYpYyix1Pb74NHE7/Xz9Mq');

INSERT INTO AVAILABILITY(ID,
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
     SUN_END)
VALUES(1, 1, 8.0,20.0, 8.0, 20.0, 8.0, 20.0, 24, 24, 8.0, 20.0, 8.0, 20.0, 8.0, 20.0),
(2, 2, 8.0,20.0, 8.0, 20.0, 8.0, 20.0, 24, 24, 8.0, 20.0, 8.0, 20.0, 8.0, 20.0),
(3, 3, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0, 24.0,24.0, 24.0,24.0),
(4, 4, 12.0, 20.0, 12.0, 20.0, 12.0, 20.0, 12.0, 20.0, 12.0, 20.0, 12.0, 20.0, 12.0, 20.0),
(5, 5, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0, 8.0, 17.0);
