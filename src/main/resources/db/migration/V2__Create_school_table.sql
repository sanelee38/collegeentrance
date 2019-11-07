create table school
(
    scid        int auto_increment
        primary key,
    name        varchar(50) null,
    areaname    varchar(20) null,
    areaid      int         null,
    batch       varchar(20) null,
    description longtext    null,
    acronym     varchar(10) null,
    region      varchar(20) null,
    reid        int(10)     null,
    constraint school_ibfk_1
        foreign key (areaid) references area (aid)
);

