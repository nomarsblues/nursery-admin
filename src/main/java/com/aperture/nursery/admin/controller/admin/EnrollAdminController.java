package com.aperture.nursery.admin.controller.admin;

import com.aperture.nursery.admin.application.EnrollApp;
import com.aperture.nursery.admin.controller.HttpResponse;
import com.aperture.nursery.admin.domain.enroll.Enroll;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/admin/enroll/")
@Validated
public class EnrollAdminController {
    @Autowired
    private EnrollApp app;

    @GetMapping("list")
    public HttpResponse<List<Enroll>> list() {
        return HttpResponse.success(app.listAll());
    }

    @PostMapping("create")
    public HttpResponse<Void> create(@Validated @RequestBody Enroll enroll) {
        app.create(enroll);
        return HttpResponse.success();
    }

    @PostMapping("update")
    public HttpResponse<Void> update(@Validated @RequestBody Enroll enroll) {
        app.update(enroll);
        return HttpResponse.success();
    }

    @GetMapping("download")
    public void download(@RequestParam Long annoId, HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = app.generateWorkbookByAnnoId(annoId);
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=student.xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }
}
