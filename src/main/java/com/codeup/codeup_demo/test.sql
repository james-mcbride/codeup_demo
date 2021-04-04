create database if not exists categories;

select * from categories as c
    join post_categories as pc
    on c.id = pc.category_id
    join posts as po
    on pc.post_id = po.id
where po.owner_id = 1
