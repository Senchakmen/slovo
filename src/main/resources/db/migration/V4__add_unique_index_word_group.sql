CREATE UNIQUE INDEX uq_user_word_group_name
ON word_group (name, user_id);