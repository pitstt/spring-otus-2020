create table genre(
    id bigserial,
    name varchar(255),
    primary key (id)
);

create table author(
    id bigserial,
    name varchar(255),
    surname varchar(255),
    primary key (id)
);

create table book(
    id bigserial,
    name varchar(255),
    author_id bigint references author (id),
    genre_id bigint references genre (id),
    primary key (id)
);

create table comment(
    id bigserial,
    text varchar(8000),
    book_id bigint references book(id) on delete cascade,
    primary key (id)
);