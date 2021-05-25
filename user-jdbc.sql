use limbo;

drop role if exists ROL_JDBC;
create role if not exists ROL_JDBC;
grant select, insert, update, delete on limbo.* to ROL_JDBC;
drop user if exists jdbc;
create user if not exists jdbc identified by 'jdbcpassword';
grant ROL_JDBC to jdbc;
set default role ROL_JDBC TO jdbc;

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