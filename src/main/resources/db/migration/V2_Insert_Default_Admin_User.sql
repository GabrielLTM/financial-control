INSERT INTO users (username, fullname, password)
VALUES ('admin', 'Administrator', '$2a$10$8.A34cRk3P9jE9L5sV9a.eJc4.0d.aR3f.B2.qGk5.z.k5.z.k5.z')
    ON DUPLICATE KEY UPDATE username = username; -