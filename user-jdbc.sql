use limbo;

drop role if exists ROL_JDBC;
create role if not exists ROL_JDBC;
grant select, insert, update, delete on limbo.* to ROL_JDBC;
drop user if exists jdbc;
create user if not exists jdbc identified by 'jdbcpassword';
grant ROL_JDBC to jdbc;
set default role ROL_JDBC TO jdbc;