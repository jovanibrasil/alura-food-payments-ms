CREATE TABLE payments (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  value DECIMAL(19, 2) NOT NULL,
  name VARCHAR(100) DEFAULT NULL,
  number VARCHAR(19) DEFAULT NULL,
  expiring VARCHAR(7) NOT NULL,
  code VARCHAR(3) DEFAULT NULL,
  status VARCHAR(255) NOT NULL,
  order_id VARCHAR(20) NOT NULL,
  PRIMARY KEY(id)
);