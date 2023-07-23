package com.cloud.controller;

import com.cloud.param.ESParam;
import com.cloud.service.ElasticSearchService;
import com.cloud.base.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;



@RestController
@RequestMapping("/es")
public class ElasticSearchController {
    @Autowired
    ElasticSearchService elasticSearchService;


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "insert a document")
    public RestResponse<?> insert(@RequestBody ESParam param) {
        return elasticSearchService.insertDocument(param.getIndex(), param.getId() ,param.getObj());
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "delete a document by name")
    public RestResponse<?> delete(@RequestBody ESParam param) {
        return elasticSearchService.deleteDocument(param.getIndex(), param.getId());
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "notice! this update is implemented by insert (will cover origin data)")
    public RestResponse<?> update(@RequestBody ESParam param) {
        return elasticSearchService.insertDocument(param.getIndex(),param.getId(), param.getObj());
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ApiOperation(value = "show es-head")
    public ModelAndView showES() {
        return new ModelAndView(new RedirectView("localhost:9100"));
    }

    @RequestMapping(value = "/synchronization", method = RequestMethod.GET)
    @ApiOperation(value = "synchronize data between Elastcsearch and MySQL")
    public RestResponse<?> synchronization() {
        return elasticSearchService.synchronization();
    }


}
