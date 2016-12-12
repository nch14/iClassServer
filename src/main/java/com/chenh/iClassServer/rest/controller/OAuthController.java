package com.chenh.iClassServer.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ChenhaoNee on 2016/11/28.
 */
@RestController
@RequestMapping(value = "/v1/users")     // 通过这里配置使下面的映射都在/users下
public class OAuthController {
}
