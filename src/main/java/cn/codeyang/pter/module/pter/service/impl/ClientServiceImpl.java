package cn.codeyang.pter.module.pter.service.impl;

import cn.codeyang.pter.module.pter.entity.Client;
import cn.codeyang.pter.module.pter.repository.ClientRepository;
import cn.codeyang.pter.module.pter.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public List<Client> selectAll() {
        return clientRepository.findAll();
    }
}
