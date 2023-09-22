select member, facility, cost from (
	select
		members.firstname || ' ' || members.surname as member,
		facilities.name as facility,
		case
			when members.memid = 0 then
				bookings.slots*facilities.guestcost
			else
				bookings.slots*facilities.membercost
		end as cost
		from
			cd.members members
			inner join cd.bookings bookings
				on members.memid = bookings.memid
			inner join cd.facilities facilities
				on bookings.facid = facilities.facid
		where
			bookings.starttime >= '2012-09-14' and
			bookings.starttime < '2012-09-15'
	) as bookings
	where cost > 30
order by cost desc;