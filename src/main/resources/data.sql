INSERT INTO job_types (id, name, handler_name)
VALUES (10001, 'Test', 'TEST_HANDLER');

INSERT INTO job_definitions (id, job_type_id)
VALUES (10001, 10001);

INSERT INTO job_definitions (id, job_type_id)
VALUES (10002, 10001);
