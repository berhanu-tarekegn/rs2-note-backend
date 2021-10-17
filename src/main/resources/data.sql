insert into USER (ID, EMAIL, NAME, SURNAME)
values (1, 'test@rs2.com', 'final', 'test');

insert into USER (ID, EMAIL, NAME, SURNAME)
values (2, 'beta@rs2.com', 'note', 'app');

insert into ROLE(ID, NAME, USER_ID)
values (1, 'ROLE_MANAGER', 1);

insert into ROLE(ID, NAME, USER_ID)
values (2, 'ROLE_MANAGER', 2);

-- 'pass$rs2' = '$2a$10$gRYq1Ijeu3acbaV1wQZwJOCYyfBLYL6il0RWwUTyh.HOqCR0Ylc0e'

insert into USERCREDENTIAL (CREDENTIALS, USER_ID)
values ('$2a$10$gRYq1Ijeu3acbaV1wQZwJOCYyfBLYL6il0RWwUTyh.HOqCR0Ylc0e', 1);

insert into USERCREDENTIAL (CREDENTIALS, USER_ID)
values ('$2a$10$gRYq1Ijeu3acbaV1wQZwJOCYyfBLYL6il0RWwUTyh.HOqCR0Ylc0e', 2);
