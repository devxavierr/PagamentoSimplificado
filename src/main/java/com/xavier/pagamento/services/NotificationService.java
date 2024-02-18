package com.xavier.pagamento.services;

import com.xavier.pagamento.domain.user.User;
import com.xavier.pagamento.dtos.NotificationDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@Log4j2
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);
        {

            if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
                log.debug("erro ao enviar notificação");
                throw new Exception("Serviço de notificação fora do ar");
            }
        }
    }
}
