insert into status_code (code, code_class, value)
values ('REL_STATUS_M', 'REL_STATUS', 'Married'),
       ('REL_STATUS_R', 'REL_STATUS', 'In a relationship'),
       ('REL_STATUS_S', 'REL_STATUS', 'Single'),
       ('REL_STATUS_C', 'REL_STATUS', 'Complicated')
on conflict do nothing;

insert into status_code(code, code_class, value)
values ('VIS_STATUS_PR', 'VIS_STATUS', 'Private'),
       ('VIS_STATUS_PU', 'VIS_STATUS', 'Public'),
       ('VIS_STATUS_FO', 'VIS_STATUS', 'Friends-Only')
on conflict do nothing;

insert into status_code(code, code_class, value)
values ('FR_STATUS_S', 'FR_STATUS', 'Sent'),
       ('FR_STATUS_B', 'FR_STATUS', 'Blocked'),
       ('FR_STATUS_A', 'FR_STATUS', 'Accepted')
on conflict do nothing;