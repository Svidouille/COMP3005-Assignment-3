Go on PostgreSQL and enter the following in the query section of the already created "Students" database : 

CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    enrollment_date DATE
);

INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');



N.B. : Download the jar driver from the PostgreSQL driver download page.

Finally, compile and run the code.

Video Demonstration link : https://youtu.be/RLdRRdobXGI

Rayane Boufarik,
101323061