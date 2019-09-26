create table profession
(
    pid     int auto_increment
        primary key,
    proname varchar(100) not null,
    constraint name
        unique (proname)
);