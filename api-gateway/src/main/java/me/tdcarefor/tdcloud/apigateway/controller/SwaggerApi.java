package me.tdcarefor.tdcloud.apigateway.controller;

import feign.RequestLine;

/**
 * 类: SwaggerApi<br/>
 * 描述:  <br>
 * 作者: tzw <br>
 * 时间: 2016 16/10/10 上午11:50 <br>
 */

interface SwaggerApi {
    @RequestLine("GET /v2/api-docs")
    String getApiDocs();
}