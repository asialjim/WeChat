/*
 * Copyright 2014-2024 <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.microapplet.wechat.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

/**
 * 微信应用配置信息，基于yaml/yml,properties
 *
 * @author Copyright © <a href="mailto:asialjim@hotmail.com">Asial Jim</a>   Co., LTD
 * @version 1.0
 * @since 2023/12/18, &nbsp;&nbsp; <em>version:1.0</em>,  &nbsp;&nbsp;  <em>java version:8</em>
 */
@Data
@Configuration
@ConfigurationProperties("wechat.platform")
public class WeChatApplicationConfigProperty {
    private List<WeChatApplicationProperty> applications;

    @Data
    public static class WeChatApplicationProperty {
        private String id;
        private String name;
        private String subjectId;
        private String appid;
        private String secret;
        private String token;
        private String aesKey;
        private String encType;
        private WeChatApplicationType appType;
        private String url;
        private String manager;
        private String description;

        public WeChatApplication weChatApplication(){
            WeChatApplication app = new WeChatApplication();
            app.setId(this.getId());
            app.setName(this.getName());
            app.setSubjectId(this.getSubjectId());
            app.setAppid(this.getAppid());
            app.setSecret(this.getSecret());
            app.setToken(this.getToken());
            app.setAesKey(this.getAesKey());
            app.setEncType(this.getEncType());
            String appTypeName = Optional.ofNullable(this.getAppType()).orElse(WeChatApplicationType.OFFICIAL).getName();
            app.setAppType(appTypeName);
            app.setUrl(this.getUrl());
            app.setManager(this.getManager());
            app.setDescription(this.getDescription());
            return app;
        }
    }
}