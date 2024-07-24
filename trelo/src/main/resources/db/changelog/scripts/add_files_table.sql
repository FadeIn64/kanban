alter table desk_task
    drop column file
;

create table desk_files(
    id int primary key generated always as identity,
    description text not null,
    url text not null,
    size int8 not null,
    fileName text not null,
    task int,

    constraint file_task_fk foreign key (task) references desk_task(id)
);

