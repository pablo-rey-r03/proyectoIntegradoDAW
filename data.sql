insert into proyectoIntegrado.user (email, password, role)
values  ('cliente@gmail.com', '$2a$10$AhdPnZ3xlCidVX/CAuruVeRzZXcNLqKRCgL2ZEAyQAMbtUNhPzJ5e', 'ROLE_CUS'), # Contraseña: cus
        ('ofertante@gmail.com', '$2a$10$4Q6eFtqN3YGXPi0XWUGeb.PxYZQeNugI04DgltjO8SKP51KaeVGpG', 'ROLE_OFF'); #Contraseña: off

insert into proyectoIntegrado.offerer (birth_date, id, address, formation, name, phone_number, surname, user_email)
values  ('2003-06-12 02:00:00.000000', 13, 'c/Calle 1, 1 A', null, 'John', '123456789', 'Doe Second', 'ofertante@gmail.com');

insert into proyectoIntegrado.customer (birth_date, id, address, name, phone_number, surname, user_email)
values  ('1992-07-30 02:00:00.000000', 7, 'c/Calle 1, 1B', 'John', '987654321', 'Doe', 'cliente@gmail.com');

insert into proyectoIntegrado.activity (available_quota, max_quota, price, date, id, offerer_id, description, duration, location, name, provided_mats, recommended_formation, required_mats)
values  (5, 5, 15, '2024-07-28 00:00:00.000000', 13, 13, 'Quedada para asistir juntos al festival de música folclórica de Hawáii.', '3 horas', 'La Cartuja', 'Festival de música hawaiana', null, null, null);

insert into proyectoIntegrado.request (customer_id, date, id, description, location, name)
values  (7, '2024-07-01 01:30:00.000000', 10, 'Me gustaría encontrar un grupo de gente para salir a hacer ejercicio: correr, calistenia, jugar a cualquier deporte de equipo, etc.', 'Parque Miraflores', 'Ejercicio en el parque');