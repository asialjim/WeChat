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

package io.github.microapplet.wechat.official.remoting.customer.meta;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 发送客服输入状态
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0.0
 * @since 2024/2/22, &nbsp;&nbsp; <em>version:1.0.0</em>
 */
@Getter
@ToString
public class SendTypingReq implements Serializable {
    private static final long serialVersionUID = -2233411643779421672L;
    private final String touser;
    private final String command;

    public SendTypingReq(String touser, Command command) {
        this.touser = touser;
        this.command = command.name();
    }

    public enum Command {
        Typing, CancelTyping
    }
}