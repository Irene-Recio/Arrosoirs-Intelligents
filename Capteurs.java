import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.Random;


public class Capteurs{

    private final static String QUEUE_NAME = "temp";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(56720);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Random random = new Random();

       try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
       ){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            while (true) {
                //chiffre random entre 0 et 40
                int temperature = random.nextInt(40);
                //int to string pour affichage
                String message = String.valueOf(temperature);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

                System.out.println("Temperature: " + message + " degrés");

                // Pause de 5 secondes
                Thread.sleep(5000);
            }
       } catch (Exception e) {
            System.out.println(e);
        }
    }

    
}
//Windows:
//java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Capteurs
