/* repeatable migration for local/demo data */

INSERT INTO "user"(id,username,email)
VALUES (999,'demo','demo@example.com')
ON CONFLICT (id) DO NOTHING;

/* assume 999 owns EN→UK default pair  */
INSERT INTO user_language_pair(user_id,source_id,target_id,is_default)
VALUES (999, (SELECT id FROM language WHERE code='en'),
            (SELECT id FROM language WHERE code='uk'),
            true)
ON CONFLICT (id) DO NOTHING;