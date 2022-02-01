package spg.pos.predjeskovic.domain;


import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class CustomAbstractPersistable extends AbstractPersistable<Long> {
}
