package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.enumeration.EmailKindEnum;
import cn.xilikeli.skyblog.common.exception.http.ParameterException;
import cn.xilikeli.skyblog.model.Email;
import cn.xilikeli.skyblog.service.MailService;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 邮件服务业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 11:55
 * @since JDK1.8
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    /**
     * 邮件服务执行对象
     */
    private final JavaMailSender mailSender;

    /**
     * FreeMarker 模板配置对象
     */
    private final FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 发件人邮箱
     */
    @Value("${spring.mail.username}")
    private String addresserEmail;

    /**
     * 发件人昵称
     */
    @Value("${spring.mail.nickname}")
    private String addresserNickname;

    public MailServiceImpl(JavaMailSender mailSender, FreeMarkerConfigurer freeMarkerConfigurer) {
        this.mailSender = mailSender;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    @Override
    public void sendInformEmail(Email email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // 设置邮件基本信息: 发件人、收件人、邮件主题
            helper.setFrom(addresserNickname + '<' + addresserEmail + '>');
            helper.setTo(email.getRecipientEmail());
            helper.setSubject(email.getSubject());

            // 设置显示在模板中的邮件内容
            // 模板文件 template.html 处于 resources 目录下的 static/template 目录中
            Map<String, Object> model = new HashMap<>(16);
            model.put("content", email.getContent());

            // 邮件模板
            Template template;

            switch (EmailKindEnum.toType(email.getEmailKind())) {
                case CUSTOMER_COMMENT:
                    // 设置游客的评论所在的文章链接
                    if (!CollectionUtils.isEmpty(email.getCustomParameters())) {
                        String articleUrl = (String) email.getCustomParameters().get("articleUrl");
                        if (StringUtils.isNotBlank(articleUrl)) {
                            model.put("articleUrl", articleUrl);
                        } else {
                            model.put("articleUrl", "https://www.xilikeli.cn/article");
                        }
                    }
                    // 设置邮件的模板
                    template = freeMarkerConfigurer.getConfiguration().getTemplate("commentInformTemplate.ftl");
                    break;
                case CUSTOMER_LEAVE_MESSAGE:
                    // 设置邮件的模板
                    template = freeMarkerConfigurer.getConfiguration().getTemplate("leaveMessageInformTemplate.ftl");
                    break;
                default:
                    throw new ParameterException(CodeMessageConstant.NONEXISTENT_EMAIL_KIND);
            }

            // 将显示在模板中的邮件内容整合到模板文件中
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            // 设置邮件文本内容,设置为 html 形式
            helper.setText(text, true);

            // 发送邮件
            mailSender.send(message);
        } catch (Exception e) {
            log.error("邮件服务发送邮件时发生异常: ", e);
        }
    }

}