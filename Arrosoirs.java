import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Arrosoirs{

    
  public static void main(String[] argv) throws Exception {

      //1- Recevoir temperature

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(56720);
        factory.setUsername("admin");
        factory.setPassword("admin123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
          DeliverCallback deliverCallback = (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), "UTF-8");            
          System.out.println(" [x] reçcoit: '" + message + "'");

          //2- Si booleen = true, alors...
        

        //3- Activer arrosage
        channel.basicPublish("", QUEUE_NAME, null, null);
        System.out.println("");

          
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

        

  }
    
}

//Windows:
//java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Arrosoirs