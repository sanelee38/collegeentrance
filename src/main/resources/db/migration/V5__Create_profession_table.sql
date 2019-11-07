create table profession
(
    pfdescription longtext     null,
    object        varchar(50)  null,
    obid          int(10)      null,
    pid           int auto_increment
        primary key,
    proname       varchar(100) not null
);