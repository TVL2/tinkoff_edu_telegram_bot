CREATE TABLE IF NOT EXISTS chat
(
    id BIGINT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS link
(
    id          BIGSERIAL PRIMARY KEY,
    url         varchar(500) UNIQUE,
    last_update timestamp
);

CREATE TABLE IF NOT EXISTS chat_links
(
    chat    BIGINT REFERENCES chat (id) ON DELETE CASCADE,
    link_id BIGINT REFERENCES link (id)
)
