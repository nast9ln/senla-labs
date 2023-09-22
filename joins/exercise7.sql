select distinct members.firstname || ' ' ||members.surname as member,
	(select recomender.firstname || ' ' || recomender.surname as recommender
		from cd.members recomender
		where recomender.memid = members.recommendedby
	)
	from
		cd.members members
order by member;