create database it_academy;

use it_academy;

create table students
(
    id         int primary key auto_increment,
    first_name varchar(50) not null,
    last_name  varchar(50) not null
);

create table teachers
(
    id         int primary key auto_increment,
    first_name varchar(50) not null,
    last_name  varchar(50) not null
);

create table subjects
(
    id   int primary key auto_increment,
    name varchar(50) not null unique
);

create table grades
(
    id         int primary key auto_increment,
    grade      int  not null,
    comment    text not null,
    date       date not null,
    subject_id int  not null,
    student_id int  not null,
    foreign key (student_id) references students (id),
    foreign key (subject_id) references subjects (id)
);


insert into students (first_name, last_name)
values
    ('Олександр', 'Шевченко'),
    ('Тарас', 'Коваленко'),
    ('Оксана', 'Бойко'),
    ('Наталія', 'Мельник'),
    ('Ігор', 'Тимошенко'),
    ('Софія', 'Кравченко'),
    ('Микола', 'Данилюк'),
    ('Іван', 'Гаврилюк'),
    ('Людмила', 'Петренко'),
    ('Віктор', 'Сидоренко');

insert into subjects (name)
values
    ('Java'),
    ('C#'),
    ('JavaScript'),
    ('React'),
    ('ASP.NET'),
    ('C++'),
    ('HTML/CSS');
