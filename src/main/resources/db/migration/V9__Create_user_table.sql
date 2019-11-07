create table user
(
    admin    int(100)     null,
    id       int(32) auto_increment
        primary key,
    userName varchar(32)  not null,
    passWord varchar(50)  not null,
    token    varchar(255) null,
    pay      int(10)      null
);

