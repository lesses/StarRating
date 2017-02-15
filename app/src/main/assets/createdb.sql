create table "day_records"(
	"id" char(32) not null primary key,
	"date" integer not null unique,
	"value" float
);
