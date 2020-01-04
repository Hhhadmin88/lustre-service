package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.exception.NotFoundException;
import com.evan.homemaking.common.model.entity.Recruitment;
import com.evan.homemaking.common.model.param.RecruitmentParam;
import com.evan.homemaking.common.utils.ParamTransformUtil;
import com.evan.homemaking.repository.RecruitmentRepository;
import com.evan.homemaking.service.RecruitmentService;
import com.evan.homemaking.service.base.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void updateRecruitment(@NonNull RecruitmentParam recruitmentParam) {
        Recruitment recruitment = ParamTransformUtil.copyProperties(recruitmentParam, Recruitment.class);
        update(recruitment);
    }

    @Override
    public void deleteRecruitment(@NonNull Integer recruitmentId) {
        removeById(recruitmentId);
    }

    @Override
    public List<Recruitment> findAllRecruitment() {
        return listAll();
    }

    @Override
    public Recruitment findOneRecruitment(@NonNull Integer recruitmentId) {
        if (!recruitmentRepository.findById(recruitmentId).isPresent()) {
            log.error("The recruitment is not exist of current id");
            throw new NotFoundException("当前Id对应的recruitment不存在");
        }
        return recruitmentRepository.findById(recruitmentId).get();
    }
}
