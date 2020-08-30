package cn.xilikeli.skyblog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;

/**
 * <p>
 * IP 地址解析工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 14:53
 * @since JDK1.8
 */
@Slf4j
public class IpParseUtil {

    /**
     * 解析 ip 地址获取地理位置信息
     *
     * @param ip 解析的 ip 地址
     * @return 返回 ip 地址解析之后的地理位置
     */
    public static String getAddressByIp(String ip) {
        try {
            if (StringUtils.isBlank(ip)) {
                return "中国";
            }

            DbConfig config = new DbConfig();
            String dbFilePath = IpParseUtil.class.getResource("/ip2region.db").getPath();
            DbSearcher searcher = new DbSearcher(config, dbFilePath);
            DataBlock block = searcher.btreeSearch(ip);
            // 解析的地理位置格式: 国家|大区|省份|城市|运营商
            String region = block.getRegion();
            // 拆分后: [国家, 大区, 省份, 城市, 运营商]
            String[] split = region.split("\\|");
            // 处理之后返回的地理位置
            String address;
            // 城市可能会出现解析不到的情况,解析不到时会为 0,例如: 中国|0|xx省|0|移动,需要处理一下
            // 本地测试时也可能会出现 0|0|0|内网IP|内网IP 的情况,也需要处理一下
            String country = split[0];
            String province = split[2];
            String city = split[3];
            String operator = split[4];

            if ("内网IP".equals(city) || "内网IP".equals(operator)) {
                address = "中国";
                return address;
            }

            if (!"0".equals(country) && !"0".equals(province) && !"0".equals(city)) {
                address = country + province + city;
                return address;
            }

            if (!"0".equals(country) && !"0".equals(province)) {
                address = country + province;
                return address;
            }

            address = "中国";
            return address;
        } catch (Exception e) {
            log.error("IpParseUtil 解析 IP 地址时出现异常: ", e);
            return "中国";
        }
    }

}