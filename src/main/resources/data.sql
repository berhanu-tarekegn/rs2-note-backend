insert into USER (EMAIL, NAME, SURNAME)
values ('test@rs2.com', 'final', 'test');

insert into USER (EMAIL, NAME, SURNAME)
values ('beta@rs2.com', 'note', 'app');

-- 'pass$rs2' = '$2a$10$gRYq1Ijeu3acbaV1wQZwJOCYyfBLYL6il0RWwUTyh.HOqCR0Ylc0e'

insert into USERCREDENTIAL (CREDENTIALS, USER_ID)
values ('$2a$10$gRYq1Ijeu3acbaV1wQZwJOCYyfBLYL6il0RWwUTyh.HOqCR0Ylc0e', 1);

insert into USERCREDENTIAL (CREDENTIALS, USER_ID)
values ('$2a$10$gRYq1Ijeu3acbaV1wQZwJOCYyfBLYL6il0RWwUTyh.HOqCR0Ylc0e', 2);
