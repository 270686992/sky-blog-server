package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 博客用户 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 22:28
 * @since JDK1.8
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * 通过博客用户 ID 查询对应的博客用户信息
     *
     * @param customerId 博客用户 ID
     * @return 返回查询的博客用户信息
     */
    Customer findOneById(Integer customerId);

    /**
     * 根据博客用户的用户名查询相应博客用户的信息
     *
     * @param username 博客用户的用户名
     * @return 返回查询的博客用户信息
     */
    Customer findByUsername(String username);

    /**
     * 根据指定用户名查询是否已经使用该用户名
     *
     * @param username 用户名
     * @return 已经使用该用户名返回 1,否则返回 0
     */
    int countByUsername(String username);

    /**
     * 根据指定的用户名和用户 ID 查询是否已经有其他用户使用该用户名
     *
     * @param username   用户名
     * @param customerId 用户 ID
     * @return 已经使用该用户名返回 1,否则返回 0
     */
    int countByUsernameAndIdIsNot(String username, Integer customerId);

    /**
     * 根据指定邮箱查询是否已经使用该邮箱
     *
     * @param email 邮箱
     * @return 已经使用该邮箱返回 1,否则返回 0
     */
    int countByEmail(String email);

    /**
     * 根据指定的邮箱和用户 ID 查询是否已经有其他用户使用该邮箱
     *
     * @param email      邮箱
     * @param customerId 用户 ID
     * @return 已经使用该邮箱返回 1,否则返回 0
     */
    int countByEmailAndIdIsNot(String email, Integer customerId);

    /**
     * 根据指定昵称查询是否已经使用该昵称
     *
     * @param nickname 昵称
     * @return 已经使用该昵称返回 1,否则返回 0
     */
    int countByNickname(String nickname);

    /**
     * 根据指定的昵称和用户 ID 查询是否已经有其他用户使用该昵称
     *
     * @param nickname   昵称
     * @param customerId 用户 ID
     * @return 已经使用该昵称返回 1,否则返回 0
     */
    int countByNicknameAndIdIsNot(String nickname, Integer customerId);

}