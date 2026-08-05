package playground.sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.time.Instant;

public class Producer {

    public static void main(String[] args) {
        try (SqsClient sqs = LocalSqs.client()) {
            String queueUrl = sqs.getQueueUrl(GetQueueUrlRequest.builder()
                    .queueName(LocalSqs.QUEUE_NAME)
                    .build()).queueUrl();

            String body = "hello sqs @ " + Instant.now();

            SendMessageResponse response = sqs.sendMessage(SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(body)
                    .build());

            System.out.println("Sent messageId=" + response.messageId() + " body=" + body);
        }
    }
}
