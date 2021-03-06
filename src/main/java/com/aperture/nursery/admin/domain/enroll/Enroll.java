package com.aperture.nursery.admin.domain.enroll;

import com.aperture.nursery.admin.common.bean.BeanContextUtil;
import com.aperture.nursery.admin.meta.Domain;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Enroll implements Domain {
    private Long id;
    @NotNull(message = "参数错误")
    private Long annoId;
    private Long userId;
    @Valid
    @NotNull(message = "信息缺失")
    private EnrollDetail detail;
    private String wxId;

    public void check() {
        EnrollChecker checker = BeanContextUtil.getBean(EnrollChecker.class);
        checker.check(this);
    }

    public List<String> getTableContent() {
        List<String> result = Lists.newArrayList();
        result.add(getDetail().getStudent().getName());
        result.add(getDetail().getStudent().getId());
        result.add(getDetail().getLocation());
        result.add(getDetail().getPoor() == 1 ? "是" : "否");
        result.add( getDetail().getParents().stream()
                .map(p -> String.format("关系: %s, 姓名: %s, 手机: %s,身份证: %s",
                        p.getRelation().getName(),
                        p.getName(),
                        p.getPhone(),
                        p.getId()))
                .collect(Collectors.joining("; "))
        );
        return result;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnrollDetail {
        @Valid
        @NotNull(message = "学生信息缺失")
        private Student student;
        @Valid
        @NotEmpty(message = "家长信息缺失")
        private List<Parent> parents;
        @NotEmpty(message = "地址不能为空")
        private String location;
        @NotNull(message = "未选择家庭情况")
        private Integer poor;
        @NotNull(message = "班级未选择")
        private Long studentClass;

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
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
        @NoArgsConstructor
        @AllArgsConstructor
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
