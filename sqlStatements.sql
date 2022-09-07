drop database ars;
create database ars;
use ars;

create table passenger(
    pid int auto_increment,
    email varchar(50) not null,
    pwd varchar(20) not null,
    name varchar(30) not null,
    gender varchar(1) not null,
    city varchar(20) not null,
    contact varchar(10) not null,
    primary key(pid,name)
);

create table flight(
    tid int,
    name varchar(20) not null,
    source varchar(30) not null,
    destination varchar(30) not null,
    max_seats int not null,
    duration int not null,
    primary key(tid)
);

create table ticket(
    ticno int auto_increment,
    pid int not null,
    tid int not null,
    dot varchar(15) not null,
    ano int not null,
    cno int not null,
    ticstatus varchar(15),
    primary key(ticno),
    foreign key(pid) references passenger(pid),
    foreign key(tid) references flight(tid)
);

insert into passenger(email, pwd, name, gender, city, contact) values("vicky@gmail.com", "1234", "svj", "m", "kpm", 1234567890);
insert into passenger(email, pwd, name, gender, city, contact) values("admin@gmail.com", "admin", "Admin", "f", "che", 7894561230);
insert into passenger(email, pwd, name, gender, city, contact) values("user@gmail.com", "user", "jansi", "f", "che", 6374388346);
insert into passenger(email, pwd, name, gender, city, contact) values("ssn@gmail.com", "ssn", "ssn", "f", "che", 7894561230);

insert into flight(tid, name, source, destination, max_seats, duration) values(1, "flight1", "Chennai", "Madurai", 78, 1);
insert into flight(tid, name, source, destination, max_seats, duration) values(2, "flight2", "Chennai", "Madurai", 89, 1);
insert into flight(tid, name, source, destination, max_seats, duration) values(3, "flight3", "Chennai", "Bangalore", 67, 1);
insert into flight(tid, name, source, destination, max_seats, duration) values(4, "flight2", "Chennai", "Erode", 45, 1);

insert into ticket(pid, tid, dot, ano, cno, ticstatus) values(1, 1, "2022-05-26", 2, 1, "Confirmed");
insert into ticket(pid, tid, dot, ano, cno, ticstatus) values(2, 2, "2022-05-31", 3, 2, "Waiting");
insert into ticket(pid, tid, dot, ano, cno, ticstatus) values(3, 3, "2022-05-24", 3, 2, "Waiting");
insert into ticket(pid, tid, dot, ano, cno, ticstatus) values(4, 4, "2022-05-26", 3, 2, "Confirmed");