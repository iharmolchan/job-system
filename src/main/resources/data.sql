/* Job types */
INSERT INTO job_types (id, name, handler_name)
VALUES (10001, 'Default', 'default_handler');

INSERT INTO job_types (id, name, handler_name)
VALUES (10002, 'Email', 'email_handler');

/* Job definitions */

INSERT INTO job_definitions (id, job_type_id, name, cron_expression, next_run, payload)
VALUES (10001, 10001, 'Some default definition', '0 */1 * ? * *', DATEADD(minute, 1, NOW()), '{}');

INSERT INTO job_definitions (id, job_type_id, name, cron_expression, next_run, payload)
VALUES (10002, 10001, 'Another default definition', '0 */2 * ? * *', DATEADD(minute, 2, NOW()), '{"data": "some string"}');
