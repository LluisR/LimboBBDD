use limbo;

/* ROL JDBC */
drop role if exists ROL_JDBC;
create role if not exists ROL_JDBC;
grant select, insert, update, delete on limbo.* to ROL_JDBC;
drop user if exists jdbc;
create user if not exists jdbc identified by 'jdbcpassword';
grant ROL_JDBC to jdbc;
set default role ROL_JDBC TO jdbc;

/* TAULES DESCOMPTE */
drop table if exists descompte_producte;
drop table if exists descompte_client;
drop table if exists descompte;
create table if not exists descompte (
                                         id int unsigned auto_increment,
                                         percentatge int not null,
                                         data_inici date not null,
                                         data_fi date,
                                         primary key (id)
);

create table if not exists descompte_client (
                                                client_id int unsigned,
                                                descompte_id int unsigned,
                                                primary key (client_id, descompte_id),
                                                constraint descompte_client_client_fk foreign key (client_id) references client (numero_client),
                                                constraint descompte_client_descompte_fk foreign key (descompte_id) references descompte (id)
);

create table if not exists descompte_producte (
                                                  descompte_id int unsigned,
                                                  producte_id int unsigned,
                                                  primary key (descompte_id, producte_id),
                                                  constraint descompte_producte_descompte_fk foreign key (descompte_id) references descompte (id),
                                                  constraint descompte_producte_producte_fk foreign key (producte_id) references producte (id)
);

/* INSERTS DEL DESCOMPTE */
insert into descompte values (null, 15, "2021-08-22", "2021-08-22");
set @descompte1=LAST_INSERT_ID();
insert into descompte values (null, 15, "2021-04-22", "2022-10-12");
set @descompte2=LAST_INSERT_ID();

/* INSERT CLIENT PROVA */
delete from client where email="provaJosep@gmail.com";
insert into client values (null, "provaJosep@gmail.com", "Josep", "Canyelles", null, "josepC", "josep123");
set @josepId = LAST_INSERT_ID();

insert into descompte_client values (@josepId, @descompte1);
insert into descompte_client values (@josepId, @descompte2);

insert into descompte_producte values (@descompte1, 10);
insert into descompte_producte values (@descompte2, 12);
insert into descompte_producte values (@descompte2, 1);
insert into descompte_producte values (@descompte1, 2);