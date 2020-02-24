package org.esa.snap.core.dataio.geocoding;

import org.esa.snap.core.dataio.dimap.spi.DimapPersistable;
import org.esa.snap.core.dataio.dimap.spi.DimapPersistableSpi;
import org.jdom.Element;

public class ComponentGeoCodingPersitableSpi implements DimapPersistableSpi {
    @Override
    public boolean canDecode(Element element) {
        return element != null && element.getChild(ComponentGeoCodingPersitable.TAG_COMPONENT_GEO_CODING) != null;
    }

    @Override
    public boolean canPersist(Object object) {
        return object instanceof ComponentGeoCoding;
    }

    @Override
    public DimapPersistable createPersistable() {
        return new ComponentGeoCodingPersitable();
    }
}
