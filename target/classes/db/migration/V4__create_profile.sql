create table profile (
    id bigserial primary key,
    name character varying(45) not null,
    user_id bigint not null,
    constraint profiles_users_fk foreign key (user_id) references users (id),
    constraint profiles_user_id unique (user_id)
);
