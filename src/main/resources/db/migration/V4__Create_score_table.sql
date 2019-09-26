create table score
(
    scid int default 0 not null,
    aid int default 0 not null,
    max_score int null,
    avg_score int null,
    min_score int null,
    min_rank int null,
    sid int default 0 not null,
    primary key (scid, aid, sid),
    constraint score_ibfk_1
        foreign key (sid) references sort (sid),
    constraint score_ibfk_2
        foreign key (aid) references area (aid),
    constraint score_ibfk_3
        foreign key (scid) references school (scid)
);

