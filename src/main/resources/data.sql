INSERT INTO filter (filter_id, name) VALUES (nextval('public.seq_filter_id'), 'TestFilter1');
INSERT INTO filter (filter_id, name) VALUES (nextval('public.seq_filter_id'), 'TestFilter2');

INSERT INTO criteria (criteria_id, type, condition, criteria_value, selected, filter_id)
VALUES (nextval('public.seq_criteria_id'), 'Amount', 'More', '4', true, 1);

INSERT INTO criteria (criteria_id, type, condition, criteria_value, selected, filter_id)
VALUES (nextval('public.seq_criteria_id'), 'Title', 'Start with', 'Meow', false, 1);

INSERT INTO criteria (criteria_id, type, condition, criteria_value, selected, filter_id)
VALUES (nextval('public.seq_criteria_id'), 'Date', 'From', '2021-10-25M00:00:00.000Z', false, 1);

INSERT INTO criteria (criteria_id, type, condition, criteria_value, selected, filter_id)
VALUES (nextval('public.seq_criteria_id'), 'Amount', 'More', '4', true, 2);