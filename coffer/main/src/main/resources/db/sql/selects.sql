SELECT *
FROM coffer.person_role pr
INNER JOIN coffer.person p ON pr.person_id = p.id
INNER JOIN coffer.role r ON pr.role_id = r.id;

SELECT *
FROM coffer.advertisement a
INNER JOIN coffer.person p ON a.person_id = p.id
INNER JOIN coffer.category c ON a.category_id = c.id
INNER JOIN coffer.top_param tp ON a.top_param_id = tp.id;

SELECT *
FROM coffer.comment cm
INNER JOIN coffer.advertisement a ON cm.advertisement_id = a.id
INNER JOIN coffer.person p ON cm.person_id = p.id;

SELECT *
FROM coffer.message m
INNER JOIN coffer.person p ON m.sender_id = p.id
INNER JOIN coffer.advertisement a ON m.advertisement_id = a.id;

SELECT *
FROM coffer.image i
INNER JOIN coffer.advertisement a ON i.advertisement_id = a.id;

