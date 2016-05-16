package pl.spring.demo.common;

import java.util.Collection;

import org.springframework.stereotype.Component;

import pl.spring.demo.to.IdAware;
@Component//TODO dodane 1.2
public class Sequence {

    public long nextValue(Collection<? extends IdAware> existingIds) {
        long result = 1;
        for (IdAware nextExistingId : existingIds) {
            if (Long.compare(nextExistingId.getId(), result) > 0) {
                result = nextExistingId.getId();
            }
        }
        return ++result;//TODO dodane bo bylo bledne
    }
}
