create table cat (
  id                    bigserial,
  name              varchar(50) not null,
  color             varchar(30) not null,
  race              varchar(40) null,
  primary key (id)
);

INSERT INTO cat (name, color, race) VALUES ('Коржик', 'Белый', 'Дворовый');