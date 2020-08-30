package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 站点信息 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 20:58
 * @since JDK1.8
 */
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Integer> {

}