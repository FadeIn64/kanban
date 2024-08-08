create table users(
    username text primary key,
    email text,

    constraint email_check check (email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
);

create table users_auth_data(
    username text primary key,
    password text,

    constraint user_fk foreign key (username) references users(username)
);