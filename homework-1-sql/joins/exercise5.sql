select  distinct members.firstname ||' '|| members.surname as member, facilities.name as facility
from cd.members  members
inner join cd.bookings bookings
on members.memid = bookings.memid
inner join cd.facilities facilities
on bookings.facid = facilities.facid
where facilities.name in ('Tennis Court 2','Tennis Court 1')
order by member, facility;
