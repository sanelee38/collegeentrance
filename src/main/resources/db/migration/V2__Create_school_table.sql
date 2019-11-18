create table school
(
    scid                 int auto_increment
        primary key,
    name                 varchar(50)  null,
    areaname             varchar(20)  null,
    areaid               int          null,
    batch                varchar(20)  null,
    description          longtext     null,
    acronym              varchar(10)  null,
    region               varchar(20)  null,
    reid                 int(10)      null,
    usedname             varchar(300) null,
    type                 varchar(60)  null,
    foundingYear         int          null,
    department           varchar(60)  null,
    iscombine            varchar(60)  null,
    is985                int(10)      null,
    is211                int(10)      null,
    isDoubleFirstClass   varchar(60)  null,
    firstClassNum        int(10)      null,
    facultyNum           int(10)      null,
    academicianNum       int(10)      null,
    changjiangScholarNum int(10)      null,
    teachersNum          int(10)      null,
    undergraProNum       int(10)      null,
    postgraProNum        int(10)      null,
    doctorProNum         int(10)      null,
    mainLabNum           int(10)      null,
    undergraEnrollNum    int(10)      null,
    postgraEnrollNum     int(10)      null,
    schoolWeb            varchar(150) null,
    constraint school_ibfk_1
        foreign key (areaid) references area (aid)
);

