drop table if exists `temp_translation`;

create table `temp_translation` (
    `lang` varchar(3),
    `language_key` text,
    `value` text
);

insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('en', 'message.successLogin', 'You login successfully');

insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('ru', 'message.successLogin', 'Авторизация прошла успешно');


insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('en', 'message.failLogin', 'Incorrect mail or password');

insert into `temp_translation` (`lang`, `language_key`, `value`)
values ('ru', 'message.failLogin', 'Неправильный логин или пароль');

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
