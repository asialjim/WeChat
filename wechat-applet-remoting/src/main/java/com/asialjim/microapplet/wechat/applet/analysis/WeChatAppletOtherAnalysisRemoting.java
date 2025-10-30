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
 * 微信小程序其他数据分析相关服务API客户端
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/11/01, &nbsp;&nbsp; <em>version:1.0</em>
 */
@JsonResult
@Server(supplier = WeChatCons.Supplier.WECHAT, namespace = WeChatCons.Namespace.COMMON, schema = WeChatCons.Api.DEFAULT_SCHEMA, host = WeChatCons.Api.DEFAULT_HOST, port = WeChatCons.Api.DEFAULT_PORT)
public interface WeChatAppletOtherAnalysisRemoting {

    /**
     * 获取用户访问小程序数据概况
     * <p>
     * 该接口用于获取用户访问小程序数据概况
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/others/getDailySummary.html">...</a>
     * <p>
     * 注意事项：限定查询1天数据，允许设置的最大值为昨日
     *
     * @param weChatIndex 微信索引
     * @param req         获取日概况请求参数
     * @return {@link GetDailySummaryRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/datacube/getweanalysisappiddailysummarytrend")
    GetDailySummaryRes getDailySummary(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetDailySummaryReq req);

    /**
     * 获取访问页面数据
     * <p>
     * 该接口用于获取访问页面数据，目前只提供按 page_visit_pv 排序的 top200
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/others/getVisitPage.html">...</a>
     * <p>
     * 注意事项：限定查询1天数据，允许设置的最大值为昨日
     *
     * @param weChatIndex 微信索引
     * @param req         获取访问页面请求参数
     * @return {@link GetVisitPageRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/datacube/getweanalysisappidvisitpage")
    GetVisitPageRes getVisitPage(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetVisitPageReq req);

    /**
     * 获取小程序用户画像分布
     * <p>
     * 该接口用于获取小程序新增或活跃用户的画像分布数据
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/others/getUserPortrait.html">...</a>
     * <p>
     * 注意事项：时间范围支持昨天、最近7天、最近30天。开始日期与结束日期相差的天数限定为0/6/29，分别表示查询最近1/7/30天数据
     *
     * @param weChatIndex 微信索引
     * @param req         获取用户画像请求参数
     * @return {@link GetUserPortraitRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/datacube/getweanalysisappiduserportrait")
    GetUserPortraitRes getUserPortrait(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetUserPortraitReq req);

    /**
     * 获取小程序性能数据
     * <p>
     * 该接口用于获取小程序启动性能，运行性能等数据
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/others/getPerformanceData.html">...</a>
     * <p>
     * 注意事项：时间跨度不能超过30天
     *
     * @param weChatIndex 微信索引
     * @param req         获取性能数据请求参数
     * @return {@link GetPerformanceDataRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/wxa/business/getperformance")
    GetPerformanceDataRes getPerformanceData(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetPerformanceDataReq req);

    /**
     * 获取用户小程序访问分布数据
     * <p>
     * 该接口用于获取用户小程序访问分布数据
     * 官方文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/data-analysis/others/getVisitDistribution.html">...</a>
     * <p>
     * 注意事项：限定查询1天数据，允许设置的最大值为昨日
     *
     * @param weChatIndex 微信索引
     * @param req         获取访问分布请求参数
     * @return {@link GetVisitDistributionRes}
     * @since 2025/11/01
     */
    @HttpMapping(method = HttpMethod.POST, uri = "/datacube/getweanalysisappidvisitdistribution")
    GetVisitDistributionRes getVisitDistribution(@WeChatAccessTokenParam String weChatIndex, @JsonBody GetVisitDistributionReq req);

    /**
     * 获取用户访问小程序数据概况请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetDailySummaryReq implements Serializable {
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
     * 获取访问页面数据请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetVisitPageReq implements Serializable {
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
     * 获取小程序用户画像分布请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetUserPortraitReq implements Serializable {
        /**
         * 开始日期。格式为 yyyymmdd
         */
        private String begin_date;

        /**
         * 结束日期，开始日期与结束日期相差的天数限定为0/6/29，分别表示查询最近1/7/30天数据。格式为 yyyymmdd
         */
        private String end_date;
    }

    /**
     * 获取小程序性能数据请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetPerformanceDataReq implements Serializable {
        /**
         * 查询数据的类型
         * 10016：打开率
         * 10017：启动各阶段耗时
         * 10021：页面切换耗时
         * 10022：内存指标
         * 10023：内存异常
         */
        private Integer module;

        /**
         * 开始和结束日期的时间戳，时间跨度不能超过30天
         */
        private Time time;

        /**
         * 查询条件，比如机型，网络类型等等
         */
        private List<Param> params;

    }

    /**
     * 时间范围
     */
    @Data
    class Time {
        /**
         * 开始日期时间戳
         */
        private Long begin_timestamp;

        /**
         * 结束日期时间戳
         */
        private Long end_timestamp;
    }

    /**
     * 查询条件参数
     */
    @Data
    class Param {
        /**
         * 查询条件
         * networktype：网络类型
         * device_level：机型
         * device：平台
         */
        private String field;

        /**
         * 查询条件值
         * networktype：-1(全部), 3g, 4g, wifi
         * device_level：-1(全部), 1(高档机), 2(中档机), 3(低档机)
         * device：-1(全部), 1(iOS), 2(Android)
         */
        private String value;
    }

    /**
     * 获取用户小程序访问分布数据请求参数
     */
    @Data
    @Accessors(chain = true)
    class GetVisitDistributionReq implements Serializable {
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
     * 获取用户访问小程序数据概况响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetDailySummaryRes extends BaseWeChatApiRes {
        /**
         * 数据列表
         */
        private List<DailySummaryData> list;

    }

    /**
     * 日概况数据项
     */
    @Data
    class DailySummaryData implements Serializable {
        /**
         * 日期，格式为 yyyymmdd
         */
        private String ref_date;

        /**
         * 累计用户数
         */
        private Integer visit_total;

        /**
         * 转发次数
         */
        private Integer share_pv;

        /**
         * 转发人数
         */
        private Integer share_uv;
    }

    /**
     * 获取访问页面数据响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetVisitPageRes extends BaseWeChatApiRes {
        /**
         * 日期，格式为 yyyymmdd
         */
        private String ref_date;

        /**
         * 数据列表
         */
        private List<VisitPageData> list;

    }

    /**
     * 访问页面数据项
     */
    @Data
    class VisitPageData implements Serializable {
        /**
         * 页面路径
         */
        private String page_path;

        /**
         * 访问次数
         */
        private Integer page_visit_pv;

        /**
         * 访问人数
         */
        private Integer page_visit_uv;

        /**
         * 次均停留时长
         */
        private Integer page_staytime_pv;

        /**
         * 进入页次数
         */
        private Integer entrypage_pv;

        /**
         * 退出页次数
         */
        private Integer exitpage_pv;

        /**
         * 转发次数
         */
        private Integer page_share_pv;

        /**
         * 转发人数
         */
        private Integer page_share_uv;
    }

    /**
     * 获取小程序用户画像分布响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetUserPortraitRes extends BaseWeChatApiRes {
        /**
         * 时间范围，如："20170611-20170617"
         */
        private String ref_date;

        /**
         * 新用户画像
         */
        private Portrait visit_uv_new;

        /**
         * 活跃用户画像
         */
        private Portrait visit_uv;

    }

    /**
     * 用户画像数据
     */
    @Data
    class Portrait {
        /**
         * 省份分布
         */
        private List<PortraitData> province;

        /**
         * 城市分布
         */
        private List<PortraitData> city;

        /**
         * 性别分布
         */
        private List<PortraitData> genders;

        /**
         * 平台分布
         */
        private List<PortraitData> platforms;

        /**
         * 终端分布
         */
        private List<PortraitData> devices;

        /**
         * 年龄分布
         */
        private List<PortraitData> ages;
    }

    /**
     * 画像数据项
     */
    @Data
    class PortraitData implements Serializable {
        /**
         * 属性值id
         */
        private Integer id;

        /**
         * 属性值名称
         */
        private String name;

        /**
         * 该场景访问uv
         */
        private Integer value;
    }

    /**
     * 获取小程序性能数据响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetPerformanceDataRes extends BaseWeChatApiRes {
        /**
         * 返回的性能数据
         */
        private GetPerformanceData data;


    }

    /**
     * 数据对象
     */
    @Data
    class GetPerformanceData {
        /**
         * 返回的数据数组
         */
        private List<Table> tables;
    }

    /**
     * 数据表
     */
    @Data
    class Table {
        /**
         * 性能数据指标id
         */
        private String id;

        /**
         * 按时间排列的性能数据
         */
        private List<Line> lines;
    }

    /**
     * 数据行
     */
    @Data
    class Line {
        /**
         * 单天的性能数据
         */
        private List<Field> fields;
    }

    /**
     * 数据字段
     */
    @Data
    class Field {
        /**
         * 日期
         */
        private String refdate;

        /**
         * 性能数据值
         */
        private String value;

        /**
         * 性能数据指标中文名
         */
        private String zh;

        /**
         * 数组大小
         */
        private Integer count;
    }

    /**
     * 获取用户小程序访问分布数据响应结果
     */
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class GetVisitDistributionRes extends BaseWeChatApiRes {
        /**
         * 日期，格式为 yyyymmdd
         */
        private String ref_date;

        /**
         * 数据列表
         */
        private List<VisitDistributionData> list;

    }

    /**
     * 访问分布数据项
     */
    @Data
    class VisitDistributionData implements Serializable {
        /**
         * 分布类型
         * access_source_session_cnt：访问来源分布
         * access_staytime_info：访问时长分布
         * access_depth_info：访问深度的分布
         */
        private String index;

        /**
         * 分布数据列表
         */
        private List<DistributionItem> item_list;
    }

    /**
     * 分布数据项
     */
    @Data
    class DistributionItem implements Serializable {
        /**
         * 场景 id
         */
        private Integer key;

        /**
         * 该场景 id 访问 pv
         */
        private Integer value;
    }
}