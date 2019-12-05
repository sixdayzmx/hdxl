package com.hd.biz.mapper;

import com.hd.biz.pojo.Biz;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BizMapper {

    public String findAddr(@Param("code")String code, @Param("type") String type,@Param("addrType") String addrType);
    public List<Biz> findAllBiz();
    public int findMaxId();
    public Integer addBiz(Biz biz);
    public Integer deleteById(int id);

}
