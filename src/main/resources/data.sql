DROP TABLE IF EXISTS transaction;
 
CREATE TABLE transaction (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id VARCHAR(250) NOT NULL,
  month VARCHAR(250) NOT NULL,
  transaction_amount VARCHAR(250) NOT NULL
);


insert into transaction
values(1,'E0',1,200);

insert into transaction
values(2,'E0',1,80);

insert into transaction
values(3,'E0',1,40);

insert into transaction
values(4,'E0',1,100);

insert into transaction
values(5,'E0',2,200);

insert into transaction
values(6,'E0',2,80);

insert into transaction
values(7,'E0',2,40);

insert into transaction
values(8,'E0',2,100);

insert into transaction
values(9,'E0',3,200);

insert into transaction
values(10,'E0',3,80);

insert into transaction
values(11,'E0',3,40);

insert into transaction
values(12,'E0',3,100);


insert into transaction
values(13,'E1',1,200);

insert into transaction
values(14,'E1',1,80);

insert into transaction
values(15,'E1',1,40);

insert into transaction
values(16,'E1',1,100);

insert into transaction
values(17,'E1',2,200);

insert into transaction
values(18,'E1',2,80);

insert into transaction
values(19,'E1',2,40);

insert into transaction
values(20,'E1',2,100);

insert into transaction
values(21,'E1',3,200);

insert into transaction
values(22,'E1',3,80);

insert into transaction
values(23,'E1',3,40);

insert into transaction
values(24,'E1',3,100);

insert into transaction
values(25,'E2',3,120);