package pwa.projet.wintter.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pwa.projet.wintter.models.Email;

@Service
@AllArgsConstructor
@Slf4j
public class MailService
{
        private final JavaMailSender javaMailSender;
        private final MailBuilderService mailBuilderService;

        void sendMail(Email email)
        {
                MimeMessagePreparator messagePreparator = mimeMessage -> {
                        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                        messageHelper.setFrom("ranyg@outlook.com");
                        messageHelper.setTo(email.getRecipient());
                        messageHelper.setSubject(email.getSubject());
                        messageHelper.setText(email.getText());
                };

                try
                {
                        javaMailSender.send(messagePreparator);
                        log.info("Email sent");
                } catch (Exception e)
                {
                        log.error("Error when sending email ", e);
                }
        }
}
