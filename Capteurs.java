import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Arrosoirs{

    
  public static void main(String[] argv) throws Exception {

    private final static String QUEUE_NAME = "temp";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            int temp = 2;
            channel.basicPublish("", QUEUE_NAME, null, temp.getBytes());
            System.out.println("Temperature: '" + temp + "'");
        }
    }

  }
    
}