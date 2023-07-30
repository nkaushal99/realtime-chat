CREATE TABLE users
(
    id    UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE rooms
(
    id   UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    type BOOLEAN     NOT NULL
);

CREATE TABLE participants
(
    id      UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    room_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (room_id) REFERENCES rooms (id)
);
