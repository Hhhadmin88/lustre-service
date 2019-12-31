package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.model.entity.Recruitment;
import com.evan.homemaking.common.model.param.RecruitmentParam;
import com.evan.homemaking.common.utils.ParamTransformUtil;
import com.evan.homemaking.repository.RecruitmentRepository;
import com.evan.homemaking.service.RecruitmentService;
import com.evan.homemaking.service.base.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * @ClassName RecruitmentServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/27 0:28
 */
@Service
@Slf4j
public class RecruitmentServiceImpl extends AbstractCrudService<Recruitment, Integer> implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository) {
        super(recruitmentRepository);
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public void createRecruitment(@NonNull RecruitmentParam recruitmentParam) {
        Recruitment recruitment = ParamTransformUtil.copyProperties(recruitmentParam, Recruitment.class);
        recruitmentRepository.save(recruitment);
    }
}
