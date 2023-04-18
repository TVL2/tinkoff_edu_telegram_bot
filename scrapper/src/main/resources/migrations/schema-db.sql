CREATE TABLE IF NOT EXISTS chat
(
    id BIGINT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS link
(
    id          BIGINT PRIMARY KEY,
    url         text UNIQUE,
    last_update time
);

CREATE TABLE IF NOT EXISTS chat_links
(
    chat    BIGINT REFERENCES chat (id),
    link_id BIGINT REFERENCES link (id)
)
