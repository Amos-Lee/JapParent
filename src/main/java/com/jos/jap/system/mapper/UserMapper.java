package com.jos.jap.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jos.jap.system.po.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
