package cn.codeyang.pter.module.client.service;

import cn.codeyang.pter.module.client.entity.Client;

import java.util.List;

/**
 * @author yangzy
 */
public interface ClientService {

    List<Client> selectAll();
}
