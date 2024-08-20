begin
   execute immediate 'drop table users';
exception
   when others then null;
end;
/

CREATE TABLE users (
    user_id varchar2(20) NOT NULL,
    email varchar2(30) NOT NULL,
    password varchar2(30) NOT NULL,
    pan_details varchar2(30) NOT NULL,
    bank_account_number varchar2(30) NOT NULL,
    ifsc_code varchar2(30) NOT NULL,
    banking_partner varchar2(30) NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO users VALUES('user001', 'john.doe@example.com', 'password123', 'ABCDE1234F', '1234567890', 'HDFC0000123', 'HDFC');
INSERT INTO users VALUES('user002', 'jane.smith@example.com', 'password456', 'ABCDE5678G', '0987654321', 'ICIC0000456', 'ICICI');
INSERT INTO users VALUES('user003', 'david.jones@example.com', 'password789', 'ABCDE9101H', '1122334455', 'SBI0000789', 'SBI');
INSERT INTO users VALUES('user004', 'emma.brown@example.com', 'password012', 'ABCDE2345J', '5566778899', 'PNB0001234', 'PNB');
