package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.PizzeriaMessage;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.complexModels.Message;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.NotificationService;
import hr.fer.opp.pizzeriasystem.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailSendController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SecurityService securityService;

    //to negdi da bude, ugl ako treba poslati mail za dostavu pizze ili tako nesto
    @PostMapping("/employee/sendMail")
    public ResponseEntity<Message> employeeSendEmail(@RequestBody PizzeriaMessage pm) throws Exception{
        if (pm == null) {
            throw new IllegalArgumentException("Pizzeria message can not be null!");
        }

        User user = securityService.loggedIn(pm.getToken());

        if (user==null  || user.getRole()!= Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        notificationService.sendNotification(pm);

        return new ResponseEntity<>(new Message("Sent."),HttpStatus.OK);
    }

    @PostMapping("/admin/sendMail")
    public ResponseEntity<Message> adminSendEmail(@RequestBody PizzeriaMessage pm) throws Exception{
        if (pm == null) {
            throw new IllegalArgumentException("Pizzeria message can not be null!");
        }

        User user = securityService.loggedIn(pm.getToken());

        if (user==null  || user.getRole()!= Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        notificationService.sendNotification(pm);

        return new ResponseEntity<>(new Message("Sent."),HttpStatus.OK);
    }
}
