alter table doctors add active tinyint;
update doctors set active = 1;
alter table doctors modify active tinyint not null;