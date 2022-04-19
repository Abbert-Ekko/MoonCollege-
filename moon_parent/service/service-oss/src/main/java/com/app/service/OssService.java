package com.app.service;

import com.app.utils.R;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    public String uploadAvatarFile(MultipartFile file);
}
