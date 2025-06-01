package me.zhengjie.modules.app.service;

import me.zhengjie.modules.app.service.dto.DownloadResult;
import me.zhengjie.modules.app.service.dto.SearchDataBody;

/**
 * @author eleven
 */
public interface IAppImageService {

    /**
     * 移动端申请下载
     * @param searchDataBody
     * @return
     */
    DownloadResult downloadImage(SearchDataBody searchDataBody);
}
