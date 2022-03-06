package com.tencent.wxcloudrun.domain.enroll;

import com.tencent.wxcloudrun.common.bean.BeanContextUtil;
import com.tencent.wxcloudrun.meta.Domain;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class Enroll implements Domain {
    private Long id;
    @NotNull
    private Long annoId;
    private Long userId;
    @Valid
    @NotNull(message = "信息缺失")
    private EnrollDetail detail;

    public void check() {
        EnrollChecker checker = BeanContextUtil.getBean(EnrollChecker.class);
        checker.check(this);
    }

    @Getter
    @Setter
    @Builder
    public static class EnrollDetail {
        @Valid
        @NotNull(message = "学生信息缺失")
        private Student student;
        @Valid
        @NotEmpty(message = "家长信息缺失")
        private List<Parent> parents;
        @NotEmpty(message = "地址不能为空")
        private String location;

        @Getter
        @Setter
        @Builder
        public static class Parent {
            @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
            private String id;
            @NotBlank(message = "手机号码不能为空")
            @Size(min = 11, max = 11, message = "手机号码长度不正确")
            @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
            private String phone;
            @NotEmpty(message = "名字不能为空")
            private String name;
            @NotNull(message = "关系不能为空")
            private Relation relation;
        }

        @Getter
        @Setter
        @Builder
        public static class Student {
            @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
            private String id;
            @NotEmpty(message = "名字不能为空")
            private String name;
        }
    }

    public enum Relation {
        father("父亲"), mother("母亲"), grandfather("爷爷/外公"), grandmother("奶奶/外婆"), other("其他");

        @Getter
        private String name;

        Relation(String name) {
            this.name = name;
        }
    }
}
