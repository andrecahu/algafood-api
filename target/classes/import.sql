insert into kitchen (id_kitchen, nm_kitchen) values (1, 'Brazilian');
insert into kitchen (id_kitchen, nm_kitchen) values (2, 'Thai');
insert into kitchen (id_kitchen, nm_kitchen) values (3, 'Indian');

insert into restaurant (nm_restaurant, vl_delivery_tax, id_kitchen) values ('Thai Gourmet', 10, 2);
insert into restaurant (nm_restaurant, vl_delivery_tax, id_kitchen) values ('Thai Delivery', 9.5, 2);
insert into restaurant (nm_restaurant, vl_delivery_tax, id_kitchen) values ('Brazilian food', 12.7, 1);

insert into permission (nm_permission , ds_permission) values ('nome 1', 'descrição 1');
insert into permission (nm_permission , ds_permission) values ('nome 2', 'descrição 2');

insert into state (nm_state) values ('estado 1');
insert into state (nm_state) values ('estado 2');


insert into city (nm_city, id_state) values ('cidade 1', 1);
insert into city (nm_city, id_state) values ('cidade 2', 2);