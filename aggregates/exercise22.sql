select dategen.date,
	(
		select sum(case
			when memid = 0 then slots * facs.guestcost
 else slots * membercost
 end) as rev

 from cd.bookings bks
 inner join cd.facilities facs
 on bks.facid = facs.facid
 where bks.starttime > dategen.date - interval '14 days'
			and bks.starttime < dategen.date + interval '1 day')/15 as revenue
 from
	(
		select cast(generate_series(timestamp '2012-08-01',
			'2012-08-31','1 day') as date) as date
 )  as dategen
order by dategen.date;