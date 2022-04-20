package com.app.eduService.service;

import com.app.eduService.entity.EduChapter;
import com.app.eduService.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-17
 */
public interface EduChapterService extends IService<EduChapter> {

    public List<ChapterVo> getChapterVideoByCourseId(String courseId);

    public boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
