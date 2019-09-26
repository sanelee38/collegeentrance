create table 2016score
(
    sort     varchar(20) null,
    scid     int         null,
    aid      int         null,
    AREA     varchar(20) null,
    maxscore int         null,
    avgscore int         null,
    minscore int         null,
    minrank  int         null,
    sid      int         null,
    constraint 2016score_area_fk
        foreign key (aid) references area (aid),
    constraint 2016score_school_fk
        foreign key (scid) references school (scid),
    constraint 2016score_sort_fk
        foreign key (sid) references sort (sid)
);
