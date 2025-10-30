/*
 * Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
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

package com.asialjim.microapplet.wechat.applet.analysis;

import com.asialjim.microapplet.remote.http.annotation.HttpMapping;
import com.asialjim.microapplet.remote.http.annotation.HttpMethod;
import com.asialjim.microapplet.remote.http.annotation.body.JsonBody;
import com.asialjim.microapplet.remote.net.annotation.Server;
import com.asialjim.microapplet.remote.net.response.JsonResult;
import com.asialjim.microapplet.wechat.constant.WeChatCons;
import com.asialjim.microapplet.wechat.remoting.context.BaseWeChatApiRes;
import com.asialjim.microapplet.wechat.remoting.context.WeChatAccessTokenParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 微信小程序数据分析相关服务API客户端
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/11/01, &nbsp;&nbsp; <em>version:1.0</em>
 */
@JsonResult
@Server(
        supplier = WeChatCons.Supplier.WECHAT,
        namespace = WeChatCons.Namespace.COMMON,
        schema = WeChatCons.Api.DEFAULT_SCHEMA,
        host = WeChatCons.Api.DEFAULT_HOST,
        port = WeChatCons.Api.DEFAULT_PORT
)
public interface WeChatAppletAnalysisRemoting {

    /**
     * 获取用户访问小程序周留存
     * <p>
     * 该接口用于获取用户访问小程序周留存
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/visit-retain/getWeeklyRetain.html">...</a>
     * <p>
     * 注意事项：请求必须按照自然周的方式输入，如：20170306(周一), 20170312(周日)
     *
     * @param weChatIndex 微信索引
     * @param req         获取周留存请求参数
     * @return {@link GetWeeklyRetainRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/datacube/getweanalysisappidweeklyretaininfo")
    GetWeeklyRetainRes getWeeklyRetain(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetWeeklyRetainReq req);

    /**
     * 获取用户访问小程序月留存
     * <p>
     * 该接口用于获取用户访问小程序月留存
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/visit-retain/getMonthlyRetain.html">...</a>
     * <p>
     * 注意事项：请求必须按照自然月的方式输入，如：20170201(月初), 20170228(月末)
     *
     * @param weChatIndex 微信索引
     * @param req         获取月留存请求参数
     * @return {@link GetMonthlyRetainRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/datacube/getweanalysisappidmonthlyretaininfo")
    GetMonthlyRetainRes getMonthlyRetain(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetMonthlyRetainReq req);

    /**
     * 获取用户访问小程序日留存
     * <p>
     * 该接口用于获取用户访问小程序日留存
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/visit-retain/getDailyRetain.html">...</a>
     * <p>
     * 注意事项：限定查询1天数据，允许设置的最大值为昨日
     *
     * @param weChatIndex 微信索引
     * @param req         获取日留存请求参数
     * @return {@link GetDailyRetainRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/datacube/getweanalysisappiddailyretaininfo")
    GetDailyRetainRes getDailyRetain(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetDailyRetainReq req);

    /**
     * 获取用户访问小程序周留存请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetWeeklyRetainReq implements Serializable {
        /**
         * 开始日期，为周一日期。格式为 yyyymmdd
         */
        private String begin_date;

        /**
         * 结束日期，为周日日期，限定查询一周数据。格式为 yyyymmdd
         */
        private String end_date;
    }

    /**
     * 获取用户访问小程序月留存请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetMonthlyRetainReq implements Serializable {
        /**
         * 开始日期，为自然月第一天。格式为 yyyymmdd
         */
        private String begin_date;

        /**
         * 结束日期，为自然月最后一天，限定查询一个月数据。格式为 yyyymmdd
         */
        private String end_date;
    }

    /**
     * 获取用户访问小程序日留存请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetDailyRetainReq implements Serializable {
        /**
         * 开始日期。格式为 yyyymmdd
         */
        private String begin_date;

        /**
         * 结束日期，限定查询1天数据，允许设置的最大值为昨日。格式为 yyyymmdd
         */
        private String end_date;
    }

    /**
     * 获取用户访问小程序周留存响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetWeeklyRetainRes extends BaseWeChatApiRes {
        /**
         * 时间，如："20170306-20170312"
         */
        private String ref_date;

        /**
         * 新增用户留存
         */
        private List<RetainData> visit_uv_new;

        /**
         * 活跃用户留存
         */
        private List<RetainData> visit_uv;

    }

    /**
     * 留存数据项
     */
    @Data
    class RetainData implements Serializable {
        /**
         * 标识，0开始，表示当周，1表示1周后。依此类推，取值分别是：0,1,2,3,4
         */
        private Integer key;

        /**
         * key对应日期的新增用户数/活跃用户数（key=0时）或留存用户数（k>0时）
         */
        private Integer value;
    }

    /**
     * 获取用户访问小程序月留存响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetMonthlyRetainRes extends BaseWeChatApiRes {
        /**
         * 时间，如："201702"
         */
        private String ref_date;

        /**
         * 新增用户留存
         */
        private List<MonthlyRetainData> visit_uv_new;

        /**
         * 活跃用户留存
         */
        private List<MonthlyRetainData> visit_uv;


    }

    /**
     * 月留存数据项
     */
    @Data
    class MonthlyRetainData implements Serializable {
        /**
         * 标识，0开始，表示当月，1表示1月后。key取值分别是：0,1
         */
        private Integer key;

        /**
         * key对应日期的新增用户数/活跃用户数（key=0时）或留存用户数（k>0时）
         */
        private Integer value;
    }

    /**
     * 获取用户访问小程序日留存响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetDailyRetainRes extends BaseWeChatApiRes {
        /**
         * 日期
         */
        private String ref_date;

        /**
         * 新增用户留存
         */
        private List<DailyRetainData> visit_uv_new;

        /**
         * 活跃用户留存
         */
        private List<DailyRetainData> visit_uv;


    }

    /**
     * 日留存数据项
     */
    @Data
    class DailyRetainData implements Serializable {
        /**
         * 标识，0开始，表示当天，1表示1天后。依此类推，key取值分别是：0,1,2,3,4,5,6,7,14,30
         */
        private Integer key;

        /**
         * key对应日期的新增用户数/活跃用户数（key=0时）或留存用户数（k>0时）
         */
        private Integer value;
    }
}