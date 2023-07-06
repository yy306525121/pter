package cn.codeyang.pter.module.pter.service;

import cn.codeyang.pter.module.pter.entity.Client;

import java.util.List;

/**
 * @author yangzy
 */
public interface ClientService {

    List<Client> selectAll();
}
