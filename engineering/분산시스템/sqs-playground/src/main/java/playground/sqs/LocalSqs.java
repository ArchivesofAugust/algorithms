package playground.sqs;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

final class LocalSqs {

    static final String QUEUE_NAME = "playground-queue";

    private LocalSqs() {
    }

    // 진짜 AWS SDK 클라이언트지만, endpointOverride로 실제 AWS 대신 로컬 LocalStack(4566)에 붙인다.
    // credentials는 LocalStack이 아무 값이나 받아주므로 더미 값. endpointOverride만 빼고
    // 진짜 자격증명을 넣으면 코드 변경 없이 실제 AWS SQS에 그대로 연결된다.
    static SqsClient client() {
        return SqsClient.builder()
                .endpointOverride(URI.create("http://localhost:4566"))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .build();
    }
}
