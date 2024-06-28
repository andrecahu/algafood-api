insert into kitchen (id_kitchen, nm_kitchen) values (1, 'Brazilian');
insert into kitchen (id_kitchen, nm_kitchen) values (2, 'Thai');
insert into kitchen (id_kitchen, nm_kitchen) values (3, 'Indian');
insert into kitchen (id_kitchen, nm_kitchen) values (4, 'kitchen 4');
insert into kitchen (id_kitchen, nm_kitchen) values (5, 'kitchen 5');

insert into restaurant (nm_restaurant, vl_delivery_tax, id_kitchen, dt_registration, dt_update) values ('Thai Gourmet', 10, 2, utc_timestamp, utc_timestamp);
insert into restaurant (nm_restaurant, vl_delivery_tax, id_kitchen, dt_registration, dt_update) values ('Thai Delivery', 9.5, 2, utc_timestamp, utc_timestamp);
insert into restaurant (nm_restaurant, vl_delivery_tax, id_kitchen, dt_registration, dt_update) values ('Brazilian food', 12.7, 1, utc_timestamp, utc_timestamp);

insert into permission (nm_permission , ds_permission) values ('nome 1', 'descrição 1');
insert into permission (nm_permission , ds_permission) values ('nome 2', 'descrição 2');

insert into state (nm_state) values ('estado 1');
insert into state (nm_state) values ('estado 2');


insert into city (nm_city, id_state) values ('cidade 1', 1);
insert into city (nm_city, id_state) values ('cidade 2', 2);

insert into payment_method (id_payment_method, ds_payment_method) values (1, 'Cartão de crédito');
insert into payment_method (id_payment_method, ds_payment_method) values (2, 'Cartão de débito');
insert into payment_method (id_payment_method, ds_payment_method) values (3, 'Dinheiro');

--insert into permission (id_permission, nm_permission, ds_permission) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
--insert into permission (id_permission, nm_permission, ds_permission) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurant_payment_method (id_restaurant, id_payment_method) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into restaurant (nm_restaurant, vl_delivery_tax, id_kitchen, id_city, adress_zip_code, adress_street , adress_number , adress_district, dt_registration, dt_update) values ('Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', utc_timestamp, utc_timestamp);


insert into product (nm_product, ds_product, vl_product, is_active, id_restaurant) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (nm_product, ds_product, vl_product, is_active, id_restaurant) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into product (nm_product, ds_product, vl_product, is_active, id_restaurant) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into product (nm_product, ds_product, vl_product, is_active, id_restaurant) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (nm_product, ds_product, vl_product, is_active, id_restaurant) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into product (nm_product, ds_product, vl_product, is_active, id_restaurant) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (nm_product, ds_product, vl_product, is_active, id_restaurant) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);