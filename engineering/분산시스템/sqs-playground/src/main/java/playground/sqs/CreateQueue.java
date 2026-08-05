package playground.sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;

public class CreateQueue {

    public static void main(String[] args) {
        try (SqsClient sqs = LocalSqs.client()) {
            CreateQueueResponse response = sqs.createQueue(CreateQueueRequest.builder()
                    .queueName(LocalSqs.QUEUE_NAME)
                    .build());
            System.out.println("Queue created: " + response.queueUrl());
        }
    }
}
