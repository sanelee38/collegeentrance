create table t_school_profession
(
    scid     int default 0 not null,
    maxscore int           null,
    avgscore int           null,
    minscore int           null,
    minrank  int           null,
    pid      int default 0 not null,
    primary key (scid, pid),
    constraint t_school_profession_ibfk_1
        foreign key (scid) references school (scid),
    constraint t_school_profession_ibfk_2
        foreign key (pid) references profession (pid)
);

create index pid
    on t_school_profession (pid);