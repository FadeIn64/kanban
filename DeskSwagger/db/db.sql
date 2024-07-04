create table desk(
    id int primary key generated always as identity,
    name text not null,
    author text not null
);

create table desk_contributors(
    id int primary key generated always as identity,
    desk int not null ,
    contributor text not null ,

    constraint desk_contributor_uq unique (desk, contributor),
    constraint desk_contributor_fk foreign key (desk) references desk(id)
                              on delete cascade
                              on update cascade
);

create table desk_column(
    id int primary key generated always as identity,
    desk int not null,
    name text not null,
    next integer,
    prev integer,

    constraint desk_column_fk foreign key (desk) references desk(id)
                        on update cascade
                        on delete cascade,
    constraint column_next_fk foreign key (next) references desk_column(id)
                        on update cascade
                        on delete no action,
    constraint column_prev_fk foreign key (prev) references desk_column(id)
                        on update cascade
                        on delete no action
);

create table desk_task(
    id int primary key generated always as identity,
    desk int not null,
    desk_column int not null,
    header text not null,
    description text null,
    author text not null,
    importance text not null,
    createDate date default now(),
    startDate date null,
    endDate date null,
    coast float8 default 0,
    file text null,

    constraint desk_task_importance
        check ( importance like 'high' or importance like 'medium' or importance like 'low'),
    constraint process_date_check check ( startDate < endDate ),
    constraint desk_task_fk foreign key (desk) references desk(id)
                      on update cascade
                      on delete cascade,
    constraint column_task_fk foreign key (desk_column) references desk_column(id)
                      on update cascade
                      on delete no action
);

create table task_performers(
    id int primary key generated always as identity,
    contributor int not null,
    task int not null,

    constraint contributor_task_fk foreign key (contributor) references desk_contributors(id)
                            on delete cascade
                            on update cascade,
    constraint task_contributor_fk foreign key (task) references desk_task(id)
                            on delete cascade
                            on update cascade
);

create table task_history(
    id int primary key generated always as identity,
    task int not null,
    column_from int not null,
    column_to int not null,
    change_date date default now(),

    constraint column_from_fk foreign key (column_from) references desk_column(id)
                         on update cascade
                         on delete cascade,
    constraint column_from_to foreign key (column_to) references desk_column(id)
                         on update cascade
                         on delete cascade,
    constraint task_history_fk foreign key (task) references desk_task(id)
                         on update cascade
                         on delete cascade
);