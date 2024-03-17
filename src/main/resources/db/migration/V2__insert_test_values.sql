SET search_path TO panda;

insert into users(user_id)
values ('1d5fcfd1-afc2-483d-8bd9-0c218e774bca');

insert into mails(mail)
values ('test_mail');

insert into accounts(user_id, name, account, password, link, description, mail, type)
values (1, 'steam', 'Apostality', 'temp_pass', 'some_link', 'some_descr', 1, 'GAMES');






