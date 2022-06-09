package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.web.bind.annotation.ResponseBody;
import com.promineotech.jeep.JeepSales;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
//@ResponseBody

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {JeepSales.class})

@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class FetchJeepTest{
  List<Jeep> buildExpected () {

    List <Jeep> a = new LinkedList <> ();
    a.add(Jeep.builder()
        .modelId(JeepModel.WRANGLER)
        .trimLevel("Sport")
        .numDoors(2)
        .wheelSize(17)
        .basePrice(new BigDecimal ("28475.00"))
        .build());
    
    a.add(Jeep.builder()
        .modelId(JeepModel.WRANGLER)
        .trimLevel("Sport")
        .numDoors(4)
        .wheelSize(17)
        .basePrice(new BigDecimal ("31975.00"))
        .build()); 
  Collections.sort(a);
    return a;}


  @Autowired
  @Getter
private TestRestTemplate restTemplate;
  
  @LocalServerPort
  private int serverPort;
@Test
   
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
 
  JeepModel model=JeepModel.WRANGLER;
 
  String trim = "Sport";
 
  String uri=String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);

  System.out.println(uri);
 
 ResponseEntity<List<Jeep>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Jeep>>() {});
  assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  
  
System.out.println(response.getBody());

    
    List <Jeep> expected = buildExpected();
     assertThat(response.getBody()).isEqualTo(expected);}


        
  
    
    
    
    
  }


