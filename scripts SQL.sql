CREATE TABLE TipoAposta (
    ID serial PRIMARY KEY,
    Descricao varchar
);

CREATE TABLE StatusAposta (
    ID serial PRIMARY KEY,
    Descricao varchar
);

CREATE TABLE Usuario (
    ID serial PRIMARY KEY,
    Nome varchar,
    Email varchar,
    Senha varchar,
	Saldo double precision,
    Administrador bool
);


CREATE TABLE Evento (
    ID serial PRIMARY KEY,
    Nome varchar,
    Data date,
    timeCasa varchar,
    timeVisitante varchar,
    oddCasa double precision,
    oddEmpate double precision,
    oddVisitante double precision,
    aberta bool
);

CREATE TABLE Aposta (
    ID serial PRIMARY KEY,
    UsuarioID int,
    EventoID int,
    Valor double precision,
    DataAposta date,
    StatusApostaID int,
    TipoApostaID int,
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(ID),
    FOREIGN KEY (EventoID) REFERENCES Evento(ID),
    FOREIGN KEY (StatusApostaID) REFERENCES StatusAposta(ID),
    FOREIGN KEY (TipoApostaID) REFERENCES TIPOAPOSTA(ID)
);


INSERT INTO TIPOAPOSTA (Descricao) VALUES ('Vitoria da casa'), ('Empate'), ('Derrota da casa');
INSERT INTO STATUSAPOSTA (Descricao) VALUES ('Pendente'), ('Vencida'), ('Perdida');
INSERT INTO usuario(nome, email, senha, saldo, administrador) VALUES ('Owner', 'admin@catsbet.com', '$2a$10$DD4AhJ/s0Fx8FV75Yxd5I.T3l2OX1d1zFCgQ9NtIoNH3AOYtVa7ja' 1000.0, true);