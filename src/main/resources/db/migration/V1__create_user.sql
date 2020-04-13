create table if not exists `user` (
    `id` bigint not null auto_increment,
    `email` varchar(255) not null,
    `password` varchar(255) not null,
    `verify_mail` bit not null,
    constraint `users_pk` primary key(`id`),
    constraint `users_mail_uq` unique(`email`)
);
