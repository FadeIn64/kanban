--liquibase formatted sql
--changeset danila_fedin:init_db
create table if not exists public.desk
(
    id     integer generated always as identity
        primary key,
    name   text not null,
    author text not null
);

alter table public.desk
    owner to danila;

create table if not exists public.desk_contributors
(
    id          integer generated always as identity
        primary key,
    desk        integer
        constraint desk_contributor_fk
            references public.desk
            on update cascade on delete cascade,
    contributor text not null,
    constraint desk_contributor_uq
        unique (desk, contributor)
);

alter table public.desk_contributors
    owner to danila;

create table if not exists public.desk_column
(
    id   integer generated always as identity
        primary key,
    desk integer
        constraint desk_column_fk
            references public.desk
            on update cascade on delete cascade,
    name text not null,
    next integer
        constraint column_next_fk
            references public.desk_column
            on update cascade,
    prev integer
        constraint column_prev_fk
            references public.desk_column
            on update cascade
);

alter table public.desk_column
    owner to danila;

create table if not exists public.desk_task
(
    id          integer generated always as identity
        primary key,
    desk        integer
        constraint desk_task_fk
            references public.desk
            on update cascade on delete cascade,
    header      text not null,
    description text,
    author      text not null,
    importance  text not null
        constraint desk_task_importance
            check ((importance ~~ 'high'::text) OR (importance ~~ 'medium'::text) OR (importance ~~ 'low'::text)),
    createdate  timestamp        default now(),
    startdate   timestamp,
    enddate     timestamp,
    coast       double precision default 0,
    file        text,
    constraint process_date_check
        check (startdate < enddate)
);

alter table public.desk_task
    owner to danila;

create table if not exists public.task_performers
(
    id          integer generated always as identity
        primary key,
    contributor integer
        constraint contributor_task_fk
            references public.desk_contributors
            on update cascade on delete cascade,
    task        integer
        constraint task_contributor_fk
            references public.desk_task
            on update cascade on delete cascade
);

alter table public.task_performers
    owner to danila;

create table if not exists public.task_history
(
    id          integer generated always as identity
        primary key,
    task        integer
        constraint task_history_fk
            references public.desk_task
            on update cascade on delete cascade,
    column_from integer
        constraint column_from_fk
            references public.desk_column
            on update cascade on delete cascade,
    column_to   integer
        constraint column_from_to
            references public.desk_column
            on update cascade on delete cascade,
    change_date timestamp default now()
);

alter table public.task_history
    owner to danila;

