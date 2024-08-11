--Стоимость всех задач по доскам
select dt.desk, desk.name, sum(dt.coast)
from desk
         join desk_task dt on desk.id = dt.desk
group by dt.desk, desk.name;

--Стоимость задач по колонкам
select distinct dc.desk, desk.name as desk_name, dc.name as column_name,
                case when sum(dt.coast) over (partition by dc.id) is null then 0
                     else sum(dt.coast) over (partition by dc.id) end as coast
from desk
         join desk_column dc
         left join desk_task dt on dc.id = find_actual_column(dt.id)
                   on desk.id = dc.desk
group by dt.desk, desk.name, dc.id, dc.name, dc.name, dt.coast;

--Статистика по времени проекта(доски)
with tmp as( select public.desk.id, public.desk.name,
                    (select desk_task.startdate
                     from desk_task
                     where desk_task.desk = public.desk.id
                     order by desk_task.startdate
                     limit 1
                    ) as start_date,
                    (select desk_task.enddate
                     from desk_task
                     where desk_task.desk = public.desk.id
                     order by desk_task.enddate desc
                     limit 1
                    ) as end_date
             from desk)
select *,
       end_date - start_date as project_absolute_time,
       now() - start_date as time_from_start,
       end_date - now() as remaining_time
from tmp;