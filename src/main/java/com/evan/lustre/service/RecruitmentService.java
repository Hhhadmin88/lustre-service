package com.evan.lustre.service;

import com.evan.lustre.common.model.dto.RecruitmentDTO;
import com.evan.lustre.common.model.entity.Recruitment;
import com.evan.lustre.common.model.param.RecruitmentParam;
import com.evan.lustre.service.base.CrudService;
import org.springframework.lang.NonNull;

import java.util.List;

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
    void create(@NonNull RecruitmentParam recruitmentParam);

    /**
     * Get One Recruitment.
     *
     * @param recruitmentId recruitment id.
     * @return One recruitment.
     */
    RecruitmentDTO getOne(@NonNull Integer recruitmentId);

    /**
     * Get All Recruitment.
     *
     * @return Recruitment list.
     */
    List<RecruitmentDTO> getAll();

    /**
     * Update a recruitment.
     *
     * @param recruitmentId    recruitment id.
     * @param recruitmentParam recruitmentParam.
     * @return recruitment dto.
     */
    RecruitmentDTO updateOne(@NonNull Integer recruitmentId, @NonNull RecruitmentParam recruitmentParam);

    /**
     * Delete a recruitment by id.
     *
     * @param recruitmentId recruitment id.
     */
    void deleteOne(@NonNull Integer recruitmentId);
}
