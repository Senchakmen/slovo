/* ---------- master tables ---------- */
CREATE TABLE language (
    id      SERIAL PRIMARY KEY,
    code    VARCHAR NOT NULL UNIQUE,
    name    VARCHAR NOT NULL
);

CREATE TABLE "user" (
    id          SERIAL PRIMARY KEY,
    username    VARCHAR NOT NULL UNIQUE,
    email       VARCHAR,
    created_at  TIMESTAMP DEFAULT NOW()
);

/* ---------- per-user helpers ---------- */
CREATE TABLE user_language_pair (
    id          SERIAL PRIMARY KEY,
    user_id     INT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
    source_id   INT NOT NULL REFERENCES language(id),
    target_id   INT NOT NULL REFERENCES language(id),
    is_default  BOOLEAN NOT NULL DEFAULT FALSE
);

/* ensure each user can flag at most one default pair */
CREATE UNIQUE INDEX uq_user_default_pair
    ON user_language_pair(user_id)
    WHERE is_default;

/* optional groups / folders */
CREATE TABLE word_group (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL,
    user_id INT REFERENCES "user"(id) ON DELETE CASCADE
);

/* ---------- core word table ---------- */
CREATE TABLE slovo (
    id           SERIAL PRIMARY KEY,
    user_id      INT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
    language_id  INT NOT NULL REFERENCES language(id),
    text         VARCHAR NOT NULL,
    audio_url    VARCHAR,
    group_id     INT REFERENCES word_group(id) ON DELETE SET NULL,
    created_at   TIMESTAMP DEFAULT NOW(),
    modified_at  TIMESTAMP DEFAULT NOW(),

    CONSTRAINT uq_slovo_user_text_lang UNIQUE (user_id, text, language_id)
);

/* ---------- child tables ---------- */
CREATE TABLE translation (
    id             SERIAL PRIMARY KEY,
    slovo_id       INT NOT NULL REFERENCES slovo(id) ON DELETE CASCADE,
    language_id    INT NOT NULL REFERENCES language(id),   -- target language
    part_of_speech VARCHAR,
    meaning        VARCHAR NOT NULL,
    example        VARCHAR
);

CREATE TABLE example_sentence (
    id           SERIAL PRIMARY KEY,
    slovo_id     INT NOT NULL REFERENCES slovo(id) ON DELETE CASCADE,
    language_id  INT NOT NULL REFERENCES language(id),    -- language of translation
    sentence     TEXT NOT NULL,
    translation  TEXT
);
