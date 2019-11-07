create table t_school_profession
(
    forecastscore int           null,
    minrank       int           null,
    minscore      int           null,
    avgscore      int           null,
    maxscore      int           null,
    scid          int default 0 not null,
    pid           int default 0 not null,
    sort          int(5)        null,
    primary key (scid, pid),
    constraint t_school_profession_ibfk_1
        foreign key (scid) references school (scid),
    constraint t_school_profession_ibfk_2
        foreign key (pid) references profession (pid)
);

create index pid
    on t_school_profession (pid);

