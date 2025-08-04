package org.bsdevelopment.simplepets.nms;

import org.bsdevelopment.simplepets.api.utils.HelperUtilities;

public interface VersionHandler {
    VersionHandler INSTANCE = HelperUtilities.getVersionedClass("NMSVersionHandler", VersionHandler.class);
}
