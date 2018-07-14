INSERT INTO users(address, email, gender, phone_number, first_name, last_name, password, role, username) VALUES('Address1', 'a@gmail.com', 'M', '+385918829191', 'Josipa', 'Mrdena', '12345678', 'ADMIN', 'josipmrdena');
INSERT INTO users(address, email, gender, phone_number, first_name, last_name, password, role, username) VALUES('Address2', 'b@gmail.com', 'M', '+385918829192', 'Josipb', 'Mrdenb', '12345678', 'USER', 'josipmrdenu');
INSERT INTO users(address, email, gender, phone_number, first_name, last_name, password, role, username) VALUES('Address3', 'c@gmail.com', 'M', '+385918829193', 'Josipc', 'Mrdenc', '12345678', 'EMPLOYEE', 'josipmrdene');

INSERT INTO loyalty_program(remaining, total, user_id) VALUES (5, 5, 1);
INSERT INTO loyalty_program(remaining, total, user_id) VALUES (5, 5, 2);
INSERT INTO loyalty_program(remaining, total, user_id) VALUES (5, 5, 3);

INSERT INTO ingredient(name) VALUES ('šunka');
INSERT INTO ingredient(name) VALUES ('sir');
INSERT INTO ingredient(name) VALUES ('rajčica');
INSERT INTO ingredient(name) VALUES ('gljive');
INSERT INTO ingredient(name) VALUES ('vrhnje');
INSERT INTO ingredient(name) VALUES ('pršut');
INSERT INTO ingredient(name) VALUES ('feferoni');
INSERT INTO ingredient(name) VALUES ('jaja');
INSERT INTO ingredient(name) VALUES ('tunjevina');
INSERT INTO ingredient(name) VALUES ('rikola');
INSERT INTO ingredient(name) VALUES ('kukuruz');
INSERT INTO ingredient(name) VALUES ('luk');
INSERT INTO ingredient(name) VALUES ('kulen');
INSERT INTO ingredient(name) VALUES ('plodovi mora');

INSERT INTO pizza(description, name, price, picture_url, offer) VALUES ('Miješana pizza, s gljivama logično', 'Miješana', 30.0,'http://pizzeria-maslina.hr/web/wp-content/uploads/2015/10/4_mjesana.jpg', 'IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Pizza bez šunke', 'Margarita', 25.0, 'http://recepti.novi.ba/wp-content/uploads/2017/08/pizza.jpg','IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Nije ljuto, ne zove se tako Vesuvio', 'Vesuvio', 32.0, 'http://www.vesuviosmillstone.com/siteimages/pizza1.jpg','IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Ako nemamo na lageru 4 vrste, stavite sami', 'Četiri vrste sira', 35.0, 'http://francina2.hr/wp-content/uploads/2015/02/Cheese-pizza-slice-hi-res-2-540x300.jpg','IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Malo tunjevine iz konzerve po pizzi', 'Al tonno', 40.0, 'http://www.restoran-rene.com/menu/img/pizza/altonno.jpg','IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Sve poštivam slavonsku pizzu uživam', 'Slavonska', 38.0, 'https://media-cdn.tripadvisor.com/media/photo-s/08/c4/d6/c5/pizzeria-samantha.jpg','IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Samo za ljubitelje životinja', 'Vegetariana', 30.0, 'https://www.tastingsicily.restaurant/wp-content/uploads/2013/06/pizza-vegetariana.jpg','IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Red školjaka red škampi', 'Frutti di mare', 35.0, 'https://www.silviocicchi.com/pizzachef/wp-content/uploads/2015/01/mare1.jpg','IN_OFFER');
INSERT INTO pizza(description, name, price, picture_url,offer) VALUES ('Sir, gljive, rokenrol', 'Al Funghi', 33.0, 'https://www.silviocicchi.com/pizzachef/wp-content/uploads/2015/02/f1.jpg','IN_OFFER');

INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (9, 4);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (9, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (9, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (9, 1);

INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (8, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (8, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (8, 14);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (7, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (7, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (7, 11);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (7, 12);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (7, 10);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (6, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (6, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (6, 1);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (6, 7);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (6, 13);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (5, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (5, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (5, 9);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (5, 12);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (1, 1);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (1, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (1, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (1, 4);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (2, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (2, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (3, 1);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (3, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (3, 3);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (3, 4);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (3, 6);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (4, 2);
INSERT INTO pizza_ingredients(pizza_id, ingredient_id) VALUES (4, 3);


INSERT INTO orders(date_created_at, status, employee_id, user_id, price) VALUES ('2018-01-03 11:15:00', 'PAID', 3, 2,80.0);
INSERT INTO orders(date_created_at, status, employee_id, user_id, price) VALUES ('2018-01-03 11:14:00', 'PAID', 3, 2,102.0);
INSERT INTO orders(date_created_at, status, employee_id, user_id, price) VALUES ('2018-01-03 11:13:00', 'PAID', 3, 2, 32.0);
INSERT INTO orders(date_created_at, status, employee_id, user_id, price) VALUES ('2018-01-03 11:12:00', 'PAID', 3, 2, 70.0);

INSERT INTO pizza_order(number_of_pizzas, order_id, pizza_id) VALUES (1, 1, 1);
INSERT INTO pizza_order(number_of_pizzas, order_id, pizza_id) VALUES (2, 1, 2);
INSERT INTO pizza_order(number_of_pizzas, order_id, pizza_id) VALUES (1, 2, 3);
INSERT INTO pizza_order(number_of_pizzas, order_id, pizza_id) VALUES (2, 2, 4);
INSERT INTO pizza_order(number_of_pizzas, order_id, pizza_id) VALUES (1, 3, 1);
INSERT INTO pizza_order(number_of_pizzas, order_id, pizza_id) VALUES (2, 4, 2);




