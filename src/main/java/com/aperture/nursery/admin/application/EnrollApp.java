package com.aperture.nursery.admin.application;

import com.aperture.nursery.admin.domain.announce.Announce;
import com.aperture.nursery.admin.domain.announce.repo.AnnounceRepo;
import com.aperture.nursery.admin.domain.enroll.Enroll;
import com.aperture.nursery.admin.domain.enroll.EnrollCreator;
import com.aperture.nursery.admin.domain.enroll.EnrollUpdater;
import com.aperture.nursery.admin.domain.enroll.repo.EnrollRepo;
import com.aperture.nursery.admin.domain.user.UserService;
import com.aperture.nursery.admin.meta.exception.ServiceException;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class EnrollApp {
    @Autowired
    private EnrollRepo enrollRepo;
    @Autowired
    private EnrollCreator enrollCreator;
    @Autowired
    private EnrollUpdater enrollUpdater;
    @Autowired
    private UserService userService;
    @Autowired
    private AnnounceRepo announceRepo;

    @Transactional
    public void create(Enroll cmd) {
        Enroll enroll = enrollCreator.build(cmd);
        enroll.check();
        enrollRepo.save(enroll);
    }

    @Transactional
    public void update(Enroll cmd) {
        Enroll ori = enrollRepo.query(EnrollRepo.Query.builder()
                        .ids(Lists.newArrayList(cmd.getId())).build())
                .stream().findAny().orElseThrow(() -> new ServiceException("未找到报名信息，id为" + cmd.getId()));
        Enroll enroll = enrollUpdater.build(cmd, ori);
        enroll.check();
        enrollRepo.save(enroll);
    }

    public List<Enroll> listAll() {
        return enrollRepo.query(EnrollRepo.Query.builder().build());
    }

    public List<Enroll> listAllForClient() {
        Long userId = userService.getUserId();
        if (userId == null) {
            return Lists.newArrayList();
        }
        return enrollRepo.query(EnrollRepo.Query.builder().userId(userId).build());
    }

    public Enroll findById(Long id) {
        return enrollRepo.query(EnrollRepo.Query.builder()
                        .ids(Lists.newArrayList(id)).build())
                .stream().findAny().orElseThrow(() -> new ServiceException("未找到报名信息，id为" + id));
    }

    public HSSFWorkbook generateWorkbookByAnnoId(Long annoId) {
        //表头数据
        String[] header = {"宝宝姓名", "宝宝身份证", "家庭地址", "是否贫困", "家长信息"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("sheet 1");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        //模拟遍历结果集，把内容加入表格
        //模拟遍历第一个学生
        Announce announce = announceRepo.findById(annoId);
        if (announce == null) {
            return workbook;
        }
        List<Enroll> enrolls = enrollRepo.query(EnrollRepo.Query.builder().annoId(annoId).build());
        for (int i = 0; i < enrolls.size(); i++) {
            Enroll enroll = enrolls.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < enroll.getTableContent().size(); j++) {
                HSSFCell cell = row.createCell(j);
                HSSFRichTextString text = new HSSFRichTextString(enroll.getTableContent().get(j));
                cell.setCellValue(text);
            }
        }
        return workbook;
    }
}
