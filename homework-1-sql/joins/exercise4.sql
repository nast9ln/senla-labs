select members.firstname as memfname ,
members.surname as memsname, recomend.firstname as recfname , recomend.surname as recsname
from cd.members members
left outer  join cd.members recomend
on members.recommendedby=recomend.memid
order by members.surname, members.firstname;