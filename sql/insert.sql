INSERT INTO admin (login, password) VALUES 
	('opsxolc', '123');

INSERT INTO flow (flow_number, year_of_study) VALUES
	(1, 1), 
	(2, 1), 
	(3, 1), 
	(1, 2), 
	(2, 2), 
	(3, 2), 
	(1, 3), 
	(2, 3), 
	(3, 3), 
	(1, 4), 
	(2, 4), 
	(3, 4);

INSERT INTO teacher (lastname, firstname, patronymic) VALUES 
	('Бахтин', 'Владимир', 'Александрович'),
	('Тыртышников', 'Евгений', 'Евгеньевич'), 
	('Хорошилов', 'Алексей', 'Владимирович'),
	('Садовничая', 'Инна', 'Викторовна'), 
	('Туйкина', 'Светлана', 'Рафгатовна'),
	('Смелянский', 'Руслан', 'Леонидович'),
	('Панфёров', 'Валерий', 'Семёнович'),
	('Вылиток', 'Алексей', 'Александрович');

INSERT INTO classroom (capacity, type, name) VALUES 
	(300, 'flow', 'П5'), 
	(280, 'flow', 'П6'), 
	(280, 'flow', 'П13'), 
	(30, 'group', '607'), 
	(28, 'group', '71'), 
	(24, 'group',  '707');

INSERT INTO course (course_name, cover, intensity, year_of_study) VALUES 
	('Конструирование ядра ОС', 'group', 2, NULL),
	('Введение в компьютерные сети ЭВМ', 'flow', 2, 3), 
	('Линейная алгебра', 'flow', 2, 1), 
	('Линейная алгебра', 'group', 2, 1),
	('Суперкопьютеры и параллельная обработка данных', 'flow', 1, 3), 
	('Уравнения математической физики', 'group', 3, 3),
	('Уравнения математической физики', 'flow', 2, 3),
	('Математический анализ', 'flow', 4, 1),
	('Математический анализ', 'group', 2, 1),
	('ОС и ЯП распределённых ВС', 'student', 1, NULL),
	('Компьютерная алгебра и теория ФЯ', 'student', 1, NULL);

INSERT INTO sgroup (flow_id, group_number) VALUES 
	(1, 102), 
	(4, 202),  
	(9, 328), 
	(9, 327), 
	(9, 325);

INSERT INTO student (lastname, firstname, patronymic, sgroup_id) VALUES 
	('Пенёк', 'Армен', 'Кашпович', 3), 
	('Орнамов', 'Колыван', 'Георгиевич', 1), 
	('Рунышкин', 'Вячеслав', 'Дилшодович', 3), 
	('Бан', 'Григорий', 'Валерьевич', 5),
	('Покажи', 'Олег', 'Макетович', 5);

INSERT INTO teacher_course (teacher_id, course_id, year) VALUES 
	(1, 5, 2018),
	(2, 3, 2016),
	(7, 4, 2016),
	(2, 4, 2016),
	(3, 1, 2018),
	(4, 8, 2016),
	(4, 9, 2016),
	(5, 6, 2018),
	(6, 2, 2018),
	(6, 2, 2017),
	(7, 3, 2017), 
	(8, 11, 2018),
	(1, 10, 2018),
	(4, 7, 2018);

create temp table wp(
	wday serial
);
insert into wp values (1), (2), (3), (4), (5), (6);
INSERT INTO sclass (classroom_id, tc_id, wday, pair_number)
	select classroom_id, tc_id, wp1.wday as wday, wp2.wday as pair_number
		from classroom as c, teacher_course as tc 
			join course as co on (tc.course_id = co.course_id), wp as wp1, wp as wp2
		where tc.year = 2018 and (co.course_name = 'ОС и ЯП распределённых ВС' or 
			co.course_name = 'Компьютерная алгебра и теория ФЯ' or co.course_name = 'Уравнения математической физики');

INSERT INTO student_class (student_id, sclass_id) 
	(select student_id, sclass_id
		from sclass as c join teacher_course as tc on (c.tc_id = tc.tc_id)
			join course as co on (tc.course_id = co.course_id) join classroom as cl 
			on (cl.classroom_id = c.classroom_id), student as s
		where co.course_name = 'ОС и ЯП распределённых ВС' and 
			(s.lastname = 'Пенёк' or s.lastname = 'Рунышкин' or s.lastname = 'Покажи')
			and wday = 5 and pair_number = 5 and cl.name = '707')
	union
	(select student_id, sclass_id
		from sclass as c join teacher_course as tc on (c.tc_id = tc.tc_id)
			join course as co on (tc.course_id = co.course_id) join classroom as cl 
			on (cl.classroom_id = c.classroom_id), student as s
		where co.course_name = 'Компьютерная алгебра и теория ФЯ' and 
			(s.lastname = 'Орнамов' or s.lastname = 'Бан')
			and wday = 2 and pair_number = 5 and cl.name = '707');

INSERT INTO group_class (sgroup_id, sclass_id)
	select sgroup_id, sclass_id
		from sclass as c join teacher_course as tc on (c.tc_id = tc.tc_id)
			join course as co on (tc.course_id = co.course_id) join classroom as cl 
			on (cl.classroom_id = c.classroom_id), sgroup as g 
		where g.group_number = 328 and co.course_name = 'Уравнения математической физики' 
			and co.cover = 'group' and (c.wday = 5 or c.wday = 3) and (c.pair_number = 1 
			or c.pair_number = 2) and cl.name = '607';

INSERT INTO flow_class (flow_id, sclass_id)
	select flow_id, sclass_id
		from sclass as c join teacher_course as tc on (c.tc_id = tc.tc_id)
			join course as co on (tc.course_id = co.course_id) join classroom as cl 
			on (cl.classroom_id = c.classroom_id), flow as f 
		where f.flow_number = 3 and f.year_of_study = co.year_of_study and co.course_name = 'Уравнения математической физики' 
			and co.cover = 'flow' and (c.wday = 5 or c.wday = 3) and (c.pair_number = 3 
			or c.pair_number = 4) and cl.name = 'П13';

DElETE FROM sclass 
	where sclass_id not in 
	(select sclass_id from student_class 
	union select sclass_id from group_class
	union select sclass_id from flow_class);

ALTER TABLE sclass
	ADD CONSTRAINT uni UNIQUE(classroom_id, pair_number, wday);


