CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO users (id, email)
VALUES
    (uuid_generate_v4(), 'user1@rtc.com'),
    (uuid_generate_v4(), 'user2@rtc.com'),
    (uuid_generate_v4(), 'user3@rtc.com'),
    (uuid_generate_v4(), 'user4@rtc.com'),
    (uuid_generate_v4(), 'user5@rtc.com');