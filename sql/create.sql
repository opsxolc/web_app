CREATE TABLE admin(
	admin_id serial not null,
	login varchar(20) unique not null,
	password varchar(20) not null,
	PRIMARY KEY(admin_id)
);

CREATE TABLE flow(
	flow_id serial not null,
	flow_number smallint CHECK (flow_number > 0) not null,
	year_of_study smallint CHECK (year_of_study > 0) not null,
	PRIMARY KEY (flow_id)
);

CREATE TABLE sgroup(
	sgroup_id serial not null,
	flow_id smallint not null,
	group_number smallint CHECK (group_number > 0),
	PRIMARY KEY (sgroup_id),
	FOREIGN KEY (flow_id) REFERENCES flow (flow_id) ON DELETE CASCADE
);

CREATE TABLE student(
	student_id serial not null,
	patronymic varchar(20),
	firstname varchar(20) not null,
	lastname varchar(20) not null,
	sgroup_id smallint not null,
	PRIMARY KEY (student_id),
	FOREIGN KEY (sgroup_id) REFERENCES sgroup (sgroup_id) ON DELETE CASCADE
);

CREATE TABLE teacher(
	teacher_id serial not null,
	patronymic varchar(20),
	firstname varchar(20) not null,
	lastname varchar(20) not null,
	PRIMARY KEY (teacher_id)
);

CREATE TABLE course(
	course_id serial not null,
	course_name varchar(50) not null,
	cover varchar(10) not null,
	intensity smallint,
	year_of_study smallint,
	PRIMARY KEY (course_id),
	UNIQUE(course_name, cover)
);

CREATE TABLE teacher_course(
	tc_id serial not null,
	teacher_id smallint not null,
	course_id smallint not null,
	year smallint,
	PRIMARY KEY (tc_id),
	FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id) ON DELETE CASCADE,
	FOREIGN KEY (course_id) REFERENCES course (course_id) ON DELETE CASCADE,
	UNIQUE(teacher_id, course_id, year)
);

CREATE TABLE classroom(
	classroom_id serial not null,
	capacity smallint CHECK (capacity > 0),
	type varchar(10) not null,
	name varchar(4) not null,
	PRIMARY KEY (classroom_id)
);

CREATE TABLE sclass(
	sclass_id serial not null,
	classroom_id smallint not null,
	pair_number smallint not null,
	tc_id smallint not null,
	wday smallint CHECK (wday > 0 AND wday < 7),
	PRIMARY KEY (sclass_id),
	FOREIGN KEY (tc_id) REFERENCES teacher_course (tc_id) ON DELETE CASCADE,
	FOREIGN KEY (classroom_id) REFERENCES classroom (classroom_id) ON DELETE CASCADE
);

CREATE TABLE student_class(
	student_id smallint not null,
	sclass_id smallint not null,
	PRIMARY KEY (student_id, sclass_id),
	FOREIGN KEY (student_id) REFERENCES student (student_id) ON DELETE CASCADE,
	FOREIGN KEY (sclass_id) REFERENCES sclass (sclass_id) ON DELETE CASCADE
);

CREATE TABLE group_class(
	sgroup_id smallint not null,
	sclass_id smallint not null,
	PRIMARY KEY (sgroup_id, sclass_id),
	FOREIGN KEY (sgroup_id) REFERENCES sgroup (sgroup_id) ON DELETE CASCADE,
	FOREIGN KEY (sclass_id) REFERENCES sclass (sclass_id) ON DELETE CASCADE
);

CREATE TABLE flow_class(
	flow_id smallint not null,
	sclass_id smallint not null,
	PRIMARY KEY (flow_id, sclass_id),
	FOREIGN KEY (flow_id) REFERENCES flow (flow_id) ON DELETE CASCADE,
	FOREIGN KEY (sclass_id) REFERENCES sclass (sclass_id) ON DELETE CASCADE
);