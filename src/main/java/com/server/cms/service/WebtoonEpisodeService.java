package com.server.cms.service;

import com.server.cms.data.request.wevtoon.QTqEpisodeData;
import com.server.cms.data.request.wevtoon.QTqEpisodePostData;
import com.server.cms.data.response.webtoon.SEpisode;
import com.server.cms.domain.Contract;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.exception.FileData;
import com.server.cms.framework.common.Base64Encoding;
import com.server.cms.framework.common.FileUtil;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.common.Unique;
import com.server.cms.framework.date.LocalDateUtil;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.framework.file.type.ImageFileEnum;
import com.server.cms.repository.TqWebtoonEpisodeRepository;
import com.server.cms.repository.TqWebtoonRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static com.server.cms.config.security.SecurityUtils.currentUserInd;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class WebtoonEpisodeService {

    private final TqWebtoonEpisodeRepository tqWebtoonEpisodeRepository;
    private final TqWebtoonRepository tqWebtoonRepository;

    @Value("${directory.content}")
    private String IMG_PATH;

    public ResponsePageDTO findByWebtoonEpisodes(QTqEpisodeData.Search param) {
        Long userInd = currentUserInd();
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        requiredBookCode(param.getBookCode());

        return tqWebtoonEpisodeRepository.findByWebtoonEpisodes(userInd, param);
    }

    public ResponsePageDTO findByEpisodes(QTqEpisodeData.Search param) {
        Long userInd = currentUserInd();
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        return tqWebtoonEpisodeRepository.findByEpisodes(userInd, param);
    }

    public SEpisode.Item cpEpisodeSave(QTqEpisodePostData.Save param, MultipartFile imageFile, MultipartFile manuscriptMultipartFile) {
        String bookCode = param.getBookCode();
        requiredBookCode(bookCode);

        TqWebtoon cpWebtoon = tqWebtoonRepository.findByCpWebtoon(bookCode);
        List<TqWebtoonEpisode> episodes = cpWebtoon.getEpisode();
        int size = episodes.size();
        String episodeOrder = Base64Encoding.encodingInteger(Long.parseLong(String.valueOf(size)));
        String bookNum = "E_W" + bookCode.substring(bookCode.length()-7) + episodeOrder;

        TqWebtoonEpisode episode = TqWebtoonEpisode.create(bookNum, param, cpWebtoon);
        TqWebtoonEpisode save = tqWebtoonEpisodeRepository.save(episode);

        // TODO Image Entity 필요
        String path = Paths.get(IMG_PATH, bookCode, String.format("%04d", size)).toString();
        FileData fileData = FileUtil.imageFileSave(imageFile, path, ImageFileEnum.WEBTOON_EPISODE);

        File manuscriptFile = new File(manuscriptMultipartFile.getOriginalFilename());
        String zipName = FileUtil.generateUniqueFileName(manuscriptFile);
        String s = LocalDateUtil.getConvertDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
        PAth

        FileUtil.unzip();

        return SEpisode.Item.create(save);
    }

    private void requiredBookCode(String bookCode) {
        if(isEmpty(bookCode)) {
            throw new ContentNotFoundException("도서코드를 정확하지 않습니다.");
        }
    }

}
