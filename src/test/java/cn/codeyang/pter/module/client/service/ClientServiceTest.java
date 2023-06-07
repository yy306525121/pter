package cn.codeyang.pter.module.client.service;

import cn.codeyang.pter.module.client.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author yangzy
 */
@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    @Test
    public void selectAll() {
        List<Client> clients = clientService.selectAll();
        System.out.println(clients);
    }
}