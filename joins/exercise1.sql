select bks.starttime
    from cd.bookings bks
        inner join cd.members mems
on mems.memid =  bks.memid
where mems.firstname='David'
and mems.surname = 'Farrell';