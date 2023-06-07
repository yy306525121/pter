package cn.codeyang.pter.module.client.service.impl;

import cn.codeyang.pter.module.client.entity.Client;
import cn.codeyang.pter.module.client.mapper.ClientMapper;
import cn.codeyang.pter.module.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;

    @Override
    public List<Client> selectAll() {
        return clientMapper.selectAll();
    }
}
