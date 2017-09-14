# users_roles.sql
# 04/04/2017 pjd
#
# See users.sql
#
# Change history:
#  04/04/2017 pjd foreign keys with cascade delete

drop table if exists users_roles;

create table users_roles
(
# Surrogate primary key
id integer auto_increment primary key,

# Foreign key users.id
users_id integer not null,

# Foreign key roles.id
roles_id integer not null,

last_edit timestamp not null default current_timestamp on update current_timestamp,
# adding unique constraint to users_id
 CONSTRAINT uc_users_roles UNIQUE (users_id)

) engine=InnoDB DEFAULT CHARSET=utf8;

alter table users_roles add index (users_id);
alter table users_roles add foreign key (users_id)
  references users (id) on update cascade on delete cascade;

alter table users_roles add index (roles_id);
alter table users_roles add foreign key (roles_id)
  references roles (id) on update cascade on delete cascade;