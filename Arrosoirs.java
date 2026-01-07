import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Arrosoirs{

    private final static String QUEUE_NAME = "arrosoirs";

    
  public static void main(String[] argv) throws Exception {

      //1- Recevoir temperature

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(56720);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
          DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");            
            System.out.println(" Activer: '" + message + "'");

            //2- Si arrosage = true, alors...
              boolean arrosage = Boolean.parseBoolean(message);

              if (arrosage) {                                       //3- Activer arrosage
                  System.out.println("Arrosoirs activés");
              } else {
                  System.out.println("Arrosoirs arrêtés");
              }
            
          };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

  }
    
}

//Windows:
//java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Arrosoirs