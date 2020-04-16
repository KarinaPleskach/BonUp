create table users (
    id bigserial primary key,
    email character varying(255) not null,
    password character varying(255) not null,
    token character varying(255),
    verify_mail boolean not null,
    mail_code character varying(6),
    constraint users_mail_uq unique(email)
);
