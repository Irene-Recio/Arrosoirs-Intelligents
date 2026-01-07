import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
//reçoit la temperature
//si temp>20 alors
//publier signal pour arrosoirs

    public class Declencheur {

    private static final String QUEUE_NAME = "temp";
    private static final String QUEUE_NAME2 = "arrosoirs";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(56720);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), "UTF-8");
            int temp = Integer.parseInt(message);

            System.out.println("Temperature: " + temp + " degrés");

            boolean arrosage;
            if(temp >=20){
               arrosage = true;
            }else{
               arrosage = false;
            }

            String activer = Boolean.toString(arrosage);

            channel.basicPublish("", QUEUE_NAME2, null, activer.getBytes());

            System.out.println("Arroser: " + activer);
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }
}



//Windows:
//java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Declencheur