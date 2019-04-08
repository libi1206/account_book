package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.TestDto;
import com.libi.accountbook.service.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.libi.accountbook.web.constant.UrlConst.*;
/**
 * @author libi
 */
@RequestMapping(TEST_ROOT)
@RestController
public class TestController {
    @Reference
    private RecordService recordService;

    /**测试普通参数*/
    @RequestMapping
    public ResponseDto testApi(@RequestParam String param) {
        return new ResponseDto(0, "成功", new TestDto(param, System.currentTimeMillis()));
    }

    /**测试获取数组*/
    @RequestMapping("/list")
    public ResponseDto testList(@RequestParam List<String> param) {
        return new ResponseDto(0, "成功", param);
    }
}
