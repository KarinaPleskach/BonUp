drop table if exists `temp_translation`;

create table `temp_translation` (
    `lang` varchar(3),
    `language_key` text,
    `value` text
);

insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('en', 'message.noLanguage', 'No such language available. Please choice ru or en');

insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('ru', 'message.noLanguage', 'Данный язык не поддерживается. Пожалуйста, выберите ru или en');

insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('en', 'message.egorDurak', 'Egorka, ti chto durachok?:)');

insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('ru', 'message.egorDurak', 'Егорка, ты что, совсем дурак?:)');

insert into `language_key` (`key`)
    select `language_key`
    from `temp_translation`
    where `lang` = 'en';

insert into `language_translation` (`value`, `key_id`, `language_id`)
    select
           `value`,
           `language_key`.`id`,
           (select `id`
           from `language`
           where `lang` = `temp_translation`.`lang`)
    from `temp_translation`
    join `language_key` on `temp_translation`.`language_key` = `language_key`.`key`;

drop table temp_translation;
