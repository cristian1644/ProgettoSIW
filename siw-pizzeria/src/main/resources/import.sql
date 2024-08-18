insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, pomodoro', 'Margherita', 6.5, 'margherita.png');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, prosciutto cotto', 'Crostino', 7.5, 'crostino.jpg');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, patate', 'Patate', 7.5, 'patate.jpg');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, pomodoro, salame piccante', 'Diavola', 7.5, 'diavola.jpg');
insert into utente(id, nome, cognome) values(nextval('utente_seq'), 'mario', 'rossi');
insert into credentials(id, username, password, role, user_id, email) values(nextval('credentials_seq'), 'mario', 'mario', 'ROLE_ADMIN', 1, 'mario.rossi@gmail.com'); 
insert into utente(id, nome, cognome) values(nextval('utente_seq'), 'luca', 'bianchi');
insert into credentials(id, username, password, role, user_id, email) values(nextval('credentials_seq'), 'luca', 'luca', 'ROLE_USER', 51, 'luca.bianchi@gmail.com');