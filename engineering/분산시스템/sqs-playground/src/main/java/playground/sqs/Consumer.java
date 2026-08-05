package playground.sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

/**
 * 실행: mvn exec:java -Dexec.mainClass=playground.sqs.Consumer -Dexec.args="--no-delete"
 * --no-delete를 주면 DeleteMessage를 호출하지 않아 visibility timeout 이후 메시지가
 * 다시 보이는(재전달되는) 것을 관찰할 수 있다.
 */
public class Consumer {

    public static void main(String[] args) {
        boolean skipDelete = args.length > 0 && args[0].equals("--no-delete");

        try (SqsClient sqs = LocalSqs.client()) {
            String queueUrl = sqs.getQueueUrl(GetQueueUrlRequest.builder()
                    .queueName(LocalSqs.QUEUE_NAME)
                    .build()).queueUrl();

            System.out.println("Polling... (long polling, waitTimeSeconds=10)");

            List<Message> messages = sqs.receiveMessage(ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .maxNumberOfMessages(5)
                    .waitTimeSeconds(10)
                    .visibilityTimeout(30)
                    .build()).messages();

            if (messages.isEmpty()) {
                System.out.println("No messages received.");
                return;
            }

            for (Message message : messages) {
                System.out.println("Received: " + message.body()
                        + " (receiptHandle=" + message.receiptHandle().substring(0, 12) + "...)");

                if (skipDelete) {
                    System.out.println("  --no-delete flag set: skipping DeleteMessage. "
                            + "Message will reappear after visibility timeout.");
                    continue;
                }

                sqs.deleteMessage(DeleteMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .receiptHandle(message.receiptHandle())
                        .build());
                System.out.println("  Deleted.");
            }
        }
    }
}
