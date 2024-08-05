insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, pomodoro', 'Margherita', 6.5, 'margherita.png');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, prosciutto cotto', 'Crostino', 7.5, 'crostino.jpg');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, patate', 'Patate', 7.5, 'patate.jpg');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, pomodoro, salame piccante', 'Diavola', 7.5, 'diavola.jpg');
insert into ingrediente (id, nome) values(nextval('ingrediente_seq'), 'mozzarella');
insert into credentials(id, username, password, role) values(nextval('credentials_seq'), 'mario', 'mario', 'ROLE_ADMIN'); 