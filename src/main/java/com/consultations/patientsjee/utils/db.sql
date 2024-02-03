CREATE DATABASE IF NOT EXISTS patients_db;
USE patients_db;

CREATE TABLE IF NOT EXISTS patients (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS medical_forms (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    care_type VARCHAR(500) NOT NULL,
    duration INT
);

CREATE TABLE IF NOT EXISTS consultations (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    date_consultation DATE NOT NULL,
    doctor_last_name VARCHAR(255) NOT NULL,
    doctor_first_name VARCHAR(255) NOT NULL,
    patient_id BIGINT NOT NULL,
    medical_form_id BIGINT NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (medical_form_id) REFERENCES medical_forms(id)
);

CREATE TABLE IF NOT EXISTS prescriptions (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    pill_type VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    consultation_id BIGINT NOT NULL,
    FOREIGN KEY (consultation_id) REFERENCES consultations(id)
);


insert into  patients (last_name,first_name,birth_date,image_url)
values
('Popov', 'Vadim', "1975-05-15",'https://img.freepik.com/free-vector/illustration-user-avatar-icon_53876-5907.jpg?w=826&t=st=1706192993~exp=1706193593~hmac=a640026012b274e821b4b273acae9a804eb77191ccef63b5210d44effe47809b' ),
('Yusuf', 'Memhet', "1998-06-22",'https://img.freepik.com/free-vector/illustration-user-avatar-icon_53876-5907.jpg?w=826&t=st=1706192993~exp=1706193593~hmac=a640026012b274e821b4b273acae9a804eb77191ccef63b5210d44effe47809b' ),
('Adams', 'Wednesday', "2008-10-01",'https://img.freepik.com/free-vector/illustration-customer-service-concept_53876-5882.jpg?t=st=1706192975~exp=1706193575~hmac=1923f76a9fb4827c6f0216032cfe646d961a25ac28f640848ce1795f257cc3f8' ),
('Stark', 'Arya', "1985-02-02",'https://img.freepik.com/free-vector/illustration-customer-service-concept_53876-5882.jpg?t=st=1706192975~exp=1706193575~hmac=1923f76a9fb4827c6f0216032cfe646d961a25ac28f640848ce1795f257cc3f8' );


select * from consultations;