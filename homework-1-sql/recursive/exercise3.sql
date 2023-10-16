with recursive recommenders(recommender, member) as (
  select recommendedby, memid
  from cd.members
  where memid = 22 or memid = 12
  union all
  select mems.recommendedby, recs.member
  from recommenders recs
  inner join cd.members mems
  on mems.memid = recs.recommender
)
select recs.member, recs.recommender, mems.firstname, mems.surname
from recommenders recs
inner join cd.members mems
on recs.recommender = mems.memid
order by recs.member , recs.recommender desc;
