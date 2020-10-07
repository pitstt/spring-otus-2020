insert into AUTHOR (id, `name`, `surname`) values (1, 'Рей', 'Брэдбери');
insert into AUTHOR (id, `name`, `surname`) values (2, 'Джордж', 'Оруэлл');
insert into GENRE (id, `name`) values (1, 'Антиутопия');
insert into GENRE (id, `name`) values (2, 'Роман');
insert into BOOK (id, `name`,`author_id`,`genre_id`) values (1, 'Вино из одуванчиков', 1,2);
insert into BOOK (id, `name`,`author_id`,`genre_id`) values (2, '1984',2,1);