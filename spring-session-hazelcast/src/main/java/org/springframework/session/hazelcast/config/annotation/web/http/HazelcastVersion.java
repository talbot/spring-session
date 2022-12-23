package org.springframework.session.hazelcast.config.annotation.web.http;

import org.springframework.util.ClassUtils;

public enum HazelcastVersion {

    HAZELCAST_5("com.hazelcast.nio.serialization.compact.CompactSerializer"),
    HAZELCAST_4("com.hazelcast.map.IMap"),
    HAZELCAST_3("com.hazelcast.core.IMap"),
    ;

    private final String hazelcastClassPathDeterminant;

    HazelcastVersion(String hazelcastClassPathDeterminant) {
        this.hazelcastClassPathDeterminant = hazelcastClassPathDeterminant;
    }

    static HazelcastVersion determineHazelcastVersion(ClassLoader classLoader) {
        for (HazelcastVersion version : HazelcastVersion.values()) {
            if (ClassUtils.isPresent(version.hazelcastClassPathDeterminant, classLoader)) {
                return version;
            }
        }
        throw new IllegalStateException("Hazelcast not found in class path");
    }
}
