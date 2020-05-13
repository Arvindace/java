package com.amazonaws.services.kinesis.samples.stocktrades.consumer;
//snippet-start:[kinesis.java2.getrecord.import]
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.DescribeStreamResponse;
import software.amazon.awssdk.services.kinesis.model.DescribeStreamRequest;
import software.amazon.awssdk.services.kinesis.model.Shard;
import software.amazon.awssdk.services.kinesis.model.GetShardIteratorRequest;
import software.amazon.awssdk.services.kinesis.model.GetShardIteratorResponse;
import software.amazon.awssdk.services.kinesis.model.Record;
import software.amazon.awssdk.services.kinesis.model.GetRecordsRequest;
import software.amazon.awssdk.services.kinesis.model.GetRecordsResponse;
import java.util.ArrayList;
import java.util.List;
//snippet-end:[kinesis.java2.getrecord.import]


/**
 * Demonstrates how to read data from a Kinesis data stream. Before running this Java code example, populate a data stream
 * by running the StockTradesWriter example. Then you can use that populated data stream for this example.
 */

public class myKinesisConsumer {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetRecords <streamName>\n\n" +
                "Where:\n" +
                "    streamName - The Kinesis data stream to read from (i.e., StockTradeStream)\n\n" +
                "Example:\n" +
                "    GetRecords streamName\n";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String streamName = args[0];

        Region region = Region.US_EAST_1;
        KinesisClient kinesisClient = KinesisClient.builder()
                .region(region)
                .build();

        getStockTrades(kinesisClient,streamName);
    }

    // snippet-start:[kinesis.java2.getrecord.main]
    public static void getStockTrades(KinesisClient kinesisClient, String streamName) {

        String shardIterator;
        String lastShardId = null;

        // Retrieve the shards from a data stream
        DescribeStreamRequest describeStreamRequest = DescribeStreamRequest.builder()
                .streamName(streamName)
                .build();
        List<Shard> shards = new ArrayList<>();

        DescribeStreamResponse streamRes;
        do {
            streamRes = kinesisClient.describeStream(describeStreamRequest);
            shards.addAll(streamRes.streamDescription().shards());
            if (shards.size() > 0) {
                lastShardId = shards.get(shards.size() - 1).shardId();
                System.out.println("Last shard id:"+lastShardId);
            }
        } while (streamRes.streamDescription().hasMoreShards());

        GetShardIteratorRequest itReq = GetShardIteratorRequest.builder()
                .streamName(streamName)

                //Option I
                .shardIteratorType("AT_SEQUENCE_NUMBER")
                .startingSequenceNumber("49606583972097278125362655939527192727920816066782035970")
                //Option II
                //.shardIteratorType("LATEST")
                //Option III
                //.shardIteratorType("TRIM_ORIGIN")
                .shardId(shards.get(0).shardId())
                .build();

        GetShardIteratorResponse shardIteratorResult = kinesisClient.getShardIterator(itReq);
        shardIterator = shardIteratorResult.shardIterator();

        System.out.println(shardIterator.length());

        // Continuously read data records from a shard
        List<Record> records;

        while(true) {

            // Create a GetRecordsRequest with the existing shardIterator,
            // and set maximum records to return to 1000
            GetRecordsRequest recordsRequest = GetRecordsRequest.builder()
                    .shardIterator(shardIterator)
                    .limit(25)
                    .build();


            GetRecordsResponse result = kinesisClient.getRecords(recordsRequest);
            System.out.println("rec size" + result.records().size());
            // Put result into a record list, result might be empty
            records = result.records();

            // Print records
            for (Record record : records) {
                SdkBytes byteBuffer = record.data();
                System.out.println(String.format("Seq No: %s - %s", record.sequenceNumber(),
                        new String(byteBuffer.asByteArray())));
            }
            shardIterator = result.nextShardIterator();
        }

    }
    // snippet-end:[kinesis.java2.getrecord.main]
}