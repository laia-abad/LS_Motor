DROP USER IF EXISTS 'analytic_user';
CREATE USER 'analytic_user';

GRANT SELECT, SHOW DATABASES, CREATE VIEW, SHOW VIEW 
ON OLAP
TO 'analytic_user';

DROP USER IF EXISTS 'manager_user';
CREATE USER 'manager_user';

GRANT SELECT, INSERT, UPDATE, DELETE
ON OLTP
TO 'manager_user';

GRANT SELECT, INSERT, UPDATE, DELETE	 
ON OLAP
TO 'manager_user';

DROP USER IF EXISTS 'rrhh_user';
CREATE USER 'rrhh_user';

GRANT CREATE USER 
ON here
TO 'rrhh_user';

SHOW GRANTS FOR 'analytic_user'