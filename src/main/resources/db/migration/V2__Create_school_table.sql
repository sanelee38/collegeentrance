CREATE TABLE school
(
	id INT primary key AUTO_INCREMENT,
	NAME VARCHAR(50) NOT NULL,
	AREA_id int  NOT NULL,
	batch VARCHAR(10) NULL,
	description LONGTEXT,
	foreign key (AREA_id) references area(id)
);