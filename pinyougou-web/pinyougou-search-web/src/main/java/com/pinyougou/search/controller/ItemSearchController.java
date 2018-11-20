package com.pinyougou.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.service.ItemSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商品搜索控制器
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2018-11-12<p>
 */
@RestController
public class ItemSearchController {

    @Reference(timeout = 30000)
    private ItemSearchService itemSearchService;

    /** 商品搜索方法 */
    @PostMapping("/Search")
    public Map<String, Object> search(@RequestBody Map<String, Object> params){
        return itemSearchService.search(params);
    }
}
