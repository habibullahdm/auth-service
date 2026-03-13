INSERT INTO auth.roles (id, name, created_by, created_at, updated_by, updated_at)
VALUES
('role_superadmin', 'SUPER_ADMIN', 'SYSTEM', now(), 'SYSTEM', now()),
('role_admin', 'ADMIN', 'SYSTEM', now(), 'SYSTEM', now());
