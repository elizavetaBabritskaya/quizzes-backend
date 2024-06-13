create table users (
  email VARCHAR(256) PRIMARY KEY,
  name VARCHAR(256),
  password VARCHAR(256),
  enabled boolean
);

create table user_roles (
  email  VARCHAR(256) REFERENCES users(email) ON DELETE CASCADE,
  role VARCHAR(256),
  PRIMARY KEY (email, role)
);

INSERT INTO users (email, name, password, enabled) VALUES
('user@example.com', 'user' ,'$2a$10$5MS5X.YIftZ4xpI9bPituOTLA9GaGUIcPWcnBgLbPnBofCJZaj.UO',true),
('admin@example.com', 'admin' ,'$2a$10$JXgnlWSXcb4JTaXgXA6tD.DPJSyHtmxoYcYEvc0Q4pjeH4thLVVjW', true);

INSERT INTO user_roles (email, role) VALUES
('user@example.com', 'USER'),
('admin@example.com', 'ADMIN');