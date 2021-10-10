package com.Hanium.CarCamping.service.Notice;

import com.Hanium.CarCamping.Exception.NoSuchNoticeException;
import com.Hanium.CarCamping.Exception.NotAdminRegisterException;
import com.Hanium.CarCamping.domain.dto.Notice.CreateNoticeDto;
import com.Hanium.CarCamping.domain.entity.Notice;
import com.Hanium.CarCamping.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;


    @Transactional
    public void createNotice(CreateNoticeDto createNoticeDto,String name){
        if(name.equals("관리자")) {
            Notice save = noticeRepository.save(Notice.createNotice(createNoticeDto, name));
        }
        else{
            throw new NotAdminRegisterException("관리자가 아닙니다.");
        }
    }

    public List<Notice> getAllNotice() {
        return noticeRepository.findAll();
    }

    public Notice findById(Long id) {
        return noticeRepository.findById(id).orElseThrow(NoSuchNoticeException::new);
    }


}
