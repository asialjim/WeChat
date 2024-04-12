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

package io.github.microapplet.wechat.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应代码
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0.0
 * @since 2024 04 10, &nbsp;&nbsp; <em>version:1.0.0</em>
 */
public interface Code {
    String getCode();

    String getMsg();

    @Getter
    @AllArgsConstructor
    enum DEF implements Code {
        SUCCESS(WeChatResult.SUCCESS_CODE,WeChatResult.SUCCESS_MSG);
        private final String code;
        private final String msg;
    }

    default<T> WeChatResult<T> result(){
        return WeChatResult.codeOf(this);
    }

    default<T> WeChatResult<T> result(T data){
        return WeChatResult.<T>codeOf(this).setData(data);
    }
}