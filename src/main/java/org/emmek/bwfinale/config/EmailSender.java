package org.emmek.bwfinale.config;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.payload.EmailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailSender {
    private final String apikey;
    private final String sender;

    public EmailSender(@Value("${sendgrid.apikey}") String apikey,
                       @Value("${sendgrid.sender}") String sender) {
        this.apikey = apikey;
        this.sender = sender;
    }

    public void sendEmail(Cliente cliente, EmailDTO body) throws IOException {
        Email from = new Email(sender);
        String subject = "\n" + body.oggetto();
        Email to = new Email(cliente.getEmailContatto(), cliente.getRagioneSociale());
        Content content = new Content("text/plain", body.messaggio());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }
}