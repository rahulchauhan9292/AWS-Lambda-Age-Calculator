package com.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.time.LocalDate;
import java.time.Period;

    public class AwsLambdaApp implements RequestHandler<AwsLambdaRequest, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(AwsLambdaRequest awsLambdaRequest, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try {
            int day = awsLambdaRequest.getDay();
            int month = awsLambdaRequest.getMonth();
            int year = awsLambdaRequest.getYear();
            LocalDate l = LocalDate.of(year, month, day);
            LocalDate now = LocalDate.now();
            Period diff = Period.between(l, now);
            String output = diff.getYears() + " years " + diff.getMonths() + " months " + diff.getDays() + " days";
            return response.withBody(output);
        } catch (Exception e) {
            return response.withBody("{}").withStatusCode(500);
        }
    }
}
