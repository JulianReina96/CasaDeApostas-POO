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
    Email varchar UNIQUE,
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
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(ID) ON DELETE CASCADE,
    FOREIGN KEY (EventoID) REFERENCES Evento(ID),
    FOREIGN KEY (StatusApostaID) REFERENCES StatusAposta(ID),
    FOREIGN KEY (TipoApostaID) REFERENCES TIPOAPOSTA(ID)
);


INSERT INTO TIPOAPOSTA (Descricao) VALUES ('Vitoria da casa'), ('Empate'), ('Derrota da casa');
INSERT INTO STATUSAPOSTA (Descricao) VALUES ('Pendente'), ('Vencida'), ('Perdida');
INSERT INTO usuario(nome, email, senha, saldo, administrador) VALUES ('Owner', 'admin@catsbet.com', '$2a$10$DD4AhJ/s0Fx8FV75Yxd5I.T3l2OX1d1zFCgQ9NtIoNH3AOYtVa7ja', 1000.0, true);
INSERT INTO Evento (Nome, Data, TimeCasa, TimeVisitante, OddCasa, OddEmpate, OddVisitante, Aberta)
VALUES ('Campeonato Brasileiro Série A', '2024-09-07', 'Flamengo', 'Corinthians', 1.80, 3.25, 4.10, true);

INSERT INTO Evento (Nome, Data, TimeCasa, TimeVisitante, OddCasa, OddEmpate, OddVisitante, Aberta)
VALUES ('Copa do Brasil', '2024-09-10', 'Athletico Paranaense', 'Fortaleza', 2.10, 3.00, 3.75, true);

INSERT INTO Evento (Nome, Data, TimeCasa, TimeVisitante, OddCasa, OddEmpate, OddVisitante, Aberta)
VALUES ('Premier League Inglesa', '2024-09-14', 'Manchester United', 'Liverpool', 2.50, 3.50, 2.80, true);

INSERT INTO Evento (Nome, Data, TimeCasa, TimeVisitante, OddCasa, OddEmpate, OddVisitante, Aberta)
VALUES ('La Liga Espanhola', '2024-09-15', 'Barcelona', 'Real Madrid', 2.30, 3.10, 3.40, true);

INSERT INTO Evento (Nome, Data, TimeCasa, TimeVisitante, OddCasa, OddEmpate, OddVisitante, Aberta)
VALUES ('Ligue 1 Francesa', '2024-09-17', 'Paris Saint-Germain', 'Olympique de Marseille', 1.70, 3.80, 4.50, true);

INSERT INTO Evento (Nome, Data, TimeCasa, TimeVisitante, OddCasa, OddEmpate, OddVisitante, Aberta)
VALUES ('Serie A Italiana', '2024-09-18', 'Juventus', 'Inter de Milão', 2.00, 3.30, 3.90, true);