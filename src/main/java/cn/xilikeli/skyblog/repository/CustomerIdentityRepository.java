package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.CustomerIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 博客用户授权信息 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 19:08
 * @since JDK1.8
 */
public interface CustomerIdentityRepository extends JpaRepository<CustomerIdentity, Integer> {

    /**
     * 根据用户 ID、标识、登录类型查询相应的用户认证信息
     *
     * @param customerId     用户 ID
     * @param identifier     标识(手机号,邮箱,用户名或第三方应用的唯一标识)
     * @param identifierType 登录类型(手机号,邮箱,用户名或第三方应用名称(微信,微博等)
     * @return 返回查询的用户认证信息
     */
    CustomerIdentity findByCustomerIdAndIdentifierAndIdentityType(Integer customerId, String identifier, String identifierType);

}