package com.example.demo.controller;

import com.example.demo.dto.SomeEntityDTO;
import com.example.demo.dto.SubEntityWithParentDataDTO;
import com.example.demo.model.SomeEntity;
import com.example.demo.service.EntityService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class Controller {


    private static final Logger LOG = LoggerFactory.getLogger("Controller");
    @Autowired
    private EntityService service;

    @GetMapping("/get/sample")
    public Response getResponse(@RequestParam Integer docId) throws InterruptedException {
        Response response = new Response();
        response.setData("DocId is " + docId);
        LOG.info("Data requested: " + response.getData());
        Thread.sleep(100);
        return response;
    }

    @GetMapping("/entities/{id}")
    public SomeEntityDTO getSomeEntity(@PathVariable Long id) {
        return service.getEntity(id);
    }

    @PostMapping("/entities")
    public void createSomeEntity(@RequestBody SomeEntityDTO dto) {
        service.saveEntity(dto);
    }


    @PostMapping("/entities/all")
    public void createSomeEntityInBatch(HttpServletRequest dto) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jfactory = new JsonFactory();
        jfactory.setCodec(mapper);
        JsonParser jParser = jfactory.createParser(dto.getInputStream());

        jParser.nextToken();
        while (jParser.nextToken() != JsonToken.END_ARRAY) {
            SomeEntityDTO someEntityDTO = jParser.readValueAs(new TypeReference<SomeEntityDTO>() {
            });
            service.saveEntity(someEntityDTO);
        }
    }

    @GetMapping("/subEntities")
    public List<SubEntityWithParentDataDTO> getSomeEntity(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return service.getPage(pageNum, pageSize);
    }
}
