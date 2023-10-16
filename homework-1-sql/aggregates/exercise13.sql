select facs.facid, facs.name, round(sum(bks.slots) / 2.0, 2) as "Total Hours"
from cd.bookings bks
inner join cd.facilities facs on facs.facid = bks.facid
group by facs.facid, facs.name
order by facs.facid;
