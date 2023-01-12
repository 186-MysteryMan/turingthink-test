package com.turingthink.rabbit.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @version v0.0.0
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2020-03-07 0007 下午 10:46
 * @description:
 */
@Slf4j
@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "基础返回类", description = "基础返回类")
public class R<T> implements Serializable {
    /**
     * 未知错误
     */
    public static final Integer UNKNOW_ERROR = -1;

    /**
     * *****************200系列***************
     * 成功
     */
    public static final Integer SUCCESS = 200;

    /**
     * *****************400系列***************
     * 请求方法错误
     */
    public static final Integer REQUEST_METHOD_ERROR = 405;
    /**
     * 需要登录，有可能登出或者账户在其他地方登录
     */
    public static final Integer LOGOUT = 403;
    /**
     * 没有认证
     */
    public static final Integer UNAUTHENTICATED = 456;
    /**
     * 没有权限
     */
    public static final Integer UNAUTHORIZED = 457;
    /**
     * 认证失败
     */
    public static final Integer AUTHENTICATION_ERROR = 458;

    /**
     * *****************500系列***************
     * 应用ID错误，多租户中的租户id错误
     */
    public static final Integer APP_ID_ERROR = 567;
    /**
     * 缺少应用ID
     */
    public static final Integer APP_ID_MISSING = 568;

    /**
     * *****************600系列Controller层问题***************
     * 非法参数：可能是格式不对，可能是类型不对，具体看message提示
     */
    public static final Integer PARAMS_ERROR = 600;
    /**
     * 重复请求，如：验证码[10]分钟内有效
     */
    public static final Integer REPEAT_ERROR = 601;

    /**
     * *****************700系列Service业务层问题***************
     * 验证码错误
     */
    public static final Integer VERIFICATION_ERROR = 710;
    /**
     * 验证码失效
     */
    public static final Integer VERIFICATION_INVALID = 711;
    /**
     * 账号无效或者账号被冻结锁定
     */
    public static final Integer ACCOUNT_UNAVAILABLE = 720;
    /**
     * 该账号已注册
     */
    public static final Integer ACCOUNT_REGISTERED = 721;
    /**
     * 该账号不存在
     */
    public static final Integer ACCOUNT_ERROR = 722;
    /**
     * 密码错误
     */
    public static final Integer PASSWORD_ERROR = 723;
    /**
     * token错误
     */
    public static final Integer TOKEN_ERROR = 724;
    /**
     * 无效登录，比如token在其他地方登录后该token失效
     */
    public static final Integer TOKEN_INVALID = 725;
    /**
     * token过期
     */
    public static final Integer TOKEN_EXPIRED = 726;
    /**
     * 笼统的操作异常，操作失败等情况
     */
    public static final Integer OPERATION_EXCEPTION = 727;
    /**
     * 操作错误，比如有前置操作
     */
    public static final Integer OPERATION_ERROR = 728;
    /**
     * 操作错误，请先绑定手机
     */
    public static final Integer OPERATION_ERROR_NO_PHONE = 729;
    /**
     * 账号或密码错误
     */
    public static final Integer ACCOUNT_CREDENTIALS_ERROR = 789;

    /**
     * *****************800系列DAO数据层问题***************
     * 数据库异常
     */
    public static final Integer DATABASE_ERROR = 800;
    /**
     * 数据不存在
     */
    public static final Integer DATA_INEXISTENT = 801;
    /**
     * 数据已存在
     */
    public static final Integer DATA_EXISTED = 802;


    /**
     * ******************900系列业务问题***************
     * 数据转换异常
     */
    public static final Integer CONVERT_ERROR = 900;
    /**
     * 服务权限异常
     */
    public static final Integer NOT_AUTH_THIS_SERVICE = 901;
    /**
     * 微信授权异常
     */
    public static final Integer WEIXIN_AUTH_ERROR = 902;
    /**
     * 绑定手机失败，该用户已存在手机号码，请先解绑。
     */
    public static final Integer BIND_PHONE_FAIL = 903;
    /**
     * 验证码发送失败
     */
    public static final Integer SEND_PHONE_FAIL = 904;
    /**
     * 验证码校验失败
     */
    public static final Integer CHECK_PHONE_FAIL = 905;
    /**
     * 创建订单失败
     */
    public static final Integer CREATE_ORDER_FAIL = 906;
    /**
     * 微信支付失败
     */
    public static final Integer WEIXIN_PAY_FAIL = 907;
    /**
     * 取消订单失败
     */
    public static final Integer CANCEL_ORDER_FAIL = 908;
    /**
     * 找不到该订单
     */
    public static final Integer NOT_FOUND_ORDER = 909;
    /**
     * 找不到该服务
     */
    public static final Integer NOT_FOUND_SERVICE = 910;
    /**
     * 找不到该服务对象
     */
    public static final Integer NOT_FOUND_FAMILY = 911;
    /**
     * 找不到该地址
     */
    public static final Integer NOT_FOUND_ADDRESS = 912;
    /**
     * 找不到该用户
     */
    public static final Integer NOT_FOUND_USER = 913;
    /**
     * 微信支付申请退款失败
     */
    public static final Integer WEIXIN_REFUND_FAIL = 914;
    /**
     * 结算订单失败
     */
    public static final Integer SETTLEMENT_ORDER_FAIL = 915;
    /**
     * 用户评论失败
     */
    public static final Integer USER_COMMENT_FAIL = 916;
    /**
     * 用户对地址操作失败
     */
    public static final Integer USER_ADDRESS_FAIL = 917;
    /**
     * 用户对服务对象操作失败
     */
    public static final Integer USER_FAMILY_FAIL = 918;
    /**
     * 用户实名认证失败
     */
    public static final Integer USER_IDENTITY_FAIL = 919;
    /**
     * 获取百度access_token失败
     */
    public static final Integer GET_ACCESS_TOKEN_FAIL = 920;
    /**
     * 身份证姓名校验失败
     */
    public static final Integer NAME_ID_CARD_IDENTITY_FAIL = 921;
    /**
     * 人脸实名认证失败
     */
    public static final Integer FACE_IDENTITY_FAIL = 922;
    /**
     * 该护士手机号码未录入到系统
     */
    public static final Integer NOT_FOUND_NURSE_BY_PHONE = 923;
    /**
     * 创建p12文件失败
     */
    public static final Integer CREATE_P12_FILE_FAIL = 924;
    /**
     * 上传p12文件失败
     */
    public static final Integer UPLOAD_P12_FILE_FAIL = 925;
    /**
     * 微信授权异常自定义
     */
    public static final Integer WEIXIN_LOGIN_ERROR = 926;
    /**
     * 该护士无法查看对应订单列表类型
     */
    public static final Integer ORDER_LIST_TYPE_ERROR = 927;
    /**
     * 无法操作非本人权限内的订单
     */
    public static final Integer ORDER_AUTH_ERROR = 928;
    /**
     * 订单不在完成状态
     */
    public static final Integer ORDER_NOT_COMPLETE = 929;
    /**
     * 找不到该订单评价
     */
    public static final Integer NOT_FOUND_ORDER_COMMENT = 930;
    /**
     * 找不到该护士
     */
    public static final Integer NOT_FOUND_NURSE = 931;
    /**
     * 找不到该管理员
     */
    public static final Integer NOT_FOUND_ADMIN = 932;
    /**
     * 没有该护士的操作权限
     */
    public static final Integer NOT_HAVE_NURSE_AUTH = 933;
    /**
     * 订单不在待指派或已拒绝状态
     */
    public static final Integer NOT_IN_APPOINT_REFUSE = 934;
    /**
     * 订单不在待处理状态
     */
    public static final Integer NOT_IN_HANDLE = 935;
    /**
     * 订单不在待评估状态
     */
    public static final Integer NOT_IN_ASSESS = 936;
    /**
     * 订单不在已支付状态
     */
    public static final Integer NOT_IN_PAID = 937;
    /**
     * 微信退款自定义异常
     */
    public static final Integer WEIXIN_REFUND_ERROR_CUSTOM = 938;
    /**
     * 订单支付金额和微信支付金额不相同
     */
    public static final Integer PAY_FEE_ERROR = 939;
    /**
     * 订单退款金额和微信退款金额不相同
     */
    public static final Integer REFUND_FEE_ERROR = 940;
    /**
     * 订单不在服务中状态
     */
    public static final Integer NOT_IN_SERVING = 941;
    /**
     * 未找到该订单在服务中对应的页面
     */
    public static final Integer NOT_FOUND_SERVING_PAGE = 942;
    /**
     * 未找到护士长
     */
    public static final Integer NOT_FOUND_HEAD_NURSE = 943;
    /**
     * 归还数量大于申领数量
     */
    public static final Integer RETURN_QUANTITY_ERROR = 944;
    /**
     * 未在该订单中找到该耗材，耗材id:{0}
     */
    public static final Integer NOT_FOUND_RETURN_CONSUMABLES = 945;
    /**
     * 耗材归还种类和订单申领耗材种类数量不对
     */
    public static final Integer LACK_RETURN_CONSUMABLES = 946;
    /**
     * 订单不在开始服务后的服务中页面
     */
    public static final Integer NOT_IN_SERVING_PAGE = 947;
    /**
     * 当前登录人员不是护士
     */
    public static final Integer NOT_IS_NURSE = 948;
    /**
     * 当前登录人员不是护士长
     */
    public static final Integer NOT_IS_HEAD_NURSE = 949;
    /**
     * 该标签不属于护士所在医院
     */
    public static final Integer NURSE_COMMENT_TAG_AUTH_ERROR = 950;
    /**
     * 该订单已经评价过
     */
    public static final Integer ORDER_COMMENT_CLOSE = 951;
    /**
     * 无法操作非本人评论
     */
    public static final Integer ORDER_COMMENT_AUTH = 952;
    /**
     * 上传时间格式不正确
     */
    public static final Integer DATE_FORMAT_ERROR = 953;
    /**
     * 存在正在进行的订单，不能开始新的服务
     */
    public static final Integer HAVE_ORDER_IN_SERVING = 954;
    /**
     * 没有提交完结束订单所需要的材料
     */
    public static final Integer MESSING_MATERIAL = 955;
    /**
     * 订单已结算，订单ID:{0}
     */
    public static final Integer ORDER_SETTLED = 956;
    /**
     * 订单处于退款中，无法再次退款
     */
    public static final Integer IN_THE_REFUNDING = 957;
    /**
     * 订单未处于取消状态
     */
    public static final Integer NOT_IN_CANCELED = 958;
    /**
     * 订单已退款，无法再次退款
     */
    public static final Integer INT_THE_REFUNDED = 959;
    /**
     * 事件对象丢失
     */
    public static final Integer EVENT_OBJECT_MESSING = 960;
    /**
     * 事件对象缺少事件枚举
     */
    public static final Integer EVENT_OBJECT_ENUM_MESSING = 961;
    /**
     * 微信提现失败：{0}
     */
    public static final Integer WEIXIN_WITHDRAWAL_FAIL = 962;
    /**
     * 提现金额超过剩余金额
     */
    public static final Integer WITHDRAWAL_AMOUNT_ERROR = 963;
    /**
     * 护士已绑定微信提现渠道
     */
    public static final Integer NURSE_WEIXIN_BIND = 964;
    /**
     * 护士未绑定微信提现渠道
     */
    public static final Integer NURSE_WEIXIN_NOT_BIND = 965;
    /**
     * 没有此用户操作权限
     */
    public static final Integer NOT_AUTH_FOR_THIS_USER = 966;
    /**
     * 服务模板不存在
     */
    public static final Integer NOT_FOUND_SERVICE_MODEL = 967;
    /**
     * 没有权限
     */
    public static final Integer NOT_HAVE_AUTH = 968;
    /**
     * 没有这个科室的操作权限
     */
    public static final Integer NOT_AUTH_THIS_DEPARTMENT = 969;
    /**
     * 未找到该科室
     */
    public static final Integer NOT_FOUND_DEPARTMENT = 970;
    /**
     * 未找到该服务类型
     */
    public static final Integer NOT_FOUND_SERVICE_TYPE = 971;
    /**
     * 没有这个服务类型的操作权限
     */
    public static final Integer NOT_AUTH_THIS_SERVICE_TYPE = 972;
    /**
     * 护士所得金额超过所有金额
     */
    public static final Integer NURSE_FEE_TO_MUCH = 973;
    /**
     * 护士长所得金额比例超过100%
     */
    public static final Integer HEAD_NURSE_FEE_PERCENT_TO_MUCH = 974;
    /**
     * 上传的耗材ID中有异常ID
     */
    public static final Integer NOT_FOUND_CONSUMABLES = 975;
    /**
     * 预约服务处于不可用状态
     */
    public static final Integer SERVICE_DISABLE = 976;
    /**
     * 服务标签未找到
     */
    public static final Integer NOT_FOUND_SERVICE_TAG = 977;
    /**
     * 没有这个服务标签的操作权限
     */
    public static final Integer NOT_AUTH_THIS_SERVICE_TAG = 978;
    /**
     * 未达到用户预约服务时间
     */
    public static final Integer NOT_ARRIVED_SERVICE_TIME = 979;
    /**
     * 没有这个订单的操作权限
     */
    public static final Integer NOT_AUTH_THIS_ORDER = 980;
    /**
     * 科室节点路径异常
     */
    public static final Integer DEPARTMENT_PATH_ERROR = 981;
    /**
     * 该科室不可用
     */
    public static final Integer DEPARTMENT_DISABLE = 982;
    /**
     * 无法关闭此科室，该科室或子科室存在可用的服务项目正在绑定中，绑定中的科室为：{0}
     */
    public static final Integer DEPARTMENT_BINDING_SERVICE = 983;
    /**
     * 该科室存在子科室，无法与其绑定
     */
    public static final Integer CHILD_DEPARTMENT_EXIST = 984;
    /**
     * 无法为此科室添加子科室，该科室存在可用的服务项目正在绑定中
     */
    public static final Integer DEPARTMENT_BINDING_SERVICE_NOT_CREATE = 985;
    /**
     * 无法为此科室添加子科室，该科室存在可用的护士正在绑定中
     */
    public static final Integer DEPARTMENT_BINDING_NURSE_NOT_CREATE = 986;
    /**
     * 该科室已存在一名护士长，操作失败
     */
    public static final Integer HEAD_NURSE_EXIST_IN_DEPARTMENT = 987;
    /**
     * 该手机号码已经之前已经录入到过系统
     */
    public static final Integer NURSE_PHONE_EXIST_IN_DATABASE = 988;
    /**
     * 科室需要一名护士长才可以开启
     */
    public static final Integer HEAD_NURSE_NOT_EXIST_IN_DEPARTMENT = 989;
    /**
     * 科室需要一名护士才可以开启
     */
    public static final Integer NURSE_NOT_EXIST_IN_DEPARTMENT = 990;
    /**
     * 科室需要所有父级处于开启状态才可以开启
     */
    public static final Integer PARENT_DEPARTMENT_DISABLE = 991;
    /**
     * 没有这个护士的操作权限
     */
    public static final Integer NOT_AUTH_THIS_NURSE = 992;
    /**
     * 科室在可用状态，无法变更护士长职位
     */
    public static final Integer CAN_NOT_CHANGE_HEAD_NURSE_POSITION = 993;
    /**
     * 护士存在有效中的订单，无法更改科室或职位
     */
    public static final Integer NURSE_HAVE_ORDER_IN_VALID = 994;
    /**
     * 无法移除最后一个护士，在科室开启状态
     */
    public static final Integer CAN_NOT_REMOVE_LAST_NURSE = 995;
    /**
     * 护士余额还剩{0}元，请全部提现后再试
     */
    public static final Integer BALANCE_NOT_ZERO = 996;
    /**
     * 预约时间段人数已满，请换个时间
     */
    public static final Integer SERVICE_TIME_LIMIT = 997;
    /**
     * 必须要在可预约的日期
     */
    public static final Integer VALID_SERVICE_TIME_DATE = 998;
    /**
     * 必须要在可预约的时间段
     */
    public static final Integer VALID_SERVICE_TIME = 999;
    /**
     * 耗材编号已经录入过系统
     */
    public static final Integer CONSUMABLES_CODE_DUPLICATE = 1000;
    /**
     * 没有该耗材的操作权限
     */
    public static final Integer NOT_AUTH_THIS_CONSUMABLES = 1001;
    /**
     * 耗材数量不足
     */
    public static final Integer CONSUMABLES_NOT_ENOUGH = 1002;
    /**
     * 没有找到该评论标签
     */
    public static final Integer NOT_FOUND_COMMENT_TAG = 1003;
    /**
     * 没有这个评论标签的操作权限
     */
    public static final Integer NOT_AUTH_THIS_COMMENT_TAG = 1004;
    /**
     * 发送微信模板消息的消息体为空
     */
    public static final Integer SEND_WEIXIN_MESSAGE_BODY_EMPTY = 1005;
    /**
     * 发送消息通知消息体为空
     */
    public static final Integer SEND_MESSAGE_BODY_EMPTY = 1006;
    /**
     * 线程池执行任务异常
     */
    public static final Integer THREAD_POOL_EXECUTE_ERROR = 1007;
    /**
     * 线程等待计时器解除失败
     */
    public static final Integer COUNT_DOWN_LATCH_REMOVE_ERROR = 1008;
    /**
     * 存在正在进行的订单，不能开始新的服务
     */
    public static final Integer SERVING_ORDER_EXIST = 1009;
    /**
     * 创建阿里云隐私号码client失败
     */
    public static final Integer CREATE_PLS_CLIENT_FAIL = 1010;
    /**
     * 暂无可用隐私号码用来拨打，请再次重试获取。
     */
    public static final Integer NO_AVAILABLE_NUMBER = 1011;
    /**
     * 获取隐私号码失败，订单不在可以获取号码的状态
     */
    public static final Integer ORDER_CAN_NOT_GET_NUMBER = 1012;
    /**
     * 护士开始服务后不可以取消订单
     */
    public static final Integer CAN_NOT_CANCEL_ORDER_IN_SERVING = 1013;
    /**
     * 只能新增不超过20个家庭成员
     */
    public static final Integer MAX_FAMILY_COUNT = 1014;
    /**
     * 只能新增不超过20个服务地址
     */
    public static final Integer MAX_ADDRESS_COUNT = 1015;
    /**
     * 提现金额必须超过0
     */
    public static final Integer WITHDRAWAL_AMOUNT_LESS_THAN_ZERO = 1016;
    /**
     * 创建apiclient_key文件失败
     */
    public static final Integer CREATE_PRIVATE_KEY_FILE_FAIL = 1017;
    /**
     * 创建apiclient_cert文件失败
     */
    public static final Integer CREATE_PRIVATE_CERT_FILE_FAIL = 1018;
    /**
     * 提现微信支付加密姓名失败
     */
    public static final Integer CRYPTO_USER_NAME_FAIL = 1019;
    /**
     * 不在服务范围
     */
    public static final Integer NOT_IN_THE_SCOPE_OF_SERVICE = 1020;
    /**
     * 无法对服务中的订单发起退款
     */
    public static final Integer CAN_NOT_CANCEL_SERVING_ORDER = 1021;
    /**
     * 单选模式只能有一条数据
     */
    public static final Integer SINGLE_MODEL_SIZE_ERROR = 1022;
    /**
     * 费用索引越界，未在该服务中找到所选费用项
     */
    public static final Integer INDEX_OUT_OF_BOUNDS = 1023;
    /**
     * 退款耗材不能超过该订单归还的耗材
     */
    public static final Integer REFUND_CONSUMABLES_EXCEED = 1024;
    /**
     * 订单未完成，无法对未使用的耗材退款
     */
    public static final Integer ORDER_IS_NOT_COMPLETE = 1025;
    /**
     * 未在该订单申领的耗材找到该耗材：{0}
     */
    public static final Integer ORDER_NOT_CONTAIN_CONSUMABLES = 1026;

    @ApiModelProperty(required = true, value = "状态码，200表示成功，非200表示其他意义", example = "200")
    public Integer code;
    @ApiModelProperty(required = true, value = "业务提示语", example = "OK")
    public String message;
    @ApiModelProperty(required = true, value = "当前页码", example = "1")
    public Long page;
    @ApiModelProperty(required = true, value = "每一页的数目", example = "20")
    public Long pageSize;
    @ApiModelProperty(value = "第一页url")
    public String first;
    @ApiModelProperty(value = "下一页url")
    public String next;
    @ApiModelProperty(value = "上一页url")
    public String previous;
    @ApiModelProperty(value = "最后一页url")
    public String last;
    @ApiModelProperty(value = "总记录数", example = "200")
    public Long total;
    @ApiModelProperty(value = "总页数", example = "10")
    public Long pageCount;
    @ApiModelProperty(value = "具体业务的数据对象")
    public T data;

    public R() {
    }

    public R(Integer code) {
        this.code = code;
        this.message = getMessage(code + "", null);
    }

    public R(Integer code, String message) {
        this(code);
        this.message = message;
    }

    public R(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public R(Integer code, T data) {
        this(code);
        this.data = data;
    }

    public R(R<T> response) {
        this.code = response.code;
        this.message = response.message;
        this.data = response.data;
        this.page = response.page;
        this.pageSize = response.pageSize;
        this.first = response.first;
        this.next = response.next;
        this.previous = response.previous;
        this.last = response.last;
    }

    @JsonIgnore
    public Boolean isSuccessful() {
        return Objects.equals(code, SUCCESS);
    }

    @JsonIgnore
    public Boolean isLogout() {
        return Objects.equals(code, LOGOUT);
    }

    @Override
    public String toString() {
        String json = JsonHelper.object2Json(this);
        log.debug("json-->" + json);
        return json;
    }

    public R<T> newBuilder() {
        return new R<T>(this);
    }

    public static R<?> success() {
        return success(null);
    }

    public static <T> R<T> pagination(Long page, Long pageSize, Long total, T data) {
        R<T> response = success(data);
        response.setTotal(total);
        response.setPage(page);
        response.setPageSize(pageSize);
        response.setPageCount((long) Math.ceil((double) total / (double) pageSize));
        return response;
    }

    public static <T> R<T> success(String message) {
        return new R<>(SUCCESS, message);
    }

    public static <T> R<T> success(T data) {
        R<T> response = new R<>(SUCCESS, "success");
        response.setData(data);
        return response;
    }

    public static <T> R<T> success(String message, T data) {
        R<T> response = new R<>(SUCCESS, message);
        response.setData(data);
        return response;
    }

    public static <T> R<T> error(Integer code, String message) {
        return new R<T>(code, message);
    }

    public static <T> R<T> error(Integer code, String message, T data) {
        return new R<T>(code, message, data);
    }

    public static <T> R<T> error(Integer code, T data) {
        return new R<T>(code, data);
    }

    public static <T> R<T> error(Integer code) {
        return new R<T>(code);
    }

    public static <T> R<T> errorParams(T data) {
        return error(R.PARAMS_ERROR, data);
    }

    public static <T> R<T> errorLogout() {
        return error(R.LOGOUT);
    }

    public static <T> R<T> errorAuthorize() {
        return error(R.LOGOUT);
    }

    public static <T> R<T> errorUnauthorized() {
        return error(R.UNAUTHORIZED);
    }

    public static <T> R<T> operationException() {
        return error(R.OPERATION_EXCEPTION);
    }

    public static <T> R<T> errorRequestMethod(T data) {
        return error(R.REQUEST_METHOD_ERROR, data);
    }

    public String getMessage(String result, Object[] params) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("classpath:i18n/message");

        String message = "";
        try {
            message = messageSource.getMessage(result, params, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error("parse message error! ", e);
        }
        return message;
    }
}
