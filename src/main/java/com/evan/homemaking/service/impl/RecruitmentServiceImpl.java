package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.exception.NotFoundException;
import com.evan.homemaking.common.model.dto.RecruitmentDTO;
import com.evan.homemaking.common.model.entity.Recruitment;
import com.evan.homemaking.common.model.param.RecruitmentParam;
import com.evan.homemaking.repository.RecruitmentRepository;
import com.evan.homemaking.service.RecruitmentService;
import com.evan.homemaking.service.base.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void create(@NonNull RecruitmentParam recruitmentParam) {
        recruitmentRepository.save(recruitmentParam.convertTo());
    }

    @Override
    public RecruitmentDTO updateOne(@NonNull Integer recruitmentId, @NonNull RecruitmentParam recruitmentParam) {
        Optional<Recruitment> recruitmentOptional = fetchById(recruitmentId);
        return (RecruitmentDTO) recruitmentOptional.map(recruitment -> {
            recruitmentParam.update(recruitment);
            return new RecruitmentDTO().convertFrom(recruitment);
        }).orElseThrow(() -> new NotFoundException("当前recruitmentId:" + recruitmentId + "对应的招聘信息不存在"));
    }


    @Override
    public void deleteOne(@NonNull Integer recruitmentId) {
        removeById(recruitmentId);
    }

    @Override
    public List<RecruitmentDTO> getAll() {
        List<Recruitment> recruitmentList = listAll();
        return recruitmentList.stream().map(recruitment -> (RecruitmentDTO)new RecruitmentDTO().convertFrom(recruitment)).collect(Collectors.toList());
    }

    @Override
    public RecruitmentDTO getOne(@NonNull Integer recruitmentId) {
        Optional<Recruitment> recruitmentOptional = fetchById(recruitmentId);
        return (RecruitmentDTO) recruitmentOptional.map(recruitment ->
                new RecruitmentDTO().convertFrom(recruitment))
                .orElseThrow(() -> new NotFoundException("当前recruitmentId:" + recruitmentId + "对应的招聘信息不存在"));
    }
}
