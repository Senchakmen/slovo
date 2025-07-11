/* =================================================================
   V2__seed_languages.sql - insert initial ISO language codes
   ================================================================= */

INSERT INTO language (code, name)
VALUES
    ('en', 'English'),
    ('uk', 'Ukrainian'),
    ('de', 'German'),
    ('es', 'Spanish'),
    ('fr', 'French'),
    ('pl', 'Polish'),
    ('it', 'Italian'),
    ('pt', 'Portuguese')
ON CONFLICT (code) DO NOTHING;
