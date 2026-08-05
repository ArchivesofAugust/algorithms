package playground.sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;

public class ListQueues {

    public static void main(String[] args) {
        try (SqsClient sqs = LocalSqs.client()) {
            ListQueuesResponse response = sqs.listQueues();
            if (response.queueUrls().isEmpty()) {
                System.out.println("No queues found. (컨테이너를 내렸다 올렸다면 메모리가 초기화된 것)");
                return;
            }
            response.queueUrls().forEach(System.out::println);
        }
    }
}
