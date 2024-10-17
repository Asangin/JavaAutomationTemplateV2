package com.skryl.logging;

import com.epam.reportportal.service.ReportPortal;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.Date;
import java.util.Objects;

public class ReportPortalNetworkTrafficFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        final Prettifier prettifier = new Prettifier();
        final String url = requestSpec.getURI();
        var reqMethod = requestSpec.getMethod();
        var reqHeader = requestSpec.getHeaders();
        var reqCookies = requestSpec.getCookies();

        var reqBuilder =  new StringBuilder()
                .append(reqMethod).append(": %s \n".formatted(url))
                .append("Headers: %s \n".formatted(reqHeader))
                .append("Cookies: %s \n".formatted(reqCookies));

        if (Objects.nonNull(requestSpec.getBody())) {
            var reqBody = requestSpec.getBody();
            reqBuilder.append("Body: %s".formatted(reqBody));
        }
        var reqLog = reqBuilder.toString();

        ReportPortal.emitLog(reqLog, "INFO", new Date());

        final Response response = filterContext.next(requestSpec, responseSpec);

        var resStatus = response.getStatusLine();
        var resStatusCode = response.getStatusCode();
        var resHeader = response.getHeaders();
        var resBody = response.getBody().asString();

        var resLog = new StringBuilder()
                .append("Status line: %s \n".formatted(resStatus))
                .append("Status code: %d \n".formatted(resStatusCode))
                .append("Headers: %s \n".formatted(resHeader))
                .append("Body: %s".formatted(resBody))
                .toString();
        ReportPortal.emitLog(resLog, "INFO", new Date());

        return response;
    }
}
