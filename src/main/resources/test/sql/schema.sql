CREATE TABLE IF NOT EXISTS COURSES
(
    id uuid NOT NULL,
    name varchar(100) NOT NULL,
    category varchar(20) NOT NULL,
    rating int NOT NULL,
    description varchar(1000) NOT NULL,
    PRIMARY KEY (id)
);