# Sistema de Clínica (ClinicaNator)
Sistema para o gerenciamento de uma clínica. A seguir é apresentado as principais funcionalidades do sistema:
- Agenda;
- Gerenciamento de Consultas;
- Gerenciamento de Pacientes;
- Gerenciamento de Profissionais - além do controle de especialidades dos profissionais;
- Gerenciamento de Usuários (acesso ao sistema);
- Gerenciamento de Procedimentos;

**ATENÇÃO**
- Aplicação desenvolvida em um computador Windows;
- foi testado em Linux/Mac;

# Desenvolvimento:
- Desenvolvedor: Lucas Esteves
- Desenvolvido Para avaliação de Prj - FAETERJ 
- Local
  - Eclipse
  - JRE 1.18.0
  - Banco de Dados PostgreSQL;

## Instalação

- Computador com Windows 7 ou superior (32/64 bits);
- Banco de Dados PostgreSQL instalado localmente na máquina.(utilizei o HEROKU para subir o banco)
- JRE 1.18.0 (Windows 32/64 bits);



- Sobre o Instalador
  - Apenas é executado em computadores com **Windows 7 ou superior (32/64 bits)**;


- Para alterar as configurações do banco de dados:
  - Abra a aplicação em uma IDE de desenvolvimento 
  - Acesse o arquivo **src/banco/Conexão** e altere as credenciais do banco de dados;
  - Execute a aplicação para recriar o **.jar**;
  

## Funcionalidades

- <strong>Login:</strong> Sistema de autenticação. Utilize os seguintes dados para realizar login na aplicação

> Login: <strong>admin</strong>;
> Senha: <strong>admin</strong>;

<br/>

[![Login](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/login.png)](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/login.png)
<br/><br/>
- **Agenda:** é possível agendar consultas, além de realizar o agendamento recorrente - diariamente, semanalmente, quinzenalmente, mensalmente e anualmente;
<br/><br/>

> **Outras funcionalidades:** 
  > - Email de confirmação;
  > - Excluir agendamento;
  > - Editar agendamento;
  > - Agendamento no sábado opcional; 
  > - O domingo *SEMPRE* é ignorado no agendamento recorrente;
  > - Filtro por data e pesquina pelo nome do paciente;
  > - Sistema 100% Forte para evitar consultas repetidas;

<br/><br/>

[![Agenda](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/agenda.png)](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/agenda.png)
<br/><br/>  
[![Novo agendamento](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/agenda2.png)](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/agenda2.png)
<br/><br/>  
[![Editar agendamento](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/AGENDA3.png)](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/AGENDA3.png)


<br/><br/>  

- <strong>Pacientes</strong>: cadastro e edição dos dados de um paciente, além da listagem

> **Outras funcionalidades:** 
 > - Editar Paciente;
 > - Exluir Paciente;
 > - API para validação de cpf (somente cpf valido);
 > - API Email para envio de confirmação;
 > - Pesquisa por código, nome, CPF e data de nascimento do paciente;
  

<br/><br/>

[![Lista de Pacientes](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/PACIENTE.png)](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/PACIENTE.png)
<br/><br/>  
[![Novo paciente](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/paciente2.png)](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/paciente2.png)
<br/><br/>  


- <strong>Profissionais</strong>: cadastro e edição dos dados de um profissional, além da listagem;

> **Outras funcionalidades:** 
  > - Editar profissional;
  > - Excluir profissional;
  > - API para validação de cpf (somente cpf valido); 
  > - Cadastro, edição, remoção e listagem de especialidades;
  > - Pesquisa por código, nome, especialidade e login do profissional;
 

<br/><br/>

[![Lista de Profissionais](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/PROFISSIONAL.png))](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/PROFISSIONAL.png)
[![Lista de especialidades](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/PROFISSIONAL2.png)](https://github.com/luqui2/Sistema-Clinica-Medicaa/blob/main/Screens/PROFISSIONAL2.png)

<br/><br/>

## Tecnologias

- Windows 8 64bits (utilizado no desenvolvimento da aplicação);
- IDE Eclipse;
- Sistema desenvolvido em JAVA com SWING;
- Banco de dados PostgreSQL;
- JRE 1.18.0;


## Testes

- A aplicação já foi instalada em computadores com Windows 7 de 32 e 64 bits;
- Já foi instalada em um computador Windows 10 de 64 bits;
- Já foi instalada em um computador LINUX;
