create table administrador(

login varchar(100) primary key,
senha varchar(100)

	
	
	
);

create table Paciente(

idPaciente serial primary key,
nome varchar(100) ,
cpf varchar(15) UNIQUE,
endereco varchar(100),
Email varchar(100),

);


create table Medico(

idMedico serial primary key,
nome varchar(100) ,
cpf varchar(15) UNIQUE,
especialidade varchar(100),
email varchar(100),
sexo varchar(50)


);




create table Agenda(
idAgenda serial primary key

horario varchar(50),
data varchar (50);

id_Medico int,

id_Paciente int,

FOREIGN KEY(id_Medico) 
REFERENCES Medico(idMedico) 

FOREIGN KEY(id_Paciente) 
REFERENCES Paciente(idPaciente) 

);


insert into administrador (login,senha) values ("admin","admin");


