# Apache Camel <img src="https://static-00.iconduck.com/assets.00/apache-camel-icon-2048x2048-63ht0ese.png" width="40" height="40"/>

## O que é?
É um framework de integração open-source que facilita a implementação de rotas de integração em sistemas complexos. Ele oferece uma abstração simples para lidar com diferentes tipos de protocolos e tecnologias, permitindo a comunicação fluida entre sistemas heterogêneos. 

## Características Principais
- **Rotas de Integração**: Define rotas que determinam como as mensagens fluem entre diferentes sistemas.
- **Componentes Diversos**: Suporte para diversos protocolos, como HTTP, JMS, FTP e muitos outros.
- **DSL Intuitiva**: Uso do Domain-Specific Language (DSL) para definir rotas de forma simples e legível.
- **Padões de Integração Corporativa (EIP)**: Implementa uma vasta gama de padrões de integração corporativa.

## Composição do Apache Camel
O núcleo do Camel é sua **engine de roteamento**, que permite definir regras de rotas e processar mensagens de qualquer formato, sem precisar convertê-las. Isso garante integração com múltiplos sistemas sem se preocupar com o tipo de informação. O Camel abstrai os protocolos, suportando mais de 80 tipos, e permite a criação de plugins para outros protocolos.
Apesar de ser confundido com um **ESB (Enterprise Service Bus)**, o Camel não é um ESB nem um container, mas pode ser implementado em containers. 

## Como Funciona
  O Apache Camel opera com base na definição de rotas de integração. Estas rotas são compostas por produtores, consumidores e processadores de mensagens, que em conjunto permitem a troca de informações entre diferentes sistemas. 

## Vantagens: 
- **Flexibilidade**: Permite integrar uma ampla gama de sistemas com o mínimo de código.
- **Escalabilidade**: Adequado tanto para pequenas integrações como para grandes arquiteturas distribuídas.
- **Compatibilidade**: Suporte para padrões de integração corporativa (EIPs).

## Desvantagens:
- **Difícil de aprender**: Ele tem muitos recursos, o que pode ser complicado para quem está começando
- **Uso de recursos**: Pode consumir bastante memória e processamento em projetos maiores
- **Excessivo para projetos pequenos**: Às vezes, é complexo demais para integrações mais simples

## Uso
**1. Adicionar a dependência no pom.xml**: Inclua a dependência do Apache Camel no arquivo pom.xml do seu projeto.  
**2. Configuração básica de rotas**: Após a instalação, você pode começar a definir rotas de integração com a linguagem de rota do Camel.  
**3. Executar com um container (opcional)**: Camel pode ser configurado para rodar em containers como Tomcat ou empacotado em uma imagem Docker para maior portabilidade.  

## Explicação do código - Servidor
**1. Criação do CamelContext**: O CamelContext é o núcleo do Apache Camel. Ele gerencia as rotas e componentes. 

     CamelContext context = new DefaultCamelContext();
     
**2. Definição de uma rota**: A rota define que o servidor Jetty ouvirá solicitações HTTP em http://0.0.0.0:8080/send. Quando uma requisição é recebida, o corpo da mensagem é processado.

      context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("jetty:http://0.0.0.0:8080/send")
                
**3. Processamento da mensagem**: A mensagem recebida é capturada, impressa no console, e uma resposta cm o texto *"Mensagem Recebida"* é enviada de volta.

         .process(exchange -> {
                        String message = exchange.getIn().getBody(String.class);
                        System.out.println("Mensagem Recebida: " + message);
                        exchange.getOut().setBody("Mensagem Recebida: " + message);
                        
**4. Start e Stop**: O servidor Camel é iniciado e fica rodando por pelos milissegundos definido e depois para o contexto. 

        context.start();
        System.out.println("Servidor Camel foi iniciado");
        Thread.sleep(1000000000);
        context.stop();

## Teste de Falha
Erro ao tentar enviar mensagem com o Servidor parado

![Erro_container](images/erro_container.jpg)

Erro ao tentar adicionar um novo cliente quando já possui outro escutando a porta 

![Erro_porta](images/erro_portaEmUso.jpg)

## Teste de Carga

| Mensagens  | Tempo gasto      |
|------------|------------------|
| 1000       | 0.68 segundos    |
| 10.000     | 4.73 segundos    |
| 100.000    | 35 segundos      |
| 500.000    | 3 minutos        |
| 1.000.000  | 6 minutos        |

<p align="left">
  <img src="images/1000_mensagens.jpg" alt="Teste de Carga - 1000 mensagens" width="200"/>
  <img src="images/10000_mensagens.jpg" alt="Teste de Carga - 10.000 mensagens" width="200"/>
</p>
<p align="left">
  <img src="images/100000_mensagens.jpg" alt="Teste de Carga - 100.000 mensagens" width="200"/>
  <img src="images/500000_mensagens.jpg" alt="Teste de Carga - 500.000 mensagens" width="200"/>
  <img src="images/1000000_mensagens.jpg" alt="Teste de Carga - 1.000.000 mensagens" width="200"/>
</p>

<p align="left">
  <img src="images/tempo_testes.jpg" alt="Tempo Testes" width="500"/>
</p>

## 

Este trabalho foi feito por:

[Cintia Mariana](https://github.com/CintiaMariana). Confira o repositório completo no GitHub:

[![GitHub](https://img.shields.io/badge/GitHub-Repositório-informational?logo=github)](https://github.com/CintiaMariana?tab=repositories)

[Gabriel Pereira](https://github.com/Gabriel-6). Confira o repositório completo no GitHub:

[![GitHub](https://img.shields.io/badge/GitHub-Repositório-informational?logo=github)](https://github.com/Gabriel-6?tab=repositories)


## Referências  
[https://camel.apache.org/](https://camel.apache.org/)  
[https://www.devmedia.com.br/apache-camel-entenda-o-que-e/28747](https://www.devmedia.com.br/apache-camel-entenda-o-que-e/28747)



