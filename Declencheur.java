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
      //1- Recevoir temperature

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), "UTF-8");            
          System.out.println(" [x] reçcoit: '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

        //2- Si..., alors...

        int temp = Integer.parseInt(message);
        boolean activer = false;
        if(temp >= 20){
          activer = true
        }

        //3- Publier signal
        


  }
    
}

