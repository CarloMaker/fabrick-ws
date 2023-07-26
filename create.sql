
    create table transaction (
        amount float(23) not null,
        id integer not null auto_increment,
        list_transaction_id integer not null,
        accounting_date varchar(255),
        currency varchar(255),
        description varchar(255),
        operation_id varchar(255),
        transaction_id varchar(255),
        value_date varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table transazioni_operation (
        id integer not null auto_increment,
        request_date datetime(6),
        primary key (id)
    ) engine=InnoDB;

    alter table transaction 
       add constraint FKb23sf6psjivpka568idqcrg6f 
       foreign key (list_transaction_id) 
       references transazioni_operation (id);
