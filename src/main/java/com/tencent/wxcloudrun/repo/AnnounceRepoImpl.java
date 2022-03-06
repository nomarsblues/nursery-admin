package com.tencent.wxcloudrun.repo;

import com.google.common.collect.Lists;
import com.tencent.wxcloudrun.domain.announce.Announce;
import com.tencent.wxcloudrun.domain.announce.repo.AnnounceRepo;
import com.tencent.wxcloudrun.repo.jpa.AnnounceRepository;
import com.tencent.wxcloudrun.repo.jpa.entity.AnnounceEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AnnounceRepoImpl implements AnnounceRepo {
    @Autowired
    private AnnounceRepository repository;

    @Override
    public boolean save(Announce domain) {
        AnnounceEntity entity = RepositoryExecutor.save(repository::save, build(domain));
        domain.setId(entity.getId());
        return true;
    }

    @Override
    public List<Announce> query(Query query) {
        Specification<AnnounceEntity> specification = (root, q, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(query.getIds())) {
                predicates.add(root.get("id").in(query.getIds()));
            }
            if (query.getStatus() != null) {
                predicates.add(cb.equal(root.get("status").as(Integer.class), query.getStatus()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return RepositoryExecutor.query(repository::findAll, specification).stream().map(this::build).collect(Collectors.toList());
    }

    @Override
    public Announce findById(Long id) {
        return build(RepositoryExecutor.findById(repository::findById, id));
    }

    private AnnounceEntity build(Announce domain) {
        AnnounceEntity entity = new AnnounceEntity();
        BeanUtils.copyProperties(domain, entity);
        return entity;
    }

    private Announce build(AnnounceEntity entity) {
        Announce domain = new Announce();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
