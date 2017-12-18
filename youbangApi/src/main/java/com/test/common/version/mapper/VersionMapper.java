package com.test.common.version.mapper;

import com.test.common.version.model.Version;
import com.test.utils.BaseMapper;
import java.util.List;

public interface VersionMapper extends BaseMapper {
    int insert(Version record);

    List<Version> selectAll();
}