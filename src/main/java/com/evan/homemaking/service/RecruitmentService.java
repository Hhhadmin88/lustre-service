package com.evan.homemaking.service;

import com.evan.homemaking.common.model.entity.Recruitment;
import com.evan.homemaking.common.model.param.RecruitmentParam;
import com.evan.homemaking.service.base.CrudService;
import org.springframework.lang.NonNull;

/**
 * @ClassName RecruitmentService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/26 20:39
 */
public interface RecruitmentService extends CrudService<Recruitment, Integer> {

    /**
     * Create a recruitment.
     *
     * @param recruitmentParam recruitmentParam.
     */
    void createRecruitment(@NonNull RecruitmentParam recruitmentParam);
}
