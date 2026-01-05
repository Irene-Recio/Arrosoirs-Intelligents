import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.Random;


public class Capteurs{

    private final static String QUEUE_NAME = "capteurs";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Random random = new Random();

       try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
       ){

       
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            while (true) {
                //chiffre random entre 0 et 40
                int humidite = random.nextInt(40);
                //int to string pour affichage
                String message = String.valueOf(humidite);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

                System.out.println("Temperature: " + message);

                // Pause de 5 secondes
                Thread.sleep(5000);
            }
       } catch (Exception e) {
            System.out.println(e);
        }
    }

    
}
