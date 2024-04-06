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
package io.github.microapplet.wechat.remoting.meta;

import io.github.microapplet.wechat.remoting.context.BaseWeChatApiRes;
import lombok.*;


/**
 * WeChatAccessTokenRes *
 *
 * @author Copyright © <a href="mailto:asialjim@hotmail.com">Asial Jim</a>   Co., LTD
 * @since 2023/1/28, &nbsp;&nbsp; <em>version:</em>, &nbsp;&nbsp; <em>java version:</em>
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WeChatAccessTokenRes extends BaseWeChatApiRes {
    private static final long serialVersionUID = -8803169692199272138L;

    private String access_token;
    private Integer expires_in;

    public static AccessTokenBuilder accessTokenBuilder(){
        return new AccessTokenBuilder();
    }

    public static class AccessTokenBuilder {
        private String access_token;
        private Integer expires_in;
        private Integer errcode;
        private String errmsg;

        public WeChatAccessTokenRes build(){
            WeChatAccessTokenRes res = new WeChatAccessTokenRes();
            res.setAccess_token(access_token);
            res.setExpires_in(expires_in);
            res.setErrcode(errcode);
            res.setErrmsg(errmsg);
            return res;
        }

        public AccessTokenBuilder access_token(String access_token){
            this.access_token = access_token;
            return this;
        }

        public AccessTokenBuilder expires_in(Integer expires_in){
            this.expires_in = expires_in;
            return this;
        }

        public AccessTokenBuilder errcode(Integer errcode){
            this.errcode = errcode;
            return this;
        }

        public AccessTokenBuilder errmsg(String errmsg){
            this.errmsg = errmsg;
            return this;
        }
    }
}