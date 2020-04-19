drop table if exists temp_translation;

create table temp_translation (
                                  lang varchar(3),
                                  language_key text,
                                  value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'message.noValid', 'Not valid request');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.noValid', 'Невалидный запрос');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.egorDurak', 'Egorka, ti chto durachok?:)');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.egorDurak', 'Егорка, ты что, совсем дурак?:)');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.userExist', 'User with such mail already exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.userExist', 'Пользователь с такой почтой уже существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.noSuchLanguage', 'No such language available. Please choice ru or en');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.noSuchLanguage', 'Данный язык не поддерживается. Пожалуйста, выберите ru или en');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.sendCode', 'Code for registrations has sent to your email');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.sendCode', 'Код для подтверждения регистрации отправлен на ваш email');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.noUserExist', 'User with such mail not exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.noUserExist', 'Пользователь с такой почтой не существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'reg.email.subject', 'Private code');

insert into temp_translation (lang, language_key, value)
values ('ru', 'reg.email.subject', 'Личный код');


insert into temp_translation (lang, language_key, value)
values ('en', 'reg.email.message', 'Your private code is: ');

insert into temp_translation (lang, language_key, value)
values ('ru', 'reg.email.message', 'Ваш личный код: ');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.invalid.email.code', 'You enter wrong code');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.invalid.email.code', 'Вы ввели неверный код');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.valid.email.code', 'Code confirmed');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.valid.email.code', 'Код подтвержден');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.access.error', 'Access error');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.access.error', 'Ошибка доступа');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.password.changed', 'New password set');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.password.changed', 'Новый пароль установлен');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.incorrect.login', 'Incorrect login, password or name');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.incorrect.login', 'Неправильный логин, пароль или имя');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.correct.login', 'Log in successfully');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.correct.login', 'Авторизация прошла успешно');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.not.validate.mail', 'At first you must verify mail');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.not.validate.mail', 'Для начала вы должны подтвердить пароль');

insert into language_key (key)
select language_key
from temp_translation
where lang = 'en';

insert into language_translation (value, key_id, language_id)
select
    value,
    language_key.id,
    (select id
     from language
     where lang = temp_translation.lang)
from temp_translation
         join language_key on temp_translation.language_key = language_key.key;

drop table temp_translation;
