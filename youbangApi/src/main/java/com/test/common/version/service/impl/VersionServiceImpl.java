package com.test.common.version.service.impl;

import com.test.common.version.mapper.VersionMapper;
import com.test.common.version.model.Version;
import com.test.common.version.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    VersionMapper versionMapper;

    @Override
    public Version select() {

        Version version = null;

        List<Version> list = new ArrayList<>();

        list = versionMapper.selectAll();

        if(list.size()>0){
             version =   list.get(0);
        }
        return version;
    }

}
