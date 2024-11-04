-- User Table
INSERT INTO "user"(id, full_name, user_name, password, email, phone_number, role) VALUES('55d18bd8-05f2-4410-8c63-14ab6b7ac06e', 'Luffy do Chapéu de Palha', 'luffyonep', '$2a$12$OYKGmXO3Ntz/pb0qRFrZjO1XuPS2asQZYvnlFuxNiu7C1Wofs.R/u', 'luffy.palha@gmail.com', '(11) 2222-2222', 'ADMIN');
INSERT INTO "user"(id, full_name, user_name, password, email, phone_number, role) VALUES('92890067-d14a-4dd9-8063-aeb1a9893c10', 'Mestre Kame', 'kamehameha', '$2a$10$.J1EJxhHaWcqxG.wo8dXkeE2Soani7Y89009Re/CyHgTRHxem.YKi', 'mestre_kame.ha@uol.com.br', '(11) 93333-2222', 'ADMIN');
INSERT INTO "user"(id, full_name, user_name, password, email, phone_number, role) VALUES('436f13b6-bf4f-45f7-8bb8-4c904fecd799', 'Paula Tejando', 'paulat', '$2a$10$xrsOKzwlwcfgR8BBRYncmeWWAwXQGqCn9bd0RKqnloa/eue.3m0NK', 'paulat.jando@hotmail.com', '(11) 96565-4141', 'USER');

-- Customer Table
INSERT INTO customer(id, name, document) VALUES('8d4de05b-0a8a-4e5b-809f-1ce392a4352a', 'Simas Turbo', '321.456.878-32');
INSERT INTO customer(id, name, document) VALUES('a8b7b18e-5e78-4fa6-970a-2c4ebfb74b4d', 'Naruto Uzumaki', '325.854.320-88');
INSERT INTO customer(id, name, document) VALUES('6837626f-ce24-4311-98d7-c7495e093158', 'Light Yagami', '402.321.898-18');

-- Product Table
INSERT INTO product(id, name, price, discount) VALUES('84804497-6f87-46ec-8b97-c9ddaae5f4fd', 'Notebook Gamer', 4999.99, 0);
INSERT INTO product(id, name, price, discount) VALUES('6e5fa41e-5e6a-44d1-b9a4-ee42e389d40f', 'Vídeo Game PolyStation', 399.99, 5.00);
INSERT INTO product(id, name, price, discount) VALUES('0bad80f4-ba82-42bb-bd9c-6f5230f9e835', 'Mochila Saint Seiya', 29.99, 0);