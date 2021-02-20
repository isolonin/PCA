package i.solonin.pca.services;

import lombok.extern.slf4j.Slf4j;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class AsteriskImpl implements Asterisk {
    private ManagerConnection managerConnection;

    @Value("${asterisk.sip.type}")
    private String sipType;
    @Value("${asterisk.hostname}")
    private String hostname;
    @Value("${asterisk.login}")
    private String login;
    @Value("${asterisk.secret}")
    private String password;
    @Value("${asterisk.outgoing}")
    private String outgoingChannel;
    @Value("${asterisk.outgoing.timeout:60000}")
    private Long timeout;

    @PostConstruct
    public void init() {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(hostname, login, password);
        managerConnection = factory.createManagerConnection();
        try {
            managerConnection.login();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public boolean origin(String from, String to) {
        try {
            String channel = String.format("%s/%s@%s", sipType, to, outgoingChannel);
            OriginateAction originateAction = new OriginateAction();
            originateAction.setChannel(channel);
            originateAction.setCallerId(from);
            originateAction.setApplication("Hangup");

            ManagerResponse originateResponse = managerConnection.sendAction(originateAction, timeout);
            if (!originateResponse.getResponse().equalsIgnoreCase("Success")) {
                log.error(originateResponse.toString());
                return false;
            } else {
                log.info(originateResponse.toString());
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
