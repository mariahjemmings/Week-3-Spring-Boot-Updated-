package com.promineotech.jeep.controller;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import java.util.List;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j


public class DefaultJeepSalesController implements JeepSalesController {
@Autowired  
private JeepSalesService jeepSalesService;



  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.debug("model = {}, trim = {}", model, trim);    // TODO Auto-generated method stub
    return jeepSalesService.fetchJeeps(model, trim);
  }

}
