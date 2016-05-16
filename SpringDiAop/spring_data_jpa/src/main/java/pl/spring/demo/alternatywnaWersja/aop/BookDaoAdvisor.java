/*package pl.spring.demo.alternatywnaWersja.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

//TODO dodane 1.3 - cala klasa od zera przebudowana
@Aspect
public class BookDaoAdvisor {
	
	@Autowired
	private Sequence sequence;
	private CollectionAware<?> objects;

	@Before("@annotation(nullableId)")
    public void before(JoinPoint joinPoint, NullableId nullableId) throws BookNotNullIdException {
    	checkNotNullId(joinPoint.getArgs()[0]);
    	if (joinPoint.getThis() instanceof CollectionAware){
	    	objects = (CollectionAware<?>)joinPoint.getThis();
	    	setNextId(joinPoint.getArgs()[0]);
    	}
    }

    private void checkNotNullId(Object o){
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }
    private void setNextId(Object o){
    	if (o instanceof IdAware){
    		((IdAware) o).setId(sequence.nextValue(objects.getCollection()));
    	}
    }
}*/
