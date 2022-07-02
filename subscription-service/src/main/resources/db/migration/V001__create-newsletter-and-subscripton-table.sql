create table if not exists newsletter
(
    id          uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    description varchar(100) not null
);

create table if not exists subscription
(
    id            uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name    varchar(100),
    email         varchar(100) not null,
    gender        varchar(15),
    date_of_birth timestamp    not null,
    consent       boolean      not null,
    newsletter_id uuid         not null,

    foreign key (newsletter_id) references newsletter (id)
);


insert into newsletter(id, description) values ('698dc19d-489c-4e4d-b73e-28a713eab07b','fixed_id_newsletter');
insert into newsletter(description) values ('random_id_newsletter');