package idv.sheng.java_grpc_v2.infra.grpc.client;

import idv.sheng.java_grpc_v2.GreetingGrpc;
import idv.sheng.java_grpc_v2.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GreetingGrpcClient {
    private static final int WAITING_TIME = 5;

    @Getter
    private final GreetingServiceGrpc.GreetingServiceStub stub;
    private final ManagedChannel channel;

    public GreetingGrpcClient(GrpcClientType clientType) {
        channel = ManagedChannelBuilder
                .forAddress(clientType.getHost(), clientType.getPort())
                .usePlaintext()
                .build();
        stub = GreetingServiceGrpc.newStub(channel);
    }

    public GreetingGrpc.Resp runStub(GreetingGrpc.Req  req) throws InterruptedException, TimeoutException {
        if (channel == null || channel.isShutdown())
            throw new RuntimeException("channel is shutdown");
        if (stub == null)
            throw new RuntimeException("stub is null");


        try {
            return asyncRun(req).get(WAITING_TIME, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            throw new TimeoutException("Request timed out.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            shutdown();
        }
    }

    private CompletableFuture<GreetingGrpc.Resp> asyncRun(GreetingGrpc.Req req) {
        CompletableFuture<GreetingGrpc.Resp> future = new CompletableFuture<>();

        stub.greet(req, new StreamObserver<GreetingGrpc.Resp>() {
            @Override
            public void onNext(GreetingGrpc.Resp resp) {
                future.complete(resp);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }

            @Override
            public void onCompleted() {
                // Complete normally if response was already handled in onNext
                if (!future.isDone()) {
                    future.complete(null);
                }
            }
        });

        return future;
    }

    private void shutdown() {
        channel.shutdown(); //Stop accepting new calls but continues processing ongoing ones until completion
        try {
            if (!channel.awaitTermination(WAITING_TIME, TimeUnit.SECONDS))
                channel.shutdownNow(); //Force the channel to terminate immediately.
        } catch (InterruptedException e) {
            channel.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
