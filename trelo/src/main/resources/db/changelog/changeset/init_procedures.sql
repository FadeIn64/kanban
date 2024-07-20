--liquibase formatted sql
--changeset danila_fedin:create_procedures splitStatements:false
create or replace function public.add_author_to_contributors() returns trigger
    language plpgsql
as
$aatc$
begin
    insert into desk_contributors(desk, contributor) values
        (new.id, new.author);
    return new;
end
$aatc$;

alter function public.add_author_to_contributors() owner to danila;

create trigger add_author_to_contributors_trigger
    after insert
    on public.desk
    for each row
execute procedure public.add_author_to_contributors();

create or replace function public.find_actual_column(task_id integer) returns integer
    language plpgsql
as
$fac$
declare
    res int;
begin
    select column_to into res from task_history
                                       inner join public.desk_task dt on dt.id = task_history.task
    where dt.id = task_id
    order by task_history.change_date desc
    limit 1;
    return res;
end
$fac$;

alter function public.find_actual_column(integer) owner to danila;

