insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, pomodoro', 'Margherita', 6.5, 'uploads/margherita.png');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, prosciutto cotto', 'Crostino', 7.5, 'uploads/crostino.jpg');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, patate', 'Patate', 7.5, 'uploads/patate.jpg');
insert into pizza (id, descrizione, nome, prezzo, path_image) values(nextval('pizza_seq'), 'mozzarella, pomodoro, salame piccante', 'Diavola', 7.5, 'uploads/diavola.jpg');
insert into ingrediente (id, nome) values(nextval('ingrediente_seq'), 'mozzarella');