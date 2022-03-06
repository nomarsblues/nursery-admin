package com.aperture.nursery.admin.infra.repo;

import com.aperture.nursery.admin.common.json.JsonUtil;
import com.aperture.nursery.admin.domain.enroll.Enroll;
import com.aperture.nursery.admin.domain.enroll.repo.EnrollRepo;
import com.aperture.nursery.admin.infra.repo.jpa.EnrollRepository;
import com.aperture.nursery.admin.infra.repo.jpa.entity.EnrollEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EnrollRepoImpl implements EnrollRepo {
    @Autowired
    private EnrollRepository repository;

    @Override
    public boolean save(Enroll domain) {
        EnrollEntity entity = RepositoryExecutor.save(repository::save, build(domain));
        domain.setId(entity.getId());
        return true;
    }

    @Override
    public List<Enroll> query(Query query) {
        Specification<EnrollEntity> specification = (root, q, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(query.getIds())) {
                predicates.add(root.get("id").in(query.getIds()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return RepositoryExecutor.query(repository::findAll, specification).stream().map(this::build).collect(Collectors.toList());
    }

    @Override
    public Enroll findById(Long id) {
        return build(RepositoryExecutor.findById(repository::findById, id));
    }

    private EnrollEntity build(Enroll domain) {
        EnrollEntity entity = new EnrollEntity();
        entity.setId(domain.getId());
        entity.setAnnoId(domain.getAnnoId());
        entity.setUserId(domain.getUserId());
        entity.setLocation(domain.getDetail().getLocation());
        entity.setStduentId(domain.getDetail().getStudent().getId());
        entity.setStudentName(domain.getDetail().getStudent().getName());
        entity.setParentInfo(JsonUtil.toJson(domain.getDetail().getParents()));
        return entity;
    }

    private Enroll build(EnrollEntity entity) {
        Enroll domain = new Enroll();
        domain.setId(entity.getId());
        domain.setUserId(entity.getUserId());
        domain.setAnnoId(entity.getAnnoId());
        Enroll.EnrollDetail detail = Enroll.EnrollDetail.builder()
                .location(entity.getLocation())
                .student(Enroll.EnrollDetail.Student.builder()
                        .id(entity.getStduentId())
                        .name(entity.getStudentName())
                        .build())
                .parents(JsonUtil.fromJson(entity.getParentInfo(), new TypeReference<List<Enroll.EnrollDetail.Parent>>() {
                }))
                .build();
        domain.setDetail(detail);
        return domain;
    }
}
