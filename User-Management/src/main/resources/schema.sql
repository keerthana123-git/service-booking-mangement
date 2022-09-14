/*
 * id;
	private String name;
	private String password;
	private Long mobile;
	private String mailId;
	private Date redgDate;
 */
create table AppUser (id int Auto_increment, name varchar(255), password varchar(255),mobile bigint,mail_Id varchar(255),redg_Date Date);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

insert into App_User (name,password,mobile,mail_Id,redg_Date) values('Fahim','1234',7004100000,'fahim@gmail.com','2022-08-17');
insert into App_User (name,password,mobile,mail_Id,redg_Date) values('Yogi','1234',9860124563,'yogi@gmail.com','2022-08-18');
insert into App_User (name,password,mobile,mail_Id,redg_Date) values('Siddesh','1234',8541236475,'siddesh@gmail.com','2022-08-19');
