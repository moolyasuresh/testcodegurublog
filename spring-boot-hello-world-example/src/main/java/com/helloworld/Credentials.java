package com.helloworld;
import java.util.logging.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
public class Credentials {
    // private constructor to avoid instantiation
    private Credentials(){}
    private static Credentials Credentials = null;
    private static String ACCESS_KEY = "";
    private static String SECERET_KEY = "";
    static AWSCredentials aac;
    static AmazonDynamoDB ddb;
    public synchronized static Credentials getInstance() {
        if (Credentials == null) {
            Credentials = new Credentials();
            try {
                aac = new BasicAWSCredentials(ACCESS_KEY, SECERET_KEY);

                ddb= AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").withCredentials(new AWSStaticCredentialsProvider(aac) ).build();
            } catch (final Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Hiding the exception");
            }
            
        }
        return Credentials;
    }
}
