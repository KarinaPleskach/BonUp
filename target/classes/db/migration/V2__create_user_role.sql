create table if not exists `role` (
    `id` bigint not null auto_increment,
    `description` varchar(45) not null,
    constraint `roles_pk` primary key(`id`),
    constraint `roles_description_uq` unique (`description`)
);

create table if not exists `user_role` (
    `user_id` bigint not null,
    `role_id` bigint not null,
    constraint `user_roles_users_fk` foreign key (`user_id`) references `user` (`id`),
    constraint `user_roles_roles_fk` foreign key (`role_id`) references `role` (`id`),
    constraint `user_roles_ids_uq` unique (`user_id`, `role_id`)
);

insert into `role` (`description`) values
    ('ROLE_MINIMAL_USER'),
    ('ROLE_GLOBAL_ADMIN');
