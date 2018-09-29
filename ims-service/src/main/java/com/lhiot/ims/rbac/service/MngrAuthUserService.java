package com.lhiot.ims.rbac.service;

import java.util.Arrays;
import com.lhiot.ims.rbac.domain.MngrAuthUser;
import com.lhiot.ims.rbac.mapper.MngrAuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lhiot.ims.rbac.common.PagerResultObject;

/**
* Description:用户服务类
* @author yijun
* @date 2018/09/29
*/
@Service
@Transactional
public class MngrAuthUserService {

    private final MngrAuthUserMapper mngrAuthUserMapper;

    @Autowired
    public MngrAuthUserService(MngrAuthUserMapper mngrAuthUserMapper) {
        this.mngrAuthUserMapper = mngrAuthUserMapper;
    }

    /** 
    * Description:新增用户
    *  
    * @param mngrAuthUser
    * @return
    * @author yijun
    * @date 2018/09/29 11:42:57
    */  
    public int create(MngrAuthUser mngrAuthUser){
        return this.mngrAuthUserMapper.create(mngrAuthUser);
    }

    /** 
    * Description:根据id修改用户
    *  
    * @param mngrAuthUser
    * @return
    * @author yijun
    * @date 2018/09/29 11:42:57
    */ 
    public int updateById(MngrAuthUser mngrAuthUser){
        return this.mngrAuthUserMapper.updateById(mngrAuthUser);
    }

    /** 
    * Description:根据ids删除用户
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/09/29 11:42:57
    */ 
    public int deleteByIds(String ids){
        return this.mngrAuthUserMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找用户
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/09/29 11:42:57
    */ 
    public MngrAuthUser selectById(Long id){
        return this.mngrAuthUserMapper.selectById(id);
    }

    /** 
    * Description: 查询用户总记录数
    *  
    * @param mngrAuthUser
    * @return
    * @author yijun
    * @date 2018/09/29 11:42:57
    */  
    public long count(MngrAuthUser mngrAuthUser){
        return this.mngrAuthUserMapper.pageMngrAuthUserCounts(mngrAuthUser);
    }
    
    /** 
    * Description: 查询用户分页列表
    *
    * @param mngrAuthUser
    * @return
    * @author yijun
    * @date 2018/09/29 11:42:57
    */  
    public PagerResultObject<MngrAuthUser> pageList(MngrAuthUser mngrAuthUser) {
       long total = 0;
       if (mngrAuthUser.getRows() != null && mngrAuthUser.getRows() > 0) {
           total = this.count(mngrAuthUser);
       }
       return PagerResultObject.of(mngrAuthUser, total,
              this.mngrAuthUserMapper.pageMngrAuthUsers(mngrAuthUser));
    }


    public MngrAuthUser selectByAccount(String account) {
        return this.mngrAuthUserMapper.selectByAccount(account);
    }

}

