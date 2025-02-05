Um sistema web que conecta voluntários a famílias de pessoas com TEA para oferecer apoio em atividades como cuidado temporário, acompanhamento a consultas, e ajuda em eventos. Desenvolvido como trabalho final da disciplina de Linguagem de programação orientada a objetos.

Instruções de instalação do sistema

Antes de começar, é preciso ter os seguintes softwares instalados:
Java Development Kit (JDK)
Eclipse IDE
Apache Tomcat 
MySQL Server

Configuração do Banco de Dados

Execute no MySQL o script presente na pasta.

Importe o projeto para o Eclipse em File > Import

Mude as credenciais do banco de dados na classe Conexao localizada em ApoioTEA\src\main\java\dao\Conexao.java
Para indicar onde imagens de perfil devem ser salvas, mude o caminho que leva até à pasta webapp/imagens no seu computador na linha 28 do arquivo \src\main\java\controlador\PerfilControlador.java.

Configuração do servidor Tomcat

No Eclipse, vá para Window > Preferences > Server > Runtime Environments.
Clique em Add, selecione Apache Tomcat e clique em Next.
Navegue até a pasta onde você instalou o Tomcat e clique em Finish.

Adicionar o projeto ao Tomcat

Clique com o botão direito no projeto, vá para Run As > Run on Server.
Selecione o servidor Tomcat que você configurou e clique em Finish.
