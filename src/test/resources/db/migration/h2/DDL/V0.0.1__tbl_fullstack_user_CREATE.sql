create sequence if not exists public.user_sequence;

create table if not exists public.tbl_fullstack_user
(
    id     bigint       not null default nextval('user_sequence'),
    email  varchar(255) not null,
    gender varchar(255) not null,
    name   varchar(255) not null,
    constraint tbl_fullstack_user_pkey
        primary key (id),
    constraint tbl_fullstack_user_email_uk
        unique (email)
);
