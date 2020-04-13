create table if not exists `language` (
    `id` bigint not null auto_increment,
    `lang` varchar(3) not null,
    `name` varchar(45) not null,
    `active` bit not null,
    constraint `languages_pk` primary key (`id`),
    constraint `languages_lang_uq` unique (`lang`),
    constraint `languages_name_uq` unique (`name`)
);

insert into `language` (`lang`, `name`, active) values
    ('en', 'English', true),
    ('ru', 'Русский', false);

create table if not exists `language_key` (
    `id` bigint not null auto_increment,
    `key` varchar(255) not null,
    constraint `language_keys_pk` primary key (`id`),
    constraint `language_keys_key_uq` unique (`key`)
);

create table `language_translation` (
    `id` bigint not null auto_increment,
    `value` varchar(512) not null,
    `key_id` bigint not null,
    `language_id` bigint not null,
    constraint `language_translations_pk` primary key (`id`),
    constraint `language_translations_ids_uq` unique (`key_id`, `language_id`),
    constraint `language_translations_language_keys_fk` foreign key (`key_id`) references `language_key` (`id`),
    constraint `language_translations_languages_fk` foreign key (`language_id`) references `language` (`id`)
);
