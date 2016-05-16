package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.DaoAbstract;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

//TODO dodane 1.3 - cala klasa od zera przebudowana
@Aspect
@Component
public class BookDaoAdvisor {
	
	@Autowired
	private Sequence sequence;

	@SuppressWarnings("unchecked")
	@Before("@annotation(nullableId)")
    public void before(JoinPoint joinPoint, NullableId nullableId) throws BookNotNullIdException {
    	checkNotNullId(joinPoint.getArgs()[0]);
    	if (joinPoint.getThis() instanceof DaoAbstract){
	    	setNextId(joinPoint.getArgs()[0], (DaoAbstract<? extends IdAware>)joinPoint.getThis());
    	}
    }

    private void checkNotNullId(Object o){
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }
    private void setNextId(Object o, DaoAbstract<? extends IdAware> daoAbstract){
    	if (o instanceof IdAware){
    		((IdAware) o).setId(sequence.nextValue(daoAbstract.findAll()));
    	}
    }
}
