create table users (
    id bigserial primary key,
    email character varying(255) not null,
    password character varying(255) not null,
    token character varying(255) not null,
    verify_mail boolean not null,
    constraint users_mail_uq unique(email)
);
