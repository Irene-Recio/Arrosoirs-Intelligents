import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Declencheur{
//reçoit la temperature
//si temp>20 alors
//publier signal pour arrosoirs

    private final static String QUEUE_NAME = "declencheur";
    
    public static void main(String[] argv) throws Exception {
      ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(56720);
        factory.setUsername("guest");
        factory.setPassword("guest");

       try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
       ){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            boolean activer = false;
            while (true) {
                String message = Boolean.toString(activer);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

                System.out.println("Activer: " + message);

                // Pause de 5 secondes
                Thread.sleep(5000);
            }
       } catch (Exception e) {
            System.out.println(e);
        }

  }
    
}

//Windows:
//java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Declencheur