// initial database sctipt

INSERT INTO customer (customer_id, first_name, last_name, address_line_1, bonus_points) VALUES (1, 'Test', 'Test', 'addr line 1', 0);
INSERT INTO customer (customer_id, first_name, last_name, address_line_1, bonus_points) VALUES (2, 'Sasa', 'Prsic', 'addr1', 0);
INSERT INTO employee (employee_id, first_name, last_name) VALUES (1, 'John', 'Doe');
INSERT INTO genre VALUES ('Action');
INSERT INTO movie (movie_id, name, year, movie_type) VALUES (1,'Some new movie', '2017', 'NEW_RELEASE');
INSERT INTO movie (movie_id, name, year, movie_type) VALUES (2,'Some regular movie', '2010', 'REGULAR_FILM');
INSERT INTO movie (movie_id, name, year, movie_type) VALUES (3,'Some old movie', '1966', 'OLD_FILM');

INSERT INTO movie_genre VALUES (1, 'Action');
INSERT INTO movie_genre VALUES (2, 'Action');
INSERT INTO movie_genre VALUES (3, 'Action');

insert INTO lease (lease_id, lease_date, customer_customer_id, employee_employee_id) VALUES (1, '2017-06-08', 2, 1);
INSERT INTO lease_details (lease_details_id, movie_movie_id, return_date, lease_lease_id) VALUES (1, 1, '2017-06-10', 1);
INSERT INTO lease_details (lease_details_id, movie_movie_id, return_date, lease_lease_id) VALUES (2, 2, '2017-06-11', 1);




