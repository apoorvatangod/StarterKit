package pl.spring.demo.to;

import java.util.Collection;

public interface CollectionAware<T extends IdAware>{//TODO pytanie dlaczego tu jest T a na dole '?'

    public Collection<? extends IdAware> getCollection();
}
