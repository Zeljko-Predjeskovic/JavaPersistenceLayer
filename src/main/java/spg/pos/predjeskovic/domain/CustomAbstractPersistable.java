package spg.pos.predjeskovic.domain;


import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class CustomAbstractPersistable extends AbstractPersistable<Long> {

    public void setIdPublic(Long id) {
        super.setId(id);
    }
}
