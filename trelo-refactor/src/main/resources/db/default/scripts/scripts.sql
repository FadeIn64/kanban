--Стоимость всех задач по доскам
select dt.desk_id, desks.name, sum(dt.coast)
from desks
         join tasks dt on desks.id = dt.desk_id
group by dt.desk_id, desks.name;

--Стоимость задач по колонкам
select distinct dc.desk_id, desks.name as desk_name, dc.name as column_name,
                case when sum(dt.coast) over (partition by dc.id) is null then 0
                     else sum(dt.coast) over (partition by dc.id) end as coast
from desks
         join columns dc
         left join tasks dt on dc.id = dt.column_id
                   on desks.id = dc.desk_id
group by dt.desk_id, desks.name, dc.id, dc.name, dc.name, dt.coast;

--Статистика по времени проекта(доски)
with tmp as( select public.desks.id, public.desks.name,
                    (select tasks.start_date
                     from tasks
                     where tasks.desk_id = public.desks.id
                     order by tasks.start_date
                     limit 1
                    ) as start_date,
                    (select tasks.end_date
                     from tasks
                     where tasks.desk_id = public.desks.id
                     order by tasks.end_date desc
                     limit 1
                    ) as end_date
             from desks)
select *,
       end_date - start_date as project_absolute_time,
       now() - start_date as time_from_start,
       end_date - now() as remaining_time
from tmp;