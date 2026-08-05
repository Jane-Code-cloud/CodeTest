package com.cyy.storage.service.impl;

import com.cyy.storage.mapper.StorageTblMapper;
import com.cyy.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageTblMapper storageTblMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        storageTblMapper.deduct(commodityCode, count);
        if (Objects.equals(5, count)) {
            throw new RuntimeException("内存不足！");
        }
    }
}
